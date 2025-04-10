package com.cengiz.natsjava.modules.genel.enums.lookup;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.exception.BaseException;

import java.util.Arrays;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */

@AllArgsConstructor
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum IslemTipi {

  EKLEME(1, "Ekleme", "ADD"),
  SILME(2, "Silme", "DEL"),
  GUNCELLEME(3, "Güncelleme", "MOD");

  private final int val;
  private final String valText;
  private String revisionType;

  public static IslemTipi of(int val) {
    return Arrays.stream(values()).filter(vt -> vt.val == val).findFirst().orElse(EKLEME);
  }

  public static IslemTipi of(String revisionType) {
    return Arrays.stream(values()).filter(islemTipi -> islemTipi.revisionType.equals(revisionType)).findFirst().orElseThrow(() -> new BaseException(
        Mesajlar.VTN_KAYIT_BULUNAMADI));
  }

}