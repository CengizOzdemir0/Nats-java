package com.cengiz.natsjava.modules.auth.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;


@Getter
@Service
public class KullaniciLoginDto {

    public Long kullaniciAdi;

    public String password;

}
