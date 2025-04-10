package com.cengiz.natsjava.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtil {

  public static boolean isEmpty(List<?> list) {
    return Objects.isNull(list) || list.isEmpty();
  }

  public static boolean isNotEmpty(List<?> list) {
    return Objects.nonNull(list) && !list.isEmpty();
  }
}
