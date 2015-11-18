import java.net.*;
import java.util.*;
import java.io.*;
class multicast implements java.io.Serializable
{
	Socket soc;
	String count="";
	int NoOfRec;
	int[] recArray;
	String message="";
	public multicast(String cnt,String msg)
	{
		count=cnt;
		message=msg;
	}
	void receiverEntry()
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
		System.out.println("client"+count+" : ");
		message="client"+count+" : "+message+"2";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}