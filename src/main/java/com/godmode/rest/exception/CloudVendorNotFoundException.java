package com.godmode.rest.exception;

public class CloudVendorNotFoundException extends RuntimeException {

    public CloudVendorNotFoundException(String message){
        super(message);
    }  
}
