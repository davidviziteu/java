package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;
import utils.Shell;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleExplore {
    public static void main(String[] args) {
        while (true) {
            ResourceBundle resources = ResourceBundle.getBundle("res.Messages", Locale.getDefault());
            System.out.println(resources.getString("prompt"));
            Shell shellCommand = new Shell();
            switch (shellCommand.tokenizer().get(0)) {
                case "locales": {
                    System.out.println(resources.getString("locales"));
                    System.out.println(new DisplayLocales().getLocales());
                    break;
                }
                case "set": {
                    new SetLocale().set(shellCommand.tokenizer().get(1), shellCommand.tokenizer().get(2));
                    System.out.println(MessageFormat.format(resources.getString("locale.set"), Locale.getDefault()));
                    new Info().getLocaleInfo();
                    break;
                }
                case "info": {
                    System.out.println(MessageFormat.format(resources.getString("info"), Locale.getDefault()));
                    new Info().getLocaleInfo();
                    break;
                }
                case "exit": {
                    System.exit(0);
                }
                default: {
                    System.out.println(resources.getString("invalid"));
                }
            }
        }
    }
}