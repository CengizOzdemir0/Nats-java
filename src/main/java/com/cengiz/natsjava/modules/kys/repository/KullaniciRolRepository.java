package com.cengiz.natsjava.modules.kys.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciRol;
import com.cengiz.natsjava.modules.kys.repository.BaseJPARepository;

import java.util.List;
import java.util.Optional;

public interface KullaniciRolRepository extends BaseJPARepository<KullaniciRol, Long> {

    Optional<List<KullaniciRol>> findByKullanici_KullaniciAdi(Long kullaniciAdi);

    List<KullaniciRol> findAllByKullanici_Id(Long fkKullaniciId);

    List<KullaniciRol> findAllByIdIn(List<Long> rolIdList);


    Optional<KullaniciRol> findByKullanici_KullaniciAdiAndLrol(@NonNull Long kullaniciAdi, @NonNull Integer lRol);

    Optional<List<KullaniciRol>> findByKullanici_KullaniciAdiAndAktifTrue(@NonNull Long kullaniciAdi);





    Optional<KullaniciRol> findByKullaniciKullaniciAdiAndLrol(Long kullaniciAdi, Integer lRol);


}