package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.domain.LottoNumberValidator.validatePurchaseManualLotto;

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

        int manualCount = inputManualLottoCount(totalCount);    // 수동 구매 개수
        runManualPurchaseFlow(manualCount, lottos);             // 수동 구매 출력 및 진행
        purchaseAutoLotto(totalCount, manualCount, lottos);     // 자동 구매

        printAllPurchasedLotto(totalCount, manualCount, lottos);    // 모든 로또(수동 + 자동) 출력
        WinningLotto winningLotto = inputAndCreateWinningLotto();   // 당첨 로또 입력 및 생성

        setAllLottoResult(winningLotto, lottos);    // 모든 로또 결과 설정
        printResult(purchaseAmount, lottos);        // 최종 결과 출력
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

    private int inputManualLottoCount(int totalCount) {
        outputView.printManualCount();
        int manualCount = inputView.inputInteger();

        validatePurchaseManualLotto(totalCount, manualCount);
        return manualCount;
    }

    private void printResult(Money purchaseAmount, Lottos lottos) {
        outputView.printResult(lottos);
        double rateOfReturn = lottos.getRateOfReturn(purchaseAmount, lottos.getLottoSum());
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
        return Money.from(inputView.inputInteger());
    }

    private int calculateLottoCount(Money money) {
        return money.calculateLottoCount();
    }

    private void purchaseAutoLotto(int totalCount, int manualCount, Lottos lottos) {
        if(totalCount - manualCount > 0) {
            lottos.purchaseAutomaticLotto(totalCount - manualCount);   // (전체 - 수동)만큼의 자동 로또 구매
        }
    }

    private void printAllPurchasedLotto(int totalCount, int manualCount, Lottos lottos) {
        outputView.printPurchaseAmount(totalCount, manualCount);
        outputView.printLottoNumbers(lottos);
    }

    // 당첨 로또 생성
    public WinningLotto createWinningLotto(List<Integer> winningNumberList, Integer bonusNumber) {
        return WinningLotto.from(winningNumberList, bonusNumber);
    }

    // 모든 로또 결과 설정
    private void setAllLottoResult(WinningLotto winningLotto, Lottos lottos) {
        lottos.setAllLottoResult(winningLotto);
    }
}
