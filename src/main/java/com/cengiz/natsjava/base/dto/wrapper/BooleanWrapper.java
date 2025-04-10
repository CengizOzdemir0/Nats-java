package com.cengiz.natsjava.base.dto.wrapper;

import com.cengiz.natsjava.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public final class BooleanWrapper extends BaseDto {

  private final Boolean value;
}
