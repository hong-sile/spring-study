package clug.ablyclone.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemPreviewResponse {

  private final String discountRate;
  private final String sellerName;
  private final String itemName;
  private final String imageUrl;
  private final Long price;
}
