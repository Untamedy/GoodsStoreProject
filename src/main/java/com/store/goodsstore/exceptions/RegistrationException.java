package com.store.goodsstore.exceptions;


/**
 *
 * @author YBolshakova
 */

public class RegistrationException extends RuntimeException{
    private String message;
    
    public RegistrationException(String message){
        super(message);
        
    }

}
