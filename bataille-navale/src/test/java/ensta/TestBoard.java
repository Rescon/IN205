package ensta;

import ensta.Board.*;
import ensta.Ship.*;

import java.util.Scanner;

public class TestBoard{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Board joueur_1 = new Board("joueur 1");
        Destroyer Destroyer_1 = new Destroyer();
        Submarine Submarine_1 = new Submarine(Orientation.NORD);
        int x,y;
        boolean flag;

        flag = Boolean.TRUE;
        while(flag) {
            flag = Boolean.FALSE;
            System.out.println("Veuillez entrer x:");
            x = scanner.nextInt();
            System.out.println("Veuillez entrer y:");
            y = scanner.nextInt();
            try {
                joueur_1.putShip(Destroyer_1, x-1, y-1);
            } catch (Exception e){
                flag = Boolean.TRUE;
                System.out.println(e);
            }
        }
        joueur_1.print();

        flag = Boolean.TRUE;
        while(flag) {
            flag = Boolean.FALSE;
            System.out.println("Veuillez entrer x:");
            x = scanner.nextInt();
            System.out.println("Veuillez entrer y:");
            y = scanner.nextInt();
            try {
                joueur_1.putShip(Submarine_1, x-1, y-1);
            } catch (Exception e){
                flag = Boolean.TRUE;
                System.out.println(e);
            }
        }
        joueur_1.print();
    }
}
