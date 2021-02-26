package ensta;

import ensta.Board.*;
import ensta.Ship.*;

import java.util.ArrayList;

public class TestGame {
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Board test = new Board("test");

        Destroyer Destroyer_1 = new Destroyer();
        Submarine Submarine_1_1 = new Submarine();
        Submarine Submarine_1_2 = new Submarine();
        BattleShip BattleShip_1 = new BattleShip();
        Carrier Carrier_1 = new Carrier();
        AbstractShip[] ships = new AbstractShip[]{Destroyer_1,Submarine_1_1,Submarine_1_2,BattleShip_1,Carrier_1};

        BattleShipsAI ai = new BattleShipsAI(test,test);
        ai.putShips(ships);
        ai.board.print();

        int countDestroyedShip = 0;
        ArrayList<Integer> coords = new ArrayList<>();
        while(countDestroyedShip != 5){
            Hit hit = ai.sendHit(coords);
            if(hit != Hit.MISS && hit != Hit.STRIKE){
                countDestroyedShip++;
            }
            ai.board.print();
            sleep(200);
        }
    }

}
