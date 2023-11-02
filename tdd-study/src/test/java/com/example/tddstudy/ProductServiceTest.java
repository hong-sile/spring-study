package com.example.tddstudy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ProductServiceTest {

  private ProductService productService;
  private ProductPort productPort;
  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository = new ProductRepository();
    productPort = new ProductAdaptor(productRepository);
    productService = new ProductService(productPort);
  }

  @Test
  void 상품등록() {
    final String name = "상품명";
    final int price = 1000;
    final DiscountPolicy policy = DiscountPolicy.NONE;
    final AddProductRequest request = new AddProductRequest(name, price, policy);

    productService.addProduct(request);
  }

  private record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {

    private AddProductRequest {
      Assert.hasText(name, "상품명은 필수입니다.");
      Assert.isTrue(price > 0, "상품 가격은 보다 커야 합니다.");
      Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
    }
  }

  private enum DiscountPolicy {
    NONE
  }

  private class ProductService {

    private final ProductPort productPort;

    private ProductService(final ProductPort productPort) {
      this.productPort = productPort;
    }

    public void addProduct(final AddProductRequest request) {
      final Product product = new Product(request.name, request.price, request.discountPolicy);

      productPort.save(product);
    }

  }

  private class Product {

    private Long id;
    private final String name;
    private final int price;
    private final DiscountPolicy discountPolicy;

    public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
      Assert.hasText(name, "상품명은 필수입니다.");
      Assert.isTrue(price > 0, "상품 가격은 보다 커야 합니다.");
      Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
      this.name = name;
      this.price = price;
      this.discountPolicy = discountPolicy;
    }

    public void assignId(final Long id) {
      this.id = id;
    }

    public Long getId() {
      return id;
    }
  }

  private interface ProductPort {

    void save(final Product product);
  }

  private class ProductAdaptor implements ProductPort {

    private final ProductRepository productRepository;

    public ProductAdaptor(final ProductRepository productRepository) {
      this.productRepository = productRepository;
    }

    @Override
    public void save(final Product product) {
      productRepository.save(product);
    }
  }

  private class ProductRepository {

    private Long seq = 0L;
    private final Map<Long, Product> persistence = new HashMap<>();

    public void save(final Product product) {
      product.assignId(++seq);
      persistence.put(product.getId(), product);
    }
  }
}
