package com.healthtrack.calculator.exception;

/**
 * Parent class for all the possible exceptions that require rollback
 */
public class TransactionException extends Exception{

    public TransactionException(){
        super();
    }

    public TransactionException(String msg){
        super(msg);
    }
}
