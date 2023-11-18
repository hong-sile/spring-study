package com.example.tddstudy.product;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Void> addProduct(@RequestBody final AddProductRequest request) {
    final Product product = productService.addProduct(request);
    return ResponseEntity.created(URI.create("/products/" + product.getId())).build();
  }

  @GetMapping("/{productId}")
  public ResponseEntity<GetProductResponse> findProduct(@PathVariable Long productId) {
    final GetProductResponse response = productService.getProduct(productId);
    return ResponseEntity.ok(response);
  }
}
