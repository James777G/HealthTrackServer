package com.healthtrack.calculator.service.mail;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;

@Component
public interface MailService {

    /**
     * This method sends recipient an email with a verification code format
     *
     * @param recipient        people who receive emails
     * @param verificationCode auto-generated
     */
    void sendEmail(String recipient, String verificationCode) throws MessagingException;

    void sendHtmlMail(String subject, String html, String recipient) throws MessagingException;
}
