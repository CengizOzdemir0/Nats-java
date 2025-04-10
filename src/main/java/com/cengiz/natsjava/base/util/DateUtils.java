package com.cengiz.natsjava.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  private static final String TIME_FORMAT = "HH:mm";
  private static final String DATE_FORMAT = "dd.MM.yyyy";
  private static String dateFormatMonthName = "dd MMMM yyyy";
  private static String dateFormatWithDay = "dd.MM.yyyy EEEE";
  private static String dayFormat = "EEEE";
  public static final String DATE_TIME_FORMAT_WITHOUT_SECOND = "dd.MM.yyyy HH:mm";
  private static String dateTimeFormatWithoutSecondWithDay = "dd.MM.yyyy HH:mm EEEE";
  private static String dateTimeFormatWithSecond = "dd.MM.yyyy HH:mm:ss";
  private static String dateTimestamp = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static String dateTimestampWithoutZone = "yyyy-MM-dd'T'HH:mm:ss";
  private static DateTimeFormatter dateTimestampFormatter = DateTimeFormatter.ofPattern(dateTimestamp);
  private static final DateTimeFormatter DATE_TIMESTAMP_FORMATTER_WITHOUT_ZONE = DateTimeFormatter.ofPattern(dateTimestampWithoutZone);

  private static String dateFormat2 = "dd/MM/yyyy";
  private static String dateFormat3 = "yyyy-MM-dd";

  private static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
  private static String dateTimeFormatStr = "yyyyMMddHHmmss";

  private static DateFormat simpleDateFormat = null;

  public static DateFormat getSimpleDateFormat() {
    if (simpleDateFormat == null) {
      simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    }
    return simpleDateFormat;
  }

  public static DateFormat getSimpleDateFormat2() {
    if (simpleDateFormat2 == null) {
      simpleDateFormat2 = new SimpleDateFormat(dateFormat2);
    }
    return simpleDateFormat2;
  }

  public static DateFormat getSimpleDateTimeFormat() {
    if (simpleDateTimeFormat == null) {
      simpleDateTimeFormat = new SimpleDateFormat(dateTimeFormat);
    }
    return simpleDateTimeFormat;
  }

  public static DateFormat getSimpleDateTimeFormatStr() {
    if (simpleDateTimeFormatStr == null) {
      simpleDateTimeFormatStr = new SimpleDateFormat(dateTimeFormatStr);
    }
    return simpleDateTimeFormatStr;
  }

  public static LocalDateTime milliSaniye2LocalDateTime(final long milisaniye) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(milisaniye), ZoneOffset.UTC);
  }

  private static DateFormat simpleDateFormat2 = null;
  private static DateFormat simpleDateTimeFormat = null;
  private static DateFormat simpleDateTimeFormatStr = null;

  private static final DateTimeFormatter dateTimeSimpleFormatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);

  public static DateTimeFormatter getDateTimeSimpleFormatDate() {
    return dateTimeSimpleFormatDate;
  }

  public static DateTimeFormatter getDateTimeSimpleFormatDate2() {
    return dateTimeSimpleFormatDate2;
  }

  public static DateTimeFormatter getDateTimeSimpleFormatDateTime() {
    return dateTimeSimpleFormatDateTime;
  }

  public static DateTimeFormatter getDateTimeSimpleFormatDateTimeStr() {
    return dateTimeSimpleFormatDateTimeStr;
  }

  public static DateTimeFormatter getDateTimeSimpleFormatTimeStr() {
    return dateTimeSimpleFormatTimeStr;
  }

  public static DateTimeFormatter getDateSimpleFormatStr() {
    return dateSimpleFormatStr;
  }

  private static DateTimeFormatter dateTimeSimpleFormatDate2 = DateTimeFormatter.ofPattern(dateFormat2);
  private static DateTimeFormatter dateTimeSimpleFormatDateTime = DateTimeFormatter.ofPattern(dateTimeFormat);
  private static DateTimeFormatter dateTimeSimpleFormatDateTimeWithSecond = DateTimeFormatter.ofPattern(dateTimeFormatWithSecond);
  private static DateTimeFormatter dateTimeSimpleFormatDateTimeStr = DateTimeFormatter.ofPattern(dateTimeFormatStr);
  private static DateTimeFormatter dateTimeSimpleFormatTimeStr = DateTimeFormatter.ofPattern(TIME_FORMAT);
  private static DateTimeFormatter dateSimpleFormatStr = DateTimeFormatter.ofPattern(dateFormat3);

  public static LocalDate getLocalDate(int gun, int ay, int yil) {
    return LocalDate.of(yil, ay, gun);
  }

  public static LocalDate getLocalDate(Date date) {
    if (date != null) {
      return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    return null;
  }

  public static Date getDate(int gun, int ay, int yil) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Turkey"));

    calendar.set(Calendar.YEAR, yil);
    calendar.set(Calendar.MONTH, ay);
    calendar.set(Calendar.DAY_OF_MONTH, gun);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  public static Instant getInstant(Timestamp timestamp) {
    if (timestamp != null) {
      return timestamp.toInstant();
    }
    return null;
  }

  public static LocalDateTime getLocalDateTime(Timestamp timestamp) {
    if (timestamp != null) {
      return timestamp.toLocalDateTime();
    }
    return null;
  }

  public static LocalDate getLocalDate(Timestamp timestamp) {
    if (timestamp != null) {
      return timestamp.toLocalDateTime().toLocalDate();
    }
    return null;
  }

  public static Instant getInstant(Date date) {
    if (date != null) {
      return date.toInstant();
    }
    return null;
  }

  public static String dateToStr(Date date) {
    return getSimpleDateFormat().format(date);
  }

  public static Date strToDate(String str) {
    try {
      return getSimpleDateFormat().parse(str);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * Girilen zaman verisini o günki tarihteki zaman olarak döner
   *
   * @param str
   * @return
   */
  public static LocalDateTime getTimeStrToLocaleDateTime(String str) {
    try {
      return LocalTime.parse(str, dateTimeSimpleFormatTimeStr).atDate(LocalDate.now());
    } catch (Exception e) {
      return null;
    }
  }

  public static LocalTime getTimeStrToLocaleTime(String str) {
    try {
      return LocalTime.parse(str, dateTimeSimpleFormatTimeStr);
    } catch (Exception e) {
      return null;
    }
  }

  public static LocalDate strToLocalDate(String str) {
    try {
      return LocalDate.parse(str, dateTimeSimpleFormatDate);
    } catch (Exception e) {
      return null;
    }
  }

  public static LocalDate strToLocalDateMsrsJsonDateFormat(String str) {
    try {
      return LocalDate.parse(str, dateSimpleFormatStr);
    } catch (Exception e) {
      return null;
    }
  }

  public static LocalDateTime strToLocalDateTime(String str) {
    try {
      return LocalDateTime.parse(str, dateTimeSimpleFormatDateTime);
    } catch (Exception e) {
      return null;
    }
  }

  /* dd.MM.yyyy HH:mm:ss */
  public static LocalDateTime strToLocalDateTimeWithSecond(String str) {
    try {
      return LocalDateTime.parse(str, dateTimeSimpleFormatDateTimeWithSecond);
    } catch (Exception e) {
      return null;
    }
  }

  public static Date strToDateTime(String str) {
    try {
      return getSimpleDateTimeFormat().parse(str);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * Tarih diger tarihten sonra ya da eşit mi kontrol eder
   *
   * @param date
   * @param when
   * @return
   */

  public static boolean isAfterOrEqual(Date date, Date when) {
    Calendar dateCal = Calendar.getInstance();
    dateCal.setTime(date);
    Calendar whenCal = Calendar.getInstance();
    whenCal.setTime(when);

    if ((whenCal.get(Calendar.YEAR) > dateCal.get(Calendar.YEAR))) {
      return false;
    } else if (whenCal.get(Calendar.YEAR) == dateCal.get(Calendar.YEAR)) {
      if (whenCal.get(Calendar.MONTH) > dateCal.get(Calendar.MONTH)) {
        return false;
      } else if (whenCal.get(Calendar.MONTH) == dateCal.get(Calendar.MONTH) && whenCal.get(Calendar.DAY_OF_MONTH) > dateCal.get(
          Calendar.DAY_OF_MONTH)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tarih diger tarihten sonra mı kontrol eder
   *
   * @param date
   * @param when
   * @return
   */
  public static boolean isAfter(Date date, Date when) {
    Calendar dateCal = Calendar.getInstance();
    dateCal.setTime(date);
    Calendar whenCal = Calendar.getInstance();
    whenCal.setTime(when);

    if ((whenCal.get(Calendar.YEAR) > dateCal.get(Calendar.YEAR))) {
      return false;
    } else if (whenCal.get(Calendar.YEAR) == dateCal.get(Calendar.YEAR)) {
      if (whenCal.get(Calendar.MONTH) > dateCal.get(Calendar.MONTH)) {
        return false;
      } else if (whenCal.get(Calendar.MONTH) == dateCal.get(Calendar.MONTH) && whenCal.get(Calendar.DAY_OF_MONTH) >= dateCal.get(
          Calendar.DAY_OF_MONTH)) {
        return false;
      }
    }
    return true;

  }

  /**
   * Tarih diger tarihten önce mi kontrol eder
   *
   * @param date
   * @param when
   * @return
   */
  public static boolean isBefore(Date date, Date when) {
    Calendar dateCal = Calendar.getInstance();
    dateCal.setTime(date);
    Calendar whenCal = Calendar.getInstance();
    whenCal.setTime(when);

    if ((whenCal.get(Calendar.YEAR) < dateCal.get(Calendar.YEAR))) {
      return false;
    } else if (whenCal.get(Calendar.YEAR) == dateCal.get(Calendar.YEAR)) {
      if (whenCal.get(Calendar.MONTH) < dateCal.get(Calendar.MONTH)) {
        return false;
      } else if (whenCal.get(Calendar.MONTH) == dateCal.get(Calendar.MONTH) && whenCal.get(Calendar.DAY_OF_MONTH) <= dateCal.get(
          Calendar.DAY_OF_MONTH)) {
        return false;
      }
    }
    return true;
  }

  public static String insDateToStr(Instant ins) {
    return DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault()).format(ins);
  }

  public static LocalDateTime timestampToLocalDateTime(String str) {
    return LocalDateTime.parse(str, dateTimestampFormatter);
  }

  public static LocalDateTime timestampToLocalDateTimeWithoutZone(String str) {
    return LocalDateTime.parse(str, DATE_TIMESTAMP_FORMATTER_WITHOUT_ZONE);
  }

  public static String insTimeToStr(Instant ins) {
    return DateTimeFormatter.ofPattern(TIME_FORMAT).withZone(ZoneId.systemDefault()).format(ins);
  }

  public static String getLocalDateToDateStr(LocalDate localDate) {
    return DateTimeFormatter.ofPattern(DATE_FORMAT).format(localDate);
  }

  public static String getLocalDateTimeToTimeStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(TIME_FORMAT).format(localDateTime);
  }

  public static String getLocalTimeToTimeStr(LocalTime localTime) {
    return DateTimeFormatter.ofPattern(TIME_FORMAT).format(localTime);
  }

  public static String getLocalDateTimeToDateMonthNameStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(dateFormatMonthName, LocaleUtils.getFromHeader()).format(localDateTime);
  }

  public static String getLocalDateTimeToDateStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(DATE_FORMAT).format(localDateTime);
  }

  public static String getLocalDateTimeToDateWithDayStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(dateFormatWithDay).format(localDateTime);
  }

  public static String getLocalDateTimeToDayStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(dayFormat, LocaleUtils.getFromHeader()).format(localDateTime);
  }

  public static String getLocalDateTimeToDateTimeWithoutSecondStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_WITHOUT_SECOND).format(localDateTime);
  }

  public static String getLocalDateTimeToDateTimeWithSecondStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(dateTimeFormatWithSecond).format(localDateTime);
  }


  public static String getLocalDateTimeToDateTimeWithDayStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(dateTimeFormatWithoutSecondWithDay, LocaleUtils.getFromHeader()).format(localDateTime);
  }

  public static String getLocalDateTimeToDateTimeStr(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern(dateTimeFormat).format(localDateTime);
  }

  /**
   * Get the days difference
   */
  public static long daysDiff(LocalDate earlierDate, LocalDate laterDate) {
    if (earlierDate == null || laterDate == null) {
      return 0;
    }
    //in milliseconds
    return Duration.between(laterDate.atStartOfDay(), earlierDate.atStartOfDay()).toDays();
  }


  /**
   * Get the days difference
   */
  public static long daysDiff(Instant earlierDate, Instant laterDate) {
    if (earlierDate == null || laterDate == null) {
      return 0;
    }
    LocalDate earlier = LocalDate.from(earlierDate);
    LocalDate later = LocalDate.from(laterDate);
    return Duration.between(later.atStartOfDay(), earlier.atStartOfDay()).toDays();
  }

  private static final int GUN = 1;
  private static final int HAFTA = 7;

  public static LocalDateTime strToLocalDateTimeTZ(String str) {
    LocalDateTime localDateTimeNew = null;
    if (str != null && !str.isEmpty()) {
      str = str.substring(0, 19) + "Z";
      localDateTimeNew = timestampToLocalDateTime(str);
    }
    return localDateTimeNew;
  }

  public static LocalDate strToLocalDateTZ(String str) {
    LocalDate localDateNew = null;
    String[] strs;
    if (str != null && !str.isEmpty()) {
      strs = str.split("T");

      localDateNew = LocalDate.parse((strs[0]));
    }
    return localDateNew;
  }
}