package com.cengiz.natsjava.base.dto;

import com.cengiz.natsjava.base.util.DateUtils;
import com.cengiz.natsjava.base.util.annotation.BaseJsonDateTimeFormat;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DateDto implements Serializable {

  public DateDto(LocalDateTime zaman) {
    this.date = zaman;
  }

  public DateDto() {

  }

  @BaseJsonDateTimeFormat
  private LocalDateTime date;

  public String getTarih() {
    if (date != null) {
      return DateUtils.getLocalDateTimeToDateStr(date);
    }
    return null;
  }

  public String getTarihAy() {
    if (date != null) {
      return DateUtils.getLocalDateTimeToDateMonthNameStr(date);
    }
    return null;
  }

  public String getGun() {
    if (date != null) {
      return DateUtils.getLocalDateTimeToDayStr(date);
    }
    return null;
  }

  public String getZaman() {
    if (date != null) {
      return DateUtils.getLocalDateTimeToDateTimeWithoutSecondStr(date);
    }
    return null;
  }

  public String getSaat() {
    if (date != null) {
      return DateUtils.getLocalDateTimeToTimeStr(date);
    }
    return null;
  }

}