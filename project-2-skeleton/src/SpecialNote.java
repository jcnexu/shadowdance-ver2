import bagel.*;

public class SpecialNote extends Note {
    private static final int START_SPECIAL_Y = 100;

    public SpecialNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.startY = START_SPECIAL_Y;
    }
}
