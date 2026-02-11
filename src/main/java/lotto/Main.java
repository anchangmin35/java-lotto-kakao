package lotto;

public class Main {
    public static void main(String[] args) {

        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        LottoGame game = new LottoGame();

        outputView.printPurchaseAmountInput();
        int purchaseAmount = inputView.inputPurchaseAmount();
        int lottoCount = game.calculateLottoCount(purchaseAmount);
        outputView.printPurchaseAmount(lottoCount);

        game.purchaseLotto(lottoCount);
        outputView.printLottoNumbers(game.getLottoListAsList());

        outputView.printWinningLottoInput();
        String input = inputView.inputLottoNumber();

        outputView.printBonusNumberInput();
        String bonusInput = inputView.inputLottoNumber();

        game.createWinningLotto(input, bonusInput);
        game.setAllLottoResult();

        outputView.printResult(game);
        outputView.printRateOfReturn(game);
    }
}
