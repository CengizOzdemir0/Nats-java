package com.cengiz.natsjava.modules.kys.service;


import com.cengiz.natsjava.modules.kys.data.dto.KullaniciParolaDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciParola;

public interface KullaniciParolaService {

    KullaniciParolaDto getById(Long id);

    void deleteById(Long id);

    KullaniciParola findByKullaniciRolId(Long kullaniciId);

}
