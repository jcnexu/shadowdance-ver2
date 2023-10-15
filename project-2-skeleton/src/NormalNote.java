import bagel.*;

/** The subclass that implements and creates a Normal Note.
 */
public class NormalNote extends Note {
    private static final int START_NORMAL_Y = 100;

    /** Constructor that creates a new NormalNote.
     * @param dir The direction/lane the NormalNote belongs to
     * @param startX The starting x-coordinate of NormalNote
     * @param appearanceFrame The frame nymber which the NormalNote starts being drawn from.
     */
    public NormalNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.noteImage = new Image("res/note" + dir + ".png");
        this.startY = START_NORMAL_Y;
    }

    /** Overrides the checkScore() method in parent class Note.
     * Checks if a NormalNote has been scored, and if so in how close of a range it has been scored.
     * @param input The input from the user/player
     * @param accuracy The accuracy used to calculate the relevant score and score range
     * @param targetHeight The target y-coordinate (y-coordinate of stationary note)
     * @param relevantKey The relevant key of the Normal Note
     * @return score The relevant score of the range in which the Normal Note was scored by the player.
     */
    // Taken from Project 1's solution's checkScore() in Note class.
    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive()) {
            // Evaluate accuracy of the key press
            int score = accuracy.evaluateScore(startY, targetHeight, input.wasPressed(relevantKey));

            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            }

        }

        return 0;
    }

}