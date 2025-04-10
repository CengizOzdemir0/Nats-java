package com.cengiz.natsjava.modules.data.dto.genel;

import com.cengiz.natsjava.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreeMenuDto extends BaseDto {

  private Integer id;

  private String title;

  private String path;

  private String pathUrl;

  private String icon;

  @JsonInclude(Include.NON_EMPTY)
  private List<TreeMenuDto> children = new ArrayList<>();

  @JsonInclude(Include.NON_EMPTY)
  private List<String> yetkiList = new ArrayList<>();


}
