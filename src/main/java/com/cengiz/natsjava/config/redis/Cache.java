package com.cengiz.natsjava.config.redis;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Cache {


  public static final String JWT_TOKENS = "JWT_TOKENS";
  public static final String JITSI_EVENTS = "JITSI_EVENTS";
  public static final String JITSI_MODERATOR_ROOM = "JITSI_MODERATOR_ROOM";
  public static final String PARAMETRELER = "PARAMETRELER";
  public static final String MESAJLAR = "MESAJLAR";
}
