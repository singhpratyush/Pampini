package main;

import java.io.FileInputStream;
import java.io.IOException;

import request_handler.Request_listening_thread;

import database.operation.Querry;


public class Main
{
	static Request_listening_thread master_listener;
	
	private static void log(String a)
	{
		System.out.println(a);
	}
	
	private static void start_listener()
	{
		try
		{
			FileInputStream ifile = new FileInputStream("");
			
			int port ;
			
			do
			{
				String port_str = "";
				for(int _ = 0 ; _ < 5 ; _ ++ )
					port_str = port_str + (char)ifile.read();
				port = Integer.parseInt(port_str);				
				master_listener = new Request_listening_thread(port);
			} while ( !master_listener.is_good());
			ifile.close();
			
			master_listener.start();
			log("Listener running on port " + port);
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		log("Starting Server");
		
		log("Checking databases and making changes if necessary");
		Querry.check_databases();
		log("Databases checked and corrected");
		
		log("Starting request listener thread");
		start_listener();
		log("Requuest listener started");

		log("Server running seccessfully");
	}
}