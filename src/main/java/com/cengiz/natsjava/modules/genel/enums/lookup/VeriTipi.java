package com.cengiz.natsjava.modules.genel.enums.lookup;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */



@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum VeriTipi {
  VARCHAR(1, "VARCHAR"),
  BIGINT(2, "BIGINT"),
  INTEGER(3, "INTEGER"),
  BOOLEAN(4, "BOOLEAN"),
  BYTEA(5, "BYTEA"),
  TEXT(6, "TEXT"),
  SERIAL(7, "SERIAL"),
  CHAR(8, "CHAR"),
  DATE(9, "DATE"),
  TIME(10, "TIME"),
  TIMESTAMP(11, "TIMESTAMP"),
  SMALLINT(12, "SMALLINT"),
  UUID(13, "UUID"),
  MONEY(14, "MONEY"),
  BIGSERIAL(15, "BIGSERIAL"),
  PASSWORD(16, "PASSWORD");


  private final int val;
  private final String valText;

  public static VeriTipi of(int val) {
    return Arrays.stream(values()).filter(vt -> vt.val == val).findFirst().orElse(VARCHAR);
  }
}
