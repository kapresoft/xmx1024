package com.lagnada.xmx1024.controller;

import com.lagnada.xmx1024.representation.ErrorMessageRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NoResultException;

@RequestMapping(produces = {"application/json", "application/xml"})
public class BaseController {

    @Autowired
    protected ConversionService conversionService;

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoResultException(NoResultException exception) { /* no op */ }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageRepresentation handleGeneralException(Exception e) {
        return conversionService.convert(e, ErrorMessageRepresentation.class);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageRepresentation handleGeneralException(MethodArgumentNotValidException e) {
        return conversionService.convert(e, ErrorMessageRepresentation.class);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageRepresentation handleGeneralException(DataIntegrityViolationException e) {
        return conversionService.convert(e, ErrorMessageRepresentation.class);
    }

}
