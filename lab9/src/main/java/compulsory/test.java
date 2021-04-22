package compulsory;

public class Test {
    public static void main(String[] args) {
        var nush = EntityManagerSingleton.getInstance();
        var nush2 = nush.createEntityManager();
        nush2.getTransaction().begin();
        System.out.println("merge?");
    }
}
