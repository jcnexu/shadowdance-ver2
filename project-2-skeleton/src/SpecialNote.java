import bagel.*;

public class SpecialNote extends Note {
    private static final int START_SPECIAL_Y = 100;
    private String specialEffect;
    private static final int SPEED_UP = 1;
    private static final int SLOW_DOWN = -1;
    // How many frames DoubleScore note's effect will be rendered for
    private static final int DOUBLESCORE_DURATION = 480;

    public SpecialNote(String dir, String specialEffect, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.specialEffect = specialEffect;
        this.startY = START_SPECIAL_Y;

        if(specialEffect.equalsIgnoreCase("DoubleScore")) {
            this.noteImage = new Image("res/note2x.png");
        }
        else {
            this.noteImage = new Image("res/note" + specialEffect + ".png");
        }
    }

    public String getSpecialEffect() {
        return this.specialEffect;
    }

    public void noteEffect() {
       if(this.getSpecialEffect().equalsIgnoreCase("DoubleScore")) {
           // Implement effect
       }

    }

}
