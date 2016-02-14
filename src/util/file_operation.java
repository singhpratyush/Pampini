package util;

public class file_operation
{
	//Get a new file ID for new download
	public static int get_new_file_id()
	{
		return 0;
	}

	//Get packet size for given file_size
	public static long get_packet_size(long file_size)
	{
		return 1000;
	}

	//Get number of packets for given file_size
	public static int get_number_packets(long file_size)
	{
		return 1;
	}
}