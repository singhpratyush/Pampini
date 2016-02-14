package database.definition;

import org.json.JSONException;
import org.json.JSONObject;

public class Pampini_file
{
	private int fid, uploaderid, nsharer, ndloader, type, no_packets;
	private String name;
	private java.sql.Date udate;
	private java.sql.Time utime;
	private long packet_size, file_size;

	public Pampini_file(int fid, String name, int uploaderid, java.sql.Date udate, java.sql.Time utime, int nsharer, int ndloader, int type, long file_size, long packet_size, int no_packets)
	{
		this.fid = fid;
		this.name = name;
		this.uploaderid = uploaderid;
		this.udate = udate;
		this.utime = utime;
		this.nsharer = nsharer;
		this.ndloader = ndloader;
		this.type = type;
		this.file_size = file_size;
		this.packet_size = packet_size;
		this.no_packets = no_packets;
	}
	
	public int get_file_id()
	{
		return this.fid;
	}
	
	public int get_uploader_id()
	{
		return this.uploaderid;
	}
	
	public int get_number_of_packets()
	{
		return this.no_packets;
	}
	
	public long get_file_size()
	{
		return this.file_size;
	}
	
	public int get_number_of_sharers()
	{
		return this.nsharer;
	}
	
	public long get_packet_size()
	{
		return this.packet_size;
	}
	
	public int get_number_of_downloaders()
	{
		return this.ndloader;
	}
	
	public int get_type()
	{
		return this.type;
	}
	
	public String get_name()
	{
		return this.name;
	}
	
	public java.sql.Date get_upload_date()
	{
		return this.udate;
	}
	
	public java.sql.Time get_upload_time()
	{
		return this.utime;
	}
	
	public JSONObject get_JSON()
	{
		JSONObject obj = new JSONObject();
		
		try
		{
			obj.put(Field_name.Pampini_file.fid, this.fid);
			obj.put(Field_name.Pampini_file.name, this.name);
			obj.put(Field_name.Pampini_file.uploaderid, this.uploaderid);
			obj.put(Field_name.Pampini_file.udate, this.udate);
			obj.put(Field_name.Pampini_file.utime, this.utime);
			obj.put(Field_name.Pampini_file.nsharer, this.nsharer);
			obj.put(Field_name.Pampini_file.ndloader, this.ndloader);
			obj.put(Field_name.Pampini_file.type, this.type);
			obj.put(Field_name.Pampini_file.file_size, this.file_size);
			obj.put(Field_name.Pampini_file.packet_size, this.packet_size);
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}