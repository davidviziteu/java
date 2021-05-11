import repositories.DirectorRepo;
import utils.MyManager;

public class Main {
    public static void main(String[] args) {

        MyManager.getInstance().initEntityManagerFactory();
        DirectorRepo directorRepo = new DirectorRepo();

        System.out.println(directorRepo.findByName("whatever"));
        MyManager.getInstance().closeEntityManagerFactory();

    }
}
