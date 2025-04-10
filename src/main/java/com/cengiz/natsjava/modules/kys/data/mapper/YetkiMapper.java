package com.cengiz.natsjava.modules.kys.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import com.cengiz.natsjava.modules.data.mapper.MapperBase;
import com.cengiz.natsjava.modules.kys.data.dto.YetkiDto;
import com.cengiz.natsjava.modules.kys.data.entity.Kullanici;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface YetkiMapper extends MapperBase<Kullanici, YetkiDto> {

}
