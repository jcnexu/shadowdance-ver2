import bagel.*;

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
}
