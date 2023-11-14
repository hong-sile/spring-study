package com.example.tddstudy.product;

import org.springframework.util.Assert;

record GetProductResponse(
    Long id,
    String name,
    int price,
    DiscountPolicy discountPolicy
) {

  public GetProductResponse {
    Assert.notNull(id, "id는 필수입니다.");
    Assert.hasText(name, "상품명은 필수 입니다.");
    Assert.isTrue(price > 0, "가격은 양수여야 합니다.");
    Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
  }
}
