package sample;

import java.sql.Connection;
import java.sql.SQLException;

public class DbModel {
    Connection connection;
    public DbModel(){
        connection = SqliteConnection.Connector();
        if(connection == null){
            System.out.println("Not working");
            System.exit(1);
        }
    }
    public boolean isDbconnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
