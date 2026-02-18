package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {

    @Test
    @DisplayName("입력받은 숫자 6개와 보너스 번호를 포함한 한 장의 로또를 생성한다.")
    public void manualBonusTest() {
        WinningLotto lotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(lotto.countMatchWithWinningNumbers(LottoNumbers.from(List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(LOTTO_NUMBER_SIZE);
        assertThat(lotto.isContainBonusNumber(LottoNumbers.from(List.of(1, 2, 3, 4, 5, 7)))).isTrue();
    }

    @Test
    @DisplayName("5개의 숫자만 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailSizeTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(List.of(1, 2, 3, 4, 5), 7));
        assertThat(exception.getMessage()).isEqualTo(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
    }

    @Test
    @DisplayName("1-45 범위를 벗어난 숫자를 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailRangeTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(List.of(1, 2, 3, 4, 5, 46), 7));
        assertThat(exception.getMessage()).isEqualTo("1 ~ 45 사이의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("중복된 숫자를 입력받으면 예외 처리한다.")
    public void manualWinningLottoFailDistinctTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(List.of(1, 2, 3, 4, 6, 6), 7));
        assertThat(exception.getMessage()).isEqualTo("로또에 중복된 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외 처리한다.")
    public void manualWinningLottoFailDistinctBonusTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 6));
        assertThat(exception.getMessage()).isEqualTo("당첨 번호와 보너스 볼의 번호가 일치합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 범위를 벗어나면 예외 처리한다.")
    public void manualWinningLottoFailBonusRangeTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 46));
        assertThat(exception.getMessage()).isEqualTo("1 ~ 45 사이의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("당첨 번호와 비교해 일치 개수를 반환한다.")
    public void countMatchWithWinningNumbersTest() {
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoNumbers lottoNumbers = LottoNumbers.from(List.of(1, 2, 3, 4, 8, 9));

        assertThat(winningLotto.countMatchWithWinningNumbers(lottoNumbers)).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스 번호 포함 여부를 반환한다.")
    public void isContainBonusNumberTest() {
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(winningLotto.isContainBonusNumber(LottoNumbers.from(List.of(1, 2, 3, 4, 5, 7)))).isTrue();
        assertThat(winningLotto.isContainBonusNumber(LottoNumbers.from(List.of(1, 2, 3, 4, 5, 8)))).isFalse();
    }

    @Test
    @DisplayName("입력 개수가 6개가 아니면 예외를 반환한다.")
    public void parseFailSizeTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(List.of(1, 2, 3, 4, 5), 7));
        assertThat(exception.getMessage()).isEqualTo(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
    }
}
