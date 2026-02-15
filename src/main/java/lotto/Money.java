package lotto;

import java.util.Objects;

import static lotto.LottoNumberValidator.validatePurchaseMoneyRange;

public class Money {
    public static final int LOTTO_PRICE = 1_000;
    private final int amount;

    private Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public int getAmount() {
        return amount;
    }

    public boolean isLessThan(Money other) {
        return this.amount < other.amount;
    }

    public int divideBy(Money other) {
        return this.amount / other.amount;
    }

    public Money multiplyBy(int multiplier) {
        return new Money(this.amount * multiplier);
    }

    // 구매 가능한 갯수 반환
    public int calculateLottoCount() {
        validatePurchaseMoneyRange(this);

        return this.divideBy(Money.from(LOTTO_PRICE));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
