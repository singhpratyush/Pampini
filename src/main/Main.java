package main;

import request_handler.Request_listening_thread;

import database.operation.Querry;


public class Main
{
	//Request Listener thread
	static Request_listening_thread master_listener;
	
	//Printing any message
	private static void log(String a)
	{
		System.out.println(a);
	}
	
	//Start request listener
	private static void start_listener()
	{
				master_listener = new Request_listening_thread(55555);
				master_listener.start();
	}
	
	
	//The world starts here
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