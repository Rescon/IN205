package ensta;

import ensta.Ship.*;

public class TestShip {
    public static void main(String [] args){
        Destroyer Destroyer_1 = new Destroyer();
        Submarine Submarine_2 = new Submarine(Orientation.NORD);
        BattleShip BattleShip_3 = new BattleShip(Orientation.SUD);
        Carrier Carrier_4 = new Carrier(Orientation.OUEST);

        System.out.println(Destroyer_1);
        System.out.println(Submarine_2);
        System.out.println(BattleShip_3);
        System.out.println(Carrier_4);
    }
}
