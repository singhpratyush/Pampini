package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class file_operation {

    public static int get_new_file_id() {
        int fid = -1;
        Connection c;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement statement = c.createStatement();

            String command = "set search_path to file;";
            statement.executeUpdate(command);
            command = "select * from (select * from availfid except select fid from files) as available order by fid limit 1;";
            ResultSet rs = statement.executeQuery(command);
            return rs.getInt("fid");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //Get packet size for given file_size
    public static long get_packet_size(long file_size) {
        return 1000;
    }

    //Get number of packets for given file_size
    public static int get_number_packets(long file_size) {
        return 1;
    }
}