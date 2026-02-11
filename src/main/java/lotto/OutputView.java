package lotto;

import java.util.List;

public class OutputView {

    public void printPurchaseAmountInput() {
        System.out.println("구입 금액을 입력해 주세요.");
    }

    public void printPurchaseAmount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다");
    }

    public void printWinningLottoInput() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printLottoNumbers(List<List<Integer>> lottoList) {
        for (List<Integer> lotto : lottoList) {
            System.out.println(lotto);
        }
    }

    // 당첨 결과 출력
    public void printResult(LottoGame lottoGame) {
        System.out.println("\n당첨 통계\n-------------");
        for (LottoRank lottoRank : LottoRank.values()) {
            printResultWithEnum(lottoRank, lottoGame);
        }
    }

    public void printResultWithEnum(LottoRank lottoRank, LottoGame lottoGame) {
        if(lottoRank == LottoRank.PENDING) return;

        if(lottoRank.isMatchBonus()) {
            System.out.println(lottoRank.getCountOfMatch() + "개 일치, 보너스 볼 일치(" + lottoRank.getValue() + "원)- " + lottoGame.getLottos().countEnum(lottoRank) + "개");
            return;
        }
        System.out.println(lottoRank.getCountOfMatch() + "개 일치 (" + lottoRank.getValue() + "원)- " + lottoGame.getLottos().countEnum(lottoRank) + "개");
    }

    // 수익률 출력
    public void printRateOfReturn(LottoGame lottoGame) {
        double rateOfReturn = lottoGame.getLottos().getRateOfReturn(lottoGame.getLottos().getLottoSum());
        System.out.print("총 수익률은 " + rateOfReturn + "입니다.(기준이 1이기 때문에 결과적으로 ");

        if(rateOfReturn < 1.0) System.out.println("손해라는 의미임)");
        if(rateOfReturn == 1.0) System.out.println("본전임 한번 더!)");
        if(rateOfReturn > 1.0) System.out.println("이득이라는 의미임)");
    }
}


