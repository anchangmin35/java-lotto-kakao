package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.domain.LottoNumberValidator.validatePurchaseManualLotto;

public class LottoController {

    private final Lottos lottos;

    public LottoController() {
        lottos = new Lottos();
    }

    public void run() {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        Money purchaseAmount = inputPurchaseAmount(outputView, inputView);
        int totalCount = calculateLottoCount(purchaseAmount);

        int manualCount = inputManualLottoCount(outputView, inputView, totalCount);   // 수동 구매 개수
        purchaseManualLotto(outputView, inputView, manualCount);    // 로또 수동 구매

        printPurchaseAllLotto(totalCount, manualCount, outputView);
        WinningLotto winningLotto = inputAndCreateWinningLotto(outputView, inputView);

        setAllLottoResult(winningLotto);
        printResult(purchaseAmount, outputView);
    }

    private void purchaseManualLotto(OutputView outputView, InputView inputView, int manualCount) {
        if(manualCount > 0) {
            outputView.printManualPurchase();
        }
        for (int i = 0; i < manualCount; i++) {
            List<Integer> inputList = inputView.inputLottoNumbers();
            lottos.purchaseManualLotto(inputList);
        }
    }

    private int inputManualLottoCount(OutputView outputView, InputView inputView, int totalCount) {
        outputView.printManualCount();
        int manualCount = inputView.inputInteger();

        validatePurchaseManualLotto(totalCount, manualCount);
        return manualCount;
    }

    private void printResult(Money purchaseAmount, OutputView outputView) {
        outputView.printResult(this.lottos);
        outputView.printRateOfReturn(purchaseAmount, this.lottos);
    }

    private WinningLotto inputAndCreateWinningLotto(OutputView outputView, InputView inputView) {
        outputView.printWinningLottoInput();
        List<Integer> winningNumberList = inputView.inputLottoNumbers();

        outputView.printBonusNumberInput();
        Integer bonusNumber = inputView.inputInteger();

        return createWinningLotto(winningNumberList, bonusNumber);
    }

    private Money inputPurchaseAmount(OutputView outputView, InputView inputView) {
        outputView.printPurchaseAmountInput();
        return Money.from(inputView.inputInteger());
    }

    private int calculateLottoCount(Money purchaseAmount) {
        return purchaseAmount.calculateLottoCount();
    }

    private void printPurchaseAllLotto(int totalCount, int manualCount, OutputView outputView) {
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
    private void setAllLottoResult(WinningLotto winningLotto) {
        lottos.setAllLottoResult(winningLotto);
    }
}
