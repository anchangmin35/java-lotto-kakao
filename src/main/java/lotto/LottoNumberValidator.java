package lotto;

import java.util.List;

public class LottoNumberValidator {

    public static void validateSize(String[] strings) {
        if(strings.length != 6) throw new IllegalArgumentException("6개의 숫자를 입력해야 합니다.");
    }

    public static void validateString(String substring) {
        if (!substring.matches("^[0-9]*$")) throw new NumberFormatException("숫자가 아닙니다.");
    }

    public static void validateRange(Integer number) {
        if (number < 1 || number > 45) throw new IllegalArgumentException("범위를 벗어난 숫자입니다.");
    }

    public static void validateDistinctNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if(lottoNumberList.contains(LottoNumber.from(number))) throw new IllegalArgumentException("로또에 중복된 숫자가 존재합니다.");
    }

    public static void validateDistinctBonusNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if(lottoNumberList.contains(LottoNumber.from(number))) throw new IllegalArgumentException("로또에 보너스와 중복된 숫자가 존재합니다.");
    }

    public static void validatePurchaseMoneyRange(int money) {
        if(money < 1000) throw new IllegalArgumentException("1000원 이상의 금액을 입력해야 합니다.");
    }
}
