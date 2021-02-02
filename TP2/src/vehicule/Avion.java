package vehicule;

public class Avion extends Vehicule{
    // Attributs
    protected int altitudeMax;

    // Constructeur
    public Avion(String modele, int nombrePlaces, float poids) {
        this(modele, nombrePlaces, poids, 10000);
    }
    public Avion(String modele, int nombrePlaces, float poids, int altitudeMax) {
        super(modele, nombrePlaces, poids);
        this.altitudeMax = altitudeMax;
    }

    // Accesseurs
    public int getAltitudeMax() {
        return altitudeMax;
    }

    // Mutateurs
    public void srtAltitudeMax(int altitudeMax) {
        this.altitudeMax = altitudeMax;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Avion(modele="+modele+
                ", nombrePlaces="+nombrePlaces+
                ", poids="+poids+
                ", altitudeMax="+altitudeMax+")";
    }
}
