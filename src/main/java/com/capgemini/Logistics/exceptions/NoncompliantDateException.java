package com.capgemini.Logistics.exceptions;

public class NoncompliantDateException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoncompliantDateException(String msg) {
        super(msg);
    }
    
}
