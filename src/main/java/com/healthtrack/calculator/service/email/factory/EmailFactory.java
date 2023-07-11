package com.healthtrack.calculator.service.email.factory;

import com.healthtrack.calculator.domain.EmailMessage;
import com.healthtrack.calculator.service.email.EmailSenderService;

public class EmailFactory {

    public enum Type {
        VERIFICATIONCODE
    }
    public static EmailType createEmail(EmailMessage emailMessage, EmailSenderService emailSenderService, Type type){
        switch(type){
            case VERIFICATIONCODE -> {
                return new VerificationCodeEmail(emailMessage, emailSenderService);
            }

            default -> {return null;}
        }
    }
}
