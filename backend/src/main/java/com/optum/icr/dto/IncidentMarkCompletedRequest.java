package com.optum.icr.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class IncidentMarkCompletedRequest {
    private String incidentRequestId;

    private String message;
}
