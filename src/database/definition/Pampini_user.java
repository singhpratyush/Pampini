/*
 * Definition for user
 */
package database.definition;

import org.json.JSONException;
import org.json.JSONObject;

public class Pampini_user
{
	private int uid, jbatch, branchcode, dpiccode;
	private String fname, lname, phash;
	private char sex;
	
	public Pampini_user(int uid, String fname, String lname, char sex, String phash, int jbatch, int branchcode, int dpiccode)
	{
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.sex = sex;
		this.phash = phash;
		this.jbatch = jbatch;
		this.branchcode = branchcode;
		this.dpiccode = dpiccode;
	}
	
	public int get_user_id()
	{
		return this.uid;
	}
	
	public int get_batch()
	{
		return this.jbatch;
	}
	
	public int get_branch_code()
	{
		return this.branchcode;
	}
	
	public int get_display_pic_code()
	{
		return this.dpiccode;
	}
	
	public String get_name()
	{
		return this.fname + " " + this.lname;
	}
	
	public String get_first_name()
	{
		return this.fname;
	}
	
	public String get_last_name()
	{
		return this.lname;
	}
	
	public String get_password_hash()
	{
		return this.phash;
	}
	
	public char get_sex()
	{
		return this.sex;
	}
	
	public JSONObject get_JSON()
	{
		JSONObject obj = new JSONObject();
		try
		{
			obj.put(Field_name.Pampani_user.uid, this.uid);
			obj.put(Field_name.Pampani_user.fname, this.fname);
			obj.put(Field_name.Pampani_user.lname, this.lname);
			obj.put(Field_name.Pampani_user.sex, this.sex);
			obj.put(Field_name.Pampani_user.phash, this.phash);
			obj.put(Field_name.Pampani_user.jbatch, this.jbatch);
			obj.put(Field_name.Pampani_user.branchcode, this.branchcode);
			obj.put(Field_name.Pampani_user.dpiccode, this.dpiccode);
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}