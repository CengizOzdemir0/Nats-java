package com.cengiz.natsjava.modules.genel.enums.lookup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AktifPasif {
  AKTIF(true, "Aktif"),
  PASIF(false, "Pasif default");

  private Boolean val;
  private String valText;
  private static final String LOOKUP_NAME = "AKTIF_PASIF";

  AktifPasif(Boolean val, String valText) {
    this.val = val;
    this.valText = valText;
  }

  public static AktifPasif of(Boolean val) {
    return Arrays.stream(values()).filter(vt -> Objects.equals(vt.val, val)).findFirst().orElse(AKTIF);
  }

  public Boolean getVal() {
    return val;
  }
  public String getValText() {
      return this.valText;
  }

    @JsonCreator
  public static AktifPasif forValues(@JsonProperty("val") Boolean val) {
    return of(val);
  }
}

