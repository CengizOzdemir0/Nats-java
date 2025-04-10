package com.cengiz.natsjava.modules.kys.data.dto;

import com.cengiz.natsjava.base.dto.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "kullaniciIletisim")
public class KysKullaniciDto extends BaseDto {

  private Long id;
  private Long kullaniciAdi;
  @NotNull
  private String ad;
  @NotNull
  private String soyad;
  @NotNull
  private KullaniciIletisimDto kullaniciIletisim;
  private UUID uuid;
  private LocalDateTime kayitZamani;
  private Long kaydedenFkKullaniciId;
}