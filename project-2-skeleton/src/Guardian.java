import bagel.*;
import bagel.util.Point;
import java.util.ArrayList;

/** Class that creates a Guardian, draws it and implements its behaviour
 * of trying to get rid of an Enemy.
 */
public class Guardian {
    private final static int IMAGE_X = 800;
    private final static int IMAGE_Y = 600;
    private final int guardianX;
    private final int guardianY;
    private final Keys relevantKey = Keys.LEFT_SHIFT;
    private final Image guardianImage = new Image("res/guardian.png");
    private ArrayList<Projectile> arrows = new ArrayList<>();

    /** Constructor that creates a new Guardian object,
     */
    public Guardian() {
        this.guardianX = IMAGE_X;
        this.guardianY = IMAGE_Y;
    }

    /** Method that draws the Guardian.
     */
    public void guardianDraw() {
        guardianImage.draw(this.guardianX, this.guardianY);
    }

    /** Method sees if relevantKey for Guardian is pressed by the player. If it
     * has been,then create a new Projectile and shot it towards the nearest
     * Enemy.
     * @param input The input from the user/player
     * @param relevantKey The relevant key of the Guardian.
     */
    public void triggerProjectile(Input input, Keys relevantKey) {
        if(input.wasPressed(relevantKey)) {
            Point destination = this.findNearestEnemy();
            double destinationX = destination.x;
            double destinationY = destination.y;
            Projectile arrow = new Projectile(guardianX, guardianY, destinationX, destinationY);
            arrow.setIsShooting(true);
            arrows.add(arrow);

        }
    }

    /** Method that finds the nearest Enemy and returns the Point of that nearest
     * Enemy.
     */
    public Point findNearestEnemy() {
        Point coord = new Point(0,0);
        return coord;
    }
}
