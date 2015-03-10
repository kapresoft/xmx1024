package com.lagnada.xmx1024.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Iterator;
import java.util.List;

/**
 * This is just an example of overriding webmvc config
 */
//@Configuration
// @EnableWebMvc
//@ComponentScan(basePackages = "com.lagnada.xmx1024.controller")
public class CustomWebMvcConfiguration extends WebMvcConfigurationSupport {

    public static final String BIRTHDAY_DATE_FORMAT = "yyyy-MM-dd";
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonOverride;

    @Override
    protected void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        addDefaultHttpMessageConverters(converters);
        overrideMappingJacksonHttpMessageConverter(converters, jacksonOverride);
    }

    protected void overrideMappingJacksonHttpMessageConverter(List<HttpMessageConverter<?>> converters, MappingJackson2HttpMessageConverter override) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> mc = iterator.next();
            if (mc instanceof MappingJackson2HttpMessageConverter) {
                iterator.remove();
                break;
            }
        }
        converters.add(override);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        //SerializationConfig serializationConfig = objectMapper
        //.getSerializationConfig()
        //.withDateFormat(new SimpleDateFormat(BIRTHDAY_DATE_FORMAT));
        //objectMapper.setSerializationConfig(serializationConfig);
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);

        MappingJackson2HttpMessageConverter mc = new MappingJackson2HttpMessageConverter();
        mc.setObjectMapper(objectMapper);
        return mc;
    }

}
