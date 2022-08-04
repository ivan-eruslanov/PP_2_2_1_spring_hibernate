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

      // Select users
      userService.add(new User("Михаил", "Расторгуев", "mi.rastorguev@mail.ru",
              (new Car("vaz", 2106))));
      userService.add(new User("Юрий", "Гагарин", "ur.gagarin@bk.ru",
              new Car("lada", 2113)));
      userService.add(new User("Ольга", "Бузова", "ol.buzova@list.ru",
              new Car("uaz", 3306)));
      userService.add(new User("Памела", "Андерсон", "pam.anderson@gmail.com",
              new Car("gaz", 2121)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      // Search car
      System.out.println("Search user by id:");
      System.out.println(userService.getUserByCar("gaz", 2121));

      context.close();
   }
}
