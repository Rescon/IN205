public class Tp1Exo3 {
    public static double Fibonacci(int n) {
        double resultat;
        if(n == 0){
            resultat = 0;
        }else if(n == 1){
            resultat = 1;
        }else{
            resultat = Fibonacci(n-1) + Fibonacci(n-2);
        }
        return  resultat;
    }
    public static void main(String[] args) {
        int maVariable = 5;
        double resultat = Fibonacci(maVariable);
        System.out.println(resultat);
    }
}
