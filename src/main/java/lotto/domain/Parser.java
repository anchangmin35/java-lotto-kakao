package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Integer> parseStringToList(String input) {
        List<Integer> list = new ArrayList<>();
        for (String token : input.split(",")) {
            String stringNumber = token.trim();
            validateString(stringNumber);
            int number = Integer.parseInt(stringNumber);
            list.add(number);
        }

        return list;
    }

    public Integer parseStringToInteger(String input) {
        validateString(input);
        return Integer.parseInt(input);
    }

    private void validateString(String substring) {
        if (substring.isEmpty() || !substring.matches("^-?[0-9]+$")) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }
}
