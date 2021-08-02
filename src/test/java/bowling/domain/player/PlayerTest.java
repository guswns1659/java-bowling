package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("플레이어는 문자열을 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(Player.from("NOK")).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어의 이름은 3자가 아닐경우 예외를 발생 시킨다")
    @NullAndEmptySource
    @ValueSource(strings = {"K", "KH"})
    @ParameterizedTest
    void initException(String name) {
        assertThatThrownBy(() -> Player.from(name)).isInstanceOf(IllegalStateException.class);
    }

}
