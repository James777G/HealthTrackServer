package com.healthtrack.calculator.config.mailConfig;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Data
public class SimpleOrderManager{

    private JavaMailSender mailSender;

    private SimpleMailMessage templateMessage;

    private MimeMessage mimeMessage;


    public void placeOrder(String recipientEmail, String text) {
        templateMessage.setTo(recipientEmail);
        templateMessage.setText(text);
        try {
            mailSender.send(templateMessage);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendHtmlEmail(String subject, String html, String recipient) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipients(MimeMessage.RecipientType.TO, recipient);
        message.setSubject(subject);
        message.setContent(html, "text/html; charset=utf-8");
        mailSender.send(message);
    }
}