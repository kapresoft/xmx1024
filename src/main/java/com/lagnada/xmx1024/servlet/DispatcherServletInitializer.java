package com.lagnada.xmx1024.servlet;

import com.lagnada.xmx1024.config.ApplicationContextConfig;
import com.lagnada.xmx1024.config.ServletContextConfig;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                InternationalizationInitializer.class,
                ConversionServiceInitializer.class,
                ValidatorInitializer.class,
                ApplicationContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                JspInitializer.class,
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
        filters[0] = characterEncodingFilter();
        filters[1] = configurableSiteMeshFilter();
        return filters;
    }

    private Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    private ConfigurableSiteMeshFilter configurableSiteMeshFilter() {
        return new ConfigurableSiteMeshFilter();
    }
}
