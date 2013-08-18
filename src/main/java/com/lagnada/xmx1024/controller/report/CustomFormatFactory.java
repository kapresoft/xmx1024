package com.lagnada.xmx1024.controller.report;

import com.google.common.base.Optional;
import net.sf.jasperreports.engine.util.DefaultFormatFactory;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class CustomFormatFactory extends DefaultFormatFactory {

    private final static Map<String, String> CURRENCY_OVERRIDES = new HashMap<String, String>();

    {
        CURRENCY_OVERRIDES.put("GBP", "£");
        CURRENCY_OVERRIDES.put("EUR", "€");
        CURRENCY_OVERRIDES.put("INR", "रू");
        CURRENCY_OVERRIDES.put("SEK", "kr");
        CURRENCY_OVERRIDES.put("CNY", "￥");
        CURRENCY_OVERRIDES.put("RUB", "руб.");
        CURRENCY_OVERRIDES.put("VND", "đ");
        CURRENCY_OVERRIDES.put("KRW", "￦");
        CURRENCY_OVERRIDES.put("UAH", "грн.");
        CURRENCY_OVERRIDES.put("PLN", "zł");
    }

    private String currencyCode;

    public static CustomFormatFactory fromCurrencyCode(String currencyCode) {
        return new CustomFormatFactory(currencyCode);
    }

    private CustomFormatFactory(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    @Override
    public DateFormat createDateFormat(String pattern, Locale locale, TimeZone tz) {

        Currency currency = Currency.getInstance(locale);
        //String currencyCode = currency.getCurrencyCode();
        String currencySymbol = currency.getSymbol(locale);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

        DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy");
//        return super.createDateFormat(pattern, locale, tz);
        return dateFormat;
    }

    @Override
    public NumberFormat createNumberFormat(String pattern, Locale currencyLocale) {
        NumberFormat format;
        if ("managed".equalsIgnoreCase(pattern)) {
            Currency currency = null;
            String currencyPattern = null;
            try {
                currency = Currency.getInstance(currencyCode);
                currencyPattern = getCurrencyPattern(currency, currencyLocale);
            } catch (Exception ignoredException) {
                currencyPattern = createStaticCurrencyPattern(currencyCode, currencyPattern);
            }

            format = super.createNumberFormat(currencyPattern, currencyLocale);
            if (Optional.fromNullable(currency).isPresent()) {
                format.setCurrency(currency);
            }

        } else {
            format = super.createNumberFormat(pattern, currencyLocale);
        }
        return format;
    }

    /**
     * Currency Symbol seems to be locale specific, i.e. EUR for Locale.US, but EURO_SYMBOL European locales.
     */
    private String getCurrencyPattern(Currency currency, Locale currencyLocale) {
        String currencyPattern = "¤ #,##0.00";
        String currencySymbol = currency.getSymbol(currencyLocale);
        if (CURRENCY_OVERRIDES.containsKey(currencySymbol)) {
            currencyPattern = createStaticCurrencyPattern(CURRENCY_OVERRIDES.get(currencySymbol), currencyPattern);
        }
        return currencyPattern;
    }

    private String createStaticCurrencyPattern(String currencyCode, String currencyPattern) {
        return currencyPattern.replace("¤", String.format("'%s'", currencyCode));
    }

    /**
     * Transform DateFormat.SHORT from "M/d/yy" to "MM/dd/yy"
     */
    private SimpleDateFormat transformShortDatePattern(Locale locale) {
        SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String datePattern = dateFormat.toPattern();
        if (countOccurrencesOf(datePattern, "M") == 1) {
            datePattern = datePattern.replace("M", "MM");
        }
        if (countOccurrencesOf(datePattern, "d") == 1) {
            datePattern = datePattern.replace("d", "dd");
        }
        dateFormat.applyPattern(datePattern);
        return dateFormat;
    }
}
