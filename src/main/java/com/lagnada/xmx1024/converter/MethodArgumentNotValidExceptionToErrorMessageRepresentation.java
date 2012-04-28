package com.lagnada.xmx1024.converter;

import com.lagnada.xmx1024.representation.ErrorMessageRepresentation;
import com.lagnada.xmx1024.representation.ErrorMessageRepresentationBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class MethodArgumentNotValidExceptionToErrorMessageRepresentation implements Converter<MethodArgumentNotValidException, ErrorMessageRepresentation> {

    @Override
    public ErrorMessageRepresentation convert(MethodArgumentNotValidException e) {
        ErrorMessageRepresentationBuilder builder = ErrorMessageRepresentationBuilder.newBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            builder.fieldError(fieldError);
        }
        return builder.build();
    }
}
