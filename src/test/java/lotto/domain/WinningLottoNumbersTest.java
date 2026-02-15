package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoNumbersTest {

    @Test
    @DisplayName("입력한 6개 숫자로 당첨 번호를 생성한다.")
    public void parseWinningNumbersTest() {
        WinningLottoNumbers winningLottoNumbers = WinningLottoNumbers.from(List.of(1, 2, 3, 4, 5, 6));

        assertThat(winningLottoNumbers.getLottoNumberList().stream().map(LottoNumber::getNumber).toList())
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("범위를 벗어난 숫자를 입력하면 예외를 반환한다.")
    public void parseFailRangeTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> WinningLottoNumbers.from(List.of(1, 2, 3, 4, 5, 46)));
        assertThat(exception.getMessage()).isEqualTo("1 ~ 45 사이의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("중복 숫자를 입력하면 예외를 반환한다.")
    public void parseFailDistinctTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> WinningLottoNumbers.from(List.of(1, 2, 3, 4, 6, 6)));
        assertThat(exception.getMessage()).isEqualTo("로또에 중복된 숫자가 존재합니다.");
    }
}
