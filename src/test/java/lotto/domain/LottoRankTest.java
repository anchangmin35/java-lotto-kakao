package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {

    @Test
    @DisplayName("MISS 여부를 반환한다.")
    void isMissTest() {
        assertThat(LottoRank.MISS.isMiss()).isTrue();
        assertThat(LottoRank.FIRST.isMiss()).isFalse();
    }

    @Test
    @DisplayName("보너스 일치 등수의 출력 메시지를 반환한다.")
    void formatResultMessageWithBonusTest() {
        String message = LottoRank.SECOND.formatResultMessage(2);

        assertThat(message).isEqualTo("5개 일치, 보너스 볼 일치(30000000원)- 2개");
    }

    @Test
    @DisplayName("일반 등수의 출력 메시지를 반환한다.")
    void formatResultMessageTest() {
        String message = LottoRank.THIRD.formatResultMessage(3);

        assertThat(message).isEqualTo("5개 일치 (1500000원)- 3개");
    }
}
