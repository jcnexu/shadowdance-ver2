import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    private final Font scoreFont = new Font("res/FSO8BITR.TTF", 30);
    private final static int SCORE_X = 35;
    private final static int SCORE_Y = 35;
    private ArrayList<Lane> lanesArray = new ArrayList<Lane>();
    private int numLanes = 0;
    private int levelScore = 0;
    private Accuracy accuracy = new Accuracy();
    private boolean levelFinished = false;

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

    public boolean getLevelFinished() {
        return this.levelFinished;
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
                if(fields[0].equalsIgnoreCase("Special")) {
                    int noteFrameNumber = Integer.parseInt(fields[2]);
                    createSpecialNote(fields[0], fields[1], noteFrameNumber, lanesArray);
                }

                // Otherwise, for the NORMAL  notes
                int noteFrameNumber = Integer.parseInt(fields[2]);
                createNormalNote(fields[0], fields[1], noteFrameNumber, lanesArray);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void createNormalNote(String laneType, String noteType, int frameNumber, ArrayList<Lane> lanesArray) {
        int startX;
        Note currNote;
        if(noteType.equalsIgnoreCase("Normal")) {
           for(int i = 0; i < lanesArray.size(); i++) {
               if(lanesArray.get(i).getLaneType().equals(laneType)) {
                   startX = lanesArray.get(i).getLaneX();
                   currNote = new NormalNote(laneType, startX, frameNumber);
                   lanesArray.get(i).addNote(currNote);
                   break;
               }
           }
        } else if(noteType.equalsIgnoreCase("Hold")) {
            for(int i = 0; i < lanesArray.size(); i++) {
                if(lanesArray.get(i).getLaneType().equalsIgnoreCase(laneType)) {
                    startX = lanesArray.get(i).getLaneX();
                    currNote = new HoldNote(laneType, startX, frameNumber);
                    lanesArray.get(i).addNote(currNote);
                    break;
                }
            }
        }
    }

    private void createSpecialNote(String dir, String specialEffect, int frameNumber, ArrayList<Lane> lanesArray) {
        int startX;
        Note currNote;
        for(int i = 0; i < lanesArray.size(); i++) {
            if(lanesArray.get(i).getLaneType().equalsIgnoreCase("Special")) {
                startX = lanesArray.get(i).getLaneX();
                currNote = new SpecialNote(dir, specialEffect, startX, frameNumber);
                lanesArray.get(i).addNote(currNote);
                break;
            }
        }
    }

    public void update(Input input, Accuracy accuracy) {
        levelDraw();

        scoreFont.drawString("SCORE " + levelScore, SCORE_X, SCORE_Y);

        for(int i = 0; i < lanesArray.size(); i++) {
            levelScore += lanesArray.get(i).update(input, accuracy);
        }

        accuracy.update();
        levelFinished = checkFinished();

    }

    public void levelDraw() {
        for(int i = 0; i < lanesArray.size(); i++) {
            lanesArray.get(i).laneDraw();
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
