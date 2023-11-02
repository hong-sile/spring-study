package com.example.tddstudy;

import org.springframework.stereotype.Component;

@Component
class ProductAdaptor implements ProductPort {

  private final ProductRepository productRepository;

  public ProductAdaptor(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void save(final Product product) {
    productRepository.save(product);
  }
}
