package clug.ablyclone.dto;

import static clug.ablyclone.fixture.domain.ItemFixture.나시_티셔츠;
import static clug.ablyclone.fixture.domain.SellerFixture.CLUG_1;
import static org.assertj.core.api.Assertions.assertThat;

import clug.ablyclone.domain.Item;
import clug.ablyclone.dto.FormattedItemPreviewResponse.ItemPreviewResponse;
import org.junit.jupiter.api.Test;

class ItemPreviewResponseTest {

  @Test
  void 정상적으로_변환한다() {
    final Item 나시_티셔츠 = 나시_티셔츠(CLUG_1());

    final ItemPreviewResponse expected = new ItemPreviewResponse(
        1L, "29%", "Clug_1", "나시 티셔츠", "image", 21229L
    );
    final ItemPreviewResponse actual = ItemPreviewResponse.from(나시_티셔츠);

    assertThat(actual)
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(expected);
  }
}