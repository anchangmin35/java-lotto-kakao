package lotto.view;

import lotto.domain.Parser;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);
    private final Parser parser = new Parser();

    public List<Integer> inputLottoNumbers() {
        String input = scanner.nextLine();

        return parser.parseStringToList(input);
    }

    // 구입금액, 로또 수 입력 메서드
    public int inputInteger() {
        String input = scanner.nextLine();
        String trimmedInput = input.trim();

        return parser.parseStringToInteger(trimmedInput);
    }
}
