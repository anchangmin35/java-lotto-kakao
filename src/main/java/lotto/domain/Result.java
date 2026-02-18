package lotto.domain;

import java.util.Map;

public record Result(Map<LottoRank, Integer> resultMap) {
    public Result(Map<LottoRank, Integer> resultMap) {
        this.resultMap = Map.copyOf(resultMap); // 방어적 복사 + 불변화
    }

    public int getCount(LottoRank lottoRank) {
        return resultMap.getOrDefault(lottoRank, 0);
    }

    public double getRateOfReturn(Money purchaseAmount) {
        long sum = resultMap.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getValue() * entry.getValue())
                .sum();

        double rate = (double) sum / purchaseAmount.getAmount();
        return Math.floor(rate * 100) / 100.0;
    }
}