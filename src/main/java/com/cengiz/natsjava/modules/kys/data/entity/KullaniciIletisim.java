/**
 * Author: Ayşe Ok Date: 17.03.2024
 */

package com.cengiz.natsjava.modules.kys.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import com.cengiz.natsjava.base.util.annotation.BaseJsonDateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kullanici_iletisim", schema = "kys")
@Getter
@Setter
@ToString(exclude = "kullanici")
public class KullaniciIletisim implements Serializable {

  private static final long serialVersionUID = -1546644680447091619L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;


  @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  @JoinColumn(name = "fk_kullanici_id", referencedColumnName = "id")
  private Kullanici kullanici;

  @Column(name = "cep_telefonu")
  private Long cepTelefonu;

  @Column(name = "aktif")
  private boolean aktif;

  @BaseJsonDateTimeFormat
  @Column(name = "kayit_zamani")
  @CreationTimestamp
  private LocalDateTime kayitZamani;

  @Column(name = "kaydeden_fk_kullanici_id")
  private Long kaydedenFkKullaniciId;

  @PrePersist
  @PreUpdate
  public void prePersist(){
    this.kayitZamani = LocalDateTime.now();
  }

}
