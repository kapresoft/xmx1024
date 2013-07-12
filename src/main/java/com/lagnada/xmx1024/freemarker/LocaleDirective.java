package com.lagnada.xmx1024.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class LocaleDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Locale requestLocale = request.getLocale();

        Locale currentLocale = Locale.GERMAN;
        Locale.setDefault(currentLocale);

        env.setVariable("currentLocale", env.getObjectWrapper().wrap(currentLocale));
        env.setVariable("availableLocales", env.getObjectWrapper().wrap(Locale.getAvailableLocales()));

        body.render(env.getOut());
    }
}
