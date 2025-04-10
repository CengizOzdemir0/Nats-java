package com.cengiz.natsjava.config.security;


public class SecurityConstant {

  private SecurityConstant() {
    throw new IllegalStateException("SecurityConstant class");
  }

  public static String[] getSwaggerurls() {
    return swaggerUrls;
  }


  public static final String JWT_USER_KEY = "user";
  public static final String VATANDAS_LOGIN = "/vatandas/auth/**";


  public static final String LOGOUT = "/logout";
  public static final String JSON_CHARSET_UTF_8_VALUE = "application/json;charset=UTF-8";


  //TODO kaldirilacak
  public static final String MESAJ = "/mesajlar/**";

  private static final String[] swaggerUrls = {"/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-ui/**", "/swagger-ui.html",};




}
