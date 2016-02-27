package database.operation;

import database.definition.Pampini_file;
import org.json.JSONArray;
import org.json.JSONObject;

;

public class Querry {
    //Validate login credentials
    public static boolean login(int uid, String phash) {
        return true;
    }

    //Fetch file details from database
    public static Pampini_file get_file_details(int fid) {
        return null;
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
    }

}