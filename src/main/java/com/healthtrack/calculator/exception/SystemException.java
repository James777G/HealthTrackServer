package com.healthtrack.calculator.exception;

/**
 * Parent class for all the exception that does not require rollback
 */
public class SystemException extends Exception{

    public SystemException(){
        super();
    }

    public SystemException(String msg){
        super(msg);
    }
}
