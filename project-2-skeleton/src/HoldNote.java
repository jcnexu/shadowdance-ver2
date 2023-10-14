import bagel.Image;
import bagel.Input;
import bagel.Keys;

public class HoldNote extends Note {
    private static final int START_HOLD_Y = 24;
    private static final int HEIGHT_OFFSET = 82;
    private boolean holdStarted = false;
    private boolean completed = false;

    public HoldNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.noteImage = new Image("res/holdNote" + dir + ".png");
        this.startY = START_HOLD_Y;
    }

    public void startHold() {
        holdStarted = true;
    }

    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive() && !holdStarted) {
            int score = accuracy.evaluateScore(getBottomHeight(), targetHeight, input.wasPressed(relevantKey));

            if (score == Accuracy.MISS_SCORE) {
                deactivate();
                return score;
            } else if (score != Accuracy.NOT_SCORED) {
                startHold();
                return score;
            }
        } else if (isActive() && holdStarted) {

            int score = accuracy.evaluateScore(getTopHeight(), targetHeight, input.wasReleased(relevantKey));

            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            } else if (input.wasReleased(relevantKey)) {
                deactivate();
                accuracy.setAccuracy(Accuracy.MISS);
                return Accuracy.MISS_SCORE;
            }
        }

        return 0;
    }

    private int getBottomHeight() {
        return startY + HEIGHT_OFFSET;
    }

    private int getTopHeight() {
        return startY - HEIGHT_OFFSET;
    }


}
