package com.cengiz.natsjava.config.security;


import com.cengiz.natsjava.config.redis.JwtCacheService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import redis.clients.jedis.exceptions.JedisException;
import com.cengiz.natsjava.base.dto.BaseDto;
import com.cengiz.natsjava.base.dto.KullaniciDto;
import com.cengiz.natsjava.base.dto.KullaniciJwt;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.domain.RestResponse;
import com.cengiz.natsjava.config.exception.TokenGecersizException;
import com.cengiz.natsjava.modules.kys.service.KullaniciRolService;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 private final KullaniciRolService kullaniciRolService;

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtCacheService jwtCacheService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = jwtTokenProvider.getJwtFromRequest(request);

      if (StringUtils.hasText(jwt)) {
        KullaniciJwt kullaniciJwt = jwtTokenProvider.getKullaniciJwtFromToken(jwt);

        KullaniciDto kullaniciDto = jwtCacheService.getJwt(kullaniciJwt.getUuid());
        if (Objects.isNull(kullaniciDto)) {
          writeToResponseMesaj(response, Mesajlar.LGN_TOKEN_GECERSIZ);
          return;
        }
        if (kullaniciDto.getTokenUuid().equals(kullaniciJwt.getTokenUuid())) {
          kullaniciDto.setIp(InetUtils.getClientIpAddress());
          // kullaniciDto.setKullaniciRolDtoList(kullaniciRolService.findByKullanici_KullaniciAdiAndAktifTrue(Long.valueOf(kullaniciDto.getKullaniciAdi())));

          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(kullaniciDto, kullaniciDto.getId(),
              kullaniciDto.getYetkiList());

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          writeToResponseMesaj(response, Mesajlar.LGN_BASKA_YERDEN_GIRIS_YAPILMIS);
          return;
        }
      }
    } catch (InsufficientAuthenticationException e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.LGN_GIRIS_BILGILERI_EKSIK);
      return;
    } catch (ExpiredJwtException | CredentialsExpiredException | NonceExpiredException | UnsupportedJwtException | MalformedJwtException |
             SignatureException | IllegalArgumentException e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.LGN_OTURUM_SONLANMIS_TEKRAR_GIRIS_YAPINIZ);
      return;
    } catch (UsernameNotFoundException e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.LGN_KULLANICI_BULUNAMADI);
      return;
    } catch (BadCredentialsException e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.LGN_KULLANICI_ADI_PAROLA_HATALI);
      return;
    } catch (JedisException e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.LGN_CACHE_OKUMA_YAZMA_HATASI);
      return;
    } catch (TokenGecersizException e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.LGN_TOKEN_GECERSIZ);
      return;
    } catch (Exception e) {
      log.error(e.getMessage());
      writeToResponseMesaj(response, Mesajlar.GNL_BEKLENMEYEN_HATA_OLUSTU);
      return;
    }

    filterChain.doFilter(request, response);
  }

  private void writeToResponseMesaj(HttpServletResponse res, Mesajlar mesajlar) throws IOException {
    RestResponse<BaseDto> restResponse = new RestResponse<>().createWithMesajEnum(mesajlar);
    res.setStatus(restResponse.getHttpStatus().getVal());
    res.setContentType(SecurityConstant.JSON_CHARSET_UTF_8_VALUE);
    res.getWriter().write(restResponse.toJson());
    res.getWriter().flush();
    res.getWriter().close();
  }

}
