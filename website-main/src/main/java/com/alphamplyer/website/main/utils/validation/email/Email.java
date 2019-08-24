package com.alphamplyer.website.main.utils.validation.email;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private static final String[] topLevelDomains = new String[] {"arpa", "root", "aero", "biz", "cat", "cam", "com", "coop", "edu", "gov", "edu", "info", "int", "jobs", "mil", "museum", "name", "net",
        "org", "pro", "profession", "travel",
        "fr", "en" // <-- Add domain of supported country here
    };

    private static final String regex_firstPart_1 = "^[a-zA-Z0-9_+&*\"-]+(?:\\.[a-zA-Z0-9_+&*-]+)*";
    private static final String regex_secondPart_1 = "(?:[a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]\\.)+[a-zA-Z]{2,7}";
    private static final String regex_secondPart_2 = "\\[?+(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\]?+";

    public static boolean isValid(String email) {
        if (email == null)
            return false;

        String[] email_parts = email.split("@");

        if (email_parts.length != 2)
            return false;

        boolean valid_first_part = executeRegex(email_parts[0], regex_firstPart_1);
        boolean valid_second_part = executeRegex(email_parts[1], regex_secondPart_1);

        if (valid_second_part) {
            String[] second_part_cut = email_parts[1].split("\\.");

            String domain = second_part_cut[second_part_cut.length - 1];
            valid_second_part = Arrays.asList(topLevelDomains).contains(domain);
        }
        else {
            valid_second_part = executeRegex(email_parts[1], regex_secondPart_2);
        }

        return valid_first_part && valid_second_part;
    }

    private static boolean executeRegex(String element, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element);
        return matcher.matches();
    }
}
