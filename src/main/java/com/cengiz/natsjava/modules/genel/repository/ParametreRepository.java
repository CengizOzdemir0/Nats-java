package com.cengiz.natsjava.modules.genel.repository;

import com.cengiz.natsjava.modules.genel.data.entity.Parametre;
import com.cengiz.natsjava.modules.kys.repository.BaseJPARepository;


/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */

public interface ParametreRepository extends BaseJPARepository<Parametre, Integer> {

  Parametre findByAdi(String adi);

  Parametre findByAdiAndAktifIsTrue(String adi);

  void deleteByIdAndAdi(Integer id, String adi);

  boolean existsById(Long id);

  boolean existsByAdi(String adi);

}
