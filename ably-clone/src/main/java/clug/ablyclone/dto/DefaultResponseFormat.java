package clug.ablyclone.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class DefaultResponseFormat {

  private final Boolean success;
  private final String msg;
}
