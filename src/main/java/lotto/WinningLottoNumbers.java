package lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.LottoNumberValidator.validateDistinctNumber;
import static lotto.LottoNumberValidator.validateRange;
import static lotto.LottoNumberValidator.validateSize;
import static lotto.LottoNumberValidator.validateString;

public class WinningLottoNumbers {

    private List<LottoNumber> lottoNumberList;


    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }

    public WinningLottoNumbers(String input) {
        parseLottoNumbers(input);
    }

    private void parseLottoNumbers(String input) {
        lottoNumberList = new ArrayList<>();

        String[] strings = input.split(", ");
        validateSize(strings);
        for (String s : strings) {
            validateString(s);
            validateRange(Integer.parseInt(s));
            validateDistinctNumber(this.lottoNumberList, Integer.parseInt(s));
            lottoNumberList.add(LottoNumber.from(Integer.parseInt(s)));
        }
    }
}
