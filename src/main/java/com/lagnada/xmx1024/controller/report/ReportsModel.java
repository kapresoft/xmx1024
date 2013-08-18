package com.lagnada.xmx1024.controller.report;

import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.StaticMessageSource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static com.google.common.base.Optional.fromNullable;

public class ReportsModel {

    private Map<String, String> resourceMap = new HashMap<String, String>();

    public Map<String, String> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(Map<String, String> resourceMap) {
        this.resourceMap = resourceMap;
    }

    public Map<String, String> getR() {
        return getResourceMap();
    }

    public void setR(Map<String, String> resourceMap) {
        setResourceMap(resourceMap);
    }

    public String getProperty(String name) {
        return resourceMap.get(name);
    }

    public void setProperty(String name, String value) {
        resourceMap.put(name, value);
    }

    public ResourceBundle toPropertyResourceBundle() {
        StaticMessageSource staticMessageSource = new StaticMessageSource();
        //Locale locale = LocaleContextHolder.getLocale();
        Locale defaultLocale = Locale.getDefault();
        for (String key : resourceMap.keySet()) {
            if (fromNullable(key).isPresent() && key.startsWith("resource.")) {
                String modifiedKey = key.replaceFirst("^resource.", "");
                String value = resourceMap.get(key);
                staticMessageSource.addMessage(modifiedKey, defaultLocale, value);
            }
        }
        return new MessageSourceResourceBundle(staticMessageSource, defaultLocale);
    }

}
