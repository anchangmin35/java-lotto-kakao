package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public static WinningLotto from(List<Integer> winningNumberList, Integer bonusNumber) {
        LottoNumbers lottoNumbers = LottoNumbers.from(winningNumberList);
        validateDistinctBonusNumber(lottoNumbers.getLottoNumberList(), bonusNumber); // 당첨 번호와 보너스 볼이 일치하지 않는지 검증

        return new WinningLotto(lottoNumbers, LottoNumber.from(bonusNumber));
    }

    private WinningLotto(LottoNumbers winningLottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.getLottoNumberList().contains(lottoNumber);
    }

    int countMatchWithWinningNumbers(LottoNumbers lottoNumbers) {
        int countMatch = 0;
        for (LottoNumber lottoNumber : lottoNumbers.getLottoNumberList()) {
            if (this.contains(lottoNumber)) countMatch++;
        }

        return countMatch;
    }

    boolean isContainBonusNumber(LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumberList().contains(LottoNumber.from(bonusNumber.getNumber()));
    }

    private static void validateDistinctBonusNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if (lottoNumberList.contains(LottoNumber.from(number))) {
            throw new IllegalArgumentException("당첨 번호와 보너스 볼의 번호가 일치합니다.");
        }
    }
}
