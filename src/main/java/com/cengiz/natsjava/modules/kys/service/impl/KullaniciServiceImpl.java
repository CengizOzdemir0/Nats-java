package com.cengiz.natsjava.modules.kys.service.impl;

import com.cengiz.natsjava.base.dto.wrapper.BooleanWrapper;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.domain.ResponseHelper;
import com.cengiz.natsjava.config.domain.RestResponse;
import com.cengiz.natsjava.modules.kys.data.dto.KysKullaniciDto;
import com.cengiz.natsjava.modules.kys.data.entity.Kullanici;
import com.cengiz.natsjava.modules.kys.data.mapper.KullaniciMapper;
import com.cengiz.natsjava.modules.kys.repository.KullaniciRepository;
import com.cengiz.natsjava.modules.kys.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

  private final KullaniciRepository kullaniciRepository;

  private final KullaniciMapper kullaniciMapper;


  @Override
  public ResponseEntity<RestResponse<KysKullaniciDto>> get(Long id) {
    return kullaniciRepository.findById(id)
        .map(kullanici -> ResponseHelper.responseEntityOkFromData(kullaniciMapper.entityToDto(kullanici)))
        .orElseGet(() -> ResponseHelper.responseEntityDataWithMesaj(Mesajlar.VTN_KAYIT_BULUNAMADI));
  }

  @Override
  public ResponseEntity<RestResponse<KysKullaniciDto>> getAll() {
    return ResponseHelper.responseEntityOkFromListData(kullaniciMapper.entityToDto(kullaniciRepository.findAll()));
  }

  @Transactional
  @Override
  public ResponseEntity<RestResponse<KysKullaniciDto>> ekle(KysKullaniciDto kullaniciDto) {
    Kullanici kullaniciEntity = kullaniciRepository.save(kullaniciMapper.dtoToEntity(kullaniciDto));
    kullaniciRepository.flush();
    return ResponseHelper.responseEntityOkFromData(kullaniciMapper.entityToDto(kullaniciEntity));
  }

  @Transactional
  @Override
  public ResponseEntity<RestResponse<KysKullaniciDto>> update(KysKullaniciDto kullaniciDto) {
    Optional<Kullanici> optionalKullanici = kullaniciRepository.findById(kullaniciDto.getId());
    if (optionalKullanici.isEmpty()){
      return ResponseHelper.responseEntityDataWithMesaj(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    Kullanici kullanici = optionalKullanici.get();
    kullaniciMapper.dtoToEntity(kullanici,kullaniciDto);
    return ResponseHelper.responseEntityOkFromData(kullaniciMapper.entityToDto(kullaniciRepository.save(kullanici)));
  }

  @Transactional
  @Override
  public ResponseEntity<RestResponse<BooleanWrapper>> delete(Long id) {
    boolean exists = kullaniciRepository.existsById(id);
    if(!exists){
      return ResponseHelper.responseEntityDataWithMesaj(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    kullaniciRepository.deleteById(id);
    return ResponseHelper.responseEntityOkFromData(new BooleanWrapper(Boolean.TRUE));
  }

  @Override
  public KysKullaniciDto findByKullaniciAdiDto(Long kullaniciAdi){
    return kullaniciRepository.findByKullaniciAdi(kullaniciAdi)
            .map(kullanici -> kullaniciMapper.entityToDto(kullanici))
            .orElse(null);
  }

  @Override
  public Kullanici findByKullaniciAdi(Long kullaniciAdi){
    return kullaniciRepository.findByKullaniciAdi(kullaniciAdi).orElse(new Kullanici());
  }

  @Override
  public Long findKullaniciAdiByUuid(UUID uuid){
    return kullaniciRepository.findKullaniciAdiByUuid(uuid);
  }

  @Override
  public Long insertOrUpdateKullanici(Kullanici kullanici) {
    if (kullanici == null || kullanici.getKullaniciAdi() == null) {
      return null;
    }
    Kullanici kysKullanici = kullaniciRepository.findByKullaniciAdi(kullanici.getKullaniciAdi()).orElse(null);

    boolean isKullaniciAktif = kysKullanici != null && kullanici.getAd().equals(kysKullanici.getAd()) &&
            kullanici.getSoyad().equals(kysKullanici.getSoyad());

    if(isKullaniciAktif){
      return kysKullanici.getId();
    }
    if(kysKullanici!=null){
    kullanici.setId(kysKullanici.getId());}

    return kullaniciRepository.save(kullanici).getId();

  }

}
