package org.visualpaper.work.km.server.impl;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Builder
@Getter
public class KmLogCode {

  /**
   * Request Related LogCode.
   */
  private static final String WEB_CASE_CODE = "WEBC";

  public static final KmLogCode BAD_REQUEST = KmLogCode.builder()
      .caseCode(WEB_CASE_CODE)
      .detailCode(0)
      .logLevel(LogLevel.INFO)
      .message("Bad Request")
      .build();

  public static final KmLogCode NOT_FOUND = KmLogCode.builder()
      .caseCode(WEB_CASE_CODE)
      .detailCode(1)
      .logLevel(LogLevel.INFO)
      .message("Not Found")
      .build();

  /**
   * Common Related LogCode.
   */
  private static final String BASE_CASE_CODE = "BASE";

  public static final KmLogCode UNEXPECTED = KmLogCode.builder()
      .caseCode(BASE_CASE_CODE)
      .detailCode(0)
      .logLevel(LogLevel.ERROR)
      .message("予期しない例外が発生しました")
      .build();

  public static final KmLogCode ILLEGAL_ARGUMENTS = KmLogCode
      .builder()
      .caseCode(BASE_CASE_CODE)
      .detailCode(1)
      .logLevel(LogLevel.ERROR)
      .message("引数が不正です")
      .build();

  private String caseCode;
  private int detailCode;
  private LogLevel logLevel;
  private String message;

  KmLogCode(
      @Nonnull String caseCode,
      int detailCode,
      @Nonnull LogLevel logLevel,
      @Nonnull String message
  ) {
    this.caseCode = caseCode;
    this.detailCode = detailCode;
    this.logLevel = logLevel;
    this.message = message;
  }

  @Nonnull
  public String getCode() {
    return caseCode + "-" + String.format("%04d", detailCode);
  }
}
