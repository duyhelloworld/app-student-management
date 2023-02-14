package testing;

import java.util.ArrayList;
import java.util.List;

import controller.CrudUserService;
import logging.Logging;

public class AppTest {
   public static void main(String[] args) {
       // System.getProperties().list(System.out);
       List<Long> list = new ArrayList<>();
       list.add(10l);
       list.add(4l);
       list.add(2l);
       Logging log = new Logging();
       // System.out.println(log.delete(list));
       // System.out.println(log.getInfos());
       // System.out.println(log.getFullInfo());
       // System.out.println(log.getError(new IOException("Not found file.")));

       CrudUserService service = new CrudUserService();
       // service.save("newwoman", "Jimmy Jackpot", true, "2022-12-01", "Berlin",
       // "0899323564");
       // System.out.println(service.getUser(1).toString());
       // System.out.println(service.getUsers(2, 2));
       // System.out.println(service.getUsers(0, 2));
       // System.out.println(service.getUsers(2, 10));
       // System.out.println(service.find(1));
       // System.out.println(service.getUsers(2, -1));
       // System.out.println(service.update(1l, "duydeptraiqua"));
       // System.out.println(service.getUsers().toString());

       //    System.out.println(log.getRangeId(list));
    // System.out.println(log.viewByUsername("duydeptraiquaa"));
    //    System.out.println(service.delete(list));
    System.out.println(service.update(0, "duyoi"));
   }
}
