package com.example.tddstudy.product;

interface ProductPort {

  Product save(final Product product);

  Product getProduct(final Long productId);
}
