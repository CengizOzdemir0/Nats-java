package com.cengiz.natsjava.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.cengiz.natsjava.base.dto.BaseDto;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.domain.RestResponse;

import java.io.IOException;

public class JwtHttp403RestAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
      throws IOException {
    RestResponse<BaseDto> restResponse = new RestResponse<>().createWithMesajEnum(Mesajlar.LGN_ERISIM_ENGELLENDI);
    httpServletResponse.setContentType(SecurityConstant.JSON_CHARSET_UTF_8_VALUE);
    httpServletResponse.setStatus(restResponse.getHttpStatus().getVal());
    httpServletResponse.getWriter().write(restResponse.toJson());
    httpServletResponse.getWriter().flush();
    httpServletResponse.getWriter().close();
  }
}
