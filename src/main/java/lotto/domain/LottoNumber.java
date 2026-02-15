package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lotto.domain.LottoNumberValidator.*;

public class LottoNumber {

    static final int LOTTO_NUMBER_START = 1;
    static final int LOTTO_NUMBER_END = 45;

    private static final List<LottoNumber> LOTTO_NUMBER_CACHE = createCache();
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validateRange(number);      // 범위 사이의 숫자인지 검증
        return LOTTO_NUMBER_CACHE.get(number - LOTTO_NUMBER_START);
    }

    private static List<LottoNumber> createCache() {
        List<LottoNumber> cache = new ArrayList<>();
        for (int i = LOTTO_NUMBER_START; i <= LOTTO_NUMBER_END; i++) {
            cache.add(new LottoNumber(i));
        }
        return cache;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
