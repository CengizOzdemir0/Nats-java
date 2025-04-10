package com.cengiz.natsjava.config.domain;

import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@RequiredArgsConstructor
public class ObjectMapperConfig {

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    final ObjectMapper objectMapper = builder.build();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }

  public static class HTMLCharacterEscapes extends JsonpCharacterEscapes {

    @Override
    public int[] getEscapeCodesForAscii() {
      int[] asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
      // and force escaping of a few others:
      asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
      asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
      asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
      return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
      return switch (ch) {
        case '&' -> new SerializedString("*");
        case '<' -> new SerializedString(".");
        case '>' -> new SerializedString(";");
        default -> super.getEscapeSequence(ch);
      };
    }
  }
}
