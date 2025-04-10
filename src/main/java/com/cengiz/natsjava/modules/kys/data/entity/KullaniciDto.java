package com.cengiz.natsjava.modules.kys.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class KullaniciDto implements Serializable {
    private final Long id;
    private final Long kullaniciAdi;
    private final String ad;
    private final String soyad;
    private final UUID uuid;
    private final LocalDateTime kayitZamani;
    private final Integer kaydedenFkKullaniciId;
}
