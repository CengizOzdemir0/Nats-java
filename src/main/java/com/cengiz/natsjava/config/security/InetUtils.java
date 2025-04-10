package com.cengiz.natsjava.config.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;

@Slf4j
public class InetUtils {

  private InetUtils() {
    throw new IllegalStateException("InetUtils class");
  }

  public static final String HTTP_BROWSER_INFO_HEADER = "user-agent";
  private static final String[] IP_HEADER_CANDIDATES = {"x-original-forwarded-for", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
      "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA",
      "REMOTE_ADDR"};

  public static String getClientIpAddress() {
    ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (reqAttr == null){
      return "0.0.0.0";
    }
    HttpServletRequest request = reqAttr.getRequest();
    return getClientIpAddress(request);
  }

  public static String getClientBrowserInfo() {
    ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (reqAttr == null) {
      return null;
    }
    HttpServletRequest request = reqAttr.getRequest();
    return request.getHeader(HTTP_BROWSER_INFO_HEADER);
  }

  public static String getHeaderValue(HttpServletRequest request, String headerName) {
    String value = null;
    if (request != null) {
      value = request.getHeader(headerName);
    }
    return value;
  }

  public static String getHostName() {
    String hostname = "";
    try {
      hostname = InetAddress.getLocalHost().getHostName();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return hostname;
  }

  public static String getClientIpAddress(HttpServletRequest request) {
    for (String header : IP_HEADER_CANDIDATES) {
      String ipList = request.getHeader(header);
      if (ipList != null && !ipList.isEmpty() && !"unknown".equalsIgnoreCase(ipList)) {
        return ipList.split(",")[0];
      }
    }
    return request.getRemoteAddr();
  }

  public static String getClientBrowserInfo(HttpServletRequest request) {
    String browserInfo = null;
    if (request != null) {
      browserInfo = request.getHeader(HTTP_BROWSER_INFO_HEADER);
    }
    return browserInfo;
  }

  public static String getLang(HttpServletRequest request) {
    String lang = "tr";
    if (request != null) {
      lang = request.getHeader("Accept-Language");
    }
    return lang;
  }

  public static String getUri(HttpServletRequest request) {
    try {
      return request.getRequestURI();
    } catch (Exception e) {
      return "";
    }
  }

  public static String getUri(WebRequest request) {
    try {
      return ((ServletWebRequest) request).getRequest().getRequestURI();
    } catch (Exception e) {
      return "";
    }
  }
}
