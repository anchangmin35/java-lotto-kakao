package lotto;

import java.util.Objects;

public class LottoNumber {
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        LottoNumberValidator.validateRange(number);
        return new LottoNumber(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
