package lotto;

import java.util.List;

public class Lotto {
    // automatic
    private final LottoNumbers lottoNumbers;
    private LottoRank lottoRank;

    public LottoNumbers getLottoNumbers() {
        return this.lottoNumbers;
    }

    public Lotto() {
        this(LottoNumbers.random());
    }

    public static Lotto from(List<Integer> numberList) {
        return new Lotto(LottoNumbers.from(numberList));
    }

    private Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        this.lottoRank = LottoRank.PENDING;
    }

    public LottoRank getLottoRank() {
        return this.lottoRank;
    }

    // 구매한 로또 리스트 반환
    public List<Integer> getLottoNumbersAsList() {
        // 로또 번호(Integer)만 담긴 리스트로 변환
        return lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();
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
}
