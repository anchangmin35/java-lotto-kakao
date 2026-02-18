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
            throw new IllegalArgumentException("0 이상의 개수를 입력해주세요.");
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 가능 개수를 초과했습니다.");
        }
    }
}
