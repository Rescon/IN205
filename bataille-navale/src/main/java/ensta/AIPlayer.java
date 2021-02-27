package ensta;

import ensta.Board.*;
import ensta.Ship.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AIPlayer extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    public void putShips() throws Exception {
        ai.putShips(ships);
    }

    public Hit sendHit(ArrayList<Integer> coords) {
        return ai.sendHit(coords);
    }
}
