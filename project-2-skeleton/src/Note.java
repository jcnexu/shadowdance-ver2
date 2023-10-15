import bagel.*;

/** This class is a superclass for the normal notes (Normal Notes and Hold Notes).
 */
public class Note {
    /** The image of a Note */
    protected Image noteImage;
    private final int appearanceFrame;
    private final int NOTE_SPEED = 2;
    private boolean active = false;
    private boolean completed = false;
    private int startX;
    /** Starting y-coordinate of a Note */
    protected int startY;
    private String noteLane;

    /** Constructor to create a new Note.
     * @param dir The direction/lane the Note belongs to
     * @param startX The starting x-coordinate of Note
     * @param appearanceFrame The frame nymber which the Note starts being drawn from.
     */
    // Adapted from constructor in Project 1's solution's Note class.
    public Note(String dir, int startX, int appearanceFrame) {
        this.startX = startX;
        this.appearanceFrame = appearanceFrame;
        this.noteLane = dir;
    }

    /** Getter to return the startX for relevant Note
     * @return startX
     */
    public int getStartX() {
        return this.startX;
    }
    /** Getter to return the startY for relevant Note
     * @return startY
     */
    public int getStartY() {
        return this.startY;
    }

    /** Setter to set new startY/change value of startY.
     * @param n The new number to change startY to.
     */
    public void setStartY(int n) {
        this.startY = n;
    }

    /** Getter to return active state of Note
     * @return active.
     */
    public boolean isActive() {
        return active;
    }

    /** Getter to return completed state of Note
     * @return completed.
     */
    // Adapted from draw() in Project 1's solution.
    public boolean isCompleted() {return completed;}

    /** Deactivates a Note so it stops drawing and marks it as completed.
     */
    // Adapted from in Project 1's solution.
    public void deactivate() {
        active = false;
        completed = true;
    }

    /** Updates the Notes every frame.
     * If a Note is active, change the startY for the Note to move it and keep on
     * drawing the Note if ShadowDance's frameNumber is at least the same number as the
     * Note's appearance frame number and if the Note isn't completed.
     */
    // Adapted from draw() in Project 1's solution.
    public void update() {
        int noteY = this.getStartY();

        if (active) {
            this.setStartY(noteY + NOTE_SPEED);
        }

        if (ShadowDance.getCurrFrame() >= appearanceFrame && !completed) {
            active = true;
        }
    }

    /** Draws the Note if it is active.
     */
    // Adapted from draw() in Project 1's solution.
    public void noteDraw() {
        if (active) {
            noteImage.draw(this.getStartX(), this.getStartY());
        }
    }

    /** The super method to check if a Note has been scored, and if it has then return
     * the associated scored amount.
     * @param input The input from the user
     * @param accuracy The accuracy used to calculate the relevant score and score range
     * @param targetHeight The target y-coordinate
     * @param relevantKey The relevant key to the Note
     * @return 0
     */
    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive()) {
        }

        return 0;
    }
}
