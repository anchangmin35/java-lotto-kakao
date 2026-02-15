package lotto.domain;

import java.util.List;

import static lotto.domain.LottoNumberValidator.validateSize;

public class Lotto {

    private final LottoNumbers lottoNumbers;
    private LottoRank lottoRank;

    public static Lotto random() {
        return new Lotto(LottoNumbers.random());
    }

    public static Lotto from(List<Integer> numberList) {
        validateSize(numberList);   // 로또 숫자가 6개인지 검증
        return new Lotto(LottoNumbers.from(numberList));
    }

    private Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        this.lottoRank = LottoRank.PENDING;
    }

    public LottoNumbers getLottoNumbers() {
        return this.lottoNumbers;
    }

    public LottoRank getLottoRank() {
        return this.lottoRank;
    }

    public void evaluateRank(WinningLotto winningLotto) {
        int matchCount = 0;
        boolean bonusCount = isContainBonusNumber(winningLotto.getBonusNumber().getNumber());

        for (LottoNumber lottoNumber : lottoNumbers.getLottoNumberList()) {
            matchCount += countMatch(lottoNumber, winningLotto);
        }

        this.lottoRank = calculateLottoRank(matchCount, bonusCount);
    }

    public boolean isContainBonusNumber(int bonusNumber) {
        return lottoNumbers.getLottoNumberList().contains(LottoNumber.from(bonusNumber));
    }

    public int countMatch(LottoNumber lottoNumber, WinningLotto winningLotto) {
        if (winningLotto.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    // 로또의 결과 Enum 반환
    public LottoRank calculateLottoRank(int matchCount, boolean bonusCount) {
        return LottoRank.valueOf(matchCount, bonusCount);
    }

    // 로또 번호들을 리스트 형태로 반환
    public List<Integer> toList() {
        return this.lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
