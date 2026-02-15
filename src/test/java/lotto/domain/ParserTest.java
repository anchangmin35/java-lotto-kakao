package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    @DisplayName("쉼표만으로 구분한 입력을 숫자 목록으로 파싱한다.")
    public void parseStringToListWithCommaOnlyTest() {
        assertThat(parser.parseStringToList("1,2,3,4,5,6"))
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("공백이 섞인 쉼표 입력도 숫자 목록으로 파싱한다.")
    public void parseStringToListWithMixedSpacesTest() {
        assertThat(parser.parseStringToList("1, 2,3 , 4,5 ,6"))
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("목록에 숫자가 아닌 값이 있으면 예외를 반환한다.")
    public void parseStringToListFailNotNumberTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> parser.parseStringToList("1, 2, a, 4, 5, 6"));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("목록에 빈 값이 있으면 예외를 반환한다.")
    public void parseStringToListFailEmptyTokenTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> parser.parseStringToList("1, 2, , 4, 5, 6"));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("숫자 문자열을 정수로 파싱한다.")
    public void parseStringToIntegerTest() {
        assertThat(parser.parseStringToInteger("1000")).isEqualTo(1000);
    }

    @Test
    @DisplayName("숫자가 아닌 문자열을 정수로 파싱하면 예외를 반환한다.")
    public void parseStringToIntegerFailTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> parser.parseStringToInteger("1000원"));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }
}
