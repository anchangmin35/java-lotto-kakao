package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberTest {

    @Test
    @DisplayName("생성한 번호 값을 반환한다.")
    public void getNumberTest() {
        LottoNumber lottoNumber = LottoNumber.from(7);

        assertThat(lottoNumber.getNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("같은 번호는 동등하다.")
    public void equalsAndHashCodeTest() {
        LottoNumber first = LottoNumber.from(3);
        LottoNumber second = LottoNumber.from(3);

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }

    @Test
    @DisplayName("다른 번호는 동등하지 않다.")
    public void notEqualsTest() {
        LottoNumber first = LottoNumber.from(3);
        LottoNumber second = LottoNumber.from(4);

        assertThat(first).isNotEqualTo(second);
    }
}
