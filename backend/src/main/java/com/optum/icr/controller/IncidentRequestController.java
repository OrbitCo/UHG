package com.optum.icr.controller;


import com.optum.icr.dto.IncidentMarkCompletedRequest;
import com.optum.icr.model.IncidentRequest;
import com.optum.icr.service.IncidentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident-request")
public class IncidentRequestController {

    @Autowired
    private IncidentRequestService incidentRequestService;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        List<?> list = incidentRequestService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        IncidentRequest incidentRequest = incidentRequestService.findById(id);
        return ResponseEntity.ok().body(incidentRequest);
    }


    @PostMapping("/save")
    public IncidentRequest save(@RequestBody IncidentRequest incidentRequest) {
        return incidentRequestService.save(incidentRequest);
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody IncidentRequest incidentRequest) {
        IncidentRequest updatedIncidentRequest = incidentRequestService.update(incidentRequest);
        return ResponseEntity.ok().body(updatedIncidentRequest);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        incidentRequestService.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }

    @PostMapping("/markCompleted")
    public IncidentRequest markCompleted(@RequestBody IncidentMarkCompletedRequest incidentMarkCompletedRequest) {
        return incidentRequestService.markCompleted(incidentMarkCompletedRequest);
    }
}
