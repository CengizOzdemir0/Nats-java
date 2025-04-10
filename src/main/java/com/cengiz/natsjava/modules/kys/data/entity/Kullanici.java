/**
 * Author: Ayşe Ok Date: 7.03.2024
 */

package com.cengiz.natsjava.modules.kys.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.cengiz.natsjava.base.util.annotation.BaseJsonDateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "kullanici", schema = "kys")
@Getter
@Setter
public class Kullanici implements Serializable {

  private static final long serialVersionUID = 5665812353762235884L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "kullanici_adi")
  private Long kullaniciAdi;

  @Column(name = "ad")
  private String ad;

  @Column(name = "soyad")
  private String soyad;

  @Column(name = "uuid")
  private UUID uuid;

  @BaseJsonDateTimeFormat
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;

  @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "kullanici")
  private KullaniciIletisim kullaniciIletisim;

  @Column(name = "kaydeden_fk_kullanici_id")
  private Long kaydedenFkKullaniciId;

  @PrePersist
  @PreUpdate
  public void prePersist(){
    this.kayitZamani = LocalDateTime.now();
  }
}
