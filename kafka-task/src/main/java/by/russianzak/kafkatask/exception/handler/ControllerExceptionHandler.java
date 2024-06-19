package by.russianzak.kafkatask.exception.handler;

import by.russianzak.kafkatask.exception.TimeOutException;
import by.russianzak.kafkatask.exception.WebError;
import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ControllerExceptionHandler {
  @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
  @ExceptionHandler(TimeOutException.class)
  public ResponseEntity<WebError> handleTimeOutException(TimeOutException ex) {
    return new ResponseEntity<>(new WebError(HttpStatus.GATEWAY_TIMEOUT.value(), ex.getMessage()), HttpStatus.GATEWAY_TIMEOUT);
  }
}

