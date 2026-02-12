package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumberList;

    // 랜덤 생성을 위한 정적 팩토리 메서드
    public static LottoNumbers random() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(LottoNumber.from(i));
        }
        Collections.shuffle(numbers);

        List<LottoNumber> selected = new ArrayList<>(numbers.subList(0, 6));
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
