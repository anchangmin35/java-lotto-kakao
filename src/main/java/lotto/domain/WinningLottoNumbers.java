package lotto.domain;

import java.util.List;

public class WinningLottoNumbers {

    private final List<LottoNumber> lottoNumberList;

    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }

    public static WinningLottoNumbers from(List<Integer> winningNumberList) {
        return new WinningLottoNumbers(LottoNumbers.from(winningNumberList).getLottoNumberList());
    }

    private WinningLottoNumbers(List<LottoNumber> lottoNumberList) {
        this.lottoNumberList = lottoNumberList;
    }
}
