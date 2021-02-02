package vehicule;

public class Planeur extends Avion{
    // Attributs
    protected int portance;

    // Constructeur
    public Planeur(String modele, int nombrePlaces, float poids){
        super(modele, nombrePlaces, poids);
    }
    public Planeur(String modele, int nombrePlaces, float poids, int altitudeMax) {
        this(modele, nombrePlaces, poids, altitudeMax, 1);
    }
    public Planeur(String modele, int nombrePlaces, float poids, int altitudeMax, int portance) {
        super(modele, nombrePlaces, poids, altitudeMax);
        this.portance = portance;
    }

    // Accesseurs
    public int getPortance() {
        return portance;
    }

    // Mutateurs
    public void setPortance(int portance) {
        this.portance = portance;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Planeur(modele=" + modele +
                ", nombrePlaces=" + nombrePlaces +
                ", poids=" + poids +
                ", altitudeMax=" + altitudeMax +
                ", portance=" + portance + ")";
    }
}
