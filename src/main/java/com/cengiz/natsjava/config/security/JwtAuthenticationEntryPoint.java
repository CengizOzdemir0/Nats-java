package com.cengiz.natsjava.config.security;

import com.cengiz.natsjava.base.dto.BaseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.domain.RestResponse;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    log.debug("Responding with unauthorized error. Message - {}", authException.getMessage());
    RestResponse<BaseDto> restResponse = new RestResponse<>().createWithMesajEnum(Mesajlar.LGN_OTURUM_SONLANMIS_TEKRAR_GIRIS_YAPINIZ);
    response.setContentType(SecurityConstant.JSON_CHARSET_UTF_8_VALUE);
    response.setStatus(restResponse.getHttpStatus().getVal());
    response.getWriter().write(restResponse.toJson());
    response.getWriter().flush();
    response.getWriter().close();
  }
}
