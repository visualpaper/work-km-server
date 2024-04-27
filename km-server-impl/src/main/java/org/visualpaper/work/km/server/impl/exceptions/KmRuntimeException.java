package org.visualpaper.work.km.server.impl.exceptions;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.visualpaper.work.km.server.impl.KmLogCode;

/**
 * 基盤 Web 非検査例外.
 */
@Getter
public class KmRuntimeException extends RuntimeException {

  private final KmLogCode logCode;

  @NonNull
  private final HttpStatus status;

  public KmRuntimeException(
      @Nonnull KmLogCode logCode,
      @Nonnull HttpStatus status
  ) {
    this.logCode = logCode;
    this.status = status;
  }

  public KmRuntimeException(
      @Nonnull KmLogCode logCode,
      @Nonnull HttpStatus status,
      @Nullable Throwable cause
  ) {
    super(cause);
    this.logCode = logCode;
    this.status = status;
  }
}
