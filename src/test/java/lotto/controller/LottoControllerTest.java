package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoControllerTest {

    private LottoController lottoGame;

    @BeforeEach
    public void setUp() {
        lottoGame = new LottoController();
    }

    @Test
    @DisplayName("구매 가능 장수를 넣으면 그에 맞는 사이즈의 로또 리스트를 반환한다.")
    public void purchaseLottoTest() {
        lottoGame.purchaseLotto(3);

        assertThat(lottoGame.getLottos().getLottoList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("정답 로또와 구매한 로또 하나를 비교하여 결과 Enum을 반환한다.")
    public void getOneLottoResultTest() {
        lottoGame.createWinningLotto("1, 2, 3, 4, 5, 6", "7");
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lotto lotto4 = Lotto.from(List.of(1, 2, 3, 4, 9, 8));
        Lotto lotto5 = Lotto.from(List.of(1, 2, 3, 10, 9, 8));

        lotto1.evaluateRank(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
        lotto2.evaluateRank(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
        lotto3.evaluateRank(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
        lotto4.evaluateRank(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
        lotto5.evaluateRank(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));

        assertThat(lotto1.getLottoRank()).isEqualTo(LottoRank.FIRST);
        assertThat(lotto2.getLottoRank()).isEqualTo(LottoRank.SECOND);
        assertThat(lotto3.getLottoRank()).isEqualTo(LottoRank.THIRD);
        assertThat(lotto4.getLottoRank()).isEqualTo(LottoRank.FOURTH);
        assertThat(lotto5.getLottoRank()).isEqualTo(LottoRank.FIFTH);
    }
}
