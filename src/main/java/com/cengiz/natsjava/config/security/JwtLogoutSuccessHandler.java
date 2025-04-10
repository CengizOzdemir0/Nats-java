package com.cengiz.natsjava.config.security;

import com.cengiz.natsjava.config.redis.JwtCacheService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.cengiz.natsjava.base.dto.BaseDto;
import com.cengiz.natsjava.base.dto.KullaniciJwt;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.domain.RestResponse;
import com.cengiz.natsjava.config.exception.BaseException;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtCacheService jwtCacheService;


  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    String token = jwtTokenProvider.getJwtFromRequest(request);
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    if (Objects.nonNull(token)) {
      try {
        KullaniciJwt kullaniciJwt = jwtTokenProvider.getKullaniciJwtFromToken(token);
        jwtCacheService.deleteJwt(kullaniciJwt.getUuid());
        restResponse.createWithMesajEnum(Mesajlar.LGN_CIKIS_BASARILI);
      } catch (BaseException e) {
        restResponse.createWithMesajEnum(Mesajlar.LGN_CIKIS_BASARISIZ);
      }
    } else {
      restResponse.createWithMesajEnum(Mesajlar.LGN_CIKIS_BASARISIZ);
    }

    response.setContentType(SecurityConstant.JSON_CHARSET_UTF_8_VALUE);
    response.setStatus(restResponse.getHttpStatus().getVal());
    response.getWriter().write(restResponse.toJson());
    response.getWriter().flush();
    response.getWriter().close();
  }
}
