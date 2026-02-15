package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private Lottos lottos;

    @BeforeEach
    public void setUp() {
        lottos = new Lottos();
    }

    @Test
    @DisplayName("로또를 추가하면 목록에 저장한다.")
    public void addLottoTest() {
        lottos.add(new Lotto());
        lottos.add(new Lotto());

        assertThat(lottos.getLottoList()).hasSize(2);
    }

    @Test
    @DisplayName("당첨 로또를 기준으로 모든 로또 결과를 계산한다.")
    public void setAllLottoResultTest() {
        Lotto firstRankLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto thirdRankLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        lottos.add(firstRankLotto);
        lottos.add(thirdRankLotto);
        lottos.setAllLottoResult(winningLotto);

        assertThat(firstRankLotto.getLottoRank()).isEqualTo(LottoRank.FIRST);
        assertThat(thirdRankLotto.getLottoRank()).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("당첨 결과를 입력하면 당첨금 총액을 반환한다.")
    public void getLottoSumTest() {
        Lotto firstRankLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto thirdRankLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        lottos.add(firstRankLotto);
        lottos.add(thirdRankLotto);
        lottos.setAllLottoResult(winningLotto);

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
