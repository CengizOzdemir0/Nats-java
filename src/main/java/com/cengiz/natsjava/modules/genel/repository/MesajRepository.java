package com.cengiz.natsjava.modules.genel.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cengiz.natsjava.modules.genel.data.entity.Mesaj;
import com.cengiz.natsjava.modules.kys.repository.BaseJPARepository;;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */

public interface MesajRepository extends BaseJPARepository<Mesaj, Long> {

  @Query(value = """ 
      SELECT * FROM genel.mesajlar m where m.kodu = :kodu or m.adi = :adi """, nativeQuery = true)
  Mesaj findByKoduAndAdi(@Param("kodu") String kodu, @Param("adi") String adi);

  Mesaj findByAdi(String adi);

  void deleteByIdAndKodu(Long id, String kodu);

  boolean existsByKoduAndAdi(String kodu, String adi);

  boolean existsById(Integer id);
}
