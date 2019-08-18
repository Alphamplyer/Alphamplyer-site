package com.alphamplyer.microservice.users.utlis;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Password {

    private static final byte[] charTable = new byte[] {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W' ,'X' ,'Y' ,'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w' ,'x' ,'y' ,'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '-', '_', '/', '.', '='
    };

    public static String encryptPassword (String password, String salt) {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(salt, 10000, 128);
        return passwordEncoder.encode(password);
    }

    public static String generateSalt(int size) {
        byte[] bytes = new byte[size];
        SecureRandom secureRandom;

        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            secureRandom = new SecureRandom();
        }

        secureRandom.setSeed(SecureRandom.getSeed(512));
        secureRandom.nextBytes(bytes);

        for (int i = 0; i < size; i++) {
            bytes[i] = charTable[(bytes[i] & 0xFF) % 67];
        }

        return new String(bytes);
    }

    public static Boolean checkPassword(String enteredPassword, String savedPassword, String salt) {
        return encryptPassword(enteredPassword, salt) == savedPassword;
    }

}
