package lotto.domain;

import java.util.Objects;

public class Money {
    public static final int LOTTO_PRICE = 1_000;
    public static final int PURCHASE_UPPER_LIMIT = 10_000_000;    // 구매 금액 상한선

    private final long amount;

    private Money(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
        this.amount = amount;
    }

    public static Money from(long amount) {
        validatePurchaseMoneyRange(amount); // 구매 금액의 유효성 판별
        return new Money(amount);
    }

    public long getAmount() {
        return amount;
    }

    public long divideBy(Money other) {
        return this.amount / other.amount;
    }

    public Money multiplyBy(int multiplier) {
        return new Money(this.amount * multiplier);
    }

    // 구매 가능한 갯수 반환
    public int calculateLottoCount() {
        return Math.toIntExact(this.divideBy(Money.from(LOTTO_PRICE)));
    }

    private static void validatePurchaseMoneyRange(long purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상의 금액을 입력해야 합니다.");
        }

        if (purchaseAmount > PURCHASE_UPPER_LIMIT) {
            throw new IllegalArgumentException(PURCHASE_UPPER_LIMIT + "원 이하의 금액을 입력해야 합니다.");
        }
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
