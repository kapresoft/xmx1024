package com.lagnada.xmx1024.representation;

import org.springframework.validation.FieldError;

import java.util.ArrayList;

public class ErrorMessageRepresentationBuilder {

    private ErrorMessageRepresentation errorMessage;

    private ErrorMessageRepresentationBuilder() {
        errorMessage = new ErrorMessageRepresentation();
        errorMessage.setErrors(new ArrayList<ErrorResponse>());
    }

    public static ErrorMessageRepresentationBuilder newBuilder() {
        return new ErrorMessageRepresentationBuilder();
    }

    public ErrorMessageRepresentationBuilder error(String code, String field, String message) {
        errorMessage.addError(new ErrorResponse(code, field, message));
        return this;
    }

    public ErrorMessageRepresentationBuilder error(String code, String message) {
        errorMessage.addError(new ErrorResponse(code, "", message));
        return this;
    }

    public ErrorMessageRepresentationBuilder fieldError(FieldError fieldError) {
        errorMessage.addError(new ErrorResponse(fieldError.getCode(), fieldError.getField(), fieldError.getDefaultMessage()));
        return this;
    }

    public ErrorMessageRepresentation build() {
        return errorMessage;
    }

}
