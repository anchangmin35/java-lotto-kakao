package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final Lottos lottos;

    public LottoController() {
        lottos = new Lottos();
    }

    public void run() {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        Money purchaseAmount = inputPurchaseAmount(outputView, inputView);
        int lottoCount = calculateLottoCount(purchaseAmount, outputView);

        printPurchaseLotto(lottoCount, outputView);
        WinningLotto winningLotto = inputAndCreateWinningLotto(outputView, inputView);

        setAllLottoResult(winningLotto);
        printResult(purchaseAmount, outputView);
    }

    private void printResult(Money purchaseAmount, OutputView outputView) {
        outputView.printResult(this.lottos);
        outputView.printRateOfReturn(purchaseAmount, this.lottos);
    }

    private WinningLotto inputAndCreateWinningLotto(OutputView outputView, InputView inputView) {
        outputView.printWinningLottoInput();
        String winningNumbers = inputView.inputLottoNumber();

        outputView.printBonusNumberInput();
        String bonusNumber = inputView.inputLottoNumber();

        return createWinningLotto(winningNumbers, bonusNumber);
    }

    private Money inputPurchaseAmount(OutputView outputView, InputView inputView) {
        outputView.printPurchaseAmountInput();
        return Money.from(inputView.inputPurchaseAmount());
    }

    private int calculateLottoCount(Money purchaseAmount, OutputView outputView) {
        int lottoCount = purchaseAmount.calculateLottoCount();
        outputView.printPurchaseAmount(lottoCount);

        return lottoCount;
    }

    private void printPurchaseLotto(int lottoCount, OutputView outputView) {
        purchaseLotto(lottoCount);
        outputView.printLottoNumbers(lottos);
    }

    // 로또 구매
    public void purchaseLotto(int count) {
        lottos.purchaseLotto(count);
    }

    // 당첨 로또 생성
    public WinningLotto createWinningLotto(String input, String bonus) {
        return new WinningLotto(input, bonus);
    }

    // 모든 로또 결과 설정
    private void setAllLottoResult(WinningLotto winningLotto) {
        if (winningLotto == null) {
            throw new IllegalStateException("당첨 로또가 설정되지 않았습니다.");
        }
        lottos.setAllLottoResult(winningLotto);
    }
}
