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
      User user1 = new User("Иван", "Иванов", "iv.ivanov@mail.ru");
      Car car1 = new Car("Gaz", 3110);
      user1.setCar(car1);
      userService.add(user1);

      User user2 = new User("Юрий", "Петров", "ur.petrov@list.ru");
      Car car2 = new Car("Vaz", 2106);
      user2.setCar(car2);
      userService.add(user2);

      User user3 = new User("Роман", "Романов", "ro.romanov@bk.ru");
      Car car3 = new Car("Lada", 2114);
      user3.setCar(car3);
      userService.add(user3);

      User user4 = new User("Максим", "Максимов", "m.maksimov@gmail.com");
      Car car4 = new Car("Uaz", 3306);
      user4.setCar(car4);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      // Get user from model and series
      User temp = userService.getUserByModelAndSeries("Lada", 2114);
      System.out.println("Get user by model and series." + temp);
      context.close();
   }
}
