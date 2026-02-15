package lotto.domain;

import java.util.List;

import static lotto.domain.LottoNumberValidator.*;

public class WinningLotto {

    private final WinningLottoNumbers winningLottoNumbers;
    private final LottoNumber bonusNumber;

    public static WinningLotto from(List<Integer> winningNumberList, Integer bonusNumber) {
        validateSize(winningNumberList);    // 리스트 사이즈 검증
        WinningLottoNumbers winningLottoNumbers = WinningLottoNumbers.from(winningNumberList);
        validateDistinctBonusNumber(winningLottoNumbers.getLottoNumberList(), bonusNumber); // 당첨 번호와 보너스 볼이 일치하지 않는지 검증

        return new WinningLotto(winningLottoNumbers, LottoNumber.from(bonusNumber));
    }

    private WinningLotto(WinningLottoNumbers winningLottoNumbers, LottoNumber bonusNumber) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningLottoNumbers getWinningLottoNumbers() {
        return this.winningLottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return winningLottoNumbers.getLottoNumberList().contains(lottoNumber);
    }
}
