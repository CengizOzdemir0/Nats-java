package com.cengiz.natsjava.modules.genel.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.cengiz.natsjava.base.dto.BaseDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "parametre", schema = "genel")
@Getter
@Setter
public class Parametre extends BaseDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "l_veri_tipi")
  private Integer lveriTipi;

  @Column(name = "adi")
  private String adi;

  @Column(name = "deger")
  private String deger;

  @Column(name = "aciklama", length = 500)
  private String aciklama;

  @Column(name = "aktif")
  private Boolean aktif;

  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;

  @Column(name = "kaydeden_fk_kullanici_rol_id")
  private Long kaydedenFkKullaniciRolId;
}
