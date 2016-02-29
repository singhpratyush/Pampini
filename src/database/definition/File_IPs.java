/*
 * Relates file packet to IPs it is available on
 */

package database.definition;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class File_IPs {
    private int fid;
    private int partno;
    private ArrayList<String> arrip;

    public File_IPs(int fid, int partno, ArrayList<String> arrip) {
        this.fid = fid;
        this.partno = partno;
        this.arrip = new ArrayList<>(arrip);
    }

    public int get_file_id() {
        return this.fid;
    }

    public int get_part_number() {
        return this.partno;
    }

    public ArrayList<String> get_IP_array_list() {
        return this.arrip;
    }

    public JSONObject get_JSON() {
        JSONObject obj = new JSONObject();

        try {
            obj.put(Field_name.File_IPs.fid, this.fid);
            obj.put(Field_name.File_IPs.partno, this.partno);
            obj.put(Field_name.File_IPs.arrip, this.arrip);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}