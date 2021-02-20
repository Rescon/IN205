package ensta;
import java.io.Serializable;
import java.util.List;

import ensta.Board.*;
import ensta.Ship.*;

public class Player {
    // Attributs
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    // Constructeur
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    // Accesseurs
    public Board getBoard() {
        return board;
    }
    public Board getOpponentBoard() {
        return opponentBoard;
    }
    public int getDestroyedCount() {
        return destroyedCount;
    }
    public boolean isLose() {
        return lose;
    }

    // Mutateurs
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }
    public void setDestroyedCount(int destroyedCount) {
        this.destroyedCount = destroyedCount;
    }
    public void setLose(boolean lose) {
        this.lose = lose;
    }


    // Méthode
    // Read keyboard input to get ships coordinates. Place ships on given coodrinates.
    public void putShips() {
        boolean done;
        boolean placement_successful;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getNom(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // Set ship orientation
            switch (res.orientation) {
                case "n":
                    s.setOrientation(Orientation.NORD);
                    break;
                case "s":
                    s.setOrientation(Orientation.SUD);
                    break;
                case "w":
                    s.setOrientation(Orientation.OUEST);
                    break;
                case "e":
                    s.setOrientation(Orientation.EST);
                    break;
            }

            // Put ship at given position
            placement_successful = true;
            try {
                board.putShip(s, res.y, res.x);
            } catch (Exception e){
                System.out.println(e);
                placement_successful = false;
            }
            // When ship placement successful
            if(placement_successful){
                ++i;
            }

            done = i == 5;
            board.print();
        } while (!done);
    }


    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }


    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
