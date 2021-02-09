package ensta;

public class TestBoard{
    public static void main(String [] args){
        Board joueur_1 = new Board("joueur 1");
        joueur_1.print();
        Board joueur_2 = new Board("joueur 2", 20);
        joueur_2.print();
    }
}
