package com.lagnada.xmx1024.config;

import com.lagnada.xmx1024.converter.CollectionConverterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConversionServiceFactory;

import java.util.Set;

@Configuration
@ComponentScan(basePackageClasses = CollectionConverterHelper.class)
public class ConverterRegistryConfig {

    //@Resource(name = "conversionService")
    @Autowired
    private ConverterRegistry registry;

    @Autowired
    public void registerConverters(Set<Converter> converters) {
        ConversionServiceFactory.registerConverters(converters, registry);
    }
}
