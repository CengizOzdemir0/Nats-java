package com.cengiz.natsjava.config.exception;

import com.cengiz.natsjava.base.tipler.Mesajlar;
import lombok.Getter;

import java.util.Map;

@Getter
public class BaseException extends RuntimeException {

  //TODO Mesajlar Redis'den alınana kadar eklenen mesajların enum' ada eklenmesi gerekecek.
  private final Mesajlar mesajlarEnum;
  private final transient Map<String, Object> mesajArgsMap;

  public BaseException(Mesajlar mesajlarEnum) {
    this(mesajlarEnum, null);
  }

  public BaseException(Mesajlar mesajlarEnum, Map<String, Object> mesajArgsMap) {
    this.mesajlarEnum = mesajlarEnum;
    this.mesajArgsMap = mesajArgsMap;
  }

}
