package com.example.tddstudy.product;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;
  private int price;
  private DiscountPolicy discountPolicy;

  public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
    Assert.hasText(name, "상품명은 필수입니다.");
    Assert.isTrue(price > 0, "상품 가격은 보다 커야 합니다.");
    Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
    this.name = name;
    this.price = price;
    this.discountPolicy = discountPolicy;
  }

  public void update(final String name, final int price, final DiscountPolicy policy) {
    Assert.hasText(name, "상품명은 필수입니다.");
    Assert.isTrue(price > 0, "상품 가격은 보다 커야 합니다.");
    Assert.notNull(policy, "할인 정책은 필수입니다.");
    this.name = name;
    this.price = price;
    this.discountPolicy = policy;
  }
}
