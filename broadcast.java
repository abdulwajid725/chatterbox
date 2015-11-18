import java.net.*;
import java.util.*;
import java.io.*;
class broadcast implements java.io.Serializable
{
	Socket soc;
	String count="";
	//int NoOfRec;
	//int[] recArray;
	String message="";
	String sendmessage="";
	public unicast(String cnt,String msg)
	{
		count=cnt;
		message=msg;
	}
	void receiverEntry()
	{
		sendmessage="client"+cnt+":"+rmsg+"3";
	}
	
}