import bagel.*;

/** The class that manages and implements the scoring of all Notes and game
 * scoring messages.
 */
// Entire class was taken and adapted from Project 1's solution's Accuracy class.
public class Accuracy {
    /** The score of getting a Note within Perfect range */
    public final static int PERFECT_SCORE = 10;
    /** The score of getting a Note within Good range */
    public final static int GOOD_SCORE = 5;
    /** The score of getting a Note within Bad range */
    public final static int BAD_SCORE = -1;
    /** The score of getting a Note within Missed range */
    public final static int MISS_SCORE = -5;
    /** Value that signifies a Note was not scored by player */
    public final static int NOT_SCORED = 0;
    /** Message rendered when a Note is scored within Perfect range */
    public final static String PERFECT = "PERFECT";
    /** Message rendered when a Note is scored within Good range */
    public final static String GOOD = "GOOD";
    /** Message rendered when a Note is scored within Bad range */
    public final static String BAD = "BAD";
    /** Message rendered when a Note is scored within Missed range */
    public final static String MISS = "MISS";
    private final static int PERFECT_RANGE = 15;
    private final static int GOOD_RANGE = 50;
    private final static int BAD_RANGE = 100;
    private final static int MISS_RANGE = 200;
    private final static Font ACCURACY_FONT = new Font("res/FSO8BITR.TTF", 40);
    private final static int RENDER_FRAMES = 30;
    private final static int SPECIAL_RANGE = 50;
    private final static String SPEED_UP = "Speed Up";
    private final static String DOUBLE_SCORE = "Double Score";
    private final static String SLOW_DOWN = "Slow Down";
    private final static String BOMB = "Lane Clear";
    private final static int SPEEDUP_SCORE = 15;
    private final static int SLOWDOWN_SCORE = 15;
    /**  Value that signifies that a Special Note was not scored by player */
    public final static int SPECIAL_MISSED = -1;
    private String currAccuracy = null;
    private int frameCount = 0;

    /** Setter to set currAccuracy (the message to be drawn when
     * a Note is scored).
     * @param accuracy The String/message to be rendered.
     */
    public void setAccuracy(String accuracy) {
        currAccuracy = accuracy;
        frameCount = 0;
    }

    /** Evaluates the score to be given by how closely the player scored the
     * Note being drawn. Also uses setAccuracy() the set the message to be drawn
     * on the screen according to the evaluated score.
     * @param height The current height/y-coordinate of the moving Note
     * @param targetHeight The target height/y-coordinate of the target (stationary) Note
     * @param triggered If the relevantKey of a Note was pressed
     * @return the relevant score.
     */
    public int evaluateScore(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= PERFECT_RANGE) {
                setAccuracy(PERFECT);
                return PERFECT_SCORE;
            } else if (distance <= GOOD_RANGE) {
                setAccuracy(GOOD);
                return GOOD_SCORE;
            } else if (distance <= BAD_RANGE) {
                setAccuracy(BAD);
                return BAD_SCORE;
            } else if (distance <= MISS_RANGE) {
                setAccuracy(MISS);
                return MISS_SCORE;
            }

        } else if (height >= (Window.getHeight())) {
            setAccuracy(MISS);
            return MISS_SCORE;
        }

        return NOT_SCORED;
    }

    /** Evaluates if a Special Note was scored/activated.
     * @param specialEffect The special effect of the Special Note
     * @param height The current height/y-coordinate of the moving Special Note
     * @param targetHeight The height/y-coordinate of the target (Stationary) image
     * @param triggered If the relevantKey (SPACE key) was pressed by player
     * @return the associated extra score points with an activated Special Note.
     */
    public int specialEvaluateScore(String specialEffect, int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);
        if(triggered) {
            if(distance <= SPECIAL_RANGE) {
                if(specialEffect.equalsIgnoreCase("DoubleScore")) {
                    setAccuracy(DOUBLE_SCORE);
                    return 0;
                } else if(specialEffect.equalsIgnoreCase("SpeedUp")) {
                    setAccuracy(SPEED_UP);
                    return SPEEDUP_SCORE;
                } else if(specialEffect.equalsIgnoreCase("SlowDown")) {
                    setAccuracy(SLOW_DOWN);
                    return SLOWDOWN_SCORE;
                } else if(specialEffect.equalsIgnoreCase("Bomb")) {
                    setAccuracy(BOMB);
                    return 0;
                }
            }
        }
        // Not scored
        return SPECIAL_MISSED;
    }

    /** Draws the corresponding score's message when needed (when Note is scored) for the
     * duration of RENDER_FRAMES.
     */
    public void update() {
        frameCount++;
        if (currAccuracy != null && frameCount < RENDER_FRAMES) {
            ACCURACY_FONT.drawString(currAccuracy,
                    Window.getWidth()/2 - ACCURACY_FONT.getWidth(currAccuracy)/2,
                    Window.getHeight()/2);
        }
    }

}
