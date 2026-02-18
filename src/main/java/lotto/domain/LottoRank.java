package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    MISS(0, false, 0);   // 당첨 x

    private final int countOfMatch;
    private final boolean matchBonus;
    private final long value;

    LottoRank(int countOfMatch, boolean matchBonus, long value) {
        this.countOfMatch = countOfMatch;
        this.matchBonus = matchBonus;
        this.value = value;
    }

    public boolean isMiss() {
        return this == MISS;
    }

    public long getValue() {
        return this.value;
    }

    public String formatResultMessage(int matchCount) {
        if (matchBonus) {
            return countOfMatch + "개 일치, 보너스 볼 일치(" + value + "원)- " + matchCount + "개";
        }
        return countOfMatch + "개 일치 (" + value + "원)- " + matchCount + "개";
    }

    public static LottoRank valueOf(int countOfMatch, boolean matchBonus) {
        LottoRank rankByCount = findByCount(countOfMatch);
        if (countOfMatch != 5) {
            return rankByCount;
        }
        return findByCountAndBonus(countOfMatch, matchBonus);
    }

    // 맞춘 개수만 판단하는 메서드
    private static LottoRank findByCount(int countOfMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.countOfMatch == countOfMatch)
                .findFirst()
                .orElse(MISS);
    }

    // 맞춘 개수 + 보너스 번호까지 맞는지 판단하는 메서드
    private static LottoRank findByCountAndBonus(int countOfMatch, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.countOfMatch == countOfMatch)
                .filter(rank -> rank.matchBonus == matchBonus)
                .findFirst()
                .orElse(MISS);
    }
}
