package com.example.tddstudy.product;

import static com.example.tddstudy.product.ProductSteps.상품등록요청;
import static com.example.tddstudy.product.ProductSteps.상품등록요청_생성;
import static com.example.tddstudy.product.ProductSteps.상품수정요청;
import static com.example.tddstudy.product.ProductSteps.상품응답_생성;
import static com.example.tddstudy.product.ProductSteps.상품조회요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.tddstudy.product.support.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
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

  @Test
  void 상품조회() {
    final var productAddResponse = 상품등록요청(상품등록요청_생성());
    final Long productId = parseProductIdFromLocation(productAddResponse);

    final var response = 상품조회요청(productId);

    assertAll(
        () -> assertThat(response.statusCode())
            .isEqualTo(HttpStatus.OK.value()),
        () -> assertThat(response.as(GetProductResponse.class))
            .usingRecursiveComparison()
            .isEqualTo(상품응답_생성(productId))
    );
  }

  @Test
  void 상품수정() {
    //given
    final var productAddResponse = 상품등록요청(상품등록요청_생성());
    final Long productId = parseProductIdFromLocation(productAddResponse);
    final UpdateProductRequest request
        = new UpdateProductRequest("상품 수정", 5000, DiscountPolicy.NONE);

    //when
    상품수정요청(productId, request);

    //then
    final var actual = 상품조회요청(productId).as(GetProductResponse.class);
    final var expected
        = new GetProductResponse(productId, request.name(), request.price(), request.policy());
    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  private static Long parseProductIdFromLocation(final ExtractableResponse<Response> response) {
    return Long.valueOf(response
        .header("Location")
        .split("/products/")[1]);
  }
}
