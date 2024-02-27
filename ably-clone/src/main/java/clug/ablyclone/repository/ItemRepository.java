package clug.ablyclone.repository;

import clug.ablyclone.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

  @Query("""
      select i
      from Item i
      join fetch Seller s on s.id = i.seller.id
      """)
  List<Item> findAllFetchSeller();
}
