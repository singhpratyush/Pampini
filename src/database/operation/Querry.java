package database.operation;

import database.definition.Pampini_file;
import org.json.JSONArray;
import org.json.JSONObject;
import util.config;

import java.sql.*;

import static java.lang.System.exit;
import static main.Main.log;


public class Querry {
    //Validate login credentials
    public static boolean login(int uid, String phash) {

        ResultSet rc;

        try {
            Connection c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement stmt = c.createStatement();

            String operation = String.format("set search_path to file;\nselect phash from user where uid = %d;", uid);

            rc = stmt.executeQuery(operation);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            String phash_org = rc.getString("phash");
            rc.close();
            return phash.equals(phash_org);
        } catch (SQLException e) {
            return false;
        }
    }

    //Fetch file details from database
    public static Pampini_file get_file_details(int fid) {
        ResultSet rc;

        try {
            Connection c = DriverManager.getConnection(config.jdbc, config.jdbc_username, config.jdbc_password);
            Statement stmt = c.createStatement();

            String operation = String.format("set search_path to file;\nselect * from file where fif = %d;", fid);

            rc = stmt.executeQuery(operation);

            return new Pampini_file(rc.getInt("fid"), rc.getString("name"), rc.getInt("uploaderid"), rc.getDate("udate"), rc.getTime("utime"), rc.getInt("nsharer"), rc.getInt("ndloader"), rc.getInt("type"), rc.getLong("file_size"), rc.getLong("packet_size"), rc.getInt("no_packets"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    //Get JSON array of files available for downloading
    public static JSONArray get_downloadable_files(int page_number, int sort_type) {
        return null;
    }

    //Get best host to download one of the packets from JSON array 'arr'
    public static JSONObject get_host_for_packets(int fid, JSONArray arr) {
        return null;
    }

    //Get JSON array of files matching search_parameter
    public static JSONArray search(String search_parameter, int sort_type) {
        return null;
    }

    //Check for integrity of database and modify as needed
    public static void check_databases() {

        //Connecting to database
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log("Database Check : Problems with JDBC, exiting.");
            e.printStackTrace();
            exit(1);
        }
        log("Database Check : JDBC Found.");

        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pampini", "pampini", "pampini");
        } catch (SQLException e) {
            log("Database Check : Unable to connect to local database, exiting.");
            e.printStackTrace();
            exit(1);
        }
        log("Database Check : Connected to local database.");

        //Creating statements
        Statement stmt = null;
        try {
            stmt = c.createStatement();
        } catch (SQLException e) {
            log("Database Check : Unable to create statement, exiting.");
            e.printStackTrace();
            exit(1);
        }

        //For table allfid
        String sql = "set search_path to file;" +
                "create table if not exists allfid\n" +
                "(fid integer primary key);";

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            exit(1);
        }

        sql = "";
        for (int i = 0; i < 1000; i++)
            sql = sql + "insert into table allfid values(" + i + ");\n";

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}