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
    public static void mark_inactive(int uid) {

    }

    //Add new file to download section and update corresponding databases
    public static void add_new_file(Pampini_file new_upload) {

    }

    //Mark that packet_no for fid is available at client_IP from uid
    public static void mark_packet_download(int fid, int uid, int packet_no, SocketAddress client_IP) {
    }
}