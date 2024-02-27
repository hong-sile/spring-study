package clug.ablyclone.dto;

import static clug.ablyclone.fixture.domain.ItemFixture.나시_티셔츠;
import static clug.ablyclone.fixture.domain.SellerFixture.CLUG_1;
import static org.assertj.core.api.Assertions.assertThat;

import clug.ablyclone.domain.Item;
import clug.ablyclone.dto.FormattedItemDetailResponse.ItemDetailResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class ItemDetailResponseTest {

  @Test
  void 정상적으로_변환한다() {
    final Item 나시_티셔츠 = 나시_티셔츠(CLUG_1());

    final ItemDetailResponse actual = ItemDetailResponse.from(나시_티셔츠);
    final ItemDetailResponse expected = new ItemDetailResponse(
        List.of("image"), "나시 티셔츠", 29900L, 21229L, "Clug_1", "클러그 프로필 이미지 url"
    );

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}