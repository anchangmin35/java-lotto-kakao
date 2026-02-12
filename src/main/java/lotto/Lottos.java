package lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.LottoGame.LOTTO_PRICE;

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
    public int getLottoSum() {
        int sum = 0;
        for (Lotto lotto : lottoList) {
            sum += (int) lotto.getLottoRank().getValue();
        }

        return sum;
    }

    // 수익률 반환
    public double getRateOfReturn(int sum) {
        double rate = (double) sum / (lottoList.size() * LOTTO_PRICE);

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

    // 로또 구매
    public void purchaseLotto(int count) {
        for (int i = 0; i < count; i++) {
            this.add(new Lotto());
        }
    }
}
