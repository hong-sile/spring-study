package com.example.tddstudy.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

class ProductSteps {

  public static ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
    return RestAssured.given()
        .log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/products")
        .then()
        .log().all().extract();
  }

  public static AddProductRequest 상품등록요청_생성() {
    final String name = "상품명";
    final int price = 1000;
    final DiscountPolicy policy = DiscountPolicy.NONE;
    return new AddProductRequest(name, price, policy);
  }

  public static GetProductResponse 상품응답_생성(final Long id) {
    final String name = "상품명";
    final int price = 1000;
    final DiscountPolicy policy = DiscountPolicy.NONE;
    return new GetProductResponse(id, name, price, policy);
  }

  public static UpdateProductRequest 상품수정요청_생성() {
    return new UpdateProductRequest("상품 수정", 5000, DiscountPolicy.NONE);
  }

  public static ExtractableResponse<Response> 상품조회요청(final Long id) {
    return RestAssured.given()
        .log().all()
        .when()
        .get("/products/{id}", id)
        .then()
        .log().all().extract();
  }

  public static ExtractableResponse<Response> 상품수정요청(
      final Long id, final UpdateProductRequest request
  ) {
    return RestAssured.given()
        .log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .put("/products/{id}", id)
        .then()
        .log().all().extract();
  }
}
