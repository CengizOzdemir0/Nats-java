package com.cengiz.natsjava.modules.kys.data.mapper;

import com.cengiz.natsjava.modules.data.mapper.MapperBase;
import com.cengiz.natsjava.modules.kys.data.dto.KysKullaniciDto;
import com.cengiz.natsjava.modules.kys.data.entity.Kullanici;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface KullaniciMapper extends MapperBase<Kullanici, KysKullaniciDto> {

}
