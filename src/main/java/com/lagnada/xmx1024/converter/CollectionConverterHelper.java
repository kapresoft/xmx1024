package com.lagnada.xmx1024.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collection;

public class CollectionConverterHelper {

    private ConversionService conversionService;
    private Object sourceObject;
    private Class<? extends Collection> sourceCollectionType;
    private Class<?> targetType;

    private CollectionConverterHelper() {
        super();
    }

    public static CollectionConverterHelper conversionService(final ConversionService conversionService) {
        CollectionConverterHelper helper = new CollectionConverterHelper();
        helper.conversionService = conversionService;
        return helper;
    }

    public CollectionConverterHelper source(final Object sourceObject, Class<? extends Collection> sourceCollectionType) {
        this.sourceObject = sourceObject;
        this.sourceCollectionType = sourceCollectionType;
        return this;
    }

    public CollectionConverterHelper targetType(Class<?> targetType) {
        this.targetType = targetType;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T convert() {
        if (sourceObject == null) {
            return null;
        }
        TypeDescriptor sourceTypeDesc = TypeDescriptor.forObject(sourceObject);
        TypeDescriptor targetTypeDesc = TypeDescriptor.collection(sourceCollectionType, TypeDescriptor.valueOf(targetType));
        return (T) conversionService.convert(sourceObject, sourceTypeDesc, targetTypeDesc);
    }
}
