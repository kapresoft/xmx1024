package com.lagnada.xmx1024.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LocaleDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        //LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        WebApplicationContext appCtx = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        LocaleResolver localeResolver = appCtx.getBean(LocaleResolver.class);
        Locale requestLocale = localeResolver.resolveLocale(request);


        env.setVariable("requestLocale", env.getObjectWrapper().wrap(requestLocale));

        List<Locale> allLocales = Arrays.asList(Locale.getAvailableLocales());
        Comparator<Locale> localeComparator = new Comparator<Locale>() {
            @Override
            public int compare(Locale locale, Locale locale2) {
                return locale.toString().compareTo(locale2.toString());
            }
        };
        Collections.sort(allLocales, localeComparator);
        List<ModelMap> availableLocales = new ArrayList<ModelMap>();
        for (Locale locale : allLocales) {
            ModelMap modelMap = new ModelMap();
            modelMap.put("localeText", locale.toString());
            modelMap.put("localeDisplayName", locale.getDisplayName(requestLocale));
            modelMap.put("displayName", locale.getDisplayName(Locale.US));
            availableLocales.add(modelMap);
        }

        env.setVariable("availableLocales", env.getObjectWrapper().wrap(availableLocales));

        body.render(env.getOut());
    }
}
