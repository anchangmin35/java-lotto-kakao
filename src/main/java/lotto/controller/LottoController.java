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

        Money purchaseAmount = inputPurchaseAmount();
        int totalCount = calculateLottoCount(purchaseAmount);

        int manualCount = inputManualLottoCount(totalCount);   // 수동 구매 개수
        purchaseManualLotto(lottos, manualCount);    // 로또 수동 구매

        printPurchaseAllLotto(totalCount, manualCount, lottos);
        WinningLotto winningLotto = inputAndCreateWinningLotto();

        setAllLottoResult(winningLotto, lottos);
        printResult(purchaseAmount, lottos);
    }

    private void purchaseManualLotto(Lottos lottos, int manualCount) {
        if(manualCount > 0) {
            outputView.printManualPurchase();
        }
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
        outputView.printRateOfReturn(purchaseAmount, lottos);
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

    private int calculateLottoCount(Money purchaseAmount) {
        return purchaseAmount.calculateLottoCount();
    }

    private void printPurchaseAllLotto(int totalCount, int manualCount, Lottos lottos) {
        if(totalCount - manualCount > 0) {
            lottos.purchaseAutomaticLotto(totalCount - manualCount);   // (전체 - 수동)만큼의 자동 로또 구매
        }
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
