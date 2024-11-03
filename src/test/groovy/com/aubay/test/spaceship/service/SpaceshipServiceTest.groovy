package com.aubay.test.spaceship.service

import com.aubay.test.spaceship.api.response.SpaceshipResponse
import com.aubay.test.spaceship.exception.SpaceshipNotFoundException
import com.aubay.test.spaceship.factory.SpaceshipDTOFactory
import com.aubay.test.spaceship.model.dto.SpaceshipDTO
import com.aubay.test.spaceship.model.entity.Spaceship
import com.aubay.test.spaceship.repository.SpaceshipRepository
import com.aubay.test.spaceship.factory.SpaceshipFactory
import com.aubay.test.spaceship.factory.SpaceshipResponseFactory
import com.aubay.test.spaceship.service.impl.SpaceshipResponseService
import com.aubay.test.spaceship.service.impl.SpaceshipService
import com.aubay.test.spaceship.service.impl.integration.SpaceshipSenderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import spock.lang.Specification

class SpaceshipServiceTest extends Specification {

    private static final Pageable PAGEABLE = PageRequest.of(0, 10);

    private static final Spaceship SPACESHIP = SpaceshipFactory.buildDefault();

    private static final SpaceshipResponse SPACESHIP_RESPONSE = SpaceshipResponseFactory.buildDefault();

    private static final SpaceshipDTO SPACESHIP_DTO = SpaceshipDTOFactory.buildDefault();

    private static final Page<Spaceship> SPACESHIP_PAGEABLE = new PageImpl<>(Arrays.asList(SPACESHIP), PAGEABLE, 1);

    private static final Page<SpaceshipResponse> SPACESHIP_RESPONSE_PAGEABLE = new PageImpl<>(Arrays.asList(SPACESHIP_RESPONSE), PAGEABLE, 1);

    private static final Page<SpaceshipDTO> SPACESHIP_DTO_PAGEABLE = new PageImpl<>(Arrays.asList(SPACESHIP_DTO), PAGEABLE, 1);

    SpaceshipRepository spaceshipRepository = Mock()
    SpaceshipResponseService spaceshipResponseService = Mock()
    SpaceshipSenderService spaceshipSenderService = Mock()

    SpaceshipService service = new SpaceshipService(spaceshipRepository, spaceshipResponseService, spaceshipSenderService)

    def "findAll"() {

        when:
        def response = service.findAllSpaceships(PAGEABLE)

        then:
        1 * spaceshipRepository.findAll(PAGEABLE) >> SPACESHIP_PAGEABLE
        1 * spaceshipResponseService.buildResponse(SPACESHIP_DTO_PAGEABLE) >> SPACESHIP_RESPONSE_PAGEABLE

        and:
        response == SPACESHIP_RESPONSE_PAGEABLE
    }

    def "findByName"() {
        given:
        def name = SpaceshipFactory.NAME

        when:
        def response = service.searchSpaceshipByName(name, PAGEABLE)

        then:
        1 * spaceshipRepository.searchByNameContaining(name, PAGEABLE) >> SPACESHIP_PAGEABLE
        1 * spaceshipResponseService.buildResponse(SPACESHIP_DTO_PAGEABLE) >> SPACESHIP_RESPONSE_PAGEABLE

        and:
        response == SPACESHIP_RESPONSE_PAGEABLE
    }

    def "getById_present"() {
        given:
        def id = SpaceshipFactory.ID

        when:
        def response = service.getSpaceshipById(id)

        then:
        1 * spaceshipRepository.findById(id) >> Optional.of(SPACESHIP)
        1 * spaceshipResponseService.buildResponse(SPACESHIP_DTO) >> SPACESHIP_RESPONSE

        and:
        response == SPACESHIP_RESPONSE
    }

    def "getById_NOT_present"() {
        given:
        def id = SpaceshipFactory.ID

        when:
        service.getSpaceshipById(id)

        then:
        1 * spaceshipRepository.findById(id) >> Optional.empty()
        0 * spaceshipResponseService.buildResponse(_ as SpaceshipDTO)

        and:
        thrown SpaceshipNotFoundException
    }

    def "create"() {

        when:
        def response = service.createSpaceship(SPACESHIP_DTO)

        then:
        1 * spaceshipRepository.save(_ as Spaceship) >> SPACESHIP
        1 * spaceshipSenderService.sendCreate(SPACESHIP_DTO)
        1 * spaceshipResponseService.buildResponse(SPACESHIP_DTO) >> SPACESHIP_RESPONSE

        and:
        response == SPACESHIP_RESPONSE
    }

    def "update_present"() {
        given:
        def id = SpaceshipFactory.ID;

        when:
        def response = service.updateSpaceship(SPACESHIP_DTO)

        then:
        1 * spaceshipRepository.findById(id) >> Optional.of(SPACESHIP)
        1 * spaceshipRepository.save(SPACESHIP) >> SPACESHIP
        1 * spaceshipSenderService.sendUpdate(SPACESHIP_DTO)
        1 * spaceshipResponseService.buildResponse(SPACESHIP_DTO) >> SPACESHIP_RESPONSE

        and:
        response == SPACESHIP_RESPONSE
    }

    def "update_NOT_present"() {
        given:
        def id = SpaceshipFactory.ID;

        when:
        service.updateSpaceship(SPACESHIP_DTO)

        then:
        1 * spaceshipRepository.findById(id) >> Optional.empty()
        0 * spaceshipRepository.save(SPACESHIP) >> SPACESHIP
        0 * spaceshipSenderService.sendUpdate(SPACESHIP_DTO)
        0 * spaceshipResponseService.buildResponse(SPACESHIP_DTO) >> SPACESHIP_RESPONSE

        and:
        thrown SpaceshipNotFoundException
    }

    def "delete_present"() {
        given:
        def id = SpaceshipFactory.ID;

        when:
        service.deleteSpaceship(id)

        then:
        1 * spaceshipRepository.findById(id) >> Optional.of(SPACESHIP)
        1 * spaceshipRepository.deleteById(id)
        1 * spaceshipSenderService.sendDelete(SPACESHIP_DTO)
    }

    def "delete_NOT_present"() {
        given:
        def id = SpaceshipFactory.ID;

        when:
        service.deleteSpaceship(id)

        then:
        1 * spaceshipRepository.findById(id) >> Optional.empty()
        0 * spaceshipRepository.deleteById(id)
        0 * spaceshipSenderService.sendDelete(SPACESHIP_DTO)

        and:
        thrown SpaceshipNotFoundException
    }
}