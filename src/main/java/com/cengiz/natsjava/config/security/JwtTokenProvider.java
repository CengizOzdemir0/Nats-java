package com.cengiz.natsjava.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.cengiz.natsjava.base.dto.KullaniciDto;
import com.cengiz.natsjava.base.dto.KullaniciJwt;
import com.cengiz.natsjava.base.dto.TokenDto;
import com.cengiz.natsjava.base.tipler.Mesajlar;
import com.cengiz.natsjava.config.exception.TokenGecersizException;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private Long expiration;

  private final ObjectMapper objectMapper;

  public TokenDto generateToken(KullaniciDto kullaniciDto) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);
    Map<String, Object> claims = new HashMap<>();
    claims.put(SecurityConstant.JWT_USER_KEY, new KullaniciJwt(kullaniciDto));

    String token = Jwts.builder()
        .setSubject(kullaniciDto.getTokenUuid().toString())
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .addClaims(claims)
        .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes()))
        .compact();
    TokenDto tokenDto = new TokenDto();
    tokenDto.setJwt(token);
    tokenDto.setUuid(kullaniciDto.getTokenUuid());
    tokenDto.setKullaniciUuid(kullaniciDto.getUuid());
    tokenDto.setKullaniciAdi(kullaniciDto.getAdi());
    tokenDto.setKullaniciSoyadi(kullaniciDto.getSoyadi());
    tokenDto.setTcKimlikNo(kullaniciDto.getTcKimlikNo());
    return tokenDto;
  }

  public KullaniciJwt getKullaniciJwtFromToken(String token) {
    try {
      Claims body = Jwts.parser()
          .setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
          .parseClaimsJws(token)
          .getBody();

      KullaniciJwt kullaniciJwt = objectMapper.convertValue(body.get(SecurityConstant.JWT_USER_KEY), KullaniciJwt.class);
      kullaniciJwt.setTokenUuid(UUID.fromString(body.getSubject()));

      return kullaniciJwt;
    } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
      log.error("Invalid JWT signature: {}", ex.getMessage());
      throw new TokenGecersizException(Mesajlar.LGN_TOKEN_GECERSIZ);
    }
  }

  public String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
