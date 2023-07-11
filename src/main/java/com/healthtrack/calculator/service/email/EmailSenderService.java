package com.healthtrack.calculator.service.email;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
