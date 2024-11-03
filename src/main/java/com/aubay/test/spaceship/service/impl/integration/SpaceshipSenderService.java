package com.aubay.test.spaceship.service.impl.integration;

import com.aubay.test.spaceship.model.domain.SpaceshipAction;
import com.aubay.test.spaceship.model.dto.SpaceshipActionNotification;
import com.aubay.test.spaceship.model.dto.SpaceshipDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpaceshipSenderService {

    @Value("${spaceship.queue.out}")
    private String queueOut;

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SpaceshipSenderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendCreate(SpaceshipDTO dto) {
        this.send(dto, SpaceshipAction.CREATE);
    }

    public void sendUpdate(SpaceshipDTO dto) {
        this.send(dto, SpaceshipAction.UPDATE);
    }

    public void sendDelete(SpaceshipDTO dto) {
        this.send(dto, SpaceshipAction.DELETE);
    }

    private void send(SpaceshipDTO dto, SpaceshipAction action) {
        try {
            jmsTemplate.convertAndSend(queueOut, buildSpaceshipNotificationMessage(dto, action));
        } catch (JsonProcessingException e) {
            log.error("Unable to send Spaceship action notification", e);
        }
    }

    private String buildSpaceshipNotificationMessage(SpaceshipDTO dto, SpaceshipAction action) throws JsonProcessingException {
        SpaceshipActionNotification notification = new SpaceshipActionNotification(dto, action);
        return objectMapper.writeValueAsString(notification);
    }
}
