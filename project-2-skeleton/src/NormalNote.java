import bagel.*;

public class NormalNote extends Note {
    private static final int START_NORMAL_Y = 100;

    public NormalNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.noteImage = new Image("res/note" + dir + ".png");
        this.startY = START_NORMAL_Y;
    }

    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive()) {
            // evaluate accuracy of the key press
            int score = accuracy.evaluateScore(startY, targetHeight, input.wasPressed(relevantKey));

            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            }

        }

        return 0;
    }

}