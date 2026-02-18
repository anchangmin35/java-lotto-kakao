package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoControllerTest {

    private final LottoController lottoController = new LottoController();

    @Test
    @DisplayName("당첨 번호와 보너스 번호로 당첨 로또를 생성한다.")
    public void createWinningLottoTest() {
        WinningLotto winningLotto = lottoController.createWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = Lotto.from(List.of(1, 2, 3, 4, 5, 7));

        assertThat(first.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FIRST);
        assertThat(second.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되면 당첨 로또 생성에 실패한다.")
    public void createWinningLottoFailDistinctBonusTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> lottoController.createWinningLotto(List.of(1, 2, 3, 4, 5, 6), 6));
        assertThat(exception.getMessage()).isEqualTo("당첨 번호와 보너스 볼의 번호가 일치합니다.");
    }

    @Test
    @DisplayName("정답 로또와 구매한 로또 하나를 비교하여 결과 Enum을 반환한다.")
    public void getOneLottoResultTest() {
        WinningLotto winningLotto = lottoController.createWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lotto lotto4 = Lotto.from(List.of(1, 2, 3, 4, 9, 8));
        Lotto lotto5 = Lotto.from(List.of(1, 2, 3, 10, 9, 8));

        assertThat(lotto1.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FIRST);
        assertThat(lotto2.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.SECOND);
        assertThat(lotto3.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.THIRD);
        assertThat(lotto4.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FOURTH);
        assertThat(lotto5.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FIFTH);
    }
}
