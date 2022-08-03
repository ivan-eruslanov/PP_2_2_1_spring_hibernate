package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Добавить пользователей
      userService.add(new User("User1", "LastName1", "user1@mail.ru",
              (new Car("vaz", 2106))));
      userService.add(new User("User2", "LastName2", "user2@mail.ru",
              new Car("lada", 2113)));
      userService.add(new User("User3", "LastName3", "user3@mail.ru",
              new Car("uaz", 3306)));
      userService.add(new User("User4", "LastName4", "user4@mail.ru",
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
      System.out.println("Search:");
      System.out.println(userService.getUserByCar("gaz", 2121));

      context.close();
   }
}
