package playground;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsingRecursiveComparisonTest {

    /**
     * usingRecursiveComparison은 무조건 마지막에 isEqualTo 또는 isNotEqualTo로 비교해야 한다. <br> 여기서 추가적인 체이닝이 필요한 경우
     * usingRecursiveFieldByFieldElementComparator로 사용해야 한다.
     */
    @Test
    @DisplayName("usingrecursivecomparison는 필드값을 비교한다.")
    void default_case() {
        final Member 홍혁준 = new Member("홍혁준", 24);

        assertThat(홍혁준)
                .isNotEqualTo(new Member("홍혁준", 24));
        assertThat(홍혁준)
                .usingRecursiveComparison()
                .isEqualTo(new Member("홍혁준", 24));
    }

    @Test
    @DisplayName("list형태를 비교해본다.")
    void list_case() {
        final Member 홍혁준 = new Member("홍혁준", 24);
        final Member 박준현 = new Member("박준현", 26);
        final Member 윤가영 = new Member("윤가영", 24);

        final List<Member> 멤버_리스트 = List.of(홍혁준, 박준현, 윤가영);

        final List<Tuple> 멤버_튜플 = 멤버_리스트.stream()
                .map(member -> tuple(member.getName(), member.getAge()))
                .collect(Collectors.toUnmodifiableList());

        assertThat(멤버_리스트)
                .extracting(Member::getName, Member::getAge)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(멤버_튜플);
    }
}
