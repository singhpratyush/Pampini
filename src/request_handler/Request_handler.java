/*
 * Class definition for server side request handler
 */

package request_handler;

import database.definition.Pampini_file;
import database.definition.Pampini_user;
import database.operation.Querry;
import database.operation.Update;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

public class Request_handler extends Thread {
    private Socket client_socket;
    private SocketAddress client_IP;
    private String response;
    private DataInputStream input_stream;
    private DataOutputStream output_stream;
    private JSONObject recieved_data, data_to_send;
    private int uid, request_type;

    //Mr. Constructor
    public Request_handler(Socket client_socket) {
        this.client_socket = client_socket;
        this.client_IP = this.client_socket.getRemoteSocketAddress();
        try {
            this.input_stream = new DataInputStream(this.client_socket.getInputStream());
            this.output_stream = new DataOutputStream(this.client_socket.getOutputStream());
            this.response = this.input_stream.readUTF();
            this.recieved_data = new JSONObject(this.response);
            this.uid = this.recieved_data.getInt(JSON_fields.Request_data.uid);
            this.data_to_send = new JSONObject();
            this.request_type = this.recieved_data.getInt(JSON_fields.Request_data.request_type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException f) {
            f.printStackTrace();
        }
    }

    //What to do when thread starts
    public void run() {
        switch (this.request_type) {
            case 0:
                //Welcome user
                say_hi();
            case 1:
                //Log in
                this.login();
                break;

            case 2:
                //Log out
                Update.mark_inactive(this.uid);
                break;

            case 3:
                //New upload
                this.new_upload();
                break;

            case 4:
                //New packet request
                this.serve_packet_request();
                break;

            case 5:
                //Packet download complete
                this.mark_complete_packet_download();
                break;

            case 6:
                //Query for download section content for user
                this.get_available_files();
                break;

            case 7:
                //Search for a file
                this.search();
                break;

            case 8:
                //Get all files by a user
                this.get_files_by_user();
                break;

            case 9:
                //Get user details
                this.get_user_details();
                break;

            case 10:
                //Get details by file id
                this.get_file_details();
                break;

            case 11:
                //Update Ratings
                this.update_ratings();
                break;
        }
    }

    private void update_ratings() {
        int fid = -1;
        try {
            fid = this.recieved_data.getInt(JSON_fields.Request_data.file_qry_id);
        } catch (JSONException e) {
            try {
                this.data_to_send.put("status", 1);
                this.output_stream.writeUTF(this.data_to_send.toString());
            } catch (JSONException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return;
        }

        int status = Update.update_ratings(fid) ? 0 : 1;

        try {
            this.data_to_send.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void get_file_details() {
        int fid = -1;

        try {
            fid = this.recieved_data.getInt(JSON_fields.Request_data.file_qry_id);
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                this.data_to_send.put("status", 1);
                this.output_stream.writeUTF(this.data_to_send.toString());
            } catch (JSONException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        Pampini_file a = Querry.get_file_by_id(fid);
    }

    private void get_user_details() {
        int uid = -1;

        try {
            uid = this.recieved_data.getInt(JSON_fields.Request_data.user_qry_id);
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                this.data_to_send.put("status", 1);
                this.output_stream.writeUTF(this.data_to_send.toString());
            } catch (JSONException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        Pampini_user a = Querry.get_user_by_id(uid);

        if (a == null) {
            try {
                this.data_to_send.put("status", 1);
                this.output_stream.writeUTF(this.data_to_send.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.data_to_send.put("status", 0);
            this.data_to_send.put(JSON_fields.To_send_data.user_details, a.get_JSON());
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Just a reply that client has reached right place
    private void say_hi() {
        try {
            this.output_stream.writeUTF(String.valueOf((new Date()).getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Execute file search request
    private void search() {
        try {
            String search_parameter = this.recieved_data.getString(JSON_fields.Request_data.search_param);
            int sort_type = this.recieved_data.getInt(JSON_fields.Request_data.sort_type);
            JSONArray search_results = Querry.search(search_parameter, sort_type);
            this.data_to_send.put(JSON_fields.To_send_data.downloadable_files, search_results);
            this.data_to_send.put("status", 0);
            this.output_stream.writeUTF(this.data_to_send.toString());
            return;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        }

        try {
            this.data_to_send.put("status", 1);
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Execute request to get available files
    private void get_available_files() {
        try {
            int page_number = this.recieved_data.getInt(JSON_fields.Request_data.page_number);
            int sort_type = this.recieved_data.getInt(JSON_fields.Request_data.sort_type);
            JSONArray files = Querry.get_downloadable_files(page_number, sort_type);
            this.data_to_send.put(JSON_fields.To_send_data.downloadable_files, files);
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    //Execute request that asks to mark that a packet has been downloaded
    private void mark_complete_packet_download() {
        int status = 0;
        try {
            int fid = this.recieved_data.getInt(JSON_fields.Request_data.file_id);
            int packet_no = this.recieved_data.getInt(JSON_fields.Request_data.packet_no);
            Update.mark_packet_download(fid, packet_no, this.client_IP);
        } catch (JSONException e) {
            e.printStackTrace();
            status = 1;
        }
        try {
            this.data_to_send.put("status", status);
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Provide client a packet to download
    private void serve_packet_request() {
        try {
            int fid = this.recieved_data.getInt(JSON_fields.Request_data.file_id);
            JSONArray arr = this.recieved_data.getJSONArray(JSON_fields.Request_data.packet_nos);
            this.data_to_send.put("file", Querry.get_host_for_packets(fid, arr));
            this.data_to_send.put("status", 0);
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        }

        try {
            this.data_to_send.put("status", 1);
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Execute request to add new file
    private void new_upload() {
        String file_name = null;
        long file_size = 0, packet_size;
        int file_id, nsharer = 1, ndloader = 0, type = -1, no_packets = 0;
        java.util.Date curr = new java.util.Date();
        java.sql.Date upload_date = new java.sql.Date(curr.getTime());
        java.sql.Time upload_time = new java.sql.Time(curr.getTime());
        try {
            file_name = this.recieved_data.getString(JSON_fields.Request_data.file_name);
            file_size = this.recieved_data.getLong(JSON_fields.Request_data.file_size);
            type = this.recieved_data.getInt(JSON_fields.Request_data.file_type);
        } catch (JSONException e) {
            try {
                this.data_to_send.put("status", 1);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return;
        }
        file_id = util.file_operation.get_new_file_id();
        packet_size = util.file_operation.get_packet_size(file_size);
        no_packets = util.file_operation.get_number_packets(file_size);
        Pampini_file new_upload = new Pampini_file(file_id, file_name, this.uid, upload_date, upload_time, nsharer, ndloader, type, file_size, packet_size, no_packets, 0);
        database.operation.Update.add_new_file(new_upload);
        try {
            this.data_to_send.put(JSON_fields.To_send_data.fid, file_id);
            this.data_to_send.put("status", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Execute login request
    private void login() {
        String phash = null;
        try {
            phash = this.recieved_data.getString(JSON_fields.Request_data.phash);
        } catch (JSONException e1) {
            e1.printStackTrace();
            try {
                this.data_to_send.put("status", 1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return;
        }
        try {
            this.data_to_send.put("status", (Querry.login(this.uid, phash) ? 0 : 1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void get_files_by_user() {
        int uid = -1;
        try {
            uid = this.recieved_data.getInt(JSON_fields.Request_data.user_qry_id);
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                this.data_to_send.put("status", 1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            try {
                this.output_stream.writeUTF(this.data_to_send.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }
        JSONArray files = Querry.get_files_by_user(uid);
        if (files == null) try {
            this.data_to_send.put("status", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        else try {
            this.data_to_send.put("status", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.output_stream.writeUTF(this.data_to_send.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}