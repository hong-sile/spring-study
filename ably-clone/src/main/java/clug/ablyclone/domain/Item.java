package clug.ablyclone.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Entity
@Getter
public class Item {

  @GeneratedValue(strategy = IDENTITY)
  @Id
  private Long id;
  private Double discountRate;
  @ManyToOne
  private Seller seller;
  private String itemName;
  private String imageUrl;
  private Long originPrice;

  @Builder
  public Item(final Double discountRate, final Seller seller, final String itemName
      , final String imageUrl, final Long originPrice) {
    this.discountRate = discountRate;
    this.seller = seller;
    this.itemName = itemName;
    this.imageUrl = imageUrl;
    this.originPrice = originPrice;
  }

  public Long getDiscountedPrice() {
    return originPrice - (long) Math.floor(discountRate * originPrice);
  }
}
