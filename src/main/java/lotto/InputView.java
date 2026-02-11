package lotto;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public String inputLottoNumber() {
        return scanner.nextLine();
    }

    public int inputPurchaseAmount() {
        return Integer.parseInt(scanner.nextLine());
    }
}
