package clug.ablyclone.fixture.domain;

import static clug.ablyclone.fixture.domain.ImageUrlFixture.IMAGES;

import clug.ablyclone.domain.Item;
import clug.ablyclone.domain.Seller;

public class ItemFixture {

  public static Item 나시_티셔츠(final Seller seller) {
    return Item.builder()
        .seller(seller)
        .discountPercentage(29)
        .itemName("나시 티셔츠")
        .imageUrls(IMAGES())
        .originPrice(29900L)
        .likeCount(10L)
        .build();
  }
}
