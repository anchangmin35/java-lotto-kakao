package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoNumbersTest {

    @Test
    @DisplayName("입력한 6개 숫자로 당첨 번호를 생성한다.")
    public void parseWinningNumbersTest() {
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers("1, 2, 3, 4, 5, 6");

        assertThat(winningLottoNumbers.getLottoNumberList().stream().map(LottoNumber::getNumber).toList())
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("입력 개수가 6개가 아니면 예외를 반환한다.")
    public void parseFailSizeTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WinningLottoNumbers("1, 2, 3, 4, 5"));
        assertThat(exception.getMessage()).isEqualTo("6개의 숫자를 입력해야 합니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력하면 예외를 반환한다.")
    public void parseFailNotNumberTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> new WinningLottoNumbers("1, 2, a, 4, 5, 6"));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("범위를 벗어난 숫자를 입력하면 예외를 반환한다.")
    public void parseFailRangeTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WinningLottoNumbers("1, 2, 3, 4, 5, 46"));
        assertThat(exception.getMessage()).isEqualTo("범위를 벗어난 숫자입니다.");
    }

    @Test
    @DisplayName("중복 숫자를 입력하면 예외를 반환한다.")
    public void parseFailDistinctTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WinningLottoNumbers("1, 2, 3, 3, 5, 6"));
        assertThat(exception.getMessage()).isEqualTo("로또에 중복된 숫자가 존재합니다.");
    }
}
