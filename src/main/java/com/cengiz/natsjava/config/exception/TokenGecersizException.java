package com.cengiz.natsjava.config.exception;

import com.cengiz.natsjava.base.tipler.Mesajlar;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.Map;

@Getter
@AllArgsConstructor
public class TokenGecersizException extends RuntimeException {

  private final Mesajlar mesajlarEnum;
  private final transient Map<String, Object> mesajArgsMap;

  public TokenGecersizException(Mesajlar mesajlarEnum) {
    this(mesajlarEnum, null);
  }
}
