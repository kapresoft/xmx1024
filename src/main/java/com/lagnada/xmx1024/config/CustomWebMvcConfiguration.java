package com.lagnada.xmx1024.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
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
    private MappingJacksonHttpMessageConverter jacksonOverride;

    @Override
    protected void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        addDefaultHttpMessageConverters(converters);
        overrideMappingJacksonHttpMessageConverter(converters, jacksonOverride);
    }

    protected void overrideMappingJacksonHttpMessageConverter(List<HttpMessageConverter<?>> converters, MappingJacksonHttpMessageConverter override) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> mc = iterator.next();
            if (mc instanceof MappingJacksonHttpMessageConverter) {
                iterator.remove();
                break;
            }
        }
        converters.add(override);
    }

    @Bean
    public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        //SerializationConfig serializationConfig = objectMapper
        //.getSerializationConfig()
        //.withDateFormat(new SimpleDateFormat(BIRTHDAY_DATE_FORMAT));
        //objectMapper.setSerializationConfig(serializationConfig);
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);

        MappingJacksonHttpMessageConverter mc = new MappingJacksonHttpMessageConverter();
        mc.setObjectMapper(objectMapper);
        return mc;
    }

}
