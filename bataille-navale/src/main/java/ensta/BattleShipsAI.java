package ensta;

import ensta.Board.*;
import ensta.Ship.*;
import java.io.Serializable;
import java.util.*;

public class BattleShipsAI implements Serializable {

    /* **
     * Attributs
     */

    /**
     * grid size.
     */
    private final int size;

    /**
     * My board. My ships have to be put on this one.
     */
    final IBoard board;

    /**
     * My opponent's board. My hits go on this one to strike his ships.
     */
    private final IBoard opponent;

    /**
     * Coords of last known strike. Would be a good idea to target next hits around this point.
     */
    private int lastStrike[];

    /**
     * If last known strike lead me to think the underlying ship has vertical placement.
     */
    private Boolean lastVertical;

    /* **
     * Constructeur
     */

    /**
     *
     * @param myBoard board where ships will be put.
     * @param opponentBoard Opponent's board, where hits will be sent.
     */
    public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
        this.board = myBoard;
        this.opponent = opponentBoard;
        size = board.getSize();
    }

    /* **
     * Méthodes publiques
     */

    /**
     * Put the ships on owned board.
     * @param ships the ships to put
     */
    public void putShips(AbstractShip ships[]) throws Exception {
        int x, y, o;
        Random rnd = new Random();

        for (AbstractShip s : ships) {
            do {
                x = rnd.nextInt(board.getSize());
                y = rnd.nextInt(board.getSize());
                o = rnd.nextInt(4);
                switch (o){
                    case 0:
                        s.setOrientation(Orientation.NORD);
                        break;
                    case 1:
                        s.setOrientation(Orientation.EST);
                        break;
                    case 2:
                        s.setOrientation(Orientation.SUD);
                        break;
                    case 3:
                        s.setOrientation(Orientation.OUEST);
                        break;
                }
            } while(!canPutShip(s, x, y));
            board.putShip(s, x, y);
        }
    }

    /**
     *
     * @param coords array must be of size 2. Will hold the coord of the send hit.
     * @return the status of the hit.
     */
    public Hit sendHit(ArrayList<Integer> coords) {
        int res[] = null;

        // already found strike & orientation?
        if (lastVertical != null) {
            if (lastVertical) {
                res = pickVCoord(coords);
            } else {
                res = pickHCoord(coords);
            }

            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
                lastVertical = null;
            }
        } else if (lastStrike != null) {
            // if already found a strike, without orientation
            // try to guess orientation
            res = pickVCoord(coords);
            if (res == null) {
                res = pickHCoord(coords);
            }
            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
            }
        }

        if (lastStrike == null) {
            res = pickRandomCoord(coords);
        }

        Hit hit = opponent.sendHit(res[0], res[1]);
        board.setHit(hit != Hit.MISS, res[0], res[1]);

        if (hit != Hit.MISS) {
            if (lastStrike != null) {
                lastVertical = guessOrientation(lastStrike, res);
            }
            lastStrike = res;
        }

        coords.add(size*res[0]+res[1]);
        return hit;
    }

    /* ***
     * Méthodes privées
     */

    private boolean canPutShip(AbstractShip ship, int x, int y) {
        Orientation o = ship.getOrientation();
        int dx = 0, dy = 0;
        if (o == Orientation.EST) {
            if (y + ship.getSize() >= this.size) {
                return false;
            }
            dy = 1;
        } else if (o == Orientation.SUD) {
            if (x + ship.getSize() >= this.size) {
                return false;
            }
            dx = 1;
        } else if (o == Orientation.NORD) {
            if (x + 1 - ship.getSize() < 0) {
                return false;
            }
            dx = -1;
        } else if (o == Orientation.OUEST) {
            if (y + 1 - ship.getSize() < 0) {
                return false;
            }
            dy = -1;
        }

        int ix = x;
        int iy = y;

        for (int i = 0; i < ship.getSize(); ++i) {
            if (board.hasShip(ix, iy)) {
                return false;
            }
            ix += dx;
            iy += dy;
        }

        return true;
    }

    private boolean guessOrientation(int[] c1, int[] c2) {
        return c1[0] == c2[0];
    }

    private boolean isUndiscovered(int x, int y, ArrayList<Integer> coords) {
        return x >= 0 && x < size && y >= 0 && y < size && !coords.contains(size*x+y);
    }

    int[] pickRandomCoord(ArrayList<Integer> coords) {
        Random rnd = new Random();
        int x;
        int y;

        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
        } while (!isUndiscovered(x, y, coords));

        return new int[] {x, y};
    }

    /**
     * pick a coord verically around last known strike
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickVCoord(ArrayList<Integer> coords) {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int iy : new int[]{y - 1, y + 1}) {
            if (isUndiscovered(x, iy, coords)) {
                return new int[]{x, iy};
            }
        }
        return null;
    }

    /**
     * pick a coord horizontally around last known strike
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickHCoord(ArrayList<Integer> coords) {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int ix : new int[]{x - 1, x + 1}) {
            if (isUndiscovered(ix, y, coords)) {
                return new int[]{ix, y};
            }
        }
        return null;
    }
}
