package com.maruti.dcms.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String mssg){
        super(mssg);
    }
}
