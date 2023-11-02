package com.example.tddstudy;

import java.util.HashMap;
import java.util.Map;

class ProductRepository {

  private Long seq = 0L;
  private final Map<Long, Product> persistence = new HashMap<>();

  public void save(final Product product) {
    product.assignId(++seq);
    persistence.put(product.getId(), product);
  }
}
