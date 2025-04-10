package com.cengiz.natsjava.config.domain;

import com.cengiz.natsjava.base.dto.KullaniciDto;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.exception.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KullaniciHelper {
  public static KullaniciDto getKullanici() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      log.error("Kullanici alınamadı, Authentication NULL!!!");
      throw new BaseException(Mesajlar.TKN_KULLANICI_BULUNAMADI);
    }

    Object principal = authentication.getPrincipal();
    if (principal == null) {
      log.error("Kullanici alınamadı, Principal NULL!!!");
      throw new BaseException(Mesajlar.TKN_KULLANICI_BULUNAMADI);
    }

    return (KullaniciDto) principal;
  }

  public static Long getKullaniciId() {
    return getKullanici().getId();
  }




  public static String getKullaniciAdi() {
    return getKullanici().getKullaniciAdi();
  }



  public static UUID getKullaniciUuid() {
    return getKullanici().getUuid();
  }

  public static boolean isYetkili(String yetkiAdi) {
    KullaniciDto kullanici = getKullanici();
    return isYetkili(yetkiAdi, kullanici.getYetkiList());
  }

  public static boolean isYetkili(String yetkiAdi, List<GrantedAuthority> yetkiList) {
    if (yetkiList == null || yetkiList.isEmpty()) {
      log.error("KullaniciIsYetkili alınamadı, yetkiList BOŞ!!!");
      return false;
    }
    for (GrantedAuthority yetki : yetkiList) {
      if (yetki.getAuthority().equalsIgnoreCase(yetkiAdi)) {
        return true;
      }
    }
    return false;
  }

  public static boolean getTokenGecerliMi() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return false;
    }
    Object principal = authentication.getPrincipal();
    return (principal != null);
  }
}
