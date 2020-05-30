package jdbcexample.manager;

import jdbcexample.db.DBConnectionProvider;
import jdbcexample.model.User;
import jdk.nashorn.internal.ir.WhileNode;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into user(name,surname,email,password) Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        Statement statement = connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM user");
        while (!resultSet1.isClosed()) {
            if (resultSet1.next()) {
                if (user.getEmail().equals(resultSet1.getString("email"))) {
                    System.out.println("User already exist");
                    break;
                }
            } else {
                preparedStatement.executeUpdate();
                break;
            }
        }
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            user.setId(anInt);
        }
    }

    public List<User> getAllUser() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        List<User> users = new LinkedList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return users;
    }

    public void deleteUserById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
