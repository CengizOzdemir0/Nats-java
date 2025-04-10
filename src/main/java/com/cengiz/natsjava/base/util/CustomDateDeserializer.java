package com.cengiz.natsjava.base.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * yyyy-MM-dd formatında gelen json veriyi instant a çevirir.Not: time bilgisi 00:00:00 ele alınır.
 *

 */

public class CustomDateDeserializer extends JsonDeserializer<LocalDate> {

  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    try{
      return LocalDate.ofEpochDay(Integer.parseInt(p.getText()));
    }catch (Exception e){
      return LocalDate.parse(p.getText());
    }

  }
}
