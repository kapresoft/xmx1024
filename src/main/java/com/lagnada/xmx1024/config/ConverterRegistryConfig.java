package com.lagnada.xmx1024.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConversionServiceFactory;

import javax.annotation.Resource;
import java.util.Set;

@Configuration
//@Profile("dev")
public class ConverterRegistryConfig {

    @Resource(name = "conversionService")
    private ConverterRegistry registry;

    @Autowired
    public void registerConverters(Set<Converter> converters) {
        ConversionServiceFactory.registerConverters(converters, registry);
    }
}
