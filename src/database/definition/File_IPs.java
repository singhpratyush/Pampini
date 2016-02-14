package database.definition;

import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;

public class File_IPs
{
	private int fid;
	private int partno;
	private String IPs;
	private ArrayList<String> arrip;
	
	public File_IPs(int fid, int partno, String IPs)
	{
		this.fid = fid;
		this.partno = partno;
		this.IPs = IPs;
		this.arrip = new ArrayList<String>();
		for( String ret : this.IPs.split(","))
			this.arrip.add(ret);
	}
	
	public int get_file_id()
	{
		return this.fid;
	}
	
	public int get_part_number()
	{
		return this.partno;
	}
	
	public ArrayList<String> get_IP_array_list()
	{
		return this.arrip;
	}
	
	public JSONObject get_JSON()
	{
		JSONObject obj = new JSONObject();
		
		try
		{
			obj.put(Field_name.File_IPs.fid, this.fid);
			obj.put(Field_name.File_IPs.partno, this.partno);
			obj.put(Field_name.File_IPs.arrip, this.arrip);
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}