package com;

import sun.rmi.runtime.Log;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class SetLocale {

    public SetLocale() {

    }
    public void set(String language, String country) {
        Locale.setDefault(new Locale(language, country));
    }
}
