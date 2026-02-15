package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberValidatorTest {

    @Test
    @DisplayName("번호 6개 입력은 크기 검증을 통과한다.")
    public void validateSizeSuccessTest() {
        assertDoesNotThrow(() -> LottoNumberValidator.validateSize(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    @DisplayName("번호가 6개가 아니면 예외를 반환한다.")
    public void validateSizeFailTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validateSize(List.of(1, 2, 3, 4, 5)));
        assertThat(exception.getMessage()).isEqualTo(LOTTO_NUMBER_SIZE + "개의 숫자를 입력해야 합니다.");
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
    @DisplayName("빈 문자열이면 예외를 반환한다.")
    public void validateStringFailEmptyTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> LottoNumberValidator.validateString(""));
        assertThat(exception.getMessage()).isEqualTo("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("부호만 입력하면 예외를 반환한다.")
    public void validateStringFailSignOnlyTest() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> LottoNumberValidator.validateString("-"));
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
        assertThat(exception.getMessage()).isEqualTo("1 ~ 45 사이의 숫자를 입력해주세요.");
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

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 검증을 통과한다.")
    public void validateDistinctBonusNumberSuccessTest() {
        List<LottoNumber> lottoNumberList = List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        );

        assertDoesNotThrow(() -> LottoNumberValidator.validateDistinctBonusNumber(lottoNumberList, 7));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외를 반환한다.")
    public void validateDistinctBonusNumberFailTest() {
        List<LottoNumber> lottoNumberList = List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validateDistinctBonusNumber(lottoNumberList, 6));
        assertThat(exception.getMessage()).isEqualTo("당첨 번호와 보너스 볼의 번호가 일치합니다.");
    }

    @Test
    @DisplayName("수동 구매 개수가 0 이상 총 구매 개수 이하면 검증을 통과한다.")
    public void validatePurchaseManualLottoSuccessTest() {
        assertDoesNotThrow(() -> LottoNumberValidator.validatePurchaseManualLotto(8, 0));
        assertDoesNotThrow(() -> LottoNumberValidator.validatePurchaseManualLotto(8, 8));
    }

    @Test
    @DisplayName("수동 구매 개수가 음수면 예외를 반환한다.")
    public void validatePurchaseManualLottoNegativeFailTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validatePurchaseManualLotto(8, -1));
        assertThat(exception.getMessage()).isEqualTo("0 이상의 개수를 입력해주세요.");
    }

    @Test
    @DisplayName("수동 구매 개수가 총 구매 개수를 초과하면 예외를 반환한다.")
    public void validatePurchaseManualLottoOverflowFailTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LottoNumberValidator.validatePurchaseManualLotto(8, 9));
        assertThat(exception.getMessage()).isEqualTo("수동 구매 가능 개수를 초과했습니다.");
    }
}
