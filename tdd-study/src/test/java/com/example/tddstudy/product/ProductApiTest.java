package com.example.tddstudy.product;

import static com.example.tddstudy.product.ProductSteps.상품등록요청;
import static com.example.tddstudy.product.ProductSteps.상품등록요청_생성;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.tddstudy.product.support.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ProductApiTest extends ApiTest {

  @Test
  void 상품등록() {
    final var request = 상품등록요청_생성();

    final var response = 상품등록요청(request);

    assertThat(response.statusCode())
        .isEqualTo(HttpStatus.CREATED.value());
  }
}
