package duelist.spirifoxy.com.github.db;

import duelist.spirifoxy.com.github.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMysql implements CommonDao<User> {

    private static final String tableName = "users";

    @Override
    public User getById(int id) {
        return null;
    }

    public User getByUsername(String username) {

        User user = null;
        String query = "select * from " + tableName + " where username = ?";
        try (Connection connection = ConnectionManager.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insert(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Ошибка при регистрации пользователя");
        }
        String query = "insert into users (username, password, damage, hp) values (?,?,?,?)";
        try (Connection connection = ConnectionManager.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getDamage());
            statement.setInt(4, user.getHp());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        String query = "select * from " + tableName;
        try (Connection connection = ConnectionManager.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
