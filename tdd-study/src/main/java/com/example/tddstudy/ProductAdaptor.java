package com.example.tddstudy;

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
