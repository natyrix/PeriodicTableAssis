package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
    private static final String NAME="Nathy";
    private static final String PASS="natnaelmel";
    private static final String CONN="jdbc:mysql://localhost/chemdb";

    public static Connection Connector() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, NAME, PASS);
            return con;

        }catch (Exception e){
            return null;
        }

    }
}
