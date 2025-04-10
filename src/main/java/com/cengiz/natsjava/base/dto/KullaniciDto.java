package com.cengiz.natsjava.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;


import java.io.Serial;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class KullaniciDto extends BaseDto {

  @Serial
  private static final long serialVersionUID = -4545454545454545L;

  private UUID tokenUuid;
  private UUID uuid;
  private Long id;
  private String kullaniciAdi;
  private String adi;
  private String soyadi;
  private String browser;
  private String ip;
  private Long tcKimlikNo;


  @JsonIgnore
  private List<GrantedAuthority> yetkiList = new ArrayList<>();


  @Override
  public String toString() {
    return "uuid : " + uuid
        + ", id : " + id
        + ", kullaniciAdi : " + kullaniciAdi
        + ", adi : " + adi
        + ", soyadi : " + soyadi;
  }

  public String getAdSoyad() {
    return this.adi + " " + this.soyadi;
  }
}
