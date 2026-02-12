package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.LottoNumbers.LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호 6개를 생성하고 오름차순 정렬한다.")
    public void generateAndSortTest() {
        LottoNumbers lottoNumbers = LottoNumbers.random();

        List<Integer> numbers = lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();

        assertThat(numbers).hasSize(LOTTO_NUMBER_SIZE);
        assertThat(numbers).isSorted();
        assertThat(numbers.stream().distinct().count()).isEqualTo(LOTTO_NUMBER_SIZE);
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    @DisplayName("수동으로 넣은 숫자 목록도 정렬할 수 있다.")
    public void sortLottoNumberListTest() {
        LottoNumbers lottoNumbers = LottoNumbers.from(List.of(9, 1, 5, 3, 7, 2));

        assertThat(lottoNumbers.getLottoNumberList().stream().map(LottoNumber::getNumber).toList())
                .containsExactly(1, 2, 3, 5, 7, 9);
    }
}
