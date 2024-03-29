import bagel.*;
import java.util.ArrayList;
/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2023
 * Please enter your name below
 * @author Jane Xu;
 */
/** The class that runs everything in the game ShadowDance.
 */
public class ShadowDance extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DANCE";
    private final static int GAME_TITLE_X = 220;
    private final static int GAME_TITLE_Y = 250;
    private final Image BACKGROUND_IMAGE = new Image("res/background.png");
    private final Font defaultFont = new Font("res/FSO8BITR.TTF", 64);
    // Font and size for starting message
    private final Font startFont = new Font("res/FSO8BITR.TTF", 24);
    private final static String START_INSTRUCTIONS = "SELECT LEVELS WITH\nNUMBER KEYS";
    private final static String LEVELS = "      1      2      3";
    private final static String CLEAR_MESSAGE = "CLEAR!";
    private final static String TRY_AGAIN_MESSAGE = "TRY AGAIN";
    private final static String END_INSTRUCTIONS = "PRESS SPACE TO RETURN TO LEVEL SELECTION";
    private final static int END_SCREEN_Y = 300;
    private final static int END_INSTRUCTION_Y = 500;
    private final static int L1_TARGET = 150;
    private final static int L2_TARGET = 400;
    private final static int L3_TARGET = 350;
    private static int currFrame = 0;
    private boolean started = false;
    private boolean finished = false;
    private boolean targetMet = false;
    private String pressedKeyNum;
    // need to set this amount later after building lanes array
    private final Accuracy accuracy = new Accuracy();
    private Level level1 = new Level(1);
    private Level level2 = new Level(2);
    private Level level3 = new Level(3);
    private Guardian guardian = new Guardian();
    private Enemy enemy = new Enemy();

    /** Constructor that creates a ShadowDance.
     */
    public ShadowDance(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDance game = new ShadowDance();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     * Allows the player/user to choose which level of ShadowDance they want to play.
     * @param input The input from the user/player.
     */
    // Adapted from Project 1's solution's update() in ShadowDance
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if(!started) {
            // The start screen
            defaultFont.drawString(GAME_TITLE, GAME_TITLE_X, GAME_TITLE_Y);
            startFont.drawString(START_INSTRUCTIONS, GAME_TITLE_X + 100, GAME_TITLE_Y + 190);
            startFont.drawString(LEVELS, GAME_TITLE_X + 100, GAME_TITLE_X + 290);

            // Game starts after either 1, 2, 3 is pressed
            if(input.wasPressed(Keys.NUM_1)) {
                pressedKeyNum = "1";
                started = true;

            }
            if(input.wasPressed(Keys.NUM_2)) {
                pressedKeyNum = "2";
                started = true;
            }
            if(input.wasPressed(Keys.NUM_3)) {
                pressedKeyNum = "3";
                started = true;
            }
        } else if (finished) {
            // Target has been met!
            if(targetMet) {
                defaultFont.drawString(CLEAR_MESSAGE, (WINDOW_WIDTH/2 - defaultFont.getWidth(CLEAR_MESSAGE)/2),
                        END_SCREEN_Y);
                startFont.drawString(END_INSTRUCTIONS, (WINDOW_WIDTH/2 - startFont.getWidth(END_INSTRUCTIONS)/2),
                        END_INSTRUCTION_Y);
            }
            if(!targetMet) {
                defaultFont.drawString(TRY_AGAIN_MESSAGE, (WINDOW_WIDTH/2 - defaultFont.getWidth(TRY_AGAIN_MESSAGE)/2),
                        END_SCREEN_Y);
                startFont.drawString(END_INSTRUCTIONS, (WINDOW_WIDTH/2 - startFont.getWidth(END_INSTRUCTIONS)/2),
                        END_INSTRUCTION_Y);
            }

            if(input.wasPressed(Keys.SPACE)) {
                started = true;
                finished = false;
                // Reset levels
                level1.resetLevel(false, 0);
                level2.resetLevel(false, 0);
                level3.resetLevel(false, 0);
            }

        } else {
            // Game has started!
            if(pressedKeyNum.equals("1")) {
                // Level 1 has been chosen
                currFrame++;
                level1.update(input, accuracy);

                finished = level1.getLevelFinished();
                if(finished) {
                    targetMet = checkTargetMet(level1, L1_TARGET);
                }
            }
            if(pressedKeyNum.equals("2")) {
                // Level 2 has been chosen
                currFrame++;
                level2.update(input, accuracy);

                finished = level2.getLevelFinished();
                if(finished) {
                    targetMet = checkTargetMet(level2, L2_TARGET);
                }
            }
            if(pressedKeyNum.equals("3")) {
                // Level 3 has been chosen
                currFrame++;
                level3.update(input, accuracy);
                guardian.guardianDraw();

                ArrayList<Lane> lanes = level3.getLanesArray();

                enemy.update(lanes);

                finished = level3.getLevelFinished();
                if(finished) {
                    targetMet = checkTargetMet(level3, L3_TARGET);
                }
            }
        }

    }

    /** Getter that returns the current frame number of ShadowDance.
     * @return currFrame.
     */
    public static int getCurrFrame() {
        return currFrame;
    }

    /** Checks if the target score of a level has been met.
     * @param level The Level that is being checked
     * @param targetScore The target score needed to clear the level
     * @return boolean The state/if the level has been cleared.
     */
    public boolean checkTargetMet(Level level, int targetScore) {
        // Score has met/exceeded level target score
        if(level.getLevelScore() >= targetScore) {
            return true;
        }
        // Score hasn't met level target score
        return false;
    }

    /** Getter to return the Enemy object created in ShadowDance.
     * @return enemy.
     */
    public Enemy getEnemy() {
        return this.enemy;
    }

}
