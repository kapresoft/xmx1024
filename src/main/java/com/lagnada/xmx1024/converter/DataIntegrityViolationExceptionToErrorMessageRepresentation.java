package com.lagnada.xmx1024.converter;

import com.lagnada.xmx1024.representation.ErrorMessageRepresentation;
import com.lagnada.xmx1024.representation.ErrorMessageRepresentationBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class DataIntegrityViolationExceptionToErrorMessageRepresentation implements Converter<DataIntegrityViolationException, ErrorMessageRepresentation> {

    @Override
    public ErrorMessageRepresentation convert(DataIntegrityViolationException e) {
        return ErrorMessageRepresentationBuilder.newBuilder()
                .error("DUPLICATE-RESOURCE", format("Duplicate resource exists [%s].", e.getMessage()))
                .build();
    }
}
