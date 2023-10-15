import bagel.*;

public class Accuracy {
    public static final int PERFECT_SCORE = 10;
    public static final int GOOD_SCORE = 5;
    public static final int BAD_SCORE = -1;
    public static final int MISS_SCORE = -5;
    public static final int NOT_SCORED = 0;
    public static final String PERFECT = "PERFECT";
    public static final String GOOD = "GOOD";
    public static final String BAD = "BAD";
    public static final String MISS = "MISS";
    private static final int PERFECT_RANGE = 15;
    private static final int GOOD_RANGE = 50;
    private static final int BAD_RANGE = 100;
    private static final int MISS_RANGE = 200;
    private static final Font ACCURACY_FONT = new Font("res/FSO8BITR.TTF", 40);
    private static final int RENDER_FRAMES = 30;
    public static final int ENEMY_RANGE = 104;
    private static final String SPEED_UP = "Speed Up";
    private static final String DOUBLE_SCORE = "Double Score";
    private static final String SLOW_DOWN = "Slow Down";
    private static final String BOMB = "Lane Clear";
    private String currAccuracy = null;
    private int frameCount = 0;

    public void setAccuracy(String accuracy) {
        currAccuracy = accuracy;
        frameCount = 0;
    }

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

    public void update() {
        frameCount++;
        if (currAccuracy != null && frameCount < RENDER_FRAMES) {
            ACCURACY_FONT.drawString(currAccuracy,
                    Window.getWidth()/2 - ACCURACY_FONT.getWidth(currAccuracy)/2,
                    Window.getHeight()/2);
        }
    }

}
