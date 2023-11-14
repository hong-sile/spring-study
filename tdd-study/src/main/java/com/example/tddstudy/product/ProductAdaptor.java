package com.example.tddstudy.product;

import org.springframework.stereotype.Component;

@Component
class ProductAdaptor implements ProductPort {

  private final ProductRepository productRepository;

  public ProductAdaptor(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product save(final Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product getProduct(final Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(IllegalArgumentException::new);
  }
}
