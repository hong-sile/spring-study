package com.example.tddstudy.product;

import org.springframework.util.Assert;

record UpdateProductRequest(String name, int price, DiscountPolicy policy) {

  UpdateProductRequest {
    Assert.hasText(name, "상품명은 필수입니다.");
    Assert.isTrue(price > 0, "가격은 0보다 커야합니다.");
    Assert.notNull(policy, "할인 정책은 필수입니다.");
  }
}
