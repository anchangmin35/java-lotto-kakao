package lotto;

import java.util.*;

import static lotto.LottoNumberValidator.*;

public class WinningLotto {
    // manual
    private WinningLottoNumbers winningLottoNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(String input, String bonusInput) {
        this.winningLottoNumbers = new WinningLottoNumbers(input);  // 6개 입력
        parseBonusNumber(bonusInput); // 보너스 점수 입력
    }

    public WinningLottoNumbers getWinningLottoNumbers() {
        return this.winningLottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }

    public void parseBonusNumber(String input) {
        validateString(input);
        validateRange(Integer.parseInt(input));

        validateDistinctBonusNumber(this.winningLottoNumbers.getLottoNumberList(), Integer.parseInt(input));
        bonusNumber = LottoNumber.from(Integer.parseInt(input));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return winningLottoNumbers.getLottoNumberList().contains(lottoNumber);
    }
}
