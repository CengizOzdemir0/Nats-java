package com.cengiz.natsjava.base.util;

import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.exception.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;
import java.util.Map.Entry;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateUtils {

  private static final String INJECT_KEY = "${%s}";


  public static String getReturnString(String formatString, Map<String, String> keyMap) {
    try {
      for (Entry<String, String> keyEntry : keyMap.entrySet()) {
        String key = String.format(INJECT_KEY, keyEntry.getKey());
        formatString = formatString.replace(key, keyEntry.getValue());
      }
      if ("${".contains(formatString)) {
        throw new BaseException(Mesajlar.GNL_BEKLENMEYEN_HATA_OLUSTU);
      }

      return formatString;
    } catch (Exception e) {
      return null;
    }
  }
}
