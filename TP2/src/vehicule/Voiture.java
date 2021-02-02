package vehicule;

public class Voiture extends Vehicule implements Motorise{
    // Attributs
    protected int nombrePortes;
    protected Propulsion propulsion;
    protected int puissance;

    // Constructeur
    public Voiture(String modele, int nombrePlaces, float poids){
        this(modele, nombrePlaces, poids, 4);
    }
    public Voiture(String modele, int nombrePlaces, float poids, int nombrePortes){
        this(modele, nombrePlaces, poids, nombrePortes, Propulsion.DIESEL);
    }
    public Voiture(String modele, int nombrePlaces, float poids, int nombrePortes, Propulsion propulsion){
        this(modele, nombrePlaces, poids, nombrePortes, propulsion, 1000);
    }
    public Voiture(String modele, int nombrePlaces, float poids, int nombrePortes, Propulsion propulsion, int puissance) {
        super(modele, nombrePlaces, poids);
        this.nombrePortes = nombrePortes;
        this.propulsion = propulsion;
        this.puissance = puissance;
    }

    // Accesseurs
    public int getNombrePortes() {
        return nombrePortes;
    }

    // Mutateurs
    public void setNombrePortes(int nombrePortes) {
        this.nombrePortes = nombrePortes;
    }

    // Implements méthode getConsommation
    @Override
    public float getConsommation() {
        int coefficient;
        switch (this.propulsion) {
            case ESSENCE:
                coefficient = 5;
                break;
            default:
                coefficient = 3;
        }
        return (puissance * coefficient) / poids;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Voiture(modele=" + modele +
                ", nombrePlaces=" + nombrePlaces +
                ", poids=" + poids +
                ", nombrePortes=" + nombrePortes +
                ", puissance=" + puissance +
                ", propulsion=" + propulsion +
                ", consommation=" + getConsommation() + ")";
    }
}
