package com.cengiz.natsjava.modules.genel.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cengiz.natsjava.base.dto.wrapper.BooleanWrapper;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.base.tipler.VeriTipiUtils;
import com.cengiz.natsjava.config.exception.BaseException;
import com.cengiz.natsjava.config.redis.Cache;
import com.cengiz.natsjava.modules.genel.data.dto.ParametreDto;
import com.cengiz.natsjava.modules.genel.data.entity.Parametre;
import com.cengiz.natsjava.modules.genel.data.mapper.ParametreMapper;
import com.cengiz.natsjava.modules.genel.enums.Parametreler;
import com.cengiz.natsjava.modules.genel.enums.lookup.VeriTipi;
import com.cengiz.natsjava.modules.genel.repository.ParametreRepository;
import com.cengiz.natsjava.modules.genel.service.ParametreService;

import java.util.List;
import java.util.Optional;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */

@Service
@RequiredArgsConstructor
@Transactional
public class ParametreServiceImpl implements ParametreService {


  private final ParametreRepository parametreRepository;
  private final ParametreMapper parametreMapper;


  @Override
  @CacheEvict(value = Cache.PARAMETRELER, key = "#dto.adi")
  public ParametreDto save(ParametreDto dto) {
    boolean parametreKontrol = parametreRepository.existsByAdi(dto.getAdi());
    if (parametreKontrol) {
      throw new BaseException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    return parametreMapper.entityToDto(parametreRepository.save(parametreMapper.dtoToEntity(dto)));
  }

  @Override
  @CacheEvict(value = Cache.PARAMETRELER, key = "#dto.adi")
  public ParametreDto update(Integer id, ParametreDto dto) {
    if (!id.equals(dto.getId())) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    Optional<Parametre> parametre = parametreRepository.findById(dto.getId());
    if (parametre.isEmpty()) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    if (!parametre.get().getAdi().equals(dto.getAdi())) {
      Parametre parametreKontrol = parametreRepository.findByAdi(dto.getAdi());
      if (parametreKontrol != null) {
        throw new BaseException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
      }
    }
    if (dto.getAdi().equals(parametre.get().getAdi()) && dto.getAciklama().equals(parametre.get().getAciklama()) && dto.getAktif()
        .equals(parametre.get().getAktif())
        && dto.getDeger().equals(parametre.get().getDeger()) && dto.getLveriTipi().equals(parametre.get().getLveriTipi())) {
      throw new BaseException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    return parametreMapper.entityToDto(parametreRepository.save(parametreMapper.dtoToEntity(dto)));
  }

  @Override
  @CacheEvict(value = Cache.PARAMETRELER, key = "#dto.adi")
  public BooleanWrapper delete(Integer id, ParametreDto dto) {
    if (!id.equals(dto.getId())) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    boolean parametreKontrol = parametreRepository.existsById(dto.getId());
    if (!parametreKontrol) {
      throw new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    parametreRepository.deleteByIdAndAdi(dto.getId(), dto.getAdi());
    return new BooleanWrapper(true);
  }

  @Override
  @Cacheable(value = Cache.PARAMETRELER, key = "#parametreEnum.name()")
  public Object getParametre(Parametreler parametreEnum) {
    return getParametre(parametreEnum, null);
  }

  @Override
  @Cacheable(value = Cache.PARAMETRELER, key = "#parametreEnum")
  public Object getParametre(Parametreler parametreEnum, Object varsayilan) {
    Parametre parametre = parametreRepository.findByAdiAndAktifIsTrue(parametreEnum.name());
    if (parametre == null || parametre.getDeger() == null) {
      return varsayilan;
    }
    return VeriTipiUtils.getValue(VeriTipi.of(parametre.getLveriTipi()), parametre.getDeger());
  }

  @Override
  public List<ParametreDto> getAll() {
    return parametreMapper.entityToDto(parametreRepository.findAll());
  }

  @Override
  public ParametreDto getById(Integer id) {
    return parametreRepository.findById(id)
        .map(parametreMapper::entityToDto)
        .orElseThrow(() -> new BaseException(Mesajlar.VTN_KAYIT_BULUNAMADI));
  }

}
