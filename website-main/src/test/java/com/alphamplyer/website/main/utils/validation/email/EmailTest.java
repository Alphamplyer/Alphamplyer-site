package com.alphamplyer.website.main.utils.validation.email;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;


public class EmailTest {

    @Test
    public void isValid() {
        Map<String, String> validEmails = new HashMap<>();
        validEmails.put("email@domain.com", "[valid] Valid email");
        validEmails.put("firstname.lastname@domain.com", "[valid] Email contains dot in the address field");
        validEmails.put("email@subdomain.domain.com", "[valid] Email contains dot with subdomain");
        validEmails.put("firstname+lastname@domain.com", "[valid] Plus sign is considered valid character");
        validEmails.put("email@123.123.123.123", "[valid] Domain is valid IP address");
        validEmails.put("email@[123.123.123.123]", "[valid] Square bracket around IP address is considered valid");
        validEmails.put("\"email\"@domain.com", "[valid] Quotes around email is considered valid");
        validEmails.put("1234567890@domain.com", "[valid] Digits in address are valid");
        validEmails.put("email@domain-one.com", "[valid] Dash in domain name is valid");
        validEmails.put("_______@domain.com", "[valid] Underscore in the address field is valid");
        validEmails.put("email@domain.name", "[valid] .name is valid Top Level Domain name");
        validEmails.put("email@domain.co.fr", "[valid] Dot in Top Level Domain name also considered valid (use co.fr as example here)");
        validEmails.put("firstname-lastname@domain.com", "[valid] Dash in address field is valid");

        for (String email : validEmails.keySet()) {
            Assertions.assertTrue(Email.isValid(email), "Email tested = " + email + "\nTested email reason = " + validEmails.get(email));
        }

        Map<String, String> invalidEmails = new HashMap<>();
        invalidEmails.put("plainaddress", "[invalid] Missing @ sign and domain");
        invalidEmails.put("#@%^%#$@#$@#.com", "[invalid] Garbage");
        invalidEmails.put("@domain.com", "[invalid] Missing username");
        invalidEmails.put("Joe Smith <email@domain.com>", "[invalid] Encoded html within email is invalid");
        invalidEmails.put("email.domain.com", "[invalid] Missing @");
        invalidEmails.put("email@domain@domain.com", "[invalid] Two @ sign");
        invalidEmails.put(".email@domain.com", "[invalid] Leading dot in address is not allowed");
        invalidEmails.put("email.@domain.com", "[invalid] Trailing dot in address is not allowed");
        invalidEmails.put("email..email@domain.com", "[invalid] Multiple dots");
        invalidEmails.put("あいうえお@domain.com", "[invalid] Unicode char as address");
        invalidEmails.put("email@domain.com (Joe Smith)", "[invalid] Text followed email is not allowed");
        invalidEmails.put("email@domain", "[invalid] Missing top level domain (.com/.net/.org/etc)");
        invalidEmails.put("email@-domain.com", "[invalid] Leading dash in front of domain is invalid");
        invalidEmails.put("email@domain.web", "[invalid] .web is not a valid top level domain");
        invalidEmails.put("email@111.222.333.44444", "[invalid] Invalid IP format");
        invalidEmails.put("email@domain..com", "[invalid] Multiple dot in the domain portion is invalid");
        invalidEmails.put(null, "[invalid] Null email");

        for (String email : invalidEmails.keySet()) {
            Assertions.assertFalse(Email.isValid(email), "Email tested = " + email + "\nTested email reason = " + invalidEmails.get(email));
        }
    }
}
