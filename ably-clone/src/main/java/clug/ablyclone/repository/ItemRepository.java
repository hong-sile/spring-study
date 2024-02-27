package clug.ablyclone.repository;

import clug.ablyclone.domain.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

  @Query("""
      select i
      from Item i
      join fetch Seller s on s.id = i.seller.id
      """)
  List<Item> findAllFetchSeller();

  @Query("""
      select i
      from Item i
      join fetch Seller s on s.id = i.seller.id
      join fetch ImageUrl u on u.item.id = i.id
      where i.id = :itemId
      """)
  Optional<Item> findByIdFetchSellerAndImageUrls(final Long itemId);
}
