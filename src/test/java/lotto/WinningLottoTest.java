package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WinningLottoTest {

    @Test
    @DisplayName("입력받은 숫자 6개를 포함한 한 장의 로또를 생성한다.")
    public void manualWinningLottoTest() {
        WinningLotto lotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        assertNotNull(lotto);
        assertThat(lotto.getWinningLottoNumbers().getLottoNumberList().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("입력받은 숫자 6개와 보너스 번호를 포함한 한 장의 로또를 생성한다.")
    public void manualBonusTest() {
        WinningLotto lotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        assertNotNull(lotto);
        assertThat(lotto.getWinningLottoNumbers().getLottoNumberList().size()).isEqualTo(6);
        assertThat(lotto.getBonusNumber().getNumber()).isEqualTo(7);
    }


    @Test
    @DisplayName("5개의 숫자만 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailSizeTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new WinningLotto("1, 2, 3, 4, 6", "7"));
        assertThat(exception.getMessage()).isEqualTo("6개의 숫자를 입력해야 합니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailNotNumberTest() {
        NumberFormatException exception = Assertions.assertThrows(NumberFormatException.class, () -> new WinningLotto("1, 2, 3, c, 5b, 6a", "7"));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("1-45 범위를 벗어난 숫자를 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailRangeTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new WinningLotto("1, 2, 3, 4, 100, 45", "7"));
        assertThat(exception.getMessage()).isEqualTo("범위를 벗어난 숫자입니다.");
    }

    @Test
    @DisplayName("중복된 숫자를 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailDistinctTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new WinningLotto("1, 2, 3, 4, 5, 5", "7"));
        assertThat(exception.getMessage()).isEqualTo("로또에 중복된 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외 처리한다.")
    public void manualWinningLottoFailDistinctBonusTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new WinningLotto("1, 2, 3, 4, 5, 6", "5"));
        assertThat(exception.getMessage()).isEqualTo("로또에 보너스와 중복된 숫자가 존재합니다.");
    }

}
