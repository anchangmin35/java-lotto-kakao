package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호 6개를 생성하고 오름차순 정렬한다.")
    public void generateAndSortTest() {
        LottoNumbers lottoNumbers = new LottoNumbers();

        List<Integer> numbers = lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();

        assertThat(numbers).hasSize(6);
        assertThat(numbers).isSorted();
        assertThat(numbers.stream().distinct().count()).isEqualTo(6);
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    @DisplayName("수동으로 넣은 숫자 목록도 정렬할 수 있다.")
    public void sortLottoNumberListTest() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        lottoNumbers.getLottoNumberList().clear();
        lottoNumbers.getLottoNumberList().add(LottoNumber.from(9));
        lottoNumbers.getLottoNumberList().add(LottoNumber.from(1));
        lottoNumbers.getLottoNumberList().add(LottoNumber.from(5));
        lottoNumbers.getLottoNumberList().add(LottoNumber.from(3));
        lottoNumbers.getLottoNumberList().add(LottoNumber.from(7));
        lottoNumbers.getLottoNumberList().add(LottoNumber.from(2));

        lottoNumbers.sortLottoNumberList();

        assertThat(lottoNumbers.getLottoNumberList().stream().map(LottoNumber::getNumber).toList())
                .containsExactly(1, 2, 3, 5, 7, 9);
    }
}
