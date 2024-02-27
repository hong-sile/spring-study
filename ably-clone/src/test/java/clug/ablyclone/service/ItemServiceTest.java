package clug.ablyclone.service;

import static clug.ablyclone.fixture.domain.ItemFixture.나시_티셔츠;
import static clug.ablyclone.fixture.domain.SellerFixture.CLUG_1;
import static org.assertj.core.api.Assertions.assertThat;

import clug.ablyclone.domain.Item;
import clug.ablyclone.domain.Seller;
import clug.ablyclone.dto.FormattedItemDetailResponse.ItemDetailResponse;
import clug.ablyclone.dto.FormattedItemPreviewResponse.ItemPreviewResponse;
import clug.ablyclone.repository.ItemRepository;
import clug.ablyclone.repository.SellerRepository;
import clug.ablyclone.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ItemServiceTest extends ServiceTest {

  @Autowired
  private SellerRepository sellerRepository;
  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private ItemService itemService;

  @Test
  void Item_목록을_조회한다() {
    final Item 나시_티셔츠 = 나시티셔츠_저장();

    final List<ItemPreviewResponse> actual = itemService.findAll();
    final List<ItemPreviewResponse> expected = List.of(
        ItemPreviewResponse.from(나시_티셔츠)
    );

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  void Item_하나를_조회한다() {
    final Item 나시_티셔츠 = 나시티셔츠_저장();

    final ItemDetailResponse actual = itemService.findById(나시_티셔츠.getId());
    final ItemDetailResponse expected = ItemDetailResponse.from(나시_티셔츠);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  private Item 나시티셔츠_저장() {
    final Seller CLUG_1 = sellerRepository.save(CLUG_1());
    return itemRepository.save(나시_티셔츠(CLUG_1));
  }
}