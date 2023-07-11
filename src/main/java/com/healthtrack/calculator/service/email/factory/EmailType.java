package com.healthtrack.calculator.service.email.factory;

import com.healthtrack.calculator.domain.EmailMessage;
import com.healthtrack.calculator.service.email.EmailSenderService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public abstract class EmailType {

    protected EmailMessage emailMessage;
    protected EmailSenderService emailSenderService;
    public EmailType(EmailMessage emailMessage, EmailSenderService emailSenderService){
        this.emailMessage = emailMessage;
        this.emailSenderService = emailSenderService;
    }
    public abstract void sendPackedEmail() throws IOException;
}
