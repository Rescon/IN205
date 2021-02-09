package ensta.Ship;

public class BattleShip extends AbstractShip {
    public BattleShip() {
        super("B", "BATTLESHIP", 4, Orientation.EST);
    }
    public BattleShip(Orientation orientation) {
        super("B", "BATTLESHIP", 4, orientation);
    }

    @Override
    public String toString() {
        return "BattleShip{" +
                "label='" + label +
                ", nom='" + nom +
                ", size=" + size +
                ", orientation=" + orientation +
                '}';
    }
}
