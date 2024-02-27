package clug.ablyclone.fixture.domain;

import static clug.ablyclone.fixture.domain.SellerFixture.CLUG_1;

import clug.ablyclone.domain.Item;

public class ItemFixture {

  public static Item 나시_티셔츠() {
    return Item.builder()
        .seller(CLUG_1())
        .discountPercentage(29)
        .itemName("나시 티셔츠")
        .imageUrl("이미지 URL")
        .originPrice(29900L)
        .build();
  }
}
