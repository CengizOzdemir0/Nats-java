/**
 * Author: Ayşe Ok Date: 17.03.2024
 */

package com.cengiz.natsjava.modules.kys.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.cengiz.natsjava.base.dto.BaseDto;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "kullanici")
public class KullaniciIletisimDto extends BaseDto {

  private static final long serialVersionUID = -5216870669183235171L;
  private Integer id;
  @JsonIgnore
  private KysKullaniciDto kullanici;
  private Long cepTelefonu;
  private boolean aktif;
  private Long kaydedenFkKullaniciId;
  private LocalDateTime kayitZamani;

}
