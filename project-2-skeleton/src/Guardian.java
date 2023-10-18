import bagel.*;
import bagel.util.Point;

public class Guardian {
    private final static int IMAGE_X = 800;
    private final static int IMAGE_Y = 600;
    private final int guardianX;
    private final int guardianY;
    private final Keys relevantKey = Keys.LEFT_SHIFT;
    private final Image guardianImage = new Image("res/guardian.png");

    public Guardian() {
        this.guardianX = IMAGE_X;
        this.guardianY = IMAGE_Y;
    }

    public void guardianDraw() {
        guardianImage.draw(this.guardianX, this.guardianY);
    }

    /* Create function that once left shift key is pressed, draw a projectile
    and shoot that projectile.
    * */
    public void triggerProjectile(Input input, Keys relevantKey) {
        if(input.wasPressed(relevantKey)) {
            Point destination = this.findNearestEnemy();
            double destinationX = destination.x;
            double destinationY = destination.y;
            Projectile arrow = new Projectile(guardianX, guardianY, destinationX, destinationY);
            arrow.setIsShooting(true);

        }
    }

    public Point findNearestEnemy() {
        Point coord = new Point(0,0);
        return coord;
    }
}
