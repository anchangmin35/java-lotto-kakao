package lotto;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String inputLottoNumber() {
        return scanner.nextLine();
    }

    public int inputPurchaseAmount() {
        return Integer.parseInt(scanner.nextLine());
    }
}
