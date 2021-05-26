package com.optum.icr.service;

import com.optum.icr.dto.IncidentMarkCompletedRequest;
import com.optum.icr.model.IncidentRequest;

public interface MailService {
    void sendIncidentCompletionEmail(IncidentRequest incidentRequest, IncidentMarkCompletedRequest incidentMarkCompletedRequest);

    void sendIncidentCreationEmail(IncidentRequest incidentRequest);
}
