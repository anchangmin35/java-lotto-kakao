package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberValidatorTest {

    @Test
    @DisplayName("번호 6개 입력은 크기 검증을 통과한다.")
    public void validateSizeSuccessTest() {
        String[] numbers = {"1", "2", "3", "4", "5", "6"};

        assertDoesNotThrow(() -> LottoNumberValidator.validateSize(numbers));
    }

    @Test
    @DisplayName("번호가 6개가 아니면 예외를 반환한다.")
    public void validateSizeFailTest() {
        String[] numbers = {"1", "2", "3", "4", "5"};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validateSize(numbers));
        assertThat(exception.getMessage()).isEqualTo("6개의 숫자를 입력해야 합니다.");
    }

    @Test
    @DisplayName("숫자 문자열은 문자열 검증을 통과한다.")
    public void validateStringSuccessTest() {
        assertDoesNotThrow(() -> LottoNumberValidator.validateString("42"));
    }

    @Test
    @DisplayName("숫자가 아닌 문자열이면 예외를 반환한다.")
    public void validateStringFailTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> LottoNumberValidator.validateString("abc"));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("1-45 범위 숫자는 범위 검증을 통과한다.")
    public void validateRangeSuccessTest() {
        assertDoesNotThrow(() -> LottoNumberValidator.validateRange(1));
        assertDoesNotThrow(() -> LottoNumberValidator.validateRange(45));
    }

    @Test
    @DisplayName("범위를 벗어난 숫자면 예외를 반환한다.")
    public void validateRangeFailTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validateRange(0));
        assertThat(exception.getMessage()).isEqualTo("범위를 벗어난 숫자입니다.");
    }

    @Test
    @DisplayName("중복되지 않은 숫자는 중복 검증을 통과한다.")
    public void validateDistinctNumberSuccessTest() {
        List<LottoNumber> lottoNumberList = new ArrayList<>();
        lottoNumberList.add(LottoNumber.from(1));
        lottoNumberList.add(LottoNumber.from(2));

        assertDoesNotThrow(() -> LottoNumberValidator.validateDistinctNumber(lottoNumberList, 3));
    }

    @Test
    @DisplayName("중복된 숫자면 예외를 반환한다.")
    public void validateDistinctNumberFailTest() {
        List<LottoNumber> lottoNumberList = new ArrayList<>();
        lottoNumberList.add(LottoNumber.from(1));
        lottoNumberList.add(LottoNumber.from(2));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validateDistinctNumber(lottoNumberList, 2));
        assertThat(exception.getMessage()).isEqualTo("로또에 중복된 숫자가 존재합니다.");
    }
}
