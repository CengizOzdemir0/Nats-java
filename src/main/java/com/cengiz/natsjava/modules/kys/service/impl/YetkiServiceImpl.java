package com.cengiz.natsjava.modules.kys.service.impl;

import com.cengiz.natsjava.modules.kys.data.entity.Yetki;
import com.cengiz.natsjava.modules.kys.repository.YetkiRepository;
import com.cengiz.natsjava.modules.kys.service.YetkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class YetkiServiceImpl implements YetkiService {

    private final YetkiRepository yetkiRepository;

    @Override
    public List<Yetki> getKullaniciIdByYetki(Long kullaniciId){
        return yetkiRepository.getKullaniciIdByYetki(kullaniciId);
    }

    @Override
    public List<Yetki> getAllByLRol(Integer lRol) {
        return yetkiRepository.getAllByLRol(lRol);
    }

    @Override
    public List<Yetki> getKullaniciIdByKullaniciYetki(Long kullaniciId){
        return yetkiRepository.getKullaniciIdByKullaniciYetki(kullaniciId);
    }

}
