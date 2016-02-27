/*
 * Just a class to store field names for JSON
 */

package request_handler;

public class JSON_fields {
    public class Request_data {
        static final String uid = "uid";
        static final String request_type = "request_type";
        static final String phash = "phash";
        static final String file_name = "file_name";
        static final String file_size = "file_size";
        static final String file_type = "file_type";
        static final String file_id = "file_id";
        static final String packet_no = "packet_no";
        static final String page_number = "page_number";
        static final String packet_nos = "packet_nos";
        static final String search_param = "search_param";
        static final String sort_type = "sort_type";
    }

    public class To_send_data {
        static final String status = "status";
        static final String fid = "fid";
        static final String downloadable_files = "downloadable_files";
    }
}