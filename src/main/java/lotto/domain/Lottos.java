package lotto.domain;

import java.util.*;
import java.util.function.Consumer;

public class Lottos {

    private final List<Lotto> lottoList;

    public static Lottos from(List<Lotto> lottoList) {
        return new Lottos(lottoList);
    }

    private Lottos(List<Lotto> lottoList) {
        this.lottoList = List.copyOf(lottoList);
    }

    public List<Lotto> getLottoList() {
        return List.copyOf(lottoList);
    }

    public void forEach(Consumer<Lotto> action) {
        lottoList.forEach(action);
    }

    // 모든 로또의 결과(Result) 반환
    public Result calculateAllLottosResult(WinningLotto winningLotto) {
        Map<LottoRank, Integer> resultMap = new HashMap<>();

        for (Lotto lotto : lottoList) {
            LottoRank lottoRank = lotto.calculateLottoRank(winningLotto);
            resultMap.merge(lottoRank, 1, Integer::sum);   // 없으면 1넣고, 있다면 기존값+1
        }

        // 없는 당첨 결과는 0으로 채움
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> resultMap.putIfAbsent(lottoRank, 0));

        return new Result(resultMap);
    }
}
