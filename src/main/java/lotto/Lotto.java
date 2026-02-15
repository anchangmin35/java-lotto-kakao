package lotto;

import java.util.*;

public class Lotto {
    // automatic
    private LottoNumbers lottoNumbers;
    private LottoRank lottoRank;

    public LottoNumbers getLottoNumbers() {
        return this.lottoNumbers;
    }

    public Lotto() {
        // 유효한 랜덤 숫자 6개를 생성
        this.lottoNumbers = new LottoNumbers();
        lottoRank = LottoRank.PENDING;
    }

    // 테스트 용 직접 로또 번호 생성을 위한 생성자
    public Lotto(List<Integer> numberList) {
        this.lottoNumbers = new LottoNumbers(numberList);
    }

    public LottoRank getLottoRank() {
        return this.lottoRank;
    }

    // 테스트 용 setter
    public void setLottoRank(LottoRank lottoRank) {
        this.lottoRank = lottoRank;
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
        if(winningLotto.contains(lottoNumber)) return 1;
        return 0;
    }

    // 로또의 결과 Enum 반환
    public LottoRank calculateLottoRank(int matchCount, boolean bonusCount) {
        return LottoRank.valueOf(matchCount, bonusCount);
    }
}
