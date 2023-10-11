import bagel.*;

public class Note {
    private final Image noteImage;
    private final int appearanceFrame;
    private final int NOTE_SPEED = 2;
    // y-coordinate of image of stationary note at end of the lanes
    private final int STATIONARY_Y = 657;
    private boolean active = false;
    private boolean completed = false;
    private int startX;
    protected int startY;
    private String noteLane;

    public Note(String dir, int startX, int appearanceFrame) {
        noteImage = new Image("res/note" + dir + ".png");
        this.startX = startX;
        this.appearanceFrame = appearanceFrame;
    }

    public int getStartX() {
        return this.startX;
    }
    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int n) {
        this.startY = n;
    }

    public boolean isActive() {
        return active;
    }
    public boolean isCompleted() {return completed;}

    public void deactivate() {
        active = false;
        completed = true;
    }

    public void update() {
        int noteY = this.getStartY();

        if (active) {
            this.setStartY(noteY + NOTE_SPEED);
        }

        if (ShadowDance.getCurrFrame() >= appearanceFrame && !completed) {
            active = true;
        }
    }

    public void noteDraw() {
        if (active) {
            noteImage.draw(this.getStartX(), this.getStartY());
        }
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
