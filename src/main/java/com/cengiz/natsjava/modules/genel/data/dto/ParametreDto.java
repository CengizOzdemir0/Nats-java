package com.cengiz.natsjava.modules.genel.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.cengiz.natsjava.base.dto.BaseDto;

import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParametreDto extends BaseDto {

  private static final long serialVersionUID = 1730195347286308444L;

  private Integer id;
  private Integer lveriTipi;
  private String adi;
  private String deger;
  private String aciklama;
  private Boolean aktif;
  private LocalDateTime kayitZamani;
  private Long kaydedenFkKullaniciRolId;

}