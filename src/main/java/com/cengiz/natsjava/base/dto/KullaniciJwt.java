package com.cengiz.natsjava.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
public class KullaniciJwt implements Serializable {

  @JsonIgnore
  private UUID tokenUuid;
  private UUID uuid;


  public KullaniciJwt(KullaniciDto kullaniciDto) {
    this.uuid = kullaniciDto.getUuid();
  }
}
