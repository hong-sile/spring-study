package clug.ablyclone.fixture.domain;

import clug.ablyclone.domain.Seller;

public class SellerFixture {

  public static Seller CLUG_1() {
    return Seller.builder()
        .name("Clug_1")
        .profileImageUrl("클러그 프로필 이미지 url")
        .build();
  }
}
