package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class LottosTest {

    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        lottos = new Lottos();
        winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        lottos.purchaseManualLotto(List.of(1, 2, 3, 4, 5, 6));
        lottos.purchaseManualLotto(List.of(1, 2, 3, 4, 5, 8));
    }

    @Test
    @DisplayName("수동 구매하면 목록에 저장한다.")
    public void addLottoTest() {
        lottos = new Lottos();

        lottos.purchaseManualLotto(List.of(1, 2, 3, 4, 5, 6));
        lottos.purchaseManualLotto(List.of(7, 8, 9, 10, 11, 12));

        assertThat(lottos.getLottoList()).hasSize(2);
    }

    @Test
    @DisplayName("자동 구매 수량만큼 로또를 생성한다.")
    public void purchaseAutomaticLottoTest() {
        lottos = new Lottos();

        lottos.purchaseAutomaticLotto(3);

        assertThat(lottos.getLottoList()).hasSize(3);
    }

    @Test
    @DisplayName("수동 입력 번호로 로또를 구매한다.")
    public void purchaseManualLottoTest() {
        lottos = new Lottos();

        lottos.purchaseManualLotto(List.of(9, 1, 5, 3, 7, 2));
        Lotto lotto = lottos.getLottoList().getFirst();

        assertThat(lottos.getLottoList()).hasSize(1);
        assertThat(lotto.toList()).containsExactly(1, 2, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("수동 구매 후 자동 구매를 추가할 수 있다.")
    public void purchaseManualAndAutomaticLottoTest() {
        lottos = new Lottos();

        lottos.purchaseManualLotto(List.of(9, 1, 5, 3, 7, 2));
        lottos.purchaseAutomaticLotto(2);

        assertThat(lottos.getLottoList()).hasSize(3);
    }

    @Test
    @DisplayName("당첨 로또를 기준으로 전체 로또 결과를 집계한다.")
    public void calculateAllLottosResultTest() {
        Result result = lottos.calculateAllLottosResult(winningLotto);

        assertThat(result.getCount(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.getCount(LottoRank.THIRD)).isEqualTo(1);
        assertThat(result.getCount(LottoRank.SECOND)).isEqualTo(0);
        assertThat(result.getCount(LottoRank.MISS)).isEqualTo(0);
    }

    @Test
    @DisplayName("고액 당첨이 여러 장이어도 수익률을 계산할 수 있다.")
    public void calculateAllLottosResultRateOfReturnTest() {
        lottos.purchaseManualLotto(List.of(1, 2, 3, 4, 5, 6)); // 1등 2장 + 3등 1장
        Result result = lottos.calculateAllLottosResult(winningLotto);

        assertThat(result.getRateOfReturn(Money.from(3_000))).isEqualTo(1_333_833.33, within(0.0001));
    }
}
