package lotto;

import java.util.List;

import static lotto.LottoNumber.LOTTO_NUMBER_END;
import static lotto.LottoNumber.LOTTO_NUMBER_START;
import static lotto.LottoNumbers.LOTTO_NUMBER_SIZE;
import static lotto.Money.LOTTO_PRICE;

public class LottoNumberValidator {

    public static void validateSize(String[] strings) {
        if (strings.length != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
        }
    }

    // List<Integer> 를 넘겨서 사이즈 유효성 검사
    public static void validateSize(List<Integer> numberList) {
        if (numberList.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
        }
    }

    public static void validateString(String substring) {
        if (!substring.matches("^[0-9]*$")) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }

    public static void validateRange(Integer number) {
        if (number < LOTTO_NUMBER_START || number > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException("범위를 벗어난 숫자입니다.");
        }
    }

    public static void validateDistinctNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if (lottoNumberList.contains(LottoNumber.from(number))) {
            throw new IllegalArgumentException("로또에 중복된 숫자가 존재합니다.");
        }
    }

    public static void validateDistinctBonusNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if (lottoNumberList.contains(LottoNumber.from(number))) {
            throw new IllegalArgumentException("로또에 보너스와 중복된 숫자가 존재합니다.");
        }
    }

    public static void validatePurchaseMoneyRange(Money money) {
        if (money.isLessThan(Money.from(LOTTO_PRICE))) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상의 금액을 입력해야 합니다.");
        }
    }
}
