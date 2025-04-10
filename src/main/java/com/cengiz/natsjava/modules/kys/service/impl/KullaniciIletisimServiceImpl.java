

package com.cengiz.natsjava.modules.kys.service.impl;

import com.cengiz.natsjava.modules.kys.data.dto.KullaniciIletisimDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciIletisim;
import com.cengiz.natsjava.modules.kys.data.mapper.KullaniciIletisimMapper;
import com.cengiz.natsjava.modules.kys.repository.KullaniciIletisimRepository;
import com.cengiz.natsjava.modules.kys.service.KullaniciIletisimService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KullaniciIletisimServiceImpl implements KullaniciIletisimService {
  private final KullaniciIletisimRepository kullaniciIletisimRepository;
  private final KullaniciIletisimMapper kullaniciIletisimMapper;

  @Transactional
  @Override
  public KullaniciIletisim save(KullaniciIletisim kullaniciIletisim) {
     return kullaniciIletisimRepository.saveAndFlush(kullaniciIletisim);
  }

  @Override
  public Optional<KullaniciIletisimDto> findByKullanici_IdAndAktifTrue(Long kullaniciId) {
    return kullaniciIletisimRepository.findByKullanici_IdAndAktifTrue(kullaniciId)
        .map(kullaniciIletisimMapper::entityToDto);
  }

  @Override
  public KullaniciIletisim findByKullanici_IdAndAktifTrueEntity(Long kullaniciId) {
    return kullaniciIletisimRepository.findByKullanici_IdAndAktifTrue(kullaniciId).orElse(null);
  }


}
