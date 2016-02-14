package database.operation;

import org.json.JSONArray;
import org.json.JSONObject;

import database.definition.Pampini_file;;

public class Querry
{
	public static boolean login(int uid, String phash)
	{
		return true;
	}
	
	public static Pampini_file get_file_details(int fid)
	{
		return null;
	}

	public static JSONArray get_downloadable_files(int page_number, int sort_type)
	{
		return null;
	}

	public static JSONObject get_host_for_packets(int fid, JSONArray arr)
	{
		return null;
	}

	public static JSONArray search(String search_parameter, int sort_type)
	{
		return null;
	}

	public static void check_databases()
	{
	}
	
}