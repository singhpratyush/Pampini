/*
 * Definition of request listening thread
 */
package request_handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Request_listening_thread extends Thread {
    private int port;
    private ServerSocket socket;
    private boolean success;

    //Mr. Constructor
    public Request_listening_thread(int port) {
        this.port = port;
        try {
            this.socket = new ServerSocket(this.port);
            System.out.println("Acquired port " + this.port + ".");
            this.success = true;
        } catch (IOException e) {
            System.out.println("Error : Unable to acquire port " + this.port + ".");
            this.success = false;
        }
    }

    //Is port working?
    public boolean is_good() {
        return this.success;
    }

    public void run() {
        //Forever
        while (true) {
            try {
                Socket client_socket = this.socket.accept();
                (new Request_handler(client_socket)).start();
            } catch (IOException e) {
                System.out.println("Error : Unable to get request.");
            }
        }
    }
}