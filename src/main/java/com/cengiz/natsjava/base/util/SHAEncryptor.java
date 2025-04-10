package com.cengiz.natsjava.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHAEncryptor {

    static SHAEncryptor instance = null;

    MessageDigest mDigest;

    public static SHAEncryptor getInstance() {
        if (instance == null) {
            instance = new SHAEncryptor();
        }
        return instance;
    }

    private SHAEncryptor() {
        try {
            this.mDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptorException(e.getMessage(), e);
        }
    }

    public String getEncrypted(String input) {
        StringBuilder sb = new StringBuilder();
        byte[] result = mDigest.digest(input.getBytes());
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
