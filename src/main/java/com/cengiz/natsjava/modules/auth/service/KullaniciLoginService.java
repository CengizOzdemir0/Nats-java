package com.cengiz.natsjava.modules.auth.service;


import com.cengiz.natsjava.base.dto.KullaniciDto;
import com.cengiz.natsjava.base.dto.KullaniciYetki;
import com.cengiz.natsjava.base.dto.TokenDto;
import com.cengiz.natsjava.base.tipler.HttpStatus;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.base.util.CustomUUID;
import com.cengiz.natsjava.base.util.SHAEncryptor;
import com.cengiz.natsjava.config.domain.RestResponse;
import com.cengiz.natsjava.config.redis.JwtCacheService;
import com.cengiz.natsjava.config.security.JwtTokenProvider;
import com.cengiz.natsjava.modules.auth.dto.KullaniciLoginDto;
import com.cengiz.natsjava.modules.genel.enums.lookup.Roller;
import com.cengiz.natsjava.modules.kys.data.entity.Kullanici;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciParola;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciRol;
import com.cengiz.natsjava.modules.kys.data.entity.Yetki;
import com.cengiz.natsjava.modules.kys.service.KullaniciParolaService;
import com.cengiz.natsjava.modules.kys.service.KullaniciRolService;
import com.cengiz.natsjava.modules.kys.service.KullaniciService;
import com.cengiz.natsjava.modules.kys.service.YetkiService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KullaniciLoginService {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtCacheService jwtCacheService;
    private final KullaniciService kullaniciService;
    private final KullaniciRolService kullaniciRolService;
    private final KullaniciParolaService kullaniciParolaService;
    private final YetkiService yetkiService;


    public RestResponse<TokenDto> login(KullaniciLoginDto dto) {
        RestResponse<TokenDto> response = new RestResponse<>();
        if (dto != null) {
            if (dto.getKullaniciAdi() == null || (dto.getKullaniciAdi() != null && dto.getKullaniciAdi().toString().equals(""))) {
                return getRestResponseResponseEntity(Mesajlar.KLN_KULLANICI_ADI_BOS_OLAMAZ);
            }
            if (dto.getPassword() == null || (dto.getPassword() != null && dto.getPassword().isEmpty())) {
                return getRestResponseResponseEntity(Mesajlar.KLN_PAROLA_BOS_OLAMAZ);
            }
            Kullanici kullanici = kullaniciService.findByKullaniciAdi(Long.parseLong(dto.getKullaniciAdi().toString()));
            if (kullanici.getKullaniciAdi() == null) {
                return getRestResponseResponseEntity(Mesajlar.LGN_KULLANICI_BULUNAMADI);
            }

            List<KullaniciRol> kullaniciRolList = kullaniciRolService.findByKullaniciAdi(Long.parseLong(dto.getKullaniciAdi().toString()));
            boolean isAktif = kullaniciRolList.stream().anyMatch(KullaniciRol::isAktif);
            if (!isAktif) {
                return getRestResponseResponseEntity(Mesajlar.KLN_KULLANICI_ROL_YOK);
            }

            KullaniciRol kullaniciRol = kullaniciRolList.stream().filter(d -> d.getRol().getVal() == (Roller.KURUM_KULLANICISI.getVal())).findFirst()
                    .orElse(null);
            if (kullaniciRol == null) {
                return getRestResponseResponseEntity(Mesajlar.LGN_GIRIS_ROLU_BULUNMAMAKTADIR);
            }
            List<Yetki> yetkiAllList = new ArrayList<>();
            List<Yetki> yetkiDtoList = yetkiService.getKullaniciIdByYetki(kullanici.getId());
            List<Yetki> kullaniciYetkiList = yetkiService.getKullaniciIdByKullaniciYetki(kullanici.getId());


            if (!yetkiDtoList.isEmpty()) {
                yetkiAllList.addAll(yetkiDtoList);
            }
            if (!kullaniciYetkiList.isEmpty()) {
                yetkiAllList.addAll(kullaniciYetkiList);
            }
            KullaniciParola kullaniciParola = kullaniciParolaService.findByKullaniciRolId(kullaniciRol.getId());
            String encodeParola = SHAEncryptor.getInstance().getEncrypted(dto.getPassword());
            if (!kullaniciParola.getParola().equals(encodeParola)) {
                return getRestResponseResponseEntity(Mesajlar.LGN_KULLANICI_ADI_PAROLA_HATALI);
            }
            return getTokenDtoRestResponse(response, kullanici, yetkiAllList);
        }
        return null;
    }

    private RestResponse<TokenDto> getTokenDtoRestResponse(RestResponse<TokenDto> response, Kullanici kysKullaniciDto, List<Yetki> yetkiDtoList) {
        KullaniciDto kullaniciDto = new KullaniciDto();

        kullaniciDto.setKullaniciAdi(kysKullaniciDto.getKullaniciAdi().toString());
        kullaniciDto.setAdi(kysKullaniciDto.getAd());
        kullaniciDto.setSoyadi(kysKullaniciDto.getSoyad());
        kullaniciDto.setTokenUuid(CustomUUID.getUUID());
        kullaniciDto.setId(kysKullaniciDto.getId());
        kullaniciDto.setUuid(kysKullaniciDto.getUuid());
        kullaniciDto.setYetkiList(getGrantedAuthorities(yetkiDtoList));
        TokenDto tokenDto = jwtTokenProvider.generateToken(kullaniciDto);
        jwtCacheService.putJwt(kullaniciDto.getUuid(), kullaniciDto);
        response = response.createOkWithData(tokenDto);
        return response;
    }
    @NotNull
    private List<GrantedAuthority> getGrantedAuthorities(List<Yetki> yetkiDtoList) {
        return yetkiDtoList.stream()
                .map(yetki -> new KullaniciYetki(yetki.getYetkiAdi()))
                .distinct()
                .collect(Collectors.toList());
    }
    @NotNull
    private RestResponse<TokenDto> getRestResponseResponseEntity(Mesajlar mesajlar) {
        RestResponse<TokenDto> response = new RestResponse<>();
        response.setHttpStatus(HttpStatus.PRECONDITION_REQUERED);
        response.addMesaj(mesajlar);
        return response;
    }
    
}
