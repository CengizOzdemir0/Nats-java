package com.cengiz.natsjava.modules.kys.data.mapper;

import org.mapstruct.Mapper;
import com.cengiz.natsjava.modules.data.mapper.MapperBase;
import com.cengiz.natsjava.modules.kys.data.dto.KullaniciRolDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciRol;

@Mapper
public interface KullaniciRolMapper extends MapperBase<KullaniciRol, KullaniciRolDto> {

}
