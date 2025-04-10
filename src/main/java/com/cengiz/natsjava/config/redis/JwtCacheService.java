package com.cengiz.natsjava.config.redis;


import com.cengiz.natsjava.base.dto.KullaniciDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
@CacheConfig(cacheNames = Cache.JWT_TOKENS)
public class JwtCacheService {

  @CachePut(key = "#key")
  public KullaniciDto putJwt(UUID key, KullaniciDto kullaniciDto) {
    log.debug("CachePut CacheName : JWT_TOKENS -> {}", kullaniciDto.toString());
    return kullaniciDto;
  }

  @Cacheable(key = "#key")
  public KullaniciDto getJwt(UUID key) {
    log.debug("Cacheable CacheName : JWT_TOKENS  -> {}", key);
    return null;
  }

  @CacheEvict(key = "#key")
  public void deleteJwt(UUID key) {
    log.debug("CacheEvict CacheName : JWT_TOKENS  -> {}", key);
  }
}
