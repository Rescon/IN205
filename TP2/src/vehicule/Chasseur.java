package vehicule;

public class Chasseur extends Avion implements Motorise {
    // Attributs
    protected int puissance;

    // Constructeur
    public Chasseur(String modele, int nombrePlaces, float poids, int altitudeMax) {
        this(modele, nombrePlaces, poids, altitudeMax, 1);
    }
    public Chasseur(String modele, int nombrePlaces, float poids, int altitudeMax, int puissance) {
        super(modele, nombrePlaces, poids, altitudeMax);
        this.puissance = puissance;
    }

    // Accesseurs
    public int getPuissance() {
        return puissance;
    }

    // Mutateurs
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    // Implements méthode getConsommation
    @Override
    public float getConsommation() {
        return puissance / poids;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Chasseur(modele=" + modele +
                ", nombrePlaces=" + nombrePlaces +
                ", poids=" + poids +
                ", altitudeMax=" + altitudeMax +
                ", puissance=" + puissance +
                ", consommation=" + getConsommation() + ")";
    }
}
