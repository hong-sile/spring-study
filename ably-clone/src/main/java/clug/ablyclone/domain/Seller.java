package clug.ablyclone.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Entity
@Getter
public class Seller {

  @GeneratedValue(strategy = IDENTITY)
  @Id
  private Long id;
  private String name;
  private String profileImageUrl;

  @Builder
  public Seller(final String name, final String profileImageUrl) {
    this.name = name;
    this.profileImageUrl = profileImageUrl;
  }
}
