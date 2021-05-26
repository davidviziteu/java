package com;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DisplayLocales {

    public DisplayLocales() {

    }

    public List<Locale> getLocales() {
        return Arrays.asList(DateFormat.getAvailableLocales());
    }

}
