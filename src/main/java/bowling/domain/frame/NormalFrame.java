package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.exception.FrameNotCorrectException;

public final class NormalFrame extends Frame {

    public static final int FIRST_ROUND_NUMBER = 1;
    public static final int ROUND_BEFORE_LAST_ROUND = 9;
    private static final int MAX_SIZE = 2;
    private static final int MAX_KNOCK_DOWN_NUMBER = 10;

    private Frame nextFrame;

    private NormalFrame(final int roundNumber) {
        super(roundNumber, Pins.of());
    }

    private NormalFrame(final int roundNumber, final Pins pins) {
        super(roundNumber, pins);
    }

    public static Frame of(final int roundNumber, final int... number) {
        return new NormalFrame(roundNumber, Pins.of(number));
    }

    public static Frame of(final int roundNumber, Pins pins) {
        return new NormalFrame(roundNumber, pins);
    }

    public static Frame of(final int roundNumber) {
        return new NormalFrame(roundNumber);
    }

    public static Frame first() {
        return new NormalFrame(FIRST_ROUND_NUMBER);
    }

    public static Frame next(NormalFrame frame) {
        NormalFrame nextFrame = new NormalFrame(frame.roundNumber + 1);
        frame.setNextFrame(nextFrame);
        return nextFrame;
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    @Override
    protected void validateFrame(final Pins pins) {
        if (pins.sumPins() > MAX_KNOCK_DOWN_NUMBER) {
            throw new FrameNotCorrectException();
        }
    }

    @Override
    public Frame bowl(final int knockDownNumber) {
        pins.add(knockDownNumber);
        return NormalFrame.of(roundNumber, pins);
    }

    @Override
    public boolean isFinished() {
        if (pins.isStrike()) {
            return true;
        }
        return pins.size() == MAX_SIZE;
    }

    @Override
    public Frame nextFrame() {
        if (pins.size() == 0) {
            return this;
        }

        if (pins.size() == 1 && FrameStatus.of(pins) != FrameStatus.STRIKE) {
            return this;
        }

        if (roundNumber == ROUND_BEFORE_LAST_ROUND) {
            return FinalFrame.of();
        }
        return NormalFrame.next(this);
    }

    @Override
    public boolean canCalculateScore() {
        return isFinished();
    }

    @Override
    public int getScore() {
        if (FrameStatus.of(pins) == FrameStatus.STRIKE) {
            return nextFrame.addScore(pins.sumPins(), 2);
        }

        if (FrameStatus.of(pins) == FrameStatus.SPARE) {
            return nextFrame.addScore(pins.sumPins(), 1);
        }
        return pins.sumPins();
    }

    @Override
    public int addScore(int score, int count) {
        if (count == 2 && FrameStatus.of(pins) == FrameStatus.STRIKE) {
            return nextFrame.addScore(score + firstPin().getKnockDownNumber(), 1);
        }
        if (count == 1) {
            return score + firstPin().getKnockDownNumber();
        }
        return score + pins.sumPins();
    }
}