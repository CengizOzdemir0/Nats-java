package com.cengiz.natsjava.modules.genel.data.dto;


import com.cengiz.natsjava.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MesajDto extends BaseDto {

  private static final long serialVersionUID = 1730195347286308344L;
  private Long id;
  private Integer lhttpStatusId;
  private String adi;
  private String kodu;
  private String mesaj;
  private Integer lseviyeTipi;
  private Boolean aktif;
  private LocalDateTime kayitZamani;
  private Long kaydedenFkKullaniciRolId;


}
