package ensta.Ship;

public class Destroyer extends AbstractShip {
    public Destroyer() {
        super("D", "DESTROYER", 2, Orientation.EST);
    }
    public Destroyer(Orientation orientation) {
        super("D", "DESTROYER", 2, orientation);
    }

    @Override
    public String toString() {
        return "Destroyer{" +
                "label='" + label +
                ", nom='" + nom +
                ", size=" + size +
                ", orientation=" + orientation +
                '}';
    }
}
