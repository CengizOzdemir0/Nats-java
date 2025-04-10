package com.cengiz.natsjava.modules.kys.service.impl;

import com.cengiz.natsjava.modules.kys.data.dto.KullaniciRolDto;
import com.cengiz.natsjava.modules.kys.data.entity.Kullanici;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciRol;

import com.cengiz.natsjava.modules.kys.data.mapper.KullaniciRolMapper;
import com.cengiz.natsjava.modules.kys.repository.KullaniciRolRepository;
import com.cengiz.natsjava.modules.kys.service.KullaniciRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class KullaniciRolServiceImpl implements KullaniciRolService {

  private final KullaniciRolRepository kullaniciRolRepository;
  private final KullaniciRolMapper kullaniciRolMapper;



  @Transactional
  @Override
  public KullaniciRolDto save(KullaniciRolDto kullaniciRolDto) {
    KullaniciRol kullaniciRol = kullaniciRolMapper.dtoToEntity(kullaniciRolDto);
    kullaniciRolRepository.save(kullaniciRol);
    return kullaniciRolMapper.entityToDto(kullaniciRol);
  }
  @Override
  public List<KullaniciRolDto> getAllByFkKullaniciId(Long fkKullaniciId) {
    return kullaniciRolMapper.entityToDto(kullaniciRolRepository.findAllByKullanici_Id(fkKullaniciId));
  }
  @Override
  public List<KullaniciRol> findByKullaniciAdi(Long kullaniciAdi) {
    return kullaniciRolRepository.findByKullanici_KullaniciAdi(kullaniciAdi)
            .orElse(new ArrayList<>());
  }

}
