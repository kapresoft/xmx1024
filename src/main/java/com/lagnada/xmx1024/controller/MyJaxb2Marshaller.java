package com.lagnada.xmx1024.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.annotation.XmlRootElement;

public class MyJaxb2Marshaller extends Jaxb2Marshaller {

    @Override
    public boolean supports(Class<?> clazz) {
        return super.supports(clazz) ? true : AnnotationUtils.findAnnotation(clazz, XmlRootElement.class) != null;
    }

}
