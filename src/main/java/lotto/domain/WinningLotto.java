package lotto.domain;

import java.util.List;

import static lotto.domain.LottoNumberValidator.*;

public class WinningLotto {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public static WinningLotto from(List<Integer> winningNumberList, Integer bonusNumber) {
        validateSize(winningNumberList);    // 리스트 사이즈 검증
        LottoNumbers lottoNumbers = LottoNumbers.from(winningNumberList);
        validateDistinctBonusNumber(lottoNumbers.getLottoNumberList(), bonusNumber); // 당첨 번호와 보너스 볼이 일치하지 않는지 검증

        return new WinningLotto(lottoNumbers, LottoNumber.from(bonusNumber));
    }

    private WinningLotto(LottoNumbers winningLottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getWinningLottoNumbers() {
        return this.lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.getLottoNumberList().contains(lottoNumber);
    }

    public int countMatchWithWinningNumbers(LottoNumbers lottoNumbers) {
        int countMatch = 0;
        for (LottoNumber lottoNumber : lottoNumbers.getLottoNumberList()) {
            if (this.contains(lottoNumber)) countMatch++;
        }

        return countMatch;
    }

    public boolean isContainBonusNumber(LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumberList().contains(LottoNumber.from(bonusNumber.getNumber()));
    }
}
