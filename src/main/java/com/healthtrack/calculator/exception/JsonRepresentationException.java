package com.healthtrack.calculator.exception;

/**
 * Json Representation Error
 */
public class JsonRepresentationException extends SystemException{

    public JsonRepresentationException(){
        super();
    }

    public JsonRepresentationException(String msg){
        super(msg);
    }
}
