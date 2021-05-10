package com.optum.icr.service.impl;

import com.optum.icr.dto.IncidentMarkCompletedRequest;
import com.optum.icr.model.IncidentRequest;
import com.optum.icr.repository.IncidentRequestRepository;
import com.optum.icr.service.PublisherService;
import com.optum.icr.repository.SequenceGeneratorRepository;
import com.optum.icr.service.IncidentRequestService;
import com.optum.icr.service.MailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentRequestServiceImpl implements IncidentRequestService {

    private final IncidentRequestRepository repository;

    private final SequenceGeneratorRepository sequenceGeneratorRepository;

    private final PublisherService publisherService;

    private final MailService mailService;

    public IncidentRequestServiceImpl(IncidentRequestRepository repository,
                                      SequenceGeneratorRepository sequenceGeneratorRepository,
                                      PublisherService publisherService,
                                      MailService mailService) {
        this.repository = repository;
        this.sequenceGeneratorRepository = sequenceGeneratorRepository;
        this.publisherService = publisherService;
        this.mailService = mailService;
    }

    @Override
    public List<IncidentRequest> findAll() {
        return repository.findAll();
    }

    @Override
    public IncidentRequest findById(String id) {
        return repository.findById(id).orElse(new IncidentRequest());
    }

    @Override
    public IncidentRequest save(IncidentRequest incidentRequest) {
        incidentRequest.setIncidentNumber(sequenceGeneratorRepository.generateSequence(IncidentRequest.SEQUENCE_NAME));
        incidentRequest = repository.insert(incidentRequest);
        mailService.sendIncidentCreationEmail(incidentRequest);
        return incidentRequest;
    }

    @Override
    public IncidentRequest update(IncidentRequest incidentRequest) {
        return repository.save(incidentRequest);
    }

    @Override
    public void delete(String id) {
       repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public IncidentRequest markCompleted(IncidentMarkCompletedRequest incidentMarkCompletedRequest) {
        publisherService.publish(incidentMarkCompletedRequest);
        return null;
    }
}
