package com.assignment.assignment1.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class CryptoService {
    private StandardPBEStringEncryptor encryptor;

    public CryptoService() {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword("something");
    }

    public String encryptText(String text) {
        return encryptor.encrypt(text);
    }

    public String decryptText(String text){
        return encryptor.decrypt(text);
    }
}
