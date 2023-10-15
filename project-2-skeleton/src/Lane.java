import bagel.*;
import java.util.ArrayList;

/** The class that implements and creates a Lane. Includes the creation of a Special Lane,
 * as a Special Lane itself is just another lane with no attributes that are special to itself
 * besides its distinct lane image (just like the other lanes); thus does not need its own extended
 * subclass.
 */
public class Lane {
    private static final int CENTRE_Y = 384;
    private static final int TARGET_HEIGHT = 657;
    private int centreX;
    private int centreY;
    private String laneType;
    private final Image laneImage;
    private ArrayList<Note> laneNotes = new ArrayList<Note>();
    private int currNote = 0;
    /** relevant key/direction of the Lane */
    protected Keys relevantKey;

    /** Constructor to create a new Lane.
     * @param dir The direction of the Lane
     * @param centreX The x-coordinate of the Lane.
     */
    // Taken and adapted from Project 1's solution.
    public Lane(String dir, int centreX) {
        this.laneType = dir;
        this.centreX = centreX;
        this.centreY = CENTRE_Y;
        laneImage = new Image("res/lane" + dir + ".png");
        switch (dir) {
            case "Left":
                relevantKey = Keys.LEFT;
                break;
            case "Right":
                relevantKey = Keys.RIGHT;
                break;
            case "Up":
                relevantKey = Keys.UP;
                break;
            case "Down":
                relevantKey = Keys.DOWN;
                break;
            case "Special":
                relevantKey = Keys.SPACE;
        }
    }

    /** Getter to return the laneType/direction of the Lane.
     * @return laneType.
     */
    public String getLaneType() {
        return laneType;
    }

    /** Getter to return the laneNotes ArrayList for a Lane.
     * @return laneNotes.
     */
    public ArrayList<Note> getNoteArray() {
        return this.laneNotes;
    }

    /** Getter to return the x-coordinate of a Lane.
     * @return centreX.
     */
    public int getLaneX() {
        return this.centreX;
    }


    /** Updates the Lane every frame and keeps track of the score for every Note in the Lane.
     * Also returns the score.
     * @param input The input from the user/player
     * @param accuracy The accuracy used to keep and check the score
     * @return score The current score from checking score of a Note in the Lane.
     */
    // Adapted from Project 1's solution's update() function in Lane class
    public int update(Input input, Accuracy accuracy) {
        laneDraw();
        int i;

        for(i = 0; i < laneNotes.size(); i++) {
            laneNotes.get(i).update();
        }

        if(currNote < laneNotes.size()) {
            int score = laneNotes.get(currNote).checkScore(input, accuracy, TARGET_HEIGHT, relevantKey);
            if(laneNotes.get(currNote).isCompleted()) {
                currNote++;
                return score;
            }
        }

        return Accuracy.NOT_SCORED;
    }

    /** Adds in a Note into the Lane's ArrayList of Notes (laneNotes).
     * @param givenNote the Note that is to be added to the ArrayList laneNotes.
     */
    public void addNote(Note givenNote) {
        this.laneNotes.add(givenNote);
    }

    /** Draws the Lane and calls noteDraw() to draw each Note in the Lane.
     */
    // Adapted from Project 1's solution's draw() in Lane class
    public void laneDraw() {
        laneImage.draw(centreX, centreY);

        for(int i = currNote; i < laneNotes.size(); i++) {
            laneNotes.get(i).noteDraw();
        }
    }

    /** Checks if the Lane is finished.
     * @return boolean The state of the Lane.
     */
    // Adapted from Project 1's solution.
    public boolean isFinished() {
        int i;
        for(i = 0; i < laneNotes.size(); i++) {
            if(!laneNotes.get(i).isCompleted()) {
                return false;
            }
        }
        return true;
    }



}
