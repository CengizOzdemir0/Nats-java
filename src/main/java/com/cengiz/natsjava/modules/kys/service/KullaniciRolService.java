package com.cengiz.natsjava.modules.kys.service;


import com.cengiz.natsjava.modules.kys.data.dto.KullaniciRolDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciRol;

import java.util.List;


public interface KullaniciRolService {


  KullaniciRolDto save(KullaniciRolDto kullaniciRolDto);
  List<KullaniciRol> findByKullaniciAdi(Long username);

  List<KullaniciRolDto> getAllByFkKullaniciId(Long fkKullaniciId);
}
