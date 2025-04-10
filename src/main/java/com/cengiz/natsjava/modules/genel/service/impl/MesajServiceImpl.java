package com.cengiz.natsjava.modules.genel.service.impl;


import com.cengiz.natsjava.base.dto.wrapper.BooleanWrapper;
import com.cengiz.natsjava.modules.genel.service.MesajService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.exception.BaseException;
import com.cengiz.natsjava.config.redis.Cache;
import com.cengiz.natsjava.modules.genel.data.dto.MesajDto;
import com.cengiz.natsjava.modules.genel.data.entity.Mesaj;
import com.cengiz.natsjava.modules.genel.data.mapper.MesajMapper;
import com.cengiz.natsjava.modules.genel.repository.MesajRepository;


import java.util.List;
import java.util.Optional;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */


@Service
@RequiredArgsConstructor
@Transactional
public class MesajServiceImpl implements MesajService {

  private final MesajRepository mesajRepository;
  private final MesajMapper mesajMapper;


  @Override
  @CacheEvict(value = Cache.MESAJLAR, key = "#dto.adi")
  public MesajDto save(MesajDto dto) {
    boolean mesajKontrol = mesajRepository.existsByKoduAndAdi(dto.getKodu(), dto.getAdi());
    if (mesajKontrol) {
      throw new BaseException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    return mesajMapper.entityToDto(mesajRepository.save(mesajMapper.dtoToEntity(dto)));
  }

  @Override
  @CacheEvict(value = Cache.MESAJLAR, key = "#dto.adi")
  public MesajDto update(Long id, MesajDto dto) {
    if (!id.equals(dto.getId())) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    Optional<Mesaj> mesaj = mesajRepository.findById(dto.getId());
    if (mesaj.isEmpty()) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    if (!dto.getKodu().equals(mesaj.get().getKodu()) || !dto.getAdi().equals(mesaj.get().getAdi())) {
      Mesaj mesajKontrol = mesajRepository.findByKoduAndAdi(dto.getKodu(), dto.getAdi());
      if (mesajKontrol != null) {
        throw new BaseException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
      }
    }
    if (dto.getAktif().equals(mesaj.get().getAktif()) && dto.getMesaj().equals(mesaj.get().getMesaj())
        && dto.getLhttpStatusId().equals(mesaj.get().getLhttpStatusId()) && dto.getAdi().equals(mesaj.get().getAdi())
        && dto.getKodu().equals(mesaj.get().getKodu()) && dto.getLseviyeTipi().equals(mesaj.get().getLseviyeTipi())) {
      throw new BaseException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    return mesajMapper.entityToDto(mesajRepository.save(mesajMapper.dtoToEntity(dto)));

  }

  @Override
  @CacheEvict(value = Cache.MESAJLAR, key = "#dto.adi")
  public BooleanWrapper delete(Long id, MesajDto dto) {
    if (!id.equals(dto.getId())) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    boolean mesajKontrol = mesajRepository.existsById(dto.getId());
    if (!mesajKontrol) {
      throw new BaseException(Mesajlar.GNL_KAYIT_BULUNAMADI);
    }
    mesajRepository.deleteByIdAndKodu(dto.getId(), dto.getKodu());
    return new BooleanWrapper(true);
  }


  @Override
  @Cacheable(value = Cache.MESAJLAR, key = "#mesajlarEnum.name()")
  public MesajDto getMesajByMesajEnum(Mesajlar mesajlarEnum) {
    Mesaj mesaj = mesajRepository.findByAdi(mesajlarEnum.name());
    MesajDto mesajDto = new MesajDto();
    mesajDto.setLhttpStatusId(mesaj.getLhttpStatusId());
    mesajDto.setKodu(mesaj.getKodu());
    mesajDto.setMesaj(mesaj.getMesaj());
    mesajDto.setLseviyeTipi(mesaj.getLseviyeTipi());
    return mesajDto;
  }

  @Override
  public List<MesajDto> getAll() {
    return mesajMapper.entityToDto(mesajRepository.findAll());
  }
  @Override
  public MesajDto getById(Long id) {
    return mesajRepository.findById(id)
        .map(mesajMapper::entityToDto)
        .orElseThrow(()-> new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI));
  }

}
