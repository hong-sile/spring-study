package com.example.tddstudy.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  void update() {
    final Product product = new Product("상품", 1000, DiscountPolicy.NONE);

    product.update("수정상품", 2000, DiscountPolicy.NONE);

    final Product expected = new Product("수정상품", 2000, DiscountPolicy.NONE);
    assertThat(product)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

}
