import bagel.*;
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

/** Class that implements the drawing, movement and actions of the Enemy.
 */
public class Enemy {
    private final static int LOWEST_X = 100;
    private final static int LOWEST_Y = 100;
    private final static int HIGHEST_X = 900;
    private final static int HIGHEST_Y = 500;
    private final Image enemyImage = new Image("res/enemy.png");
    private final static int ENEMY_SPEED = 1;
    private static final int ENEMY_RANGE = 104;
    private int startX;
    private int startY;
    private String direction;
    private boolean active = false;
    private boolean killed = false;

    /** Constructor that creates a new Enemy object.
     * Generates a random x-coordinate, y-coordinate and moving direction.
     */
    public Enemy() {
        this.startX = generateX();
        this.startY = generateY();
        this.direction = generateDirection();
    }

    /** Getter that returns the direction the Enemy is currently
     * moving in.
     * @return direction.
     */
    public String getDirection() {
        return this.direction;
    }

    /** Setter that sets the direction of Enemy using given
     * new direction.
     * @param newDirection The new direction Enemy is to move in.
     */
    public void setDirection(String newDirection) {
        this.direction = newDirection;
    }
    /** Getter that returns the startX of Enemy object.
     * @return startX.
     */
    public int getStartX() {
        return this.startX;
    }

    /** Getter that returns the startY of Enemy object.
     * @return startY.
     */
    public int getStartY() {
        return this.startY;
    }

    /** Getter that returns the active state of Enemy object.
     * @return active;
     */
    public boolean getActive() {
        return this.active;
    }

    /** Setter that sets new value for startX
     * @param n New value to set startX to.
     */
    public void setStartX(int n) {
        this.startX = n;
    }

    /** Setter that sets new state for whether the Enemy was killed or not
     * @param state New state for killed to be changed to.
      */
    public void setKilled(boolean state) {
        this.killed = state;
    }

    /** Generates a random value to be the startX.
     * Generates a value between the lower and upper bounds set by constant values of
     * LOWEST_X and HIGHEST_X.
     * @return x The randomly generated integer value.
     */
    public int generateX() {
        int x = (int)Math.floor(Math.random() * (HIGHEST_X - LOWEST_X + 1) + LOWEST_X);
        return x;
    }

    /** Generates a random value to be the startY.
     * Generates a value between the lower and upper bounds set by constant values of
     * LOWEST_Y and HIGHEST_Y.
     * @return y The randomly generated integer value.
     */
    public int generateY() {
        int y = (int)Math.floor(Math.random() * (HIGHEST_Y - LOWEST_Y + 1) + LOWEST_Y);
        return y;
    }

    /** Generates a randomly picked direction for Enemy object to move in.
     * Generates either 0 to 1 and picks to move Left if 0 is generated, or Right if
     * otherwise.
     * @return Direction to be moved in.
     */
    public String generateDirection() {
        Random random = new Random();
        int randomPicker = random.nextInt(2);

        if(randomPicker == 0) {
            return "Left";
        } else {
            return "Right";
        }
    }

    /** Draws the Enemy object if it is active.
     */
    public void enemyDraw() {
        if(active) {
            enemyImage.draw(this.getStartX(), this.getStartY());
        }
    }

    /** Updates the Enemy object frame by frame. If Enemy is active
     * and within its coordinate bounds, it will keep on moving. If active
     * and outside its bounds, will make it move the opposite way.
     * Also updates whether an Enemy is active or not.
     * @param lanes The lanes ArrayList to be iterated through to see if the Enemy
     *              can steal a Note.
     */
    public void update(ArrayList<Lane> lanes) {
        int enemyX = this.getStartX();
        String currDirection = this.getDirection();

        enemyDraw();

        if(active) {

            if((this.getStartX() >= LOWEST_X) && (this.getStartX() <= HIGHEST_X)) {
                if(currDirection.equalsIgnoreCase("Left")) {
                    this.setStartX(enemyX - ENEMY_SPEED);
                }
                if(currDirection.equalsIgnoreCase("Right")) {
                    this.setStartX(enemyX + ENEMY_SPEED);
                }
            } else {
                // Need to change direction Enemy is moving in
                if(currDirection.equalsIgnoreCase("Left")) {
                    this.setDirection("Right");
                    this.setStartX(enemyX + ENEMY_SPEED);
                }
                if(currDirection.equalsIgnoreCase("Right")) {
                    this.setDirection("Left");
                    this.setStartX(enemyX - ENEMY_SPEED);
                }
            }
        }

        if (!killed && ((ShadowDance.getCurrFrame() % 600) == 0)) {
            active = true;
        }
    }


}
