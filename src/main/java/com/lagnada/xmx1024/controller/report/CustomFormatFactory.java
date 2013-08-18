package com.lagnada.xmx1024.controller.report;

import net.sf.jasperreports.engine.util.DefaultFormatFactory;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class CustomFormatFactory extends DefaultFormatFactory {
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
    public NumberFormat createNumberFormat(String pattern, Locale locale) {
        NumberFormat format;
        if ("managed".equalsIgnoreCase(pattern)) {
            format = new DecimalFormat("'USD' ¤ #,##0.00");
        } else {
            format = super.createNumberFormat(pattern, locale);
        }
        return format;
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
