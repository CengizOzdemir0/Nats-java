

package com.cengiz.natsjava.modules.kys.data.mapper;

import com.cengiz.natsjava.modules.data.mapper.MapperBase;
import com.cengiz.natsjava.modules.kys.data.dto.KullaniciIletisimDto;
import com.cengiz.natsjava.modules.kys.data.entity.KullaniciIletisim;
import org.mapstruct.Mapper;


@Mapper
public interface KullaniciIletisimMapper extends MapperBase<KullaniciIletisim, KullaniciIletisimDto> {

}
