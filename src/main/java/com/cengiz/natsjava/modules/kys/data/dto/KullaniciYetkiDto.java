package com.cengiz.natsjava.modules.kys.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cengiz.natsjava.base.dto.BaseDto;
import com.cengiz.natsjava.modules.kys.data.entity.Yetki;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciYetkiDto extends BaseDto{
  private Integer id;
  private KysKullaniciDto kullanici;
  private Yetki yetki;
  private boolean aktif = Boolean.TRUE;
  private LocalDateTime kayitZamani = LocalDateTime.now();
  private Long kaydedenFkKullaniciRolId;
}