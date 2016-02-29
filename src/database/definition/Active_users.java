/*
 *Class definition for Active_users
 *Relates user with IP its active on.
*/
package database.definition;

import org.json.JSONException;
import org.json.JSONObject;

public class Active_users {
    private int uid;
    private int no_conn;
    private String IP;

    public Active_users(int uid, String IP, int no_conn) {
        this.uid = uid;
        this.IP = IP;
        this.no_conn = no_conn;
    }

    public int get_user_id() {
        return this.uid;
    }

    public String get_IP() {
        return this.IP;
    }

    public void decrement_connections() {
        this.no_conn--;
    }

    public JSONObject get_JSON() {
        JSONObject obj = new JSONObject();

        try {
            obj.put(Field_name.Active_users.uid, this.uid);
            obj.put(Field_name.Active_users.IP, this.IP);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}