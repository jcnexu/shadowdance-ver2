import bagel.*;
import bagel.util.Vector2;

import java.lang.Math;

/** Class that creates a Projectile object and implements the drawing of it.
 */
public class Projectile {
    private final int PROJECTILE_SPEED = 6;
    private final int PROJECTILE_RANGE = 62;
    private final Image projectileImage = new Image("res/arrow.PNG");
    private double rotation = 0;
    private Vector2 start;
    private Vector2 dest;
    private boolean isShooting = false;

    /** Constructor that creates a Projectile object.
     * @param startX The starting x-coordinate of the Projectile
     * @param startY The starting y-coordinate of the Projectile
     * @param destX The x-coordinate of the destination (x-coordinate of Enemy)
     * @param destY The y-coordinate of the destination (y-coordinate of Enemy).
     */
    public Projectile(int startX, int startY, double destX, double destY) {
        this.start = new Vector2(startX, startY);
        this.dest = new Vector2(destX, destY);
        this.rotation = calculateRotation(this.start, this.dest);
        this.setIsShooting(true);
    }

    /** Getter to return the start Vector2 of
     * Projectile object.
     * @return start.
     */
    public Vector2 getStart() {
        return this.start;
    }

    /** Getter to return the dest Vector2 of
     * Projectile object
     * @return dest.
     */
    public Vector2 getDest() {
        return this.dest;
    }


    /** Setter that sets the isShooting attribute of a Projectile.
     * @param state The state that isShooting should be changed to.
     */
    public void setIsShooting(boolean state) {
        this.isShooting = state;
    }

    /** Calculates the angle (in radians) of the rotation of the
     * Projectile.
     * @param start The starting vector/coordinates of the Projectile
     * @param dest The destination vector/coordinates of the Projectile
     * @return angle The angle calculated in radians.
     */
    public double calculateRotation(Vector2 start, Vector2 dest) {
        double adjacent = dest.x - start.x;
        double opposite = dest.y - start.y;
        double oppAdj = opposite / adjacent;

        return Math.tanh(oppAdj);
    }

    /** Draws the Projectile if it has been created/is being shot by the player.
     */
    public void projectileDraw() {
        if(isShooting) {
            double x = this.getStart().x;
            double y = this.getStart().y;
            DrawOptions rotating = new DrawOptions();

            projectileImage.draw(x, y, rotating.setRotation(rotation));
        }
    }

    /** Moves the Projectile towards its destination.
     * Once Projectile reaches within the PROJECTILE_RANGE of its target,
     * it stops being drawn and kills the Enemy it's aimed at too.
     */
    public void update() {
        projectileDraw();

        /* Increase x and y-coordinates of Projectile by
        PROJECTILE_SPEED to move it
        */

        if((this.getDest().x - this.getStart().x >= PROJECTILE_RANGE)
        && (this.getDest().y - this.getStart().y >= PROJECTILE_RANGE)) {
            this.setIsShooting(false);
            /* Deactivate Enemy as well */
        }
    }

}
