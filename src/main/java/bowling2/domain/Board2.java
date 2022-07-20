package bowling2.domain;

import bowling2.domain.frame.Frame2;
import bowling2.domain.frame.NormalFrame2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board2 {
    private final List<Frame2> frame2s;
    private Frame2 currentFrame;

    public static Board2 init() {
        return new Board2(new ArrayList<>(), new NormalFrame2(1));
    }

    public Board2(List<Frame2> frame2s) {
        this.frame2s = frame2s;
    }

    public Board2(List<Frame2> frame2s, Frame2 currentFrame) {
        this.frame2s = frame2s;
        this.currentFrame = currentFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board2 board2 = (Board2) o;
        return Objects.equals(frame2s, board2.frame2s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame2s);
    }
}
