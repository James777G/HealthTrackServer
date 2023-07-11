package com.healthtrack.calculator.controller.email;

import com.healthtrack.calculator.domain.EmailMessage;
import com.healthtrack.calculator.service.email.EmailSenderService;
import com.healthtrack.calculator.service.email.factory.EmailFactory;
import com.healthtrack.calculator.service.email.factory.EmailType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
public class EmailController {

    private EmailSenderService emailSenderService;
    private EmailType emailType;

    public EmailController( EmailType emailType , EmailSenderService emailSenderService) {
        this.emailType = emailType;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) throws IOException {
        //this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        EmailFactory.Type type = null;
        if(Objects.equals(emailMessage.getType(), "verificationCode")) {
            type =  EmailFactory.Type.VERIFICATIONCODE;
        }
        assert type != null;
        this.emailType = EmailFactory.createEmail(emailMessage, emailSenderService, type);
        assert emailType != null;
        emailType.sendPackedEmail();
        return ResponseEntity.ok("Success");
    }
}
