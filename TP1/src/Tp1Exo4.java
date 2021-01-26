class Tp1Exo4 {
    public static void print(Object o) {
        System.out.println(o);
    }

    public static boolean estPalindrome(String mot) {
        boolean estPalidrome = true;
        int len = mot.length();

        for (int i = 0; i < (int)(len/2); i++) {
            if(mot.charAt(i) != mot.charAt(len-i-1)){
                estPalidrome = false;
                break;
            }
        }
        return estPalidrome;
    }

    public static void main(String[] args) {
        boolean pizzaEstUnPalindrome = estPalindrome("pizza");
        print( pizzaEstUnPalindrome ? "pizza est un palindrome" : "pizza n'est pas un palindrome" );
        boolean kayakEstUnPalindrome = estPalindrome("kayak");
        print( kayakEstUnPalindrome ? "kayak est un palindrome" : "kayak n'est pas un palindrome" );
    }
}