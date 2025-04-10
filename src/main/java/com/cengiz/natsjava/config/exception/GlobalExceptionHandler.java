package com.cengiz.natsjava.config.exception;

import com.cengiz.natsjava.base.dto.BaseDto;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.domain.ResponseHelper;
import com.cengiz.natsjava.config.domain.RestResponse;
import com.cengiz.natsjava.config.security.InetUtils;
import com.cengiz.natsjava.modules.genel.service.MesajService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.StringWriter;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  private final MesajService mesajService;

  /*@ExceptionHandler(FeignException.class)
  public ResponseEntity<RestResponse<BaseDto>> handleFeignStatusException(FeignException e, HttpServletResponse response, WebRequest webRequest) {
    logExp(e, webRequest);
    response.setStatus(e.status());
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    if (e.status() == 401 && e.responseBody().isPresent() && (e.getMessage().contains("LGN2001") || e.getMessage().contains("LGN1004"))) {
      MhrsLoginService loginService = BeanUtil.getBean(MhrsLoginService.class);
      loginService.removeToken();
      restResponse = restResponse.createWithMesajEnum(Mesajlar.TKN_KULLANICI_BULUNAMADI);
      return ResponseHelper.responseEntityFromResponse(restResponse);
    } else if (e.status() == 404 && e.responseBody().isPresent() && (e.getMessage().contains("GNL1005"))) {
      restResponse = restResponse.createWithMesajEnum(Mesajlar.GNL_KAYIT_BULUNAMADI);
      return ResponseHelper.responseEntityFromResponse(restResponse);
    }
    return ResponseHelper.responseEntityFromResponse(restResponse);
  }*/


  @ExceptionHandler(BaseException.class)
  ResponseEntity<RestResponse<BaseDto>> handleDefaultException(BaseException e, WebRequest webRequest) {
    logExp(e, webRequest);
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    if (Objects.nonNull(e.getMesajlarEnum())) {
      restResponse = restResponse.createWithMesajEnum(e.getMesajlarEnum());
    } else {
      restResponse = restResponse.createWithMesajEnum(Mesajlar.GNL_BEKLENMEYEN_HATA_OLUSTU);
    }
    return ResponseHelper.responseEntityFromResponse(restResponse);

  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestResponse<BaseDto>> handleException(Exception e, WebRequest webRequest) {
    logExp(e, webRequest);
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    restResponse = restResponse.createWithMesajEnum(Mesajlar.GNL_BEKLENMEYEN_HATA_OLUSTU);
    return ResponseHelper.responseEntityFromResponse(restResponse);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<RestResponse<BaseDto>> handleException(HttpMessageNotReadableException e, WebRequest webRequest) {
    logExp(e, webRequest);
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    restResponse = restResponse.createWithMesajEnum(Mesajlar.VLD_GECERSIZ_PAYLOAD);
    return ResponseHelper.responseEntityFromResponse(restResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<RestResponse<BaseDto>> handleException(MethodArgumentNotValidException e, WebRequest webRequest) {
    logExp(e, webRequest);
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      String defaultMessage = fieldError.getDefaultMessage();
      if (Objects.nonNull(defaultMessage) && !defaultMessage.isEmpty()) {
        restResponse.addMesaj(Mesajlar.VLD_GECERSIZ_KEY);
      }
    }
    return ResponseHelper.responseEntityFromResponse(restResponse);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<RestResponse<BaseDto>> handleAccessDeniedException(AccessDeniedException e, WebRequest webRequest) {
    logExp(e, webRequest);
    RestResponse<BaseDto> restResponse = new RestResponse<>();
    restResponse = restResponse.createWithMesajEnum(Mesajlar.YETKISI_YOKTUR);
    return ResponseHelper.responseEntityFromResponse(restResponse);
  }


  private void logExp(Exception ex, WebRequest request) {
    StringWriter writer = new StringWriter();
    writer.write(System.lineSeparator() + "REQUEST_URI : " + InetUtils.getUri(request) + System.lineSeparator());
    StackTraceElement el = ex.getStackTrace()[0];
    if (el != null) {
      writer.write("SINIF_ADI : " + el.getClassName() + System.lineSeparator());
      writer.write("SATIR_NO : " + el.getLineNumber() + System.lineSeparator());
      writer.write("METOD_ADI : " + el.getMethodName() + System.lineSeparator());
      writer.write("SINIF_DOSYA_ADI : " + el.getFileName() + System.lineSeparator());
    }
    log.error(writer.toString());
    if (log.isDebugEnabled()) {
      for (StackTraceElement el1 : ex.getStackTrace()) {
        log.debug(el1 + "\n");
      }
    }
  }

}