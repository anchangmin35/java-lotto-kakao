package lotto;

import java.util.*;

public class LottoNumbers {

    private List<LottoNumber> lottoNumberList;

    public LottoNumbers() {
        generateRandomNumbers();    // 랜덤 6개 숫자 생성
        sortLottoNumberList();      // sorting
    }

    // 테스트 용 직접 로또 번호 생성을 위한 생성자
    public LottoNumbers(List<Integer> numberList) {
        List<LottoNumber> numbers = new ArrayList<>();

        for (Integer i : numberList) {
            numbers.add(LottoNumber.from(i));
        }
        this.lottoNumberList = numbers;
        sortLottoNumberList();
    }

    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }

    private void generateRandomNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(LottoNumber.from(i));
        }
        Collections.shuffle(numbers);

        this.lottoNumberList = numbers.subList(0, 6);
    }

    public void sortLottoNumberList() {
        this.lottoNumberList.sort(new Comparator<LottoNumber>() {
            @Override
            public int compare(LottoNumber o1, LottoNumber o2) {
                return Integer.compare(o1.getNumber(), o2.getNumber());
            }
        });
    }
}
