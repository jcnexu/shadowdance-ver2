import bagel.Image;
import bagel.Input;
import bagel.Keys;

/** The subclass that implements and creates a Hold Note.
 */
public class HoldNote extends Note {
    private static final int START_HOLD_Y = 24;
    private static final int HEIGHT_OFFSET = 82;
    private boolean holdStarted = false;

    /** Constructor the create a new Hold Note.
     * @param dir The direction/lane of the Hold Note
     * @param startX The starting x-coordinate of the Hold Note
     * @param appearanceFrame The frame number which the Hold Note starts being drawn from.
     */
    public HoldNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.noteImage = new Image("res/holdNote" + dir + ".png");
        this.startY = START_HOLD_Y;
    }

    /** Sets the holdStarted attribute to true; the game player
     * has started the press down on the relevant key of the Hold Note.
     */
    // Taken from Project 1's solution's HoldNote class.
    public void startHold() {
        holdStarted = true;
    }

    /** Overrides the checkScore method in parent class Note.
     * Checks if a HoldNote has been scored, and if so in how close of a range it has been scored.
     * @param input The input from the user/player
     * @param accuracy The accuracy used to calculate the relevant score and score range
     * @param targetHeight The target y-coordinate (y-coordinate of stationary note)
     * @param relevantKey The relevant key of the Hold Note
     * @return score The relevant score of the range in which the Hold Note was scored by the player.
     */
    // Taken from Project 1's solution's checkScore() in HoldNote class.
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

    // Taken from Project 1's solution's HoldNote class.
    private int getBottomHeight() {
        return startY + HEIGHT_OFFSET;
    }

    // Taken from Project 1's solution's HoldNote class.
    private int getTopHeight() {
        return startY - HEIGHT_OFFSET;
    }


}
