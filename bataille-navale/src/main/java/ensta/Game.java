package ensta;

import ensta.Board.*;
import ensta.Ship.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /* ***
     * Constante
     */
    private static final int size_grille = 10;
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private final Scanner sin = new Scanner(System.in);

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (true /*!loadSave()*/) {
            // init attributes
            System.out.println("entre ton nom:");
            String username = sin.nextLine();
            System.out.println("Votre nom est : " + username);

            List<AbstractShip> player1Ships = createDefaultShips();
            List<AbstractShip> player2Ships = createDefaultShips();
            Board b1 = new Board(username,size_grille);
            Board b2 = new Board("AI board",size_grille);
            player1 = new Player(b1,b2,player1Ships);
            player2 = new AIPlayer(b2,b1,player2Ships);

            b1.print();

            // place player ships
            try {
                player1.putShips();
                player2.putShips();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        ArrayList<Integer> coords_player = new ArrayList<>();
        ArrayList<Integer> coords_ai = new ArrayList<>();
        Board b1 = player1.board;
        Hit hit;
        int[] coords = new int[2];

        // main loop
        b1.print();
        boolean done;
        boolean strike;
        do {
            hit = player1.sendHit(coords_player);
            coords[0] = coords_player.get(coords_player.size() - 1)%size_grille;
            coords[1] = coords_player.get(coords_player.size() - 1)/size_grille;
            strike = hit != Hit.MISS;
            b1.setHit(strike, coords[1], coords[0]);

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            //save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords_ai);
                    coords[0] = coords_ai.get(coords_ai.size() - 1)/size_grille;
                    coords[1] = coords_ai.get(coords_ai.size() - 1)%size_grille;
                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        //save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    /*
    private void save() {
        try {
            // TODO bonus 2 : uncomment
            //  if (!SAVE_FILE.exists()) {
            //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            //  }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    */

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
