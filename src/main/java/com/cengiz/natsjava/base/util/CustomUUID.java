package com.cengiz.natsjava.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomUUID {

  public static UUID getUUID() {
    return UUID.randomUUID();
  }

}
