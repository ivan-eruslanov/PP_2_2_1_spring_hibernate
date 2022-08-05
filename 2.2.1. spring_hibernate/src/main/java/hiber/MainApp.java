package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Users and Cars
      User ivan = new User("Иван", "Иванов", "iv.ivanov@mail.ru");
      Car gaz = new Car("Gaz", 3110);
      ivan.setCar(gaz);
      userService.add(ivan);

      User uri = new User("Юрий", "Петров", "ur.petrov@list.ru");
      Car vaz = new Car("Vaz", 2106);
      uri.setCar(vaz);
      userService.add(uri);

      User roman = new User("Роман", "Романов", "ro.romanov@bk.ru");
      Car lada = new Car("Lada", 2114);
      roman.setCar(lada);
      userService.add(roman);

      User maks = new User("Максим", "Максимов", "m.maksimov@gmail.com");
      Car uaz = new Car("Uaz", 3306);
      maks.setCar(uaz);
      userService.add(maks);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      // Search car
      User tmp = userService.getUserByCar("Lada", 2114);
      System.out.println("Find a car by model and series." + tmp);
      context.close();
   }
}
