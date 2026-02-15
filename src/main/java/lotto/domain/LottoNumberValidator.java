package lotto.domain;

import java.util.List;

import static lotto.domain.LottoNumber.LOTTO_NUMBER_END;
import static lotto.domain.LottoNumber.LOTTO_NUMBER_START;
import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;
import static lotto.domain.Money.LOTTO_PRICE;

public class LottoNumberValidator {

    // List<Integer> 를 넘겨서 사이즈 유효성 검사
    public static void validateSize(List<Integer> numberList) {
        if (numberList.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
        }
    }

    public static void validateString(String substring) {
        if (substring.isEmpty() || !substring.matches("^-?[0-9]+$")) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }

    public static void validateRange(Integer number) {
        if (number < LOTTO_NUMBER_START || number > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException(LOTTO_NUMBER_START + " ~ " +  LOTTO_NUMBER_END + " 사이의 숫자를 입력해주세요.");
        }
    }

    public static void validateDistinctNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if (lottoNumberList.contains(LottoNumber.from(number))) {
            throw new IllegalArgumentException("로또에 중복된 숫자가 존재합니다.");
        }
    }

    public static void validateDistinctBonusNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if (lottoNumberList.contains(LottoNumber.from(number))) {
            throw new IllegalArgumentException("당첨 번호와 보너스 볼의 번호가 일치합니다.");
        }
    }

    public static void validatePurchaseMoneyRange(Money money) {
        if (money.isLessThan(Money.from(LOTTO_PRICE))) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상의 금액을 입력해야 합니다.");
        }
    }

    public static void validatePurchaseManualLotto(int totalCount, int manualCount) {
        if(manualCount < 0) {
            throw new IllegalArgumentException("0 이상의 개수를 입력해주세요.");
        }

        if(manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 가능 개수를 초과했습니다.");
        }
    }
}
