package baseFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBUtil {

    static {
        try {
            Class.forName("org.h2.Driver");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static Connection getConH21() throws Exception {
        Connection con = null;
        con = DriverManager.getConnection(DBConfig.DBURL, DBConfig.DBNAME, DBConfig.DBPASS);
        return con;
    }

    public static Connection getConP1() throws Exception {
        Connection con = null;
        con = DriverManager.getConnection(DBConfig.DBURL1, DBConfig.DBNAME1, DBConfig.DBPASS1);
        return con;
    }


    public static void release(Connection con) throws Exception {
        if (!con.isClosed()) {
            con.close();
        }
    }

    public static void release(PreparedStatement pstmt, Connection con) throws Exception {
        if (!pstmt.isClosed()) {
            try {
                pstmt.close();
            } catch (Exception e) {

            } finally {
                release(con);
            }
        }
    }

    public static void release(ResultSet rs, PreparedStatement pstmt, Connection con) throws Exception {
        if (!rs.isClosed()) {
            try {
                rs.close();
            } catch (Exception e) {

            } finally {
                release(pstmt, con);
            }
        }
    }
}
