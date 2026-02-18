package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {

    @Test
    @DisplayName("랜덤 숫자 6개를 포함한 한 장의 로또를 생성한다.")
    public void generateLottoTest() {
        Lotto lotto = Lotto.random();

        assertNotNull(lotto);
        assertThat(lotto.toList()).hasSize(LOTTO_NUMBER_SIZE);
        assertThat(lotto.toList().stream().distinct().count()).isEqualTo(LOTTO_NUMBER_SIZE);
    }

    @Test
    @DisplayName("당첨 로또를 기준으로 로또의 당첨 등수를 계산한다.")
    public void calculateLottoRankTest() {
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto third = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lotto fourth = Lotto.from(List.of(1, 2, 3, 4, 9, 8));
        Lotto fifth = Lotto.from(List.of(1, 2, 3, 10, 9, 8));
        Lotto miss = Lotto.from(List.of(1, 2, 10, 11, 12, 13));

        assertThat(first.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FIRST);
        assertThat(second.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.SECOND);
        assertThat(third.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.THIRD);
        assertThat(fourth.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FOURTH);
        assertThat(fifth.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FIFTH);
        assertThat(miss.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.MISS);
    }

    @Test
    @DisplayName("3개 일치, 보너스 번호 일치일 때에는 5등을 반환한다.")
    public void calculateLottoRankWithBonusTest() {
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 7, 10, 11));

        assertThat(lotto.calculateLottoRank(winningLotto)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("수동 입력 숫자로 로또를 생성할 수 있다.")
    public void fromManualLottoTest() {
        Lotto lotto = Lotto.from(List.of(9, 1, 5, 3, 7, 2));

        assertThat(lotto.toList()).containsExactly(1, 2, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("수동 입력 숫자 개수가 6개가 아니면 예외를 반환한다.")
    public void fromManualLottoFailSizeTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Lotto.from(List.of(1, 2, 3, 4, 5)));
        assertThat(exception.getMessage()).isEqualTo(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
    }
}
