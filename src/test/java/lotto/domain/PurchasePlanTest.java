package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchasePlanTest {

    @Test
    @DisplayName("수동/자동 구매 개수를 계산한다.")
    public void createPurchasePlanTest() {
        PurchasePlan purchasePlan = new PurchasePlan(8, 3);

        assertThat(purchasePlan.totalCount()).isEqualTo(8);
        assertThat(purchasePlan.manualCount()).isEqualTo(3);
        assertThat(purchasePlan.getAutoCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("수동 구매 개수가 음수면 예외를 반환한다.")
    public void createPurchasePlanFailNegativeManualCountTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new PurchasePlan(8, -1));
        assertThat(exception.getMessage()).isEqualTo("수동 로또는 0개 이상 구매 가능합니다. (음수 입력 불가)");
    }

    @Test
    @DisplayName("수동 구매 개수가 총 구매 개수를 초과하면 예외를 반환한다.")
    public void createPurchasePlanFailExceedManualCountTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new PurchasePlan(8, 9));
        assertThat(exception.getMessage()).isEqualTo("수동 구매 가능 개수를 초과했습니다. (최대 8장 구매 가능합니다.)");
    }
}
