import bagel.*;

public class Projectile {
    private final int PROJECTILE_SPEED = 6;
    private final Image projectileImage = new Image("res/arrow.PNG");
    private int rotation = 0;
    private int projectileX;
    private int projectileY;
    private int destX;
    private int destY;
    private boolean isShooting = false;

    public Projectile(int startX, int startY, int destX, int destY) {
        this.projectileX = startX;
        this.projectileY = startY;
        this.destX = destX;
        this.destY = destY;
        this.setIsShooting(true);
    }

    public void setIsShooting(boolean state) {
        this.isShooting = state;
    }

    public void projectileDraw() {

    }
}
