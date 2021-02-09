package ensta.Ship;

public abstract class AbstractShip {
    // Attributs
    protected String label;
    protected String nom;
    protected int size;
    protected Orientation orientation;

    // Constructeur
    public AbstractShip(String label, String nom, int size, Orientation orientation){
        this.label = label;
        this.nom = nom;
        this.size = size;
        this.orientation = orientation;
    }

    // Accesseurs
    public String getLabel() {
        return label;
    }
    public String getNom() {
        return nom;
    }
    public int getSize() {
        return size;
    }
    public Orientation getOrientation() {
        return orientation;
    }

    // Mutateurs
    public void setLabel(String label) {
        this.label = label;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "AbstractShip{" +
                "label='" + label +
                ", nom='" + nom +
                ", size=" + size +
                ", orientation=" + orientation +
                '}';
    }
}