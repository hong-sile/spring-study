package clug.ablyclone.dto;

import clug.ablyclone.domain.ImageUrl;
import clug.ablyclone.domain.Item;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FormattedItemDetailResponse extends DefaultResponseFormat {

  private static final String MSG = "아이템 상세 조회 성공";

  private final ItemDetailResponse data;

  private FormattedItemDetailResponse() {
    this(null);
  }

  public FormattedItemDetailResponse(final ItemDetailResponse data) {
    super(true, MSG);
    this.data = data;
  }

  @Getter
  @RequiredArgsConstructor
  public static class ItemDetailResponse {

    private final List<String> imageUrls;
    private final String name;
    private final Long originPrice;
    private final Long discountedPrice;
    //    private final Integer likeCount;
    private final String sellerName;
    private final String sellerProfileImageUrl;

    public static ItemDetailResponse from(final Item item) {
      return new ItemDetailResponse(
          extractUrl(item),
          item.getItemName(),
          item.getOriginPrice(),
          item.getDiscountedPrice(),
          item.getSeller().getName(),
          item.getSeller().getProfileImageUrl()
      );
    }

    private static List<String> extractUrl(final Item item) {
      return item.getImageUrls().stream()
          .map(ImageUrl::getUrl)
          .toList();
    }
  }
}
