package com.healthtrack.calculator.service.message;

import com.healthtrack.calculator.annotation.Warning;

@Warning(Warning.Type.REVIEW_NEEDED)
public interface MessageService {

    /**
     * Sends a message to the specified message queue
     * @param message Must be in specified Json format
     */
    void sendMessage(Object message);

    /**
     * Sends and receives message from other services
     * Implementation of RPC
     *
     * @param message the message specified in Json format
     * @return an string also specified in Json format
     */
    Object sendAndReceive(String message);

}
