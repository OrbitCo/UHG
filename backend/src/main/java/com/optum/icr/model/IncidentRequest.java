package com.optum.icr.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "incidentRequest")
public class IncidentRequest implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "incident_number_sequence";

    @Id
    private String id;

    private long incidentNumber;
    private String name;
    private String organization;
    private String application;
    private String url;
    private String api;
    private String errorCode;
    private String email;
    private String message;
    private String status;
    private String resolutionMessage;

    // Constructor, Getter and Setter
}