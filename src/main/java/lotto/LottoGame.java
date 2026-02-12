package lotto;

import static lotto.LottoNumberValidator.validatePurchaseMoneyRange;

public class LottoGame {

    static final int LOTTO_PRICE = 1000;
    private WinningLotto winningLotto;
    private final Lottos lottos;

    public LottoGame() {
        lottos = new Lottos();
    }

    public Lottos getLottos() {
        return lottos;
    }

    // 구매 가능한 갯수 반환
    public int calculateLottoCount(int money) {
        validatePurchaseMoneyRange(money);

        return money / LOTTO_PRICE;
    }

    // 로또 구매
    public void purchaseLotto(int count) {
        lottos.purchaseLotto(count);
    }

    // 당첨 로또 생성
    public void createWinningLotto(String input, String bonus) {
        winningLotto = new WinningLotto(input, bonus);
    }

    // 모든 로또 결과 설정
    public void setAllLottoResult() {
        if (winningLotto == null) {
            throw new IllegalStateException("당첨 로또가 설정되지 않았습니다.");
        }
        lottos.setAllLottoResult(winningLotto);
    }
}
