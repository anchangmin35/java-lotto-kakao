package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Result;

public class OutputView {

    public void printPurchaseAmountInput() {
        System.out.println("구입 금액을 입력해 주세요.");
    }

    public void printPurchaseAmount(int totalCount, int manualCount) {
        System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + (totalCount - manualCount) + "장을 구매했습니다.");
    }

    public void printWinningLottoInput() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printLottoNumbers(Lottos lottos) {
        lottos.forEach(this::printLottoNumbers);
    }

    private void printLottoNumbers(Lotto lotto) {
        System.out.println(lotto.toList());
    }

    // 당첨 결과 출력
    public void printResult(Result result) {
        System.out.println("\n당첨 통계\n-------------");
        for (LottoRank lottoRank : LottoRank.values()) {
            printResultWithLottoRank(lottoRank, result.getCount(lottoRank));
        }
    }

    private void printResultWithLottoRank(LottoRank lottoRank, Integer matchCount) {
        if (lottoRank.isMiss()) {
            return;
        }

        System.out.println(lottoRank.formatResultMessage(matchCount));
    }

    // 수익률 출력
    public void printRateOfReturn(double rateOfReturn) {
        System.out.print("총 수익률은 " + rateOfReturn + "입니다.(기준이 1이기 때문에 결과적으로 ");

        if (rateOfReturn < 1.0) {
            System.out.println("손해라는 의미임)");
        }
        if (rateOfReturn == 1.0) {
            System.out.println("본전임 한번 더!)");
        }
        if (rateOfReturn > 1.0) {
            System.out.println("이득이라는 의미임)");
        }
    }

    public void printManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualPurchase() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printErrorMessage(String message) {
        System.out.println("=============== [에러] " + message + " ===============");
    }
}
