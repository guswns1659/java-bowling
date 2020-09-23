package bowling.domain;

import bowling.exception.GameOverException;

import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(String playerName) {
        this.player = new Player(playerName);
        this.frames = new Frames();
    }

    public void pitch(int count) {
        if (isEnd()) {
            throw new GameOverException();
        }

        frames.pitch(count);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public int getFrameCount() {
        return frames.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
