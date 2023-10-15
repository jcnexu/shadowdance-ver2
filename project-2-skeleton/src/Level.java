import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** This Level class is used to generate and create each level made to play in
 * ShadowDance.
 */
public class Level {
    private final Font scoreFont = new Font("res/FSO8BITR.TTF", 30);
    private final static int SCORE_X = 35;
    private final static int SCORE_Y = 35;
    private ArrayList<Lane> lanesArray = new ArrayList<Lane>();
    private int numLanes = 0;
    private int levelScore = 0;
    private Accuracy accuracy = new Accuracy();
    private boolean levelFinished = false;

    /** This method is the constructor to create a new Level object.
     * @param levelNum This is the level number used to read the relevant CSV file.
     */
    public Level(int levelNum) {
        readCSV(levelNum);
        this.numLanes = lanesArray.size();
    }

    /** A getter to return the lanesArray ArrayList of a created Level object
     * @return lanesArray
     */
    public ArrayList<Lane> getLanesArray() {
        return this.lanesArray;
    }
    /** A getter to return the Level object's score
     * @return levelScore
     */
    public int getLevelScore() {
        return this.levelScore;
    }
    /** A getter to return the state of the Level object.
     * @return levelFinished
     */
    public boolean getLevelFinished() {
        return this.levelFinished;
    }

    /** Reads in a relevant Level world file to create the needed Lanes and Notes
     * @param levelNum The level number of the Level to be created.
     */
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

    // Creates a new Normal (Normal or Hold) note and adds into relevant Lane's Note ArrayList
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

    // Creates a new Special Note and adds into Special Lane's Note ArrayList
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

    /** This function updates the Level every frame by updating the Lanes in lanesArray
     * and also drawing and updating the Level's score.
     * @param input This is the input from the user
     * @param accuracy This is the accuracy used to keep and check the score
     */
    // Modelled after the update() function from Project 1's solution's update() in ShadowDance
    public void update(Input input, Accuracy accuracy) {
        levelDraw();

        scoreFont.drawString("SCORE " + levelScore, SCORE_X, SCORE_Y);

        for(int i = 0; i < lanesArray.size(); i++) {
            // Update score
            levelScore += lanesArray.get(i).update(input, accuracy);
        }

        accuracy.update();
        levelFinished = checkFinished();

    }

    /** This function draws each Lane in lanesArray and calls each Lane's laneDraw() function
     * to draw each Lane's notes.
     */
    public void levelDraw() {
        // Draw each Lane and call laneDraw() for each lane to draw their notes
        for(int i = 0; i < lanesArray.size(); i++) {
            lanesArray.get(i).laneDraw();
        }
    }

    private boolean checkFinished() {
        for (int i = 0; i < lanesArray.size(); i++) {
            // Check if every Lane in lanesArray is finished
            if (!lanesArray.get(i).isFinished()) {
                return false;
            }
        }
        // All Lanes are finished!
        return true;
    }

}
