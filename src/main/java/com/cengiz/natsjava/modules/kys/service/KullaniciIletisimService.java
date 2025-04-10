/**
 * Author: Ayşe Ok Date: 18.03.2024
 */

package com.cengiz.natsjava.modules.kys.service;



import com.cengiz.natsjava.modules.kys.data.dto.KullaniciIletisimDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciIletisim;

import java.util.Optional;

public interface KullaniciIletisimService {
  KullaniciIletisim save(KullaniciIletisim kullaniciIletisim);

  Optional<KullaniciIletisimDto> findByKullanici_IdAndAktifTrue(Long kullaniciId);

  KullaniciIletisim findByKullanici_IdAndAktifTrueEntity(Long kullaniciId);

}
