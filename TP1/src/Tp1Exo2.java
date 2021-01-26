class Tp1Exo2 {
    /*
     * Exercice 2 du TP1
     * vous pouvez par exemple utiliser la commande suivante:
     * user@machine$> javac Tp1Exo2.java ; java Tp1Exo2
     */

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        // Exercice 2
        /*
        Algèbre de base de Java
            types primitifs,
            opérations,
            comparaisons...
        */
        {   // les symboles '+' et '++' (pré/post -incrémentation)
            // Décommentez/Recommentez les lignes de code pour vous familiariser avec les opérations.
            int a = 1;
            int b;
            b = a;
            b = b + 1;
            //b = a++;
            //b = ++a;
            print("a:" + a + " b:" + b);
            // Le fonctionnement est le même pour les symboles '-' et '--' (pré/post -décrémentation)
        }
        {
            int a;
            long b, c = 1L;
            a = 0;
            b = 2;// Commentez cette ligne et interprétez l'erreur Java
            print("a:" + a + " b:" + b + " c:" + c);
        }
        {
            short a = 257;
            byte b = (byte) 257;
            print("a:" + a + " b:" + b);
        }
        {
            float a = 42.0F;
            double b = 1000.0;
            float c = (float) (a / b);
            print("a:" + a + " b:" + b + " c:" + c);
            // Essayez de diviser par zér0 ?
        }
        {
            byte a = 1;
            a = (byte) (a * 2);
            print("a:" + a);
        }
        /*
         *   En pratique nous utilisons surtout les types "int" et "double"
         *   ("long" pour des identifiants sur 64 bits)
         */

        {// Expressions booléennes
            int small = 0;
            double big = 1000.0;
            int same1 = 42, same2 = 42;

            boolean bigIsBiggerThanSmall = big > small;
            print(bigIsBiggerThanSmall);

            print(same1 == same2);
            print( 1 == 1 || same1 >= same2 && same2 < same1);
        }
        // assurez vous de comprendre tout cet algèbre de base avant de passer à la suite
        // n'hésitez pas à vous reporter au cours précédent ou de demander aux responsables de l'enseignement
    }
}
