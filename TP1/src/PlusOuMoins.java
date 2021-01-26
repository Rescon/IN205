import java.util.Scanner;

public class PlusOuMoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = (int) (Math.random() * 100);
        int time = 7;
        int proposition;

        System.out.println("Entrez un nombre entre 1 et 100 :");
        do {
            proposition = scanner.nextInt();
            if (proposition < 1 || proposition > 100) {
                System.out.println("Le nombre doit être entre 1 et 100 :");
            } else if (proposition < num) {
                System.out.println("Le nombre cherché est plus grand");
            } else if (proposition > num) {
                System.out.println("Le nombre cherché est plus petit");
            }
        } while (proposition != num && --time > 0);

        if (time > 0) {
            System.out.println("Bravo ! Le nombre mystère était " + num);
        } else {
            System.out.println("Dommage, il ne te reste plus d'essai.\nLe nombre mystère était " + num);
        }
    }
}
