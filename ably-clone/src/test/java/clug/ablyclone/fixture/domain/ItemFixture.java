package clug.ablyclone.fixture.domain;

import static clug.ablyclone.fixture.domain.SellerFixture.CLUG_1;

import clug.ablyclone.domain.Item;
import clug.ablyclone.domain.Seller;

public class ItemFixture {

  public static Item 나시_티셔츠(final Seller seller) {
    return Item.builder()
        .seller(seller)
        .discountPercentage(29)
        .itemName("나시 티셔츠")
        .imageUrl("이미지 URL")
        .originPrice(29900L)
        .build();
  }
}
