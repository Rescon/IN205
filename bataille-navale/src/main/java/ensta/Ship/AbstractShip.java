package ensta.Ship;

public abstract class AbstractShip {
    // Attributs
    protected String label;
    protected String nom;
    protected int size;
    protected Orientation orientation;
    protected int strikeCount;

    // Constructeur
    public AbstractShip(String label, String nom, int size, Orientation orientation){
        this.label = label;
        this.nom = nom;
        this.size = size;
        this.orientation = orientation;
        this.strikeCount = 0;
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
    public int getStrikeCount() {
        return strikeCount;
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
    public void setStrikeCount(int strikeCount) { this.strikeCount = strikeCount; }

    // Méthode
    @Override
    public String toString() {
        return "AbstractShip{" +
                "label='" + label +
                ", nom='" + nom +
                ", size=" + size +
                ", orientation=" + orientation +
                '}';
    }

    public void addStrike() {
        this.strikeCount ++;
    }

    public boolean isSunk() {
        if (this.strikeCount==this.size) {
            return true;
        }
        return false;
    }
}