package com.cengiz.natsjava.modules.genel.enums.lookup;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Roller {
  SISTEM_KULLANICISI(1, "Sistem Kullanıcısı"),
  KURUM_KULLANICISI(2, "Kurum Kullanıcısı"),
  SISTEM(99, "Sistem");


  private final int val;
  private final String valText;

  public static Roller of(int val) {
    return Arrays.stream(values()).filter(en -> en.val == val).findFirst().orElse(null);
  }

  public static Roller of(String valText) {
    return Arrays.stream(values()).filter(en -> en.valText.equals(valText)).findFirst().orElse(null);
  }

}
