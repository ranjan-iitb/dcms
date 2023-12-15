package com.maruti.dcms.exceptionHandler;

import com.maruti.dcms.dto.ApiResponse;
import com.maruti.dcms.dto.ErrorDTO;
import com.maruti.dcms.exceptions.EmployeeNotFoundException;
import com.maruti.dcms.exceptions.IncorrectDateFormatException;
import com.maruti.dcms.exceptions.ModelServiceActivityNotFoundException;
import com.maruti.dcms.exceptions.ModelServiceMilestoneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ModelServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ApiResponse<?> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(IncorrectDateFormatException.class)
    public ApiResponse<?> handleIncorrectDateFormatException(IncorrectDateFormatException exception) {
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(ModelServiceActivityNotFoundException.class)
    public ApiResponse<?> handleModelServiceActivityNotFoundException(ModelServiceActivityNotFoundException exception) {
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(ModelServiceMilestoneNotFoundException.class)
    public ApiResponse<?> handleModelServiceMilestoneNotFoundException(ModelServiceMilestoneNotFoundException exception) {
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

}
