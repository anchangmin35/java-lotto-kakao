package lotto.domain;

import java.util.*;

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

    // 모든 로또의 결과(Result) 반환
    public Result calculateAllLottosResult(WinningLotto winningLotto) {
        Map<LottoRank, Integer> resultMap = new HashMap<>();

        for (Lotto lotto : this.lottoList) {
            LottoRank lottoRank = lotto.calculateLottoRank(winningLotto);
            resultMap.merge(lottoRank, 1, Integer::sum);   // 없으면 1넣고, 있다면 기존값+1
        }

        // 없는 당첨 결과는 0으로 채움
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> resultMap.putIfAbsent(lottoRank, 0));

        return new Result(resultMap);
    }
}
