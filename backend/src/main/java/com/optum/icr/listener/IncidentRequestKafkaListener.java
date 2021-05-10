package com.optum.icr.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optum.icr.dto.IncidentMarkCompletedRequest;
import com.optum.icr.model.IncidentRequest;
import com.optum.icr.repository.IncidentRequestRepository;
import com.optum.icr.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentRequestKafkaListener {
    private final IncidentRequestRepository incidentRequestRepository;

    private final MailService mailService;

    public IncidentRequestKafkaListener(IncidentRequestRepository incidentRequestRepository, MailService mailService) {
        this.incidentRequestRepository = incidentRequestRepository;
        this.mailService = mailService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-name}")
    public void listen(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            IncidentMarkCompletedRequest incidentMarkCompletedRequest = objectMapper.readValue(message, IncidentMarkCompletedRequest.class);
            System.out.println("Received Message in incidents: " + incidentMarkCompletedRequest);
            Optional<IncidentRequest> incidentRequestOptional = incidentRequestRepository.findById(incidentMarkCompletedRequest.getIncidentRequestId());
            incidentRequestOptional.ifPresent(incidentRequest -> mailService.sendIncidentCompletionEmail(incidentRequest, incidentMarkCompletedRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
