import bagel.*;
import java.util.ArrayList;

public class Lane {
    private static final int CENTRE_Y = 384;
    private static final int TARGET_HEIGHT = 657;
    private int centreX;
    private int centreY;
    private String laneType;
    private final Image laneImage;
    private ArrayList<Note> laneNotes = new ArrayList<>();
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

    // Adapted from Project 1's solution's update() function in Lane class
    public int update(Input input, Accuracy accuracy) {
        draw();
        int i;

        for(i = 0; i < laneNotes.size(); i++) {
            laneNotes.get(i).update();
        }

        if(currNote < laneNotes.size()) {
            int score = laneNotes.get(i).checkScore(input, accuracy, TARGET_HEIGHT, relevantKey);
            if(laneNotes.get(i).isCompleted()) {
                currNote++;
                return score;
            }
        }

        return Accuracy.NOT_SCORED;
    }

    // Adapted from Project 1's solution's draw() in Lane class
    public void draw() {
        laneImage.draw(centreX, centreY);

        for(int i = currNote; i < laneNotes.size(); i++) {
            laneNotes.get(i).noteDraw();
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
