package com.cengiz.natsjava.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestMesajDTO implements Serializable {

  private String kodu;
  private String mesaj;

}
