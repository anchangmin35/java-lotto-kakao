package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResultTest {

    @Test
    @DisplayName("존재하지 않는 등수의 개수는 0을 반환한다.")
    public void getCountDefaultZeroTest() {
        Result result = new Result(Map.of(LottoRank.FIFTH, 1));

        assertThat(result.getCount(LottoRank.FIRST)).isZero();
    }

    @Test
    @DisplayName("생성자에 전달된 맵이 바뀌어도 결과는 변경되지 않는다.")
    public void defensiveCopyTest() {
        Map<LottoRank, Integer> source = new HashMap<>();
        source.put(LottoRank.FIRST, 1);

        Result result = new Result(source);
        source.put(LottoRank.FIRST, 3);

        assertThat(result.getCount(LottoRank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("결과 맵은 외부에서 수정할 수 없다.")
    public void immutableResultMapTest() {
        Result result = new Result(Map.of(LottoRank.FIRST, 1));

        assertThrows(UnsupportedOperationException.class, () -> result.resultMap().put(LottoRank.FIRST, 2));
    }

    @Test
    @DisplayName("당첨 결과와 구매 금액으로 수익률을 계산한다.")
    public void getRateOfReturnTest() {
        Result result = new Result(Map.of(
                LottoRank.FIRST, 2,
                LottoRank.THIRD, 1
        ));

        assertThat(result.getRateOfReturn(Money.from(3_000))).isEqualTo(1_333_833.33, within(0.0001));
    }
}
