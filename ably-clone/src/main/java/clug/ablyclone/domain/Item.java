package clug.ablyclone.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
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
  private int discountPercentage;
  @ManyToOne(fetch = LAZY)
  private Seller seller;
  private String itemName;
  @OneToMany(mappedBy = "item", cascade = PERSIST, orphanRemoval = true)
  private List<ImageUrl> imageUrls;
  private Long originPrice;

  @Builder
  public Item(final int discountPercentage, final Seller seller, final String itemName
      , final List<ImageUrl> imageUrls, final Long originPrice) {
    associationMappingImageUrls(imageUrls);
    this.discountPercentage = discountPercentage;
    this.seller = seller;
    this.itemName = itemName;
    this.imageUrls = imageUrls;
    this.originPrice = originPrice;
  }

  private void associationMappingImageUrls(final List<ImageUrl> imageUrls) {
    imageUrls.forEach(imageUrl -> imageUrl.updateItem(this));
  }

  public Long getDiscountedPrice() {
    return originPrice - discountPercentage * originPrice / 100;
  }

  public String getThumbnailUrl() {
    return imageUrls.get(0).getUrl();
  }
}
