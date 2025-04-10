package com.cengiz.natsjava.modules.genel.enums.lookup;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Diller {
  TURKCE(1, "Türkçe"),
  INGILIZCE(2, "İngilizce");


  private final int val;
  private final String valText;

  public static Diller of(int val) {
    return Arrays.stream(values()).filter(en -> en.val == val).findFirst().orElse(null);
  }

  public static Diller of(String valText) {
    return Arrays.stream(values()).filter(en -> en.valText.equals(valText)).findFirst().orElse(null);
  }

}
