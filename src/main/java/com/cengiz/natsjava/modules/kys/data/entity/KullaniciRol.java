/**
 * Author: Ayşe Ok Date: 7.03.2024
 */

package com.cengiz.natsjava.modules.kys.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.cengiz.natsjava.base.util.annotation.BaseJsonDateTimeFormat;
import com.cengiz.natsjava.modules.genel.enums.lookup.Roller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "kullanici_rol", schema = "kys")
@Data
public class KullaniciRol implements Serializable {

  private static final long serialVersionUID = 1907265274384646695L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "fk_kullanici_id")
  private Kullanici kullanici;

  @Column(name = "l_rol")
  private Integer lrol;

  @Transient
  private Roller rol;

  @Column(name = "aktif")
  private boolean aktif;

  @BaseJsonDateTimeFormat
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;

  @Column(name = "kaydeden_fk_kullanici_rol_id")
  private Long kaydedenFkKullaniciRolId;

  @PrePersist
  @PreUpdate
  public void prePersist() {
    if (Objects.isNull(lrol) && Objects.nonNull(rol)) {
      this.lrol = rol.getVal();
    }
    this.kayitZamani = LocalDateTime.now();
  }

  @PostLoad
  public void postLoad() {
    if (Objects.nonNull(lrol)) {
      this.rol = Roller.of(lrol);
    }

  }

}
