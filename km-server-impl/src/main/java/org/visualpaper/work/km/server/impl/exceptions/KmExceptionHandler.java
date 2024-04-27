package org.visualpaper.work.km.server.impl.exceptions;

import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.visualpaper.work.km.server.impl.KmLogCode;
import org.visualpaper.work.km.server.impl.openapi.model.ErrorSchema;

@ControllerAdvice
public class KmExceptionHandler {

  private static final String LOG_PATTERN = "{} {}";
  private static final Logger LOGGER = LoggerFactory
      .getLogger(KmExceptionHandler.class);

  /**
   * Valid アノテーションでのエラー (API リクエストのバリデーションエラーなど) の場合にこちらの Handler に来る.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorSchema> openApiBadRequestException(
      @Nonnull Throwable cause
  ) {
    KmLogCode logCode = KmLogCode.BAD_REQUEST;

    // ロギングする。
    // ※ ここ以外でのロギングも log　ファイルに出力されるので、
    //    この場所での責務は例外が発生した際のロギングと見る。
    logging(logCode, cause);

    // エラーレスポンスを返却する。
    // ※ code と message を body に、HttpStatus をヘッダに置いている。
    // ※ エラーレスポンスとして見たときは取り急ぎ充分と見ている。
    return new ResponseEntity<>(
        new ErrorSchema()
            .code(logCode.getCode())
            .message(logCode.getMessage()),
        HttpStatus.BAD_REQUEST
    );
  }

  /**
   * API リクエスト対象が存在しない場合にこちらの Handler に来る.
   */
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorSchema> openApiNotFoundException(
      @Nonnull Throwable cause
  ) {
    KmLogCode logCode = KmLogCode.NOT_FOUND;

    // ロギングする。
    // ※ ここ以外でのロギングも log　ファイルに出力されるので、
    //    この場所での責務は例外が発生した際のロギングと見る。
    logging(logCode, cause);

    // エラーレスポンスを返却する。
    // ※ code と message を body に、HttpStatus をヘッダに置いている。
    // ※ エラーレスポンスとして見たときは取り急ぎ充分と見ている。
    return new ResponseEntity<>(
        new ErrorSchema()
            .code(logCode.getCode())
            .message(logCode.getMessage()),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorSchema> anotherException(
      @Nonnull Throwable cause
  ) {
    KmLogCode logCode = resolveLogCode(cause);
    HttpStatus status = resolveStatus(cause);

    // ロギングする。
    // ※ ここ以外でのロギングも log　ファイルに出力されるので、
    //    この場所での責務は例外が発生した際のロギングと見る。
    logging(logCode, cause);

    // エラーレスポンスを返却する。
    // ※ code と message を body に、HttpStatus をヘッダに置いている。
    // ※ エラーレスポンスとして見たときは取り急ぎ充分と見ている。
    return new ResponseEntity<>(
        new ErrorSchema()
            .code(logCode.getCode())
            .message(logCode.getMessage()),
        status
    );
  }

  @Nonnull
  private KmLogCode resolveLogCode(@Nonnull Throwable cause) {

    if (cause instanceof KmException) {
      return ((KmException) cause).getLogCode();
    } else if (cause instanceof KmRuntimeException) {
      return ((KmRuntimeException) cause).getLogCode();
    } else {
      return KmLogCode.UNEXPECTED;
    }
  }

  @Nonnull
  private HttpStatus resolveStatus(@Nonnull Throwable cause) {
    if (cause instanceof KmException) {
      return ((KmException) cause).getStatus();
    } else if (cause instanceof KmRuntimeException) {
      return ((KmRuntimeException) cause).getStatus();
    } else {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

  private void logging(
      @Nonnull KmLogCode logCode,
      @Nonnull Throwable cause
  ) {

    switch (logCode.getLogLevel()) {
      case ERROR:
        LOGGER.error(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
      case WARN:
        LOGGER.warn(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
      case INFO:
        LOGGER.info(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
      case DEBUG:
        LOGGER.debug(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
    }
  }
}
