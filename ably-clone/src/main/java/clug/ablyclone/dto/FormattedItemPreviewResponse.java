package clug.ablyclone.dto;

import clug.ablyclone.domain.Item;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FormattedItemPreviewResponse extends DefaultResponseFormat {

  private static final String MSG = "아이템 목록 조회 성공";

  private final List<ItemPreviewResponse> data;

  private FormattedItemPreviewResponse() {
    this(null);
  }

  public FormattedItemPreviewResponse(final List<ItemPreviewResponse> data) {
    super(true, MSG);
    this.data = data;
  }

  @Getter
  @RequiredArgsConstructor
  public static class ItemPreviewResponse {

    private static final String DISCOUNT_PERCENTAGE_FORMAT = "%d%%";

    private final Long id;
    private final String discountPercentage;
    private final String sellerName;
    private final String itemName;
    private final String imageUrl;
    private final Long discountedPrice;

    public static ItemPreviewResponse from(final Item item) {
      return new ItemPreviewResponse(
          item.getId(),
          String.format(DISCOUNT_PERCENTAGE_FORMAT, item.getDiscountPercentage()),
          item.getSeller().getName(),
          item.getItemName(),
          item.getImageUrl(),
          item.getDiscountedPrice()
      );
    }
  }
}
