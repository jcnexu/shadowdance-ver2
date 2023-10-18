import bagel.*;

/** Class that creates a Projectile object and implements the drawing of it.
 */
public class Projectile {
    private final int PROJECTILE_SPEED = 6;
    private final Image projectileImage = new Image("res/arrow.PNG");
    private int rotation = 0;
    private int projectileX;
    private int projectileY;
    private double destX;
    private double destY;
    private boolean isShooting = false;

    /** Constructor that creates a Projectile object.
     * @param startX The starting x-coordinate of the Projectile
     * @param startY The starting y-coordinate of the Projectile
     * @param destX The x-coordinate of the destination (x-coordinate of Enemy)
     * @param destY The y-coordinate of the destination (y-coordinate of Enemy).
     */
    public Projectile(int startX, int startY, double destX, double destY) {
        this.projectileX = startX;
        this.projectileY = startY;
        this.destX = destX;
        this.destY = destY;
        this.setIsShooting(true);
    }

    /** Setter that sets the isShooting attribute of a Projectile.
     * @param state The state that isShooting should be changed to.
     */
    public void setIsShooting(boolean state) {
        this.isShooting = state;
    }

    /** Draws the Projectile if it has been created/is being shot by the player.
     */
    public void projectileDraw() {

    }
}
