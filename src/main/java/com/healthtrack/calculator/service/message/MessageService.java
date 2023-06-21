package com.healthtrack.calculator.service.message;

import com.healthtrack.calculator.annotation.Warning;

@Warning(Warning.Type.REVIEW_NEEDED)
public interface MessageService {

    void sendMessage(Object message);

}
