package com.cengiz.natsjava.modules.kys.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cengiz.natsjava.base.dto.BaseDto;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciParolaDto extends BaseDto {
  private Integer id;
  private KullaniciRolDto kullaniciRol;
  private String parola;
  private boolean aktif = Boolean.TRUE;
  private LocalDateTime kayitZamani = LocalDateTime.now();
  private Long kaydedenFkKullaniciRolId;
}