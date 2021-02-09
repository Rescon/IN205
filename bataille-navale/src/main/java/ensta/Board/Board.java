package ensta.Board;

import ensta.Ship.*;

public class Board implements IBoard {
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
    @Override
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

    protected void checkOutOfRange(int x,int y) throws Exception{
        if(x < 0 || x >= size || y < 0 || y >= size){
            throw new Exception("Pas assez d'espace. Veuillez sélectionner une autre zone.");
        }
    }

    protected void checkNavireChevaucher(int x,int y) throws Exception {
        if (hasShip(x, y)) {
            throw new Exception("Deux navires se chevauchent. Veuillez sélectionner une autre zone.");
        }
    }

    @Override
    public void putShip(AbstractShip ship, int x, int y) throws Exception {
        int[][] shipPositions = new int[ship.getSize()][2];

        checkOutOfRange(x,y);
        checkNavireChevaucher(x,y);
        shipPositions[0][0] = x;
        shipPositions[0][1] = y;

        for(int i = 1; i < ship.getSize(); ++i){
            switch (ship.getOrientation()){
                case NORD:
                    x -= 1;
                    checkOutOfRange(x,y);
                    checkNavireChevaucher(x,y);
                    shipPositions[i][0] = x;
                    shipPositions[i][1] = y;
                    break;
                case SUD:
                    x += 1;
                    checkOutOfRange(x,y);
                    checkNavireChevaucher(x,y);
                    shipPositions[i][0] = x;
                    shipPositions[i][1] = y;
                    break;
                case EST:
                    y += 1;
                    checkOutOfRange(x,y);
                    checkNavireChevaucher(x,y);
                    shipPositions[i][0] = x;
                    shipPositions[i][1] = y;
                    break;
                case OUEST:
                    y -= 1;
                    checkOutOfRange(x,y);
                    checkNavireChevaucher(x,y);
                    shipPositions[i][0] = x;
                    shipPositions[i][1] = y;
                    break;
            }
        }

        for(int i = 0; i < ship.getSize(); ++i){
            setNavires_i_j(shipPositions[i][0],shipPositions[i][1],EtatNavires.NAVIRE);
        }
    }

    @Override
    public boolean hasShip(int x, int y) {
        if(getNavires_i_j(x,y) == EtatNavires.LIBRE){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public void setHit(boolean hit, int x, int y) {
        if(hit){
            setFrappes_i_j(x,y,EtatFrappes.FRAPPE);
        }else{
            setFrappes_i_j(x,y,EtatFrappes.RIEN);
        }
    }

    @Override
    public Boolean getHit(int x, int y) {
        if(getNavires_i_j(x,y) == EtatNavires.LIBRE){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}