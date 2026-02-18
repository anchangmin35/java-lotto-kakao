package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public static WinningLotto from(List<Integer> winningNumberList, Integer bonusNumber) {
        LottoNumbers lottoNumbers = LottoNumbers.from(winningNumberList);
        validateDistinctBonusNumber(lottoNumbers, bonusNumber); // 당첨 번호와 보너스 볼이 일치하지 않는지 검증

        return new WinningLotto(lottoNumbers, LottoNumber.from(bonusNumber));
    }

    private WinningLotto(LottoNumbers winningLottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    int countMatchWithWinningNumbers(LottoNumbers purchasedLottoNumbers) {
        return lottoNumbers.countMatchingNumbers(purchasedLottoNumbers);
    }

    boolean isContainBonusNumber(LottoNumbers purchasedLottoNumbers) {
        return purchasedLottoNumbers.contains(bonusNumber);
    }

    private static void validateDistinctBonusNumber(LottoNumbers lottoNumbers, Integer number) {
        LottoNumber lottoNumber = LottoNumber.from(number);
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 볼의 번호가 일치합니다.");
        }
    }
}
