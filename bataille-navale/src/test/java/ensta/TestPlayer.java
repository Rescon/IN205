package ensta;

import ensta.Board.*;
import ensta.Ship.*;

import java.util.*;

public class TestPlayer {
    public static void main(String [] args){
        Board board = new Board("joueur 1");
        Board opponentBoard = new Board("joueur 2");
        Destroyer Destroyer_1 = new Destroyer();
        Submarine Submarine_1_1 = new Submarine();
        Submarine Submarine_1_2 = new Submarine();
        BattleShip BattleShip_1 = new BattleShip();
        Carrier Carrier_1 = new Carrier();
        List<AbstractShip> ships = Arrays.asList(Destroyer_1,Submarine_1_1,Submarine_1_2,BattleShip_1,Carrier_1);
        Player player = new Player(board,opponentBoard,ships);
        player.putShips();

        Destroyer Destroyer_2 = new Destroyer();
        Submarine Submarine_2_1 = new Submarine();
        Submarine Submarine_2_2 = new Submarine();
        BattleShip BattleShip_2 = new BattleShip();
        Carrier Carrier_2 = new Carrier();
        try{
            opponentBoard.putShip(Destroyer_2, 0, 0);
            opponentBoard.putShip(Submarine_2_1, 1, 0);
            opponentBoard.putShip(Submarine_2_2, 2, 0);
            opponentBoard.putShip(BattleShip_2, 3, 0);
            opponentBoard.putShip(Carrier_2, 4, 0);
        }catch (Exception e){
            //System.out.println(e);
        }

        opponentBoard.print();

        ArrayList<Integer> coords = new ArrayList<>();
        for(int i = 0; i < 5;++i){
            player.sendHit(coords);
            System.out.println(coords);
        }
    }
}
