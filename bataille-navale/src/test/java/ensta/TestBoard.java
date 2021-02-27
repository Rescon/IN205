package ensta;

import ensta.Board.*;
import ensta.Ship.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestBoard{
    public static void main(String [] args){
        Board board = new Board("joueur 1");
        Board opponentBoard = new Board("joueur 2");
        Destroyer Destroyer_1 = new Destroyer();
        Submarine Submarine_1 = new Submarine();
        Submarine Submarine_2 = new Submarine();
        BattleShip BattleShip_1 = new BattleShip();
        Carrier Carrier_1 = new Carrier();
        List<AbstractShip> ships = Arrays.asList(Destroyer_1,Submarine_1,Submarine_2,BattleShip_1,Carrier_1);
        Player player = new Player(board,opponentBoard,ships);
        try {
            player.putShips();
        } catch (Exception e) {
            e.printStackTrace();
        }
        board.setHit(true,1,1);
        board.print();
        board.setHit(false,1,2);
        board.print();
        board.sendHit(0,0);
        board.print();
        board.sendHit(0,1);
        board.print();
        board.sendHit(0,2);
        board.print();
    }
}
