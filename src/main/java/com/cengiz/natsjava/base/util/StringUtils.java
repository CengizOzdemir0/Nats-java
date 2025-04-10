package com.cengiz.natsjava.base.util;

import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor
public class StringUtils {

    public static String convertTrToEn(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Türkçe karakterleri İngilizce karakterlere dönüştürme işlemi
        String trChars = "çÇğĞıİöÖşŞüÜ";
        String enChars = "cCgGiIoOsSuU";

        StringBuilder builder = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int index = trChars.indexOf(currentChar);

            if (index != -1) {
                builder.append(enChars.charAt(index));
            } else {
                builder.append(currentChar);
            }
        }

        return builder.toString();
    }

    public static byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    public static String dosyaUzantisiAl(String dosyaAdi) {
        if (dosyaAdi != null && dosyaAdi.contains(".")) {
            return dosyaAdi.substring(dosyaAdi.lastIndexOf("."));
        } else {
            return "";
        }
    }

    public static boolean isEmpty(final String cs) {
        return cs == null || cs.length() == 0 || cs.trim().length() == 0;
    }

    public static boolean isNotEmpty(final String cs) {
        return !isEmpty(cs);
    }

}