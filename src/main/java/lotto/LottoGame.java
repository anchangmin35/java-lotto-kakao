package lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.LottoNumberValidator.*;

public class LottoGame {

    // 정답 로또
    private WinningLotto winningLotto;

    // 구매 로또 리스트
    private Lottos lottos;

    public LottoGame() {
        lottos = new Lottos();
    }

    public Lottos getLottos() {
        return lottos;
    }

    // 구매 가능한 갯수 반환
    public int calculateLottoCount(int money) {
        validatePurchaseMoneyRange(money);

        return money / 1000;
    }

    // 로또 구매
    public void purchaseLotto(int count) {
        lottos.purchaseLotto(count);
    }

    // 구매한 로또 출력
    public List<List<Integer>> getLottoListAsList() {
        List<List<Integer>> list = new ArrayList<>();

        for (Lotto lotto : lottos.getLottoList()) {
            list.add(lotto.getLottoNumbersAsList());
        }

        return list;
    }

    // 당첨 로또 생성
    public void createWinningLotto(String input, String bonus) {
        winningLotto = new WinningLotto(input, bonus);
    }

    // 모든 로또 결과 설정
    public void setAllLottoResult() {
        if (winningLotto == null) throw new IllegalStateException("당첨 로또가 설정되지 않았습니다.");
        lottos.setAllLottoResult(winningLotto);
    }
}
