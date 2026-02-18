package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PurchasePlan;
import lotto.domain.Result;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {
        Lottos lottos = new Lottos();

        Money purchaseAmount = inputPurchaseAmount();           // 구매 금액 입력
        int totalCount = calculateLottoCount(purchaseAmount);   // 총 로또 구매 장 수 계산

        PurchasePlan purchasePlan = inputPurchasePlan(totalCount);   // 수동/자동 구매 계획 생성
        runManualPurchaseFlow(purchasePlan.manualCount(), lottos);   // 수동 구매 출력 및 진행
        purchaseAutoLotto(purchasePlan.getAutoCount(), lottos);      // 자동 구매

        printAllPurchasedLotto(purchasePlan, lottos);                // 모든 로또(수동 + 자동) 출력
        WinningLotto winningLotto = inputAndCreateWinningLotto();    // 당첨 로또 입력 및 생성

        Result result = calculateAllLottosResult(lottos, winningLotto);   // 모든 로또에 대한 Result 생성
        printResult(purchaseAmount, result);                              // 최종 결과 출력
    }

    // 수동 구매 관련 출력 + 수동 구매 진행
    private void runManualPurchaseFlow(int manualCount, Lottos lottos) {
        printManualPurchaseGuide(manualCount);                  // 수동으로 몇 장 구매했는지 출력
        purchaseManualLotto(lottos, manualCount);               // 로또 수동 구매
    }

    private void printManualPurchaseGuide(int manualCount) {
        if (manualCount > 0) {
            outputView.printManualPurchase();
        }
    }

    private void purchaseManualLotto(Lottos lottos, int manualCount) {
        for (int i = 0; i < manualCount; i++) {
            List<Integer> inputList = inputView.inputLottoNumbers();
            lottos.purchaseManualLotto(inputList);
        }
    }

    private PurchasePlan inputPurchasePlan(int totalCount) {
        outputView.printManualCount();
        int manualCount = inputView.inputInteger();

        return new PurchasePlan(totalCount, manualCount);
    }

    private Result calculateAllLottosResult(Lottos lottos, WinningLotto winningLotto) {
        return lottos.calculateAllLottosResult(winningLotto);
    }

    private void printResult(Money purchaseAmount, Result result) {
        outputView.printResult(result);
        double rateOfReturn = result.getRateOfReturn(purchaseAmount);
        outputView.printRateOfReturn(rateOfReturn);
    }

    private WinningLotto inputAndCreateWinningLotto() {
        outputView.printWinningLottoInput();
        List<Integer> winningNumberList = inputView.inputLottoNumbers();

        outputView.printBonusNumberInput();
        Integer bonusNumber = inputView.inputInteger();

        return createWinningLotto(winningNumberList, bonusNumber);
    }

    private Money inputPurchaseAmount() {
        outputView.printPurchaseAmountInput();
        int amount = inputView.inputInteger();
        return Money.from(amount);
    }

    private int calculateLottoCount(Money money) {
        return money.calculateLottoCount();
    }

    private void purchaseAutoLotto(int autoCount, Lottos lottos) {
        if (autoCount > 0) {
            lottos.purchaseAutomaticLotto(autoCount);
        }
    }

    private void printAllPurchasedLotto(PurchasePlan purchasePlan, Lottos lottos) {
        int totalCount = purchasePlan.totalCount();
        int manualCount = purchasePlan.manualCount();
        outputView.printPurchaseAmount(totalCount, manualCount);
        outputView.printLottoNumbers(lottos);
    }

    // 당첨 로또 생성
    public WinningLotto createWinningLotto(List<Integer> winningNumberList, Integer bonusNumber) {
        return WinningLotto.from(winningNumberList, bonusNumber);
    }
}
