package com.cengiz.natsjava.modules.kys.data.mapper;

import org.mapstruct.Mapper;
import com.cengiz.natsjava.modules.kys.data.dto.KullaniciParolaDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciParola;

@Mapper
public abstract class KullaniciParolaMapper {

    public KullaniciParola getKullaniciRol(KullaniciParolaDto kullaniciParolaDto){
    return getKullaniciParola_(kullaniciParolaDto);
    }

    public KullaniciParolaDto getKullaniciRolDto(KullaniciParola kullaniciParola){
        return getKullaniciParolaDto_(kullaniciParola);
    }

    protected abstract KullaniciParola getKullaniciParola_(KullaniciParolaDto kullaniciParolaDto);

    protected abstract KullaniciParolaDto getKullaniciParolaDto_(KullaniciParola kullaniciParola);


}
