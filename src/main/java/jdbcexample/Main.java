package jdbcexample;

import jdbcexample.manager.UserManager;
import jdbcexample.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserManager userManager=new UserManager();
        try {
            User user=new User("Poxos","Pososyan","poxos@mail.com","pp");
            userManager.addUser(user);
            List<User> allUser = userManager.getAllUser();
            for (User users : allUser) {
                System.out.println(users);
            }
//            userManager.deleteUserById(202);
//            allUser = userManager.getAllUser();
//            for (User users : allUser) {
//                System.out.println(users);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
