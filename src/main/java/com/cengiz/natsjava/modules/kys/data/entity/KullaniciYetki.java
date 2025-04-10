/**
 * Author: Ayşe Ok Date: 7.03.2024
 */

package com.cengiz.natsjava.modules.kys.data.entity;

import jakarta.persistence.*;
import lombok.Data;


import com.cengiz.natsjava.base.util.annotation.BaseJsonDateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kullanici_yetki", schema = "kys")
@Data

public class KullaniciYetki implements Serializable {
  private static final long serialVersionUID = -6052256385723934974L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_kullanici_id")
  private Kullanici kullanici;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_yetki_id")
  private Yetki yetki;

  @Column(name = "aktif")
  private boolean aktif = Boolean.TRUE;

  @BaseJsonDateTimeFormat
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;


  @Column(name = "kaydeden_fk_kullanici_rol_id")
  private Long kaydedenFkKullaniciRolId;

  @PrePersist
  @PreUpdate
  public void prePersist(){
    this.kayitZamani = LocalDateTime.now();
  }
}
