package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrame2Test {

    @Test
    void fallenPinsGiven_subtractPins() {
        Frame2 frame = new NormalFrame2();
        frame.subtractPins(4);

        assertThat(frame).isEqualTo(new NormalFrame2(6, List.of(4)));
    }

    @DisplayName("프레임이 투구를 다 던졌는지 물어보고 다 던졌으면 다음 프레임으로 넘어간다.")
    @Test
    void askFrameIsOver() {
        Frame2 frame = new NormalFrame2(0, List.of(3, 7));
        assertThat(frame.askCurrentFrame()).isEqualTo(new NormalFrame2());
    }

    @DisplayName("프레임이 투구를 다 던졌는지 물어보고 다 안 던졌으면 자기 자신 리턴한다.")
    @Test
    void askFrameIsOver2() {
        Frame2 frame = new NormalFrame2(4, List.of(6));
        assertThat(frame.askCurrentFrame()).isEqualTo(frame);
    }

    @DisplayName("프레임이 투구를 다 던지면 다음 프레임 생성하고 현재 프레임과 연결해준다.")
    @Test
    void link() {

    }

    @DisplayName("9번째 프레임이 다 던지면 파이널 프레임을 생성한다.")
    @Test
    void createFinalFrame() {

    }
}
