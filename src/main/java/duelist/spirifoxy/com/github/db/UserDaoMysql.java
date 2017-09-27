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

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.connect();
            String query = "select * from " + tableName;

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

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
