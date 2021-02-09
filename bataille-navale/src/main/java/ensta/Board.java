package ensta;

public class Board {
    // Attributs
    protected String nom;
    protected int size;
    protected EtatNavires[][] navires;
    protected EtatFrappes[][] frappes;

    // Constructeur
    public Board(String nom){
        this.nom = nom;
        this.size = 10;
        this.navires = new EtatNavires[size][size];
        this.frappes = new EtatFrappes[size][size];
        for(int i = 0;i < size;++i){
            for(int j = 0;j < size;++j){
                navires[i][j] = EtatNavires.LIBRE;
                frappes[i][j] = EtatFrappes.LIBRE;
            }
        }
    }
    public Board(String nom, int size){
        this.nom = nom;
        this.size = size;
        this.navires = new EtatNavires[size][size];
        this.frappes = new EtatFrappes[size][size];
        for(int i = 0; i < size; ++i){
            for(int j = 0; j < size; ++j){
                navires[i][j] = EtatNavires.LIBRE;
                frappes[i][j] = EtatFrappes.LIBRE;
            }
        }
    }

    // Accesseurs
    public String getNom() {
        return nom;
    }
    public int getSize() {
        return size;
    }
    public EtatNavires getNavires_i_j(int i, int j) {
        return navires[i][j];
    }
    public EtatFrappes getFrappes_i_j(int i, int j) {
        return frappes[i][j];
    }

    // Mutateurs
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setNavires_i_j(int i, int j, EtatNavires etat) {
        this.navires[i][j] = etat;
    }
    public void setFrappes_i_j(int i, int j, EtatFrappes etat) {
        this.frappes[i][j] = etat;
    }

    // Méthode print
    protected void print_aux(){
        System.out.println(nom);
        String ligne = "";
        ligne += "Navires :";
        for(int i = 0; i < 2*size-5; ++i){
            ligne += " ";
        }
        ligne += "Frappes :";
        System.out.println("Navires :");

        ligne = "  ";
        for(int i = 0; i < size; ++i){
            ligne += " " + (char)(i+65);
        }
        for(int i = 0; i < 5; ++i){
            ligne += " ";
        }
        for(int i = 0; i < size; ++i){
            ligne += " " + (char)(i+65);
        }
        System.out.println(ligne);
    }

    public void print(){
        print_aux();
        String ligne = "";
        for (int i = 1; i <= size; ++i) {
            if(i < 10) {
                ligne = i + " ";
            }else{
                ligne = i + "";
            }
            for (int j = 1; j <= size; ++j) {
                switch (navires[i-1][j-1]){
                    case LIBRE:
                        ligne += " " + ".";
                        break;
                    case NAVIRE:
                        ligne += " " + "X";
                        break;
                }
            }

            if(i < 10) {
                ligne += "   " + i + " ";
            }else{
                ligne += "   " + i + "";
            }
            for (int j = 1; j <= size; ++j) {
                switch (frappes[i-1][j-1]){
                    case LIBRE:
                        ligne += " " + ".";
                        break;
                    case FRAPPE:
                        ligne += " " + "X";
                        break;
                    case RIEN:
                        ligne += " " + "O";
                        break;
                }
            }
            System.out.println(ligne);
        }
    }
}
