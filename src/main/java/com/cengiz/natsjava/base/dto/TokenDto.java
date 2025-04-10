package com.cengiz.natsjava.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
public class TokenDto extends BaseDto {

  private UUID uuid;
  private String jwt;
  private UUID kullaniciUuid;
  private Long tcKimlikNo;
  private String kullaniciAdi;
  private String kullaniciSoyadi;

}
