package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return List.copyOf(cache);
    }

    public int getNumber() {
        return number;
    }

    // 로또 숫자가 범위에 맞는지 검증
    private static void validateRange(Integer number) {
        if (number < LOTTO_NUMBER_START || number > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException(LOTTO_NUMBER_START + " ~ " + LOTTO_NUMBER_END + " 사이의 숫자를 입력해주세요.");
        }
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
