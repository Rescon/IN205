package vehicule;

public abstract class Vehicule {
    // Attributs
    protected String modele;
    protected int nombrePlaces;
    protected float poids;
    private static int nbVehicule = 0;

    // Constructeur
    public Vehicule(String modele, int nombrePlaces, float poids) {
        this.modele = modele;
        this.nombrePlaces = nombrePlaces;
        this.poids = poids;
        nbVehicule++;
    }

    // Accesseurs
    public String getModele() {
        return modele;
    }
    public int getNombrePlaces() {
        return nombrePlaces;
    }
    public float getPoids() {
        return poids;
    }
    public static int getNbVehicule() { return nbVehicule; }

    // Mutateurs
    public void setModele(String modele) {
        this.modele = modele;
    }
    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }
    public void setPoids(float poids) {
        this.poids = poids;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Vehicule(modele="+modele+
                ", nombrePlaces="+nombrePlaces+
                ", poids="+poids+")";
    }
}
