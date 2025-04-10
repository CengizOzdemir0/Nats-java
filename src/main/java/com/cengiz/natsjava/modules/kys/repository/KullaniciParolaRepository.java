package com.cengiz.natsjava.modules.kys.repository;

import com.cengiz.natsjava.modules.kys.data.entity.KullaniciParola;
import com.cengiz.natsjava.modules.kys.repository.BaseJPARepository;


public interface KullaniciParolaRepository extends BaseJPARepository<KullaniciParola, Long> {

    KullaniciParola findByKullaniciRol_Id(Long kullaniciId);

}