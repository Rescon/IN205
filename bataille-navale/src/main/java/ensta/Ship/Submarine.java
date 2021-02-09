package ensta.Ship;

public class Submarine extends AbstractShip {
    public Submarine() {
        super("S", "SUBMARINE", 3, Orientation.EST);
    }
    public Submarine(Orientation orientation) {
        super("S", "SUBMARINE", 3, orientation);
    }

    @Override
    public String toString() {
        return "Submarine{" +
                "label='" + label +
                ", nom='" + nom +
                ", size=" + size +
                ", orientation=" + orientation +
                '}';
    }
}
