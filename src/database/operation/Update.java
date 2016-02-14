package database.operation;

import java.net.SocketAddress;

import database.definition.Pampini_file;

public class Update
{
	public static boolean mark_active(int uid, SocketAddress client_IP)
	{ 
		return true;
	}
	
	public static void mark_inactive(int uid)
	{
		
	}

	public static void add_new_file(Pampini_file new_upload)
	{

	}

	public static void mark_packet_download(int fid, int uid, int packet_no, SocketAddress client_IP)
	{
	}
}