package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lotto.domain.LottoNumber.LOTTO_NUMBER_END;
import static lotto.domain.LottoNumber.LOTTO_NUMBER_START;

public class LottoNumbers {

    public static final int LOTTO_NUMBER_SIZE = 6;
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
    public static LottoNumbers from(List<Integer> inputNumberList) {
        validateSize(inputNumberList);  // 로또 숫자 사이즈 검증

        List<LottoNumber> numbers = new ArrayList<>();
        for (Integer inputNumber : inputNumberList) {
            validateDistinctNumber(numbers, inputNumber);   // 중복 숫자가 있는 지 검증
            numbers.add(LottoNumber.from(inputNumber));
        }

        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new LottoNumbers(numbers);
    }

    private LottoNumbers(List<LottoNumber> lottoNumberList) {
        this.lottoNumberList = List.copyOf(lottoNumberList);
    }

    public List<Integer> toNumberList() {
        return lottoNumberList.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumberList.contains(lottoNumber);
    }

    public int countMatchingNumbers(LottoNumbers other) {
        return (int) other.lottoNumberList.stream()
                .filter(this::contains)
                .count();
    }

    // 로또 숫자가 6개 인지 검증
    private static void validateSize(List<Integer> numberList) {
        if (numberList.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
        }
    }

    // 로또에 중복된 숫자가 있는지 검증
    private static void validateDistinctNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if (lottoNumberList.contains(LottoNumber.from(number))) {
            throw new IllegalArgumentException("로또에 중복된 숫자가 존재합니다.");
        }
    }
}
