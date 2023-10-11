import bagel.Keys;

public class SpecialLane extends Lane {
    public SpecialLane(String dir, int centreX) {
        super(dir, centreX);
        this.relevantKey = Keys.SPACE;
    }
}
