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
@Table(name = "kullanici_parola", schema = "kys")
@Data
public class KullaniciParola implements Serializable {
  private static final long serialVersionUID = -7514416657323734615L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_kullanici_rol_id")
  private KullaniciRol kullaniciRol;

  @Column(name = "parola")
  private String parola;

  @Column(name = "aktif")
  private boolean aktif;

  @BaseJsonDateTimeFormat
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;

  @Column(name = "kaydeden_fk_kullanici_rol_id")
  private Long kaydedenFkKullaniciRolId;

}
