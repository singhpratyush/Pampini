package database.operation;

import java.net.SocketAddress;

import database.definition.Pampini_file;

public class Update
{
	//Mark user as online
	public static boolean mark_active(int uid, SocketAddress client_IP)
	{ 
		return true;
	}
	
	//Mark user as offline
	public static void mark_inactive(int uid)
	{
		
	}

	//Add new file to download section and update corresponding databases
	public static void add_new_file(Pampini_file new_upload)
	{

	}

	//Mark that packet_no for fid is available at client_IP from uid
	public static void mark_packet_download(int fid, int uid, int packet_no, SocketAddress client_IP)
	{
	}
}