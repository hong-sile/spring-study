package com.example.tddstudy.product;

import static com.example.tddstudy.product.ProductSteps.상품등록요청_생성;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.tddstudy.product.support.DataCleanerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(DataCleanerExtension.class)
public class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @Test
  void 상품조회() {
    //given
    final var savedProduct = productService.addProduct(상품등록요청_생성());
    final var productId = savedProduct.getId();

    //when
    final GetProductResponse actual = productService.getProduct(productId);

    //then
    final GetProductResponse expected = new GetProductResponse(
        productId, savedProduct.getName(), savedProduct.getPrice(), savedProduct.getDiscountPolicy()
    );

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  void 상품수정() {
    //given
    final var savedProduct = productService.addProduct(상품등록요청_생성());
    final var productId = savedProduct.getId();

    final UpdateProductRequest request
        = new UpdateProductRequest("상품 수정", 5000, DiscountPolicy.NONE);

    //when
    productService.updateProduct(productId, request);

    //then
    final GetProductResponse actual = productService.getProduct(productId);
    final GetProductResponse expected = new GetProductResponse(
        productId, request.name(), request.price(), request.policy()
    );

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}
