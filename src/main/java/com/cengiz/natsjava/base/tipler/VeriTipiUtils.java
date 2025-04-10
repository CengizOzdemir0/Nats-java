package com.cengiz.natsjava.base.tipler;


import com.cengiz.natsjava.base.util.DateUtils;
import com.cengiz.natsjava.modules.genel.enums.lookup.VeriTipi;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */

public class VeriTipiUtils {

  private VeriTipiUtils() {
    throw new IllegalStateException("VeriTipiUtils class");
  }

  public static Object getValue(VeriTipi veriTipi, String value) {
    return switch (veriTipi) {
      case INTEGER, SMALLINT -> Integer.valueOf(value);
      case BOOLEAN -> value.equals("true");
      case TIME -> DateUtils.getTimeStrToLocaleTime(value);
      default -> value;
    };
  }
}