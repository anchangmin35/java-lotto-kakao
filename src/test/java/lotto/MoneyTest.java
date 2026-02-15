package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.Money.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    @DisplayName("금액은 0 이상만 생성할 수 있다.")
    public void fromFailTest() {
        assertThrows(IllegalArgumentException.class, () -> Money.from(-1_000));
    }

    @Test
    @DisplayName("로또 구매 금액을 전달하면 구매할 수 있는 로또 장수를 반환한다.")
    public void calculateLottoCountTest() {
        Money money = Money.from(1_500);
        assertThat(money.calculateLottoCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 미만이면 예외를 반환한다.")
    public void calculateLottoCountExceptionTest() {
        Money money = Money.from(800);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, money::calculateLottoCount);
        assertThat(exception.getMessage()).isEqualTo(LOTTO_PRICE + "원 이상의 금액을 입력해야 합니다.");
    }

    @Test
    @DisplayName("금액 나눗셈과 곱셈을 수행한다.")
    public void calculateTest() {
        Money money = Money.from(5_000);

        assertThat(money.divideBy(Money.from(1_000))).isEqualTo(5);
        assertThat(money.multiplyBy(2)).isEqualTo(Money.from(10000));
    }

    @Test
    @DisplayName("금액 대소 비교를 수행한다.")
    public void compareTest() {
        Money money = Money.from(5_000);

        assertThat(money.isLessThan(Money.from(10_000))).isTrue();
    }
}
