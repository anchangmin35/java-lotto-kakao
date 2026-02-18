package lotto.domain;

import java.util.List;

import static lotto.domain.LottoNumberValidator.validateSize;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public static Lotto random() {
        return new Lotto(LottoNumbers.random());
    }

    public static Lotto from(List<Integer> numberList) {
        validateSize(numberList);   // 로또 숫자가 6개인지 검증
        return new Lotto(LottoNumbers.from(numberList));
    }

    private Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoNumbers getLottoNumbers() {
        return this.lottoNumbers;
    }

    public LottoRank calculateLottoRank(WinningLotto winningLotto) {
        int matchCount = winningLotto.countMatchWithWinningNumbers(this.lottoNumbers);
        boolean bonusCount = winningLotto.isContainBonusNumber(this.lottoNumbers);

        return LottoRank.valueOf(matchCount, bonusCount);
    }

    // 로또 번호들을 리스트 형태로 반환
    public List<Integer> toList() {
        return this.lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
