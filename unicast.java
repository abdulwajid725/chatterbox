import java.net.*;
import java.util.*;
import java.io.*;
class unicast implements java.io.Serializable
{
	Socket soc;
	String count="";
	int NoOfRec;
	int[] recArray;
	String message="";
	String sendmessage="";
	String cntmsg="";
	public unicast(String cnt,String msg)
	{
		count=cnt;
		message=msg;
	}
	void unicastmessage()
	{
		System.out.print("Send To Client : ");
		
		try
		{
			//System.out.print("cntmessage->");
			BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
			cntmsg=br1.readLine();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		sendmessage=("client"+count+":"+message+"  ,TO CLIENT:"+cntmsg+"1");
		//System.out.print("sendmessage");
	/*	if(rmsg.equals("stop"))
		{
			break;
		}*/
	}
	void multicastmessage()
	{
		try
		{
		System.out.print("Enter No Of Receiver : ");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		NoOfRec=Integer.parseInt(br.readLine());
		recArray=new int[NoOfRec];
		System.out.println("Enter The Receivers : ");
		for(int i=0;i<NoOfRec;i++)
		{
			System.out.print("REceiver"+(i+1)+" : ");
			recArray[i]=Integer.parseInt(br.readLine());
			//System.out.print("\n");
		}
		//System.out.println("client"+count+" : ");
		sendmessage="client"+count+" : "+message+"2";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	void broadcastmessage()
	{
		sendmessage="client"+count+":"+message+"3";
	}
	
}