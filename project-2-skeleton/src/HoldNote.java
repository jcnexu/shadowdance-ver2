public class HoldNote extends Note {
    private static final int START_HOLD_Y = 24;
    private static final int HEIGHT_OFFSET = 82;

    public HoldNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.startY = START_HOLD_Y;
    }
}
