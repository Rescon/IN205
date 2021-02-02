package test;

import vehicule.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String [] args) {
//		Vehicule est devenu une classe abstriate donc cette partie du code n'est plus valide
//		Vehicule vehicule = new Vehicule("Renault", 4, 4000);
//		System.out.println(vehicule);
//		System.out.println("Nombre de Vehicule : " + Vehicule.getNbVehicule());


        Voiture voiture = new Voiture("Renault", 4, 4000);
//		System.out.println(voiture);
//      System.out.println("Nombre de Vehicule : " + Vehicule.getNbVehicule());

        Avion avion = new Avion("AirFrance", 300, 12500);
//		System.out.println(avion);
//      System.out.println("Nombre de Vehicule : " + Vehicule.getNbVehicule());

        Planeur planeur = new Planeur("Planeur-312", 2, 6000);
//		System.out.println(planeur);
//      System.out.println("Nombre de Vehicule : " + Vehicule.getNbVehicule());

        Chasseur chasseur = new Chasseur("Chasseur-312", 1, 4000, 9000, 4200);
//		System.out.println(chasseur);
//      System.out.println("Nombre de Vehicule : " + Vehicule.getNbVehicule());

        /*-------------------------------------------------------------------------*/

        List<Vehicule> Vehicules = new ArrayList<>();
        Vehicules.add(voiture);
        Vehicules.add(avion);
        Vehicules.add(chasseur);
        Vehicules.add(planeur);

        for(Vehicule vehicule : Vehicules) {
            System.out.println(vehicule);
        }
        System.out.println("Nombre de Vehicule : " + Vehicule.getNbVehicule());
    }
}
