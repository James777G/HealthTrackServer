package com.healthtrack.calculator.domain;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmailMessage {
    private String to;

    private String type;

    public EmailMessage(){}

    public EmailMessage(String to, String type) {
        this.to = to;
        this.type = type;
    }
}
