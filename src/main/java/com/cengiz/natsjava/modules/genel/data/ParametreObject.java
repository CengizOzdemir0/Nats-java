package com.cengiz.natsjava.modules.genel.data;

import com.cengiz.natsjava.base.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ParametreObject extends BaseDto {
  Object value;
}
