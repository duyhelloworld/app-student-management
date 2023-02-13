import controller.CrudUserService;

public class App {
    public static void main(String[] args) {
        // System.getProperties().list(System.out);
    //     Logging log = new Logging();
    //     System.out.println(log.getTime());
    //     System.out.println(log.getInfos());
    //     System.out.println(log.getFullInfo());
    //     System.out.println(log.getError(new IOException("Not found file.")));

    CrudUserService service = new CrudUserService();
    service.create("newwoman", "Jimmy Jackpot", true, "2022-12-01", "Berlin", "0899323564");
    // System.out.println(service.getUser(1).toString());
    System.out.println(service.getUsers(2, 2));
    System.out.println(service.getUsers(0, 2));
    System.out.println(service.getUsers(2, 10));
    System.out.println(service.find(1));
    // System.out.println(service.getUsers(2, -1));
    System.out.println(service.update(1l, "duydeptraiqua"));
    }
}
