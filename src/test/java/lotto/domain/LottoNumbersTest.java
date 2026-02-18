package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호 6개를 생성하고 오름차순 정렬한다.")
    public void generateAndSortTest() {
        LottoNumbers lottoNumbers = LottoNumbers.random();

        List<Integer> numbers = lottoNumbers.toNumberList();

        assertThat(numbers).hasSize(LOTTO_NUMBER_SIZE);
        assertThat(numbers).isSorted();
        assertThat(numbers.stream().distinct().count()).isEqualTo(LOTTO_NUMBER_SIZE);
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    @DisplayName("수동으로 넣은 숫자 목록도 정렬할 수 있다.")
    public void sortLottoNumberListTest() {
        LottoNumbers lottoNumbers = LottoNumbers.from(List.of(9, 1, 5, 3, 7, 2));

        assertThat(lottoNumbers.toNumberList())
                .containsExactly(1, 2, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("수동 번호에 중복이 있으면 예외를 반환한다.")
    public void fromFailDuplicateTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumbers.from(List.of(1, 2, 3, 4, 5, 5)));
        assertThat(exception.getMessage()).isEqualTo("로또에 중복된 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("다른 로또 번호와 일치하는 숫자 개수를 반환한다.")
    public void countMatchingNumbersTest() {
        LottoNumbers winningNumbers = LottoNumbers.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers purchasedNumbers = LottoNumbers.from(List.of(1, 2, 3, 7, 8, 9));

        assertThat(winningNumbers.countMatchingNumbers(purchasedNumbers)).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 번호가 범위를 벗어나면 예외를 반환한다.")
    public void fromFailRangeTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumbers.from(List.of(1, 2, 3, 4, 5, 46)));
        assertThat(exception.getMessage()).isEqualTo("1 ~ 45 사이의 숫자를 입력해주세요.");
    }
}
