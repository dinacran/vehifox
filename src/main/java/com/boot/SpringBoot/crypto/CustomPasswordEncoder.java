package com.boot.SpringBoot.crypto;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private static final PasswordEncoder INSTANCE = new CustomPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return java.util.Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return java.util.Base64.getEncoder().encodeToString(rawPassword.toString().getBytes()).equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

}
