import bagel.*;

public class Enemy {
    private final static int LOWEST_X = 100;
    private final static int LOWEST_Y = 100;
    private final static int HIGHEST_X = 900;
    private final static int HIGHEST_Y = 500;
    private final Image enemyImage = new Image("res/enemy.png");
    private final static int ENEMY_SPEED = 1;
    private int startX;
    private int startY;
    private String direction;

    public Enemy() {
        this.startX = generateX();
        this.startY = generateY();
        this.direction = generateDirection();
    }

    public int generateX() {
        return 0;
    }

    public int generateY() {
        return 0;
    }

    public String generateDirection() {
        return "Left";
    }
}
