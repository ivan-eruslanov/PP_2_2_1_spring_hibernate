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
      userService.addUser(new User("Иван", "Иванов", "iv.ivanov@mail.ru",
              (new Car("Gaz", 3110))));
      userService.addUser(new User("Юрий", "Петров", "ur.petrov@list.ru",
              (new Car("Vaz", 2106))));
      userService.addUser(new User("Роман", "Романов", "ro.romanov@bk.ru",
              (new Car("Lada", 2114))));
      userService.addUser(new User("Максим", "Максимов", "m.maksimov@gmail.com",
              (new Car("Uaz", 3306))));

      List<User> users = userService.getUserList();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      // Get user from model and series
      User temp = userService.getUserByModelAndSeries("Vaz", 2106);
      System.out.println("Get user by model and series." + temp);
      context.close();
   }
}
