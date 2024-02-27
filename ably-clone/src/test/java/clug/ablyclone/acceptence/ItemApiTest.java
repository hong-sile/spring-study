package clug.ablyclone.acceptence;

import static clug.ablyclone.acceptence.support.AssuredSupport.get;
import static clug.ablyclone.fixture.domain.ItemFixture.나시_티셔츠;
import static clug.ablyclone.fixture.domain.SellerFixture.CLUG_1;
import static org.assertj.core.api.Assertions.assertThat;

import clug.ablyclone.domain.Item;
import clug.ablyclone.domain.Seller;
import clug.ablyclone.dto.ItemPreviewResponse;
import clug.ablyclone.dto.FormattedItemPreviewResponse;
import clug.ablyclone.support.AcceptanceTest;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ItemApiTest extends AcceptanceTest {

  @Test
  void 아이템_목록을_조회한다() {
    final Seller 판매자 = 판매자를_등록한다();
    final Item 아이템 = 아이템을_등록한다(판매자);

    final FormattedItemPreviewResponse expected
        = new FormattedItemPreviewResponse(List.of(ItemPreviewResponse.from(아이템)));
    final FormattedItemPreviewResponse actual = get("/items").as(FormattedItemPreviewResponse.class);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  void 아이템_하나_조회한다() {
    final Seller 판매자 = 판매자를_등록한다();
    final Item 아이템 = 아이템을_등록한다(판매자);

    final FormattedItemPreviewResponse expected
        = new FormattedItemPreviewResponse(List.of(ItemPreviewResponse.from(아이템)));
    final FormattedItemPreviewResponse actual = get("/items").as(FormattedItemPreviewResponse.class);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  private Item 아이템을_등록한다(final Seller 판매자) {
    return itemRepository.save(나시_티셔츠(판매자));
  }

  private Seller 판매자를_등록한다() {
    return sellerRepository.save(CLUG_1());
  }
}
