/*
 *Class definition for Active_users
 *Relates user with IP its active on.
*/
package database.definition;

import org.json.*;

public class Active_users
{
	private int uid;
	private String IP;
	
	public int get_user_id()
	{
		return this.uid;
	}
	
	public String get_IP()
	{
		return this.IP;
	}
	
	public JSONObject get_JSON()
	{
		JSONObject obj = new JSONObject();
		
		try
		{
			obj.put(Field_name.Active_users.uid, this.uid);
			obj.put(Field_name.Active_users.IP, this.IP);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}