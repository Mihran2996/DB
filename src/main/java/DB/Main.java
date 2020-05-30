package DB;

import DB.manager.UserManager;
import DB.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserManager userManager=new UserManager();
//        User user=new User("poxos","poxosyan","fcdahgdwdsbjk","psvm");
//        userManager.userAdd(user);
//        System.out.println(user);
        List<User> allUser = userManager.getAllUser();
        for (User user1 : allUser) {
            System.out.println(user1);
        }
//        userManager.deleteById(5);

    }
}
