package com.optum.icr.service;

import com.optum.icr.dto.IncidentMarkCompletedRequest;
import com.optum.icr.model.IncidentRequest;

import java.util.List;

public interface IncidentRequestService {

    List<?> findAll();

    IncidentRequest findById(String id);

    IncidentRequest save(IncidentRequest incidentRequest);

    IncidentRequest update(IncidentRequest incidentRequest);

    void delete(String id);

    IncidentRequest markCompleted(IncidentMarkCompletedRequest incidentMarkCompletedRequest);
}
