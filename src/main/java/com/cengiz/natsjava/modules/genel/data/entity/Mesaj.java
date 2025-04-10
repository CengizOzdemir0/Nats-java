package com.cengiz.natsjava.modules.genel.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.cengiz.natsjava.base.dto.BaseDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "mesaj", schema = "genel")
@Getter
@Setter
public class Mesaj extends BaseDto {

  private static final long serialVersionUID = 4543453545454545L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "l_http_status")
  private Integer lhttpStatusId;

  @Column(name = "adi", length = 150)
  private String adi;

  @Column(name = "kodu", length = 50)
  private String kodu;

  @Column(name = "mesaj", length = 1000)
  private String mesaj;

  @Column(name = "l_seviye_tipi")
  private Integer lseviyeTipi;

  @Column(name = "aktif")
  private Boolean aktif;

  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;

  @Column(name = "kaydeden_fk_kullanici_rol_id")
  private Long kaydedenFkKullaniciRolId;
}
