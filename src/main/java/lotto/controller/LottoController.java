package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PurchasePlan;
import lotto.domain.Result;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {
        Money purchaseAmount = readPurchaseAmount();            // 구매 금액 입력 (예외 시 다시)
        int totalCount = calculateLottoCount(purchaseAmount);   // 총 로또 구매 장 수 계산

        PurchasePlan purchasePlan = readInputPurchasePlan(totalCount);  // 수동/자동 구매 계획 생성 (예외 시 다시)
        Lottos lottos = purchaseLottos(purchasePlan);                   // 로또 일괄 생성

        printAllPurchasedLotto(purchasePlan, lottos);       // 모든 로또(수동 + 자동) 출력
        WinningLotto winningLotto = readWinningLotto();     // 당첨 로또 입력 및 생성 (예외 시 다시)

        Result result = calculateAllLottosResult(lottos, winningLotto);   // 모든 로또에 대한 Result 생성
        printResult(purchaseAmount, result);                              // 최종 결과 출력
    }

    // inputPurchaseAmount()를 retryUntilValid()로 감싼 메서드
    private Money readPurchaseAmount() {
        return retryUntilValid(this::inputPurchaseAmount);
    }

    // 구입 금액 입력 메서드
    private Money inputPurchaseAmount() {
        outputView.printPurchaseAmountInput();
        int amount = inputView.inputInteger();
        return Money.from(amount);
    }

    // 구입 가능한 로또 장 수 계산 메서드
    private int calculateLottoCount(Money money) {
        return money.calculateLottoCount();
    }

    // inputPurchasePlan()를 retryUntilValid()로 감싼 메서드
    private PurchasePlan readInputPurchasePlan(int totalCount) {
        return retryUntilValid(() -> inputPurchasePlan(totalCount));
    }

    // 구입 계획 (PurchasePlan) 입력받는 메서드
    private PurchasePlan inputPurchasePlan(int totalCount) {
        outputView.printManualCount();
        int manualCount = inputView.inputInteger();

        return new PurchasePlan(totalCount, manualCount);
    }

    // 수동 + 자동 로또를 구매하는 메서드
    private Lottos purchaseLottos(PurchasePlan purchasePlan) {
        List<Lotto> purchasedLottos = new ArrayList<>();

        List<Lotto> manualLottos = purchaseManualLottos(purchasePlan.manualCount());
        List<Lotto> autoLottos = purchaseAutoLottos(purchasePlan.getAutoCount());

        purchasedLottos.addAll(manualLottos);
        purchasedLottos.addAll(autoLottos);
        return Lottos.from(purchasedLottos);
    }

    // 수동 로또 구매하는 메서드
    private List<Lotto> purchaseManualLottos(int manualCount) {
        printManualPurchaseGuide(manualCount);      // 수동 로또 구매 장 수 출력

        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(readManualLotto());   // 수동 로또를 입력받아서 List에 추가
        }
        return manualLottos;
    }

    // inputManualLotto()를 retryUntilValid()로 감싼 메서드
    private Lotto readManualLotto() {
        return retryUntilValid(this::inputManualLotto);
    }

    // 수동 로또 입력받는 메서드
    private Lotto inputManualLotto() {
        List<Integer> inputList = inputView.inputLottoNumbers();
        return Lotto.from(inputList);
    }

    // 수동 로또 구매 장 수를 출력하는 메서드
    private void printManualPurchaseGuide(int manualCount) {
        if (manualCount > 0) {
            outputView.printManualPurchase();
        }
    }

    // 자동 로또 구매하는 메서드
    private List<Lotto> purchaseAutoLottos(int autoCount) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoCount; i++) {
            autoLottos.add(Lotto.random());
        }
        return autoLottos;
    }

    // 모든 구매한 로또를 출력하는 메서드
    private void printAllPurchasedLotto(PurchasePlan purchasePlan, Lottos lottos) {
        int totalCount = purchasePlan.totalCount();
        int manualCount = purchasePlan.manualCount();
        outputView.printPurchaseAmount(totalCount, manualCount);
        outputView.printLottoNumbers(lottos);
    }

    // inputAndCreateWinningLotto()를 retryUntilValid()로 감싼 메서드
    private WinningLotto readWinningLotto() {
        return retryUntilValid(this::inputAndCreateWinningLotto);
    }

    // 당첨 로또를 입력 + 생성하는 메서드
    private WinningLotto inputAndCreateWinningLotto() {
        outputView.printWinningLottoInput();
        List<Integer> winningNumberList = inputView.inputLottoNumbers();

        outputView.printBonusNumberInput();
        Integer bonusNumber = inputView.inputInteger();

        return createWinningLotto(winningNumberList, bonusNumber);
    }

    // 당첨 로또 생성하는 메서드
    public WinningLotto createWinningLotto(List<Integer> winningNumberList, Integer bonusNumber) {
        return WinningLotto.from(winningNumberList, bonusNumber);
    }

    // 모든 로또의 결과 계산하는 메서드
    private Result calculateAllLottosResult(Lottos lottos, WinningLotto winningLotto) {
        return lottos.calculateAllLottosResult(winningLotto);
    }

    // 최종 결과를 출력하는 메서드
    private void printResult(Money purchaseAmount, Result result) {
        outputView.printResult(result);
        double rateOfReturn = result.getRateOfReturn(purchaseAmount);
        outputView.printRateOfReturn(rateOfReturn);
    }

    // 예외가 발생하지 않을 때 (유효할 때) 까지 반복하는 공통 메서드
    private <T> T retryUntilValid(Supplier<T> inputAction) {
        while (true) {
            try {
                return inputAction.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
