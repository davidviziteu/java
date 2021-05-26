package com;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class Info {

    public Info() {
    }

    public void getLocaleInfo() {
        Locale loc = Locale.getDefault();
        System.out.println("Country: " +  loc.getCountry() + " " + loc.getDisplayCountry(Locale.getDefault()));
        System.out.println("Language: " + loc.getLanguage() + " " + loc.getDisplayLanguage(Locale.getDefault()));
        System.out.println("Currency: " + Currency.getInstance(Locale.getDefault()) + " " +
                Currency.getInstance(Locale.getDefault()).getDisplayName());
        System.out.println("Week Days: "
                + Arrays.toString(DateFormatSymbols.getInstance(Locale.getDefault()).getWeekdays()));
        System.out.println("Months : "
                + Arrays.toString(DateFormatSymbols.getInstance(Locale.getDefault()).getMonths()));

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.forLanguageTag(Locale.getDefault().getLanguage()));
        System.out.println("Today: " +  today.format(formatter));
    }
}
