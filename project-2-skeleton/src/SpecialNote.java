import bagel.*;

/** The subclass of Note that implements the drawing, movement and respective special effect
 * of each Special Note. All contained in 1 class to allow easier access from other classes.
 */
public class SpecialNote extends Note {
    private static final int START_SPECIAL_Y = 100;
    private String specialEffect;
    private static final int SPEED_UP = 1;
    private static final int SLOW_DOWN = -1;
    // How many frames DoubleScore note's effect will be rendered for
    private static final int DOUBLESCORE_DURATION = 480;
    private static final int TARGET_HEIGHT = 657;


    /** Constructor that creates a new SpecialNote.
     * @param dir The direction/type of lane of SpecialNote (always Special but needed to user super
     *            constructor from parent class Note
     * @param specialEffect The specific special effect of this SpecialNote
     * @param startX The starting x-coordinate of the SpecialNote
     * @param appearanceFrame The frame number where SpecialNote starts being drawn.
     */
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

    /** Getter that returns the specific special effect of the SpecialNote.
     * @return specialEffect.
     */
    public String getSpecialEffect() {
        return this.specialEffect;
    }

    /** Implements the special effect of the SpecialNote depending on the specified
     * specialEffect of the SpecialNote object.
     */
    public void noteEffect() {
       if(this.getSpecialEffect().equalsIgnoreCase("DoubleScore")) {
           // Implement effect
       } else if(this.getSpecialEffect().equalsIgnoreCase("SlowDown")) {
           // Implement effect
       } else if(this.getSpecialEffect().equalsIgnoreCase("SpeedUp")) {
           // Implement effect
       } else if(this.getSpecialEffect().equalsIgnoreCase("Bomb")) {
           // Implement effect
       }

    }

    /** Overrides checkScore in parent Note class. Sees if specialNote object was scored/activated
     * by the player.
     * @param input The input from the user/player
     * @param accuracy The accuracy used to calculate the relevant score and score range
     * @param targetHeight The target y-coordinate (y-coordinate of stationary image)
     * @param relevantKey The relevant key of the Special Note
     * @return The relevant score of the SpecialNote scored by the player.
     */
    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if(isActive()) {
            String specialEffect = this.getSpecialEffect();
            int score = accuracy.specialEvaluateScore(specialEffect, startY, targetHeight,
                    input.wasPressed(relevantKey));

            if(score != Accuracy.SPECIAL_MISSED) {
                deactivate();
                return score;
            }
            // Deactivate SpecialNote once it passes the stationary image and hasn't been scored
            if(score == Accuracy.SPECIAL_MISSED && this.getStartY() > TARGET_HEIGHT + 200) {
                deactivate();
                return score;
            }
        }
        return 0;
    }

}
