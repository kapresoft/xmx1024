package com.lagnada.xmx1024.converter;

import com.lagnada.xmx1024.representation.ErrorMessageRepresentation;
import com.lagnada.xmx1024.representation.ErrorMessageRepresentationBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class ExceptionToErrorMessageRepresentation implements Converter<Exception, ErrorMessageRepresentation> {

    @Override
    public ErrorMessageRepresentation convert(Exception e) {
        return ErrorMessageRepresentationBuilder.newBuilder()
                .error("SERVER-ERROR", format("Unexpected error message occurred [%s]. Please contact support.", e.getMessage()))
                .build();
    }
}
