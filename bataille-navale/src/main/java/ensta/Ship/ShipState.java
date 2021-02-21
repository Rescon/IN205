package ensta.Ship;

import ensta.*;

public class ShipState {
    // Attributs
    protected AbstractShip ship;
    protected Boolean struck;

    // Constructeur
    public ShipState()
    {
        this.ship = null;
        this.struck = null;
    }

    public ShipState(AbstractShip ship, boolean struck) {
        this.ship = ship;
        this.struck = struck;
    }

    // Accesseurs
    public AbstractShip getShip() {
        return ship;
    }

    public Boolean isStruck() {
        return struck;
    }

    // Mutateurs
    public void setShip(AbstractShip ship) {
        this.ship = ship;
    }

    public void setStruck(Boolean struck) {
        this.struck = struck;
    }

    // Méthode
    public void addStrike() throws Exception {
        if (this.ship.isSunk()) {
            throw new Exception("Ce navire a déjà coulé.");
        } else {
            this.struck = true;
            this.ship.addStrike();
        }
    }

    public String toString() {
        String result;
        if (this.isStruck()) {
            result = ColorUtil.colorize(this.ship.label, ColorUtil.Color.RED);
        } else {
            result = this.ship.label;
        }
        return result;
    }

    public boolean isSunk() {
        return this.ship.isSunk();
    }
}
