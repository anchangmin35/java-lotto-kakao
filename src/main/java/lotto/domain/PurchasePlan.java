package lotto.domain;

public record PurchasePlan(int totalCount, int manualCount) {

    public PurchasePlan {
        validateManualCount(totalCount, manualCount);
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }

    private static void validateManualCount(int totalCount, int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 로또는 0개 이상 구매 가능합니다. (음수 입력 불가)");
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 가능 개수를 초과했습니다. (최대 " + totalCount + "장 구매 가능합니다.)");
        }
    }
}
