package database.operation;

import database.definition.Pampini_file;
import util.config;

import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    //Mark user as online
    public static boolean mark_active(int uid, SocketAddress client_IP) {
        try {
            Connection c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement stmt = c.createStatement();

            String operation = "set search path to file;\n" +
                    " insert into active_users values(" +
                    uid + "," + client_IP.toString() +
                    "0);";

            stmt.executeUpdate(operation);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Mark user as offline
    public static boolean mark_inactive(int uid) {
        try {
            Connection c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement stmt = c.createStatement();

            String operation = "set search path to file;\n" +
                    "delete grom active_users where uid = " + uid + ";";

            stmt.executeUpdate(operation);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Add new file to download section and update corresponding databases
    public static boolean add_new_file(Pampini_file new_upload) {
        try {
            Connection c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement stmt = c.createStatement();

            String operation = "set search_path to file;\n" +
                    "insert into files values(" +
                    new_upload.get_file_id() + "," +
                    new_upload.get_uploader_id() + "," +
                    new_upload.get_upload_date() + "," +
                    new_upload.get_upload_time() + "," +
                    new_upload.get_number_of_sharers() + "," +
                    new_upload.get_number_of_downloaders() + "," +
                    new_upload.get_type() + "," +
                    new_upload.get_file_size() + "," +
                    new_upload.get_packet_size() + "," +
                    new_upload.get_name() + "," +
                    ");";
            stmt.executeUpdate(operation);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Mark that packet_no for fid is available at client_IP from uid
    public static boolean mark_packet_download(int fid, int packet_no, SocketAddress client_IP) {
        try {
            Connection c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement stmt = c.createStatement();

            String operation = "set search path to file;\n" +
                    " insert into file_ips values(" +
                    fid + "," + packet_no + "," + client_IP.toString() +
                    ");";

            stmt.executeUpdate(operation);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}