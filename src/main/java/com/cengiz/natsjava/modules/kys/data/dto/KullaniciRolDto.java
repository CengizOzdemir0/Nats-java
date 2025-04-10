package com.cengiz.natsjava.modules.kys.data.dto;

import com.cengiz.natsjava.base.dto.BaseDto;
import com.cengiz.natsjava.modules.genel.enums.lookup.Roller;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciRolDto extends BaseDto {

  private Long id;
  @NotNull
  private KysKullaniciDto kullanici;
  private Integer lrol;
  private Roller rol;
  private boolean aktif = Boolean.TRUE;
  private LocalDateTime kayitZamani = LocalDateTime.now();
  private Long kaydedenFkKullaniciRolId;
}