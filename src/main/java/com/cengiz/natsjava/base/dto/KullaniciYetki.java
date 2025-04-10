package com.cengiz.natsjava.base.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
public class KullaniciYetki implements GrantedAuthority {

  private final String yetki;

  @Override
  public String getAuthority() {
    return this.yetki;
  }

}
