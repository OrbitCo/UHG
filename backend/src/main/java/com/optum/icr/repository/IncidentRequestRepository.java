package com.optum.icr.repository;

import com.optum.icr.model.IncidentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface IncidentRequestRepository extends MongoRepository<IncidentRequest, String> {

}
