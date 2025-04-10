package com.cengiz.natsjava.modules.kys.repository;


import com.cengiz.natsjava.modules.kys.data.entity.Kullanici;

import java.util.Optional;
import java.util.UUID;

public interface KullaniciRepository extends BaseJPARepository<Kullanici, Long> {

  Optional<Kullanici> findByKullaniciAdi(Long kullaniciAdi);
  Long findKullaniciAdiByUuid(UUID uuid);
}