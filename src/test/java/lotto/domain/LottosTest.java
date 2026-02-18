package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class LottosTest {

    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 8)));

        lottos = Lottos.from(purchasedLottos);
        winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    @DisplayName("전달한 로또 목록으로 Lottos를 생성한다.")
    public void createLottosTest() {
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
        purchasedLottos.add(Lotto.from(List.of(7, 8, 9, 10, 11, 12)));
        lottos = Lottos.from(purchasedLottos);

        assertThat(lottos.getLottoList()).hasSize(2);
    }

    @Test
    @DisplayName("생성 시 원본 목록 변경이 내부 상태에 영향을 주지 않는다.")
    public void createLottosWithDefensiveCopyTest() {
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
        lottos = Lottos.from(purchasedLottos);

        purchasedLottos.add(Lotto.from(List.of(7, 8, 9, 10, 11, 12)));

        assertThat(lottos.getLottoList()).hasSize(1);
    }

    @Test
    @DisplayName("수동 입력 번호 목록으로 로또를 생성한다.")
    public void createLottosWithManualNumbersTest() {
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(Lotto.from(List.of(9, 1, 5, 3, 7, 2)));
        lottos = Lottos.from(purchasedLottos);

        Lotto lotto = lottos.getLottoList().getFirst();
        assertThat(lottos.getLottoList()).hasSize(1);
        assertThat(lotto.toList()).containsExactly(1, 2, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("수동 로또와 자동 로또를 함께 생성할 수 있다.")
    public void createLottosWithManualAndAutomaticTest() {
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(Lotto.from(List.of(9, 1, 5, 3, 7, 2)));
        purchasedLottos.add(Lotto.random());
        purchasedLottos.add(Lotto.random());
        lottos = Lottos.from(purchasedLottos);

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
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 8)));
        purchasedLottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
        lottos = Lottos.from(purchasedLottos);

        Result result = lottos.calculateAllLottosResult(winningLotto);

        assertThat(result.getRateOfReturn(Money.from(3_000))).isEqualTo(1_333_833.33, within(0.0001));
    }
}
