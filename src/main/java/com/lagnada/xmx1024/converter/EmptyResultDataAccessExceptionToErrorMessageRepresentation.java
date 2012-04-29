package com.lagnada.xmx1024.converter;

import com.lagnada.xmx1024.representation.ErrorMessageRepresentation;
import com.lagnada.xmx1024.representation.ErrorMessageRepresentationBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import static java.lang.String.format;

@Component
public class EmptyResultDataAccessExceptionToErrorMessageRepresentation implements Converter<EmptyResultDataAccessException, ErrorMessageRepresentation> {

    @Override
    public ErrorMessageRepresentation convert(EmptyResultDataAccessException e) {
        UriComponents uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build();
        return ErrorMessageRepresentationBuilder.newBuilder()
                .error("RESOURCE-NOT-FOUND", format("Resource not found: " + uri))
                .build();
    }
}
