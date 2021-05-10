package com.optum.icr.service;

import com.optum.icr.dto.IncidentMarkCompletedRequest;

public interface PublisherService {
    void publish(IncidentMarkCompletedRequest incidentMarkCompletedRequest);
}
