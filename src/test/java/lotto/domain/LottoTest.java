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
        assertThat(lotto.getLottoNumbers().getLottoNumberList().size()).isEqualTo(LOTTO_NUMBER_SIZE);
    }

    @Test
    @DisplayName("매칭 수와 보너스 여부에 따라 당첨 등수를 반환한다.")
    public void calculateLottoRankTest() {
        Lotto lotto = Lotto.random();

        assertThat(lotto.calculateLottoRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(lotto.calculateLottoRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(lotto.calculateLottoRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(lotto.calculateLottoRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(lotto.calculateLottoRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(lotto.calculateLottoRank(2, false)).isEqualTo(LottoRank.PENDING);
    }

    @Test
    @DisplayName("3개 일치, 보너스 번호 일치일 때에는 5등을 반환한다.")
    public void calculateLottoRankWithBonusTest() {
        Lotto lotto = Lotto.random();

        assertThat(lotto.calculateLottoRank(3, true)).isEqualTo(LottoRank.FIFTH);
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
