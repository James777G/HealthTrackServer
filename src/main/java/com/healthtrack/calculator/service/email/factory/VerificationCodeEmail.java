package com.healthtrack.calculator.service.email.factory;

import com.healthtrack.calculator.domain.EmailMessage;
import com.healthtrack.calculator.service.email.EmailSenderService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class VerificationCodeEmail extends EmailType{

    private EmailSenderService emailSenderService;
    private String to;

    public VerificationCodeEmail(EmailMessage emailMessage, EmailSenderService emailSenderService) {
        super(emailMessage, emailSenderService);
    }

    @Override
    public void sendPackedEmail() throws IOException {
        InputStream inputStream = VerificationCodeEmail.class.getResourceAsStream("/mail/account.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        emailSenderService.sendEmail(emailMessage.getTo(), properties.getProperty("subject"), properties.getProperty("message") );
    }
}
