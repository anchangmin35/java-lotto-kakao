package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LottoGameTest {

    private LottoGame lottoGame;

    @BeforeEach
    public void setUp() {
        lottoGame = new LottoGame();
    }

    @Test
    @DisplayName("로또 구매 금액을 전달하면 구매할 수 있는 로또 장수를 반환한다.")
    public void calculateLottoCountTest() {
        Assertions.assertThat(lottoGame.calculateLottoCount(1500)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 미만이면 예외를 반환한다.")
    public void calculateLottoCountExceptionTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> lottoGame.calculateLottoCount(500));
        assertThat(exception.getMessage()).isEqualTo("1000원 이상의 금액을 입력해야 합니다.");
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
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto lotto4 = new Lotto(List.of(1, 2, 3, 4, 9, 8));
        Lotto lotto5 = new Lotto(List.of(1, 2, 3, 10, 9, 8));

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
