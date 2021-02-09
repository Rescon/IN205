package ensta.Ship;

public class Carrier extends AbstractShip {
    public Carrier() {
        super("C", "CARRIER", 5, Orientation.EST);
    }
    public Carrier(Orientation orientation) {
        super("C", "CARRIER", 5, orientation);
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "label='" + label +
                ", nom='" + nom +
                ", size=" + size +
                ", orientation=" + orientation +
                '}';
    }
}
