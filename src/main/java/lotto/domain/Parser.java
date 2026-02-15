package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.LottoNumberValidator.validateString;

public class Parser {

    public List<Integer> parseStringToList(String input) {
        List<Integer> list = new ArrayList<>();
        for (String token : input.split(",")) {
            String number = token.trim();
            validateString(number);
            list.add(Integer.parseInt(number));
        }

        return list;
    }

    public Integer parseStringToInteger(String input) {
        validateString(input);
        return Integer.parseInt(input);
    }
}
