package com.optum.icr.service.impl;

import com.optum.icr.model.IncidentRequest;
import com.optum.icr.dto.IncidentMarkCompletedRequest;
import com.optum.icr.service.MailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendIncidentCompletionEmail(IncidentRequest incidentRequest, IncidentMarkCompletedRequest incidentMarkCompletedRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(incidentRequest.getEmail());
        message.setSubject("You incident #" + incidentRequest.getIncidentNumber() + " has been resolved");
        message.setText("You incident #" + incidentRequest.getIncidentNumber() + " has been resolved. Resolution message is: " + incidentMarkCompletedRequest.getMessage());
        javaMailSender.send(message);
    }

    @Override
    public void sendIncidentCreationEmail(IncidentRequest incidentRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(incidentRequest.getEmail());
            message.setSubject("You incident #" + incidentRequest.getIncidentNumber() + " has been created");
            message.setText("You incident #" + incidentRequest.getIncidentNumber() + " has been created. We will get back to you once it is resolved.");
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
