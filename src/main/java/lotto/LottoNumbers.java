package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lotto.LottoNumber.LOTTO_NUMBER_START;
import static lotto.LottoNumber.LOTTO_NUMBER_END;

public class LottoNumbers {

    static final int LOTTO_NUMBER_SIZE = 6;
    private final List<LottoNumber> lottoNumberList;

    // 랜덤 생성을 위한 정적 팩토리 메서드
    public static LottoNumbers random() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = LOTTO_NUMBER_START; i <= LOTTO_NUMBER_END; i++) {
            numbers.add(LottoNumber.from(i));
        }
        Collections.shuffle(numbers);

        List<LottoNumber> selected = new ArrayList<>(numbers.subList(0, LOTTO_NUMBER_SIZE));
        selected.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new LottoNumbers(selected);
    }

    // 수동 생성을 위한 정적 팩토리 메서드
    public static LottoNumbers from(List<Integer> numberList) {
        LottoNumberValidator.validateSize(numberList);

        List<LottoNumber> numbers = new ArrayList<>();

        for (Integer i : numberList) {
            LottoNumberValidator.validateDistinctNumber(numbers, i);
            numbers.add(LottoNumber.from(i));
        }

        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new LottoNumbers(numbers);
    }

    private LottoNumbers(List<LottoNumber> lottoNumberList) {
        this.lottoNumberList = new ArrayList<>(lottoNumberList);
    }

    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }
}
