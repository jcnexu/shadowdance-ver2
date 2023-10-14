import bagel.*;
import java.util.ArrayList;

public class Lane {
    private static final int CENTRE_Y = 384;
    private static final int TARGET_HEIGHT = 657;
    private int centreX;
    private int centreY;
    private String laneType;
    private final Image laneImage;
    private ArrayList<Note> laneNotes = new ArrayList<Note>();
    private int currNote = 0;
    protected Keys relevantKey;

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
        }
    }

    public String getLaneType() {
        return laneType;
    }

    public ArrayList<Note> getNoteArray() {
        return this.laneNotes;
    }

    public int getLaneX() {
        return this.centreX;
    }

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

    public void addNote(Note givenNote) {
        this.laneNotes.add(givenNote);
    }

    // Adapted from Project 1's solution's draw() in Lane class
    public void laneDraw() {
        laneImage.draw(centreX, centreY);

        for(int i = currNote; i < laneNotes.size(); i++) {
            laneNotes.get(i).noteDraw();
            System.out.println("index note:" + i + " noteType:" + laneNotes.get(i).getStartX()
                    + " noteLane: " + laneNotes.get(i).getNoteLane());
        }
    }

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
