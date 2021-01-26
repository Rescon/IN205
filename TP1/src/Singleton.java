public class Singleton {

    // Singleton Logic
    private static Singleton instance;
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = s1;
        print(s1 == s2);
        print(s2 == s3);
    }
}