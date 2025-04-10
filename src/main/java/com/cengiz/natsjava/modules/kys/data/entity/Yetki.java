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
@Table(name = "yetki", schema = "kys")
@Data
public class Yetki implements Serializable {
  private static final long serialVersionUID = 3897545740939225981L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "yetki_adi")
  private String yetkiAdi;

  @Column(name = "aciklama")
  private String aciklama;

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
