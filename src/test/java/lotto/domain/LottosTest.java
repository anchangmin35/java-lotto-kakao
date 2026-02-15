package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private Lottos lottos;
    private Lotto firstRankLotto;
    private Lotto thirdRankLotto;

    @BeforeEach
    public void setUp() {
        lottos = new Lottos();

        firstRankLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        thirdRankLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);

        lottos.add(firstRankLotto);
        lottos.add(thirdRankLotto);
        lottos.setAllLottoResult(winningLotto);
    }

    @Test
    @DisplayName("로또를 추가하면 목록에 저장한다.")
    public void addLottoTest() {
        lottos = new Lottos();

        lottos.add(Lotto.random());
        lottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));

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

        assertThat(lottos.getLottoList()).hasSize(1);
        assertThat(lottos.getLottoList().get(0).toList()).containsExactly(1, 2, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("수동 구매 후 자동 구매를 추가할 수 있다.")
    public void purchaseManualAndAutomaticLottoTest() {
        lottos = new Lottos();

        lottos.purchaseManualLotto(List.of(9, 1, 5, 3, 7, 2));
        lottos.purchaseAutomaticLotto(2);

        assertThat(lottos.getLottoList()).hasSize(3);
        assertThat(lottos.getLottoList().get(0).toList()).containsExactly(1, 2, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("당첨 로또를 기준으로 모든 로또 결과를 계산한다.")
    public void setAllLottoResultTest() {
        assertThat(firstRankLotto.getLottoRank()).isEqualTo(LottoRank.FIRST);
        assertThat(thirdRankLotto.getLottoRank()).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("당첨 결과를 입력하면 당첨금 총액을 반환한다.")
    public void getLottoSumTest() {
        assertThat(lottos.getLottoSum().getAmount()).isEqualTo(2_001_500_000);
    }

    @Test
    @DisplayName("당첨 금액과 구매 수량을 넣으면 수익률을 반환한다.")
    public void getRateOfReturn() {
        Money winning = Money.from(1_500_000);  // 당첨금 150만원
        Money purchase = Money.from(3_000);     // 로또 3장 구매

        assertThat(lottos.getRateOfReturn(purchase, winning)).isEqualTo(500.0);
    }

    @Test
    @DisplayName("당첨 금액과 구매 수량을 넣으면 수익률을 반환한다. (예시 데이터와 동일)")
    public void getRateOfReturnWithSampleData() {
        Money winning = Money.from(5_000);       // 당첨금 5천원
        Money purchase = Money.from(14_000);     // 로또 14장 구매

        assertThat(lottos.getRateOfReturn(purchase, winning)).isEqualTo(0.35);
    }
}
