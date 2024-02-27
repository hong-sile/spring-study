package clug.ablyclone.domain;

import static clug.ablyclone.fixture.domain.ItemFixture.나시_티셔츠;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ItemTest {

  @Test
  void 할인율과_가격으로_할인가격을_계산한다() {
    final Item 나시_티셔츠 = 나시_티셔츠();

    final Long actual = 나시_티셔츠.getDiscountedPrice();

    assertThat(actual)
        .isEqualTo(21229);
  }
}