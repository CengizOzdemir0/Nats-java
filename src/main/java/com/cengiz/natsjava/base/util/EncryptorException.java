package com.cengiz.natsjava.base.util;

import lombok.Data;


@Data
public class EncryptorException extends RuntimeException {

  public EncryptorException(String message, Throwable cause) {
    super(message, cause);
  }

}
