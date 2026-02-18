package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottoList;

    public Lottos() {
        this.lottoList = new ArrayList<>();
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public void add(Lotto lotto) {
        this.lottoList.add(lotto);
    }

    public void setAllLottoResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottoList) {
            lotto.evaluateRank(winningLotto);
        }
    }

    // 로또 리스트들의 당첨금 총액 반환
    public Money getLottoSum() {
        long sum = 0;
        for (Lotto lotto : lottoList) {
            sum += lotto.getLottoRank().getValue();
        }

        return Money.from(sum);
    }

    // 수익률 반환
    public double getRateOfReturn(Money purchaseAmount, Money winningMoney) {
        double rate = (double) winningMoney.getAmount() / purchaseAmount.getAmount();

        return Math.floor(rate * 100) / 100.0;
    }

    // 특정 Enum(몇개 당첨인지) 개수 카운트
    public int countEnum(LottoRank lottoRank) {
        int count = 0;
        for (Lotto lotto : lottoList) {
            if (lotto.getLottoRank() == lottoRank) {
                count++;
            }
        }

        return count;
    }

    // 자동 로또 구매
    public void purchaseAutomaticLotto(int count) {
        for (int i = 0; i < count; i++) {
            this.add(Lotto.random());
        }
    }

    // 수동 로또 구매
    public void purchaseManualLotto(List<Integer> inputList) {
        this.add(Lotto.from(inputList));
    }
}
