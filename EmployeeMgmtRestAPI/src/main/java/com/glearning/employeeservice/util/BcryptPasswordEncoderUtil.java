package com.glearning.employeeservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

public class BcryptPasswordEncoderUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawText = "welcome";

        String cipherEncodedText1 = bCryptPasswordEncoder.encode(rawText);
        String cipherEncodedText2 = bCryptPasswordEncoder.encode(rawText);
        String cipherEncodedText3 = bCryptPasswordEncoder.encode(rawText);

        Arrays.asList(cipherEncodedText1, cipherEncodedText2,cipherEncodedText3)
                .forEach(System.out::println);

        System.out.println(bCryptPasswordEncoder.matches(rawText, cipherEncodedText1));
        System.out.println(bCryptPasswordEncoder.matches(rawText, cipherEncodedText2));
        System.out.println(bCryptPasswordEncoder.matches(rawText, cipherEncodedText3));
    }
}
