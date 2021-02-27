package ensta.Board;

import ensta.ColorUtil;
import ensta.Hit;
import ensta.Ship.*;

public class Board implements IBoard {
    // Attributs
    protected String nom;
    protected int size;
    protected ShipState[][] navires;
    protected Boolean[][] frappes;

    // Constructeur
    public Board(String nom){
        this.nom = nom;
        this.size = 10;
        this.navires = new ShipState[size][size];
        this.frappes = new Boolean[size][size];
        for(int i = 0;i < size;++i){
            for(int j = 0;j < size;++j){
                navires[i][j] = new ShipState();
                frappes[i][j] = null;
            }
        }
    }
    public Board(String nom, int size){
        this.nom = nom;
        this.size = size;
        this.navires = new ShipState[size][size];
        this.frappes = new Boolean[size][size];
        for(int i = 0; i < size; ++i){
            for(int j = 0; j < size; ++j){
                navires[i][j] = new ShipState();
                frappes[i][j] = null;
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
    public ShipState getNavires_i_j(int i, int j) {
        return navires[i][j];
    }
    public Boolean getFrappes_i_j(int i, int j) {
        return frappes[i][j];
    }

    // Mutateurs
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setNavires_i_j(int i, int j, AbstractShip ship) {
        this.navires[i][j].setShip(ship);
        this.navires[i][j].setStruck(false);
    }
    public void setFrappes_i_j(int i, int j, Boolean etat) {
        this.frappes[i][j] = etat;
    }

    // Méthode print
    protected void print_aux(){
        System.out.println(nom);
        String ligne = "";
        ligne += "Navires :";
        for(int i = 0; i < 2*size-4; ++i){
            ligne += " ";
        }
        ligne += "Frappes :";
        System.out.println(ligne);

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

    @Override
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
                if(navires[i-1][j-1].getShip() == null){
                    ligne += " " + ".";
                }else{
                    ligne += " " + navires[i-1][j-1].toString();
                }
            }

            if(i < 10) {
                ligne += "   " + i + " ";
            }else{
                ligne += "   " + i + "";
            }
            for (int j = 1; j <= size; ++j) {
                if (frappes[i - 1][j - 1] == null) {
                    ligne += " " + ".";
                } else if (frappes[i - 1][j - 1] == false) {
                    ligne += " " + "X";
                } else {
                    ligne += " " + ColorUtil.colorize("X", ColorUtil.Color.RED);
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
            setNavires_i_j(shipPositions[i][0],shipPositions[i][1],ship);
        }
    }

    @Override
    public boolean hasShip(int x, int y) {
        if(getNavires_i_j(x,y).getShip() == null){
            return false;
        }
        return true;
    }

    @Override
    public void setHit(boolean hit, int x, int y) {
        setFrappes_i_j(x,y,hit);
    }

    @Override
    public Boolean getHit(int x, int y) {
        if(getNavires_i_j(x,y).getShip() == null){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Hit sendHit(int x, int y) {
        Hit result;
        if(getNavires_i_j(x,y).getShip() != null) {//Un bateau a été touché
            result = Hit.STRIKE;
            getNavires_i_j(x,y).addStrike();
            //System.out.println(result);
            if(getNavires_i_j(x,y).getShip().isSunk()) {//Le bateau a été coulé
                switch (getNavires_i_j(x,y).getShip().getLabel()) {
                    case "D":
                        result = Hit.DESTROYER;
                        break;
                    case "S":
                        result = Hit.SUBMARINE;
                        break;
                    case "B":
                        result = Hit.BATTLESHIP;
                        break;
                    case "C":
                        result = Hit.CARRIER;
                        break;
                }
                //System.out.println(result + " Coulé!");
            }
        }
        else {
            result = Hit.MISS;
            //System.out.println(result);
        }
        return result;
    }
}