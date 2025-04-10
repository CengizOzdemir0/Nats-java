package com.cengiz.natsjava.modules.kys.repository;

import com.cengiz.natsjava.modules.kys.data.entity.Yetki;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface YetkiRepository extends BaseJPARepository<Yetki, Integer> {

  @Query(value = " SELECT y.* FROM kys.kullanici_rol kr " +
      " JOIN kys.rol_yetki ry ON kr.l_rol = ry.l_rol " +
      " JOIN kys.yetki y ON y.id = ry.fk_yetki_id " +
      " WHERE kr.fk_kullanici_id =:kullaniciId AND ry.aktif AND y.aktif AND kr.aktif ", nativeQuery = true)
  List<Yetki> getKullaniciIdByYetki(Long kullaniciId);

  @Query(value = " select y.* from kys.rol_yetki ry " +
      " inner join kys.yetki y on y.id = ry.fk_yetki_id and y.aktif is true " +
      " where ry.l_rol = :lRol ", nativeQuery = true)
  List<Yetki> getAllByLRol(Integer lRol);

  @Query(value = """
      SELECT y.* FROM kys.kullanici_yetki kr
      JOIN kys.yetki y ON y.id = kr.fk_yetki_id
      WHERE kr.fk_kullanici_id =:kullaniciId  AND y.aktif AND kr.aktif
      """, nativeQuery = true)
  List<Yetki> getKullaniciIdByKullaniciYetki(Long kullaniciId);
}