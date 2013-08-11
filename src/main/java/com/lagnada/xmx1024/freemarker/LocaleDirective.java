package com.lagnada.xmx1024.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.joda.time.DateTime;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.google.common.base.Optional.fromNullable;

@SuppressWarnings("UnusedDeclaration")
public class LocaleDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        //LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        WebApplicationContext appCtx = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        LocaleResolver localeResolver = appCtx.getBean(LocaleResolver.class);
        Locale requestLocale = localeResolver.resolveLocale(request);
        env.setVariable("requestLocale", env.getObjectWrapper().wrap(requestLocale));

        setDateFormatVariables(env, requestLocale);


        Currency currency = null;
        String currencyCode = null;
        String currencySymbol = null;
        try {
            currency = Currency.getInstance(requestLocale);
            currencyCode = currency.getCurrencyCode();
            currencySymbol = currency.getSymbol(requestLocale);
        } catch (Exception e) {
            // ignore
        }
        if (!fromNullable(currency).isPresent()) {
            currencyCode = "N/A";
            currencySymbol = "N/A";
        }
        env.setVariable("currency", env.getObjectWrapper().wrap(currencyCode));
        env.setVariable("currencySymbol", env.getObjectWrapper().wrap(currencySymbol));

        Currency unitedStatesCurrency = Currency.getInstance(Locale.US);
        env.setVariable("unitedStatesCurrency", env.getObjectWrapper().wrap(unitedStatesCurrency.getCurrencyCode()));
        env.setVariable("unitedStatesCurrencySymbol", env.getObjectWrapper().wrap(unitedStatesCurrency.getSymbol(Locale.US)));


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

    private void setDateFormatVariables(Environment env, Locale requestLocale) throws TemplateModelException {
        Date today = DateTime.now().toDate();

        getDateFormatText(env, requestLocale, today, DateFormat.SHORT, "shortDate");
        getDateFormatText(env, requestLocale, today, DateFormat.MEDIUM, "mediumDate");
        getDateFormatText(env, requestLocale, today, DateFormat.LONG, "longDate");
        getDateFormatText(env, Locale.US, today, DateFormat.SHORT, "unitedStatesShortDate");
        getDateFormatText(env, Locale.US, today, DateFormat.MEDIUM, "unitedStatesMediumDate");
        getDateFormatText(env, Locale.US, today, DateFormat.LONG, "unitedStatesLongDate");
    }

    private void getDateFormatText(Environment env, Locale requestLocale, Date today, int predefinedFormat, String varName) throws TemplateModelException {
        SimpleDateFormat shortDateFormat = (SimpleDateFormat) DateFormat.getDateInstance(predefinedFormat, requestLocale);
        env.setVariable(varName, env.getObjectWrapper().wrap(shortDateFormat.format(today)));
        env.setVariable(varName + "Pattern", env.getObjectWrapper().wrap(shortDateFormat.toPattern()));
    }
}
