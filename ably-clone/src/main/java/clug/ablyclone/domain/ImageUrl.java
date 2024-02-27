package clug.ablyclone.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Entity
@Getter
public class ImageUrl {

  @GeneratedValue(strategy = IDENTITY)
  @Id
  private Long id;
  private String url;
  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  public ImageUrl(final String url) {
    this.url = url;
  }

  public void updateItem(final Item item) {
    this.item = item;
  }
}
