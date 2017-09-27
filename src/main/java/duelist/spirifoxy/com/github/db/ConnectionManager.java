package duelist.spirifoxy.com.github.db;

import duelist.spirifoxy.com.github.main.ServerPreferences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName(ServerPreferences.DB_DRIVER);
            connection = DriverManager.getConnection(ServerPreferences.DB_URL + ServerPreferences.DB_NAME,
                    ServerPreferences.DB_USERNAME, ServerPreferences.DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
