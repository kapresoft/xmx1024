package com.lagnada.xmx1024.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        String useLocale = request.getParameter("useLocale");
        Locale requestLocale = request.getLocale();
        if (StringUtils.hasText(useLocale)) {
            String[] localeParts = useLocale.split("_");
            if (localeParts.length == 2) {
                requestLocale = new Locale(localeParts[0], localeParts[1]);
            } else {
                requestLocale = new Locale(useLocale);
            }
        }
        // Unfortunately there's no other way to get ServletContext
        // in Servlet 2.5 than getting the session first
        ServletContext context = request.getSession().getServletContext();
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);


//        Locale currentLocale = new Locale("fi", "FI");
        Locale.setDefault(requestLocale);

        MessageSource messageSource = appCtx.getBean(MessageSource.class);
        String invalidEmail = messageSource.getMessage("invalid.email", new Object[0], requestLocale);
        System.out.printf("InvalidEmail:%s%n", invalidEmail);


        env.setVariable("requestLocale", env.getObjectWrapper().wrap(requestLocale));
        List<Locale> availableLocales = Arrays.asList(Locale.getAvailableLocales());
        Comparator<Locale> localeComparator = new Comparator<Locale>() {
            @Override
            public int compare(Locale locale, Locale locale2) {
                return locale.toString().compareTo(locale2.toString());
            }
        };
        Collections.sort(availableLocales, localeComparator);

        env.setVariable("availableLocales", env.getObjectWrapper().wrap(availableLocales));

        body.render(env.getOut());
    }
}
