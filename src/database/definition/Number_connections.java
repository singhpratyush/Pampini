/*
 * Relates user to number of connections it is currently using
 */

package database.definition;

import org.json.JSONException;
import org.json.JSONObject;

public class Number_connections {
    private int uid, noconn;

    public Number_connections(int uid, int noconn) {
        this.uid = uid;
        this.noconn = noconn;
    }

    public int get_user_id() {
        return this.uid;
    }

    public int get_number_of_connections() {
        return this.noconn;
    }

    public JSONObject to_JSON() {
        JSONObject obj = new JSONObject();

        try {
            obj.put(Field_name.Number_connections.uid, this.uid);
            obj.put(Field_name.Number_connections.noconn, this.noconn);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}