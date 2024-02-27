package clug.ablyclone.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ItemPreviewResponses extends DefaultResponseFormat {

  private static final String MSG = "아이템 목록 조회 성공";

  private final List<ItemPreviewResponse> data;

  private ItemPreviewResponses() {
    this(null);
  }

  public ItemPreviewResponses(final List<ItemPreviewResponse> data) {
    super(true, MSG);
    this.data = data;
  }
}
