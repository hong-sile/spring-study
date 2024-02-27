package clug.ablyclone.dto;

import clug.ablyclone.domain.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemPreviewResponse {

  private static final String DISCOUNT_PERCENTAGE_FORMAT = "%d%%";

  private final String discountPercentage;
  private final String sellerName;
  private final String itemName;
  private final String imageUrl;
  private final Long discountedPrice;

  public static ItemPreviewResponse from(final Item item) {
    return new ItemPreviewResponse(
        String.format(DISCOUNT_PERCENTAGE_FORMAT, item.getDiscountPercentage()),
        item.getSeller().getName(),
        item.getItemName(),
        item.getImageUrl(),
        item.getDiscountedPrice()
    );
  }
}
