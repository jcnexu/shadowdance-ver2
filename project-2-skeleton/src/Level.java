import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    private final Font scoreFont = new Font("res/FSO8BITR.TTF", 30);
    private final static int SCORE_X = 35;
    private final static int SCORE_Y = 35;
    private final static int L1_TARGET = 150;
    private final static int L2_TARGET = 400;
    private final static int L3_TARGET = 350;
    private ArrayList<Lane> lanesArray = new ArrayList<Lane>();
    private int numLanes = 0;
    private int levelScore = 0;
    private Accuracy accuracy = new Accuracy();

    public Level(int levelNum) {
        readCSV(levelNum);
        this.numLanes = lanesArray.size();
    }

    public ArrayList<Lane> getLanesArray() {
        return this.lanesArray;
    }

    public int getNumLanes() {
        return this.numLanes;
    }

    public int getLevelScore() {
        return this.levelScore;
    }

    public void setLevelScore(int newScore) {
        this.levelScore = newScore;
    }

    protected void readCSV(int levelNum) {
        try(BufferedReader br = new BufferedReader(new FileReader("res/level" + levelNum + ".csv"))) {
            String csvLine;
            while((csvLine = br.readLine()) != null) {
                String fields[] = csvLine.split(",");
                // Read in the lane coordinates
                if(fields[0].equalsIgnoreCase("Lane")) {
                    String laneType = fields[1];
                    int laneXCoord = Integer.parseInt(fields[2]);
                    // Create the lan to add into lanes ArrayList
                    Lane currLane = new Lane(laneType, laneXCoord);
                    lanesArray.add(currLane);
                }
                // Otherwise, for the notes
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Input input, Accuracy accuracy) {
        levelDraw();

        scoreFont.drawString("SCORE " + levelScore, SCORE_X, SCORE_Y);

        for(int i = 0; i < lanesArray.size(); i++) {
            levelScore += lanesArray.get(i).update(input, accuracy);
        }

        accuracy.update();

    }

    public void levelDraw() {
        for(int i = 0; i < lanesArray.size(); i++) {
            lanesArray.get(i).draw();
        }
    }

    private boolean checkFinished() {
        for (int i = 0; i < lanesArray.size(); i++) {
            if (!lanesArray.get(i).isFinished()) {
                return false;
            }
        }
        return true;
    }

}
