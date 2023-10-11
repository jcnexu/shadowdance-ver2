import bagel.*;

public class NormalNote extends Note {
    private static final int START_NORMAL_Y = 100;

    public NormalNote(String dir, int startX, int appearanceFrame) {
        super(dir, startX, appearanceFrame);
        this.startY = START_NORMAL_Y;
    }

}


   /* public Note(String dir, int appearanceFrame) {
        noteImage = new Image("res/note" + dir + ".png");
        this.appearanceFrame = appearanceFrame;
    } */