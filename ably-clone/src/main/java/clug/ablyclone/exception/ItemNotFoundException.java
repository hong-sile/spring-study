package clug.ablyclone.exception;

import clug.ablyclone.base.BaseException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends BaseException {

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
  private static final String MESSAGE = "Item을 찾을 수 없습니다.";

  public ItemNotFoundException() {
    super(STATUS, MESSAGE);
  }
}
