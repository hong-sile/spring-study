package com.example.tddstudy.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ProductService {

  private final ProductPort productPort;

  ProductService(final ProductPort productPort) {
    this.productPort = productPort;
  }

  @Transactional
  public Product addProduct(final AddProductRequest request) {
    final Product product = new Product(request.name(), request.price(), request.discountPolicy());

    return productPort.save(product);
  }

  public GetProductResponse getProduct(final Long productId) {
    final Product product = productPort.getProduct(productId);
    return new GetProductResponse(
        product.getId(), product.getName(), product.getPrice(), product.getDiscountPolicy()
    );
  }
}
