package com.store.goodsstore.exceptions;

/**
 *
 * @author YBolshakova
 */
public class AlreadyExistsException extends RuntimeException{
    
    private String message;

    public AlreadyExistsException() {
        super();
    }  
    public AlreadyExistsException(String message){
        super(message);
    }

}
