package com.optum.icr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optum.icr.service.PublisherService;
import com.optum.icr.dto.IncidentMarkCompletedRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherServiceImpl implements PublisherService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic-name}")
    private String topicName;

    public KafkaPublisherServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(IncidentMarkCompletedRequest incidentMarkCompletedRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kafkaTemplate.send(topicName, objectMapper.writeValueAsString(incidentMarkCompletedRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
