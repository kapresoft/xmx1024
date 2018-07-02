package com.lagnada.xmx1024.servlet;

import com.lagnada.xmx1024.config.ApplicationContextConfig;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

import static com.lagnada.xmx1024.servlet.CharacterEncodingFilterBuilder.newCharacterEncodingFilterBuilder;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                ApplicationContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                ServletContextConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter[] filters = new Filter[2];
        filters[0] = newCharacterEncodingFilterBuilder().build();
        filters[1] = configurableSiteMeshFilter();
        return filters;
    }

    private ConfigurableSiteMeshFilter configurableSiteMeshFilter() {
        return new ConfigurableSiteMeshFilter();
    }
}
