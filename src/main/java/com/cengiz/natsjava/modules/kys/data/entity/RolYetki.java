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
@Table(name = "rol_yetki", schema = "kys")
@Data

public class RolYetki implements Serializable {
  private static final long serialVersionUID = 5235048305928405016L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "l_rol")
  private Integer lRol;


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
