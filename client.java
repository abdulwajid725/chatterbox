import java.net.*;
import java.util.*;
import java.io.*;
class client
{
	//static DataOutputStream objdos;
	static DataInputStream objdis;
	static ObjectOutputStream out;
	static String msg="";
	static String cnt="";
	public static void main(String args[])
	{
	try
	{
		Socket soc=new Socket("localhost",5000);
		System.out.println("socket created");
		//objdos=new DataOutputStream(soc.getOutputStream());
		objdis=new DataInputStream(soc.getInputStream());
		out=new ObjectOutputStream(soc.getOutputStream());
		cnt=objdis.readUTF();
		messagehandler mhandler=new messagehandler(soc);
		Thread t=new Thread(mhandler);
		t.start();
		while(true)
		{
			{
				System.out.println("UNICASTING ->1\nMULTICASTING ->2\nBROADCASTING ->3");
				System.out.print("U WANT : ");
				BufferedReader br2=new BufferedReader(new InputStreamReader(System.in));
				int msgtype=Integer.parseInt(br2.readLine());
				System.out.println("client"+cnt+" : ");
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				String rmsg=br.readLine();
				if(msgtype==1)
				{		
					unicast ucast=new unicast(cnt,rmsg);
					ucast.unicastmessage();
					try
					{
						out.writeObject(ucast);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else if(msgtype==3)
				{
					unicast ucast=new unicast(cnt,rmsg);
					ucast.broadcastmessage();
					try
					{
						out.writeObject(ucast);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else if(msgtype==2)
				{
					unicast ucast=new unicast(cnt,rmsg);
					ucast.multicastmessage();
					try
					{
						//ByteArrayOutputStream baos=new ByteArrayOutputStream();						
						out.writeObject(ucast);
						//byte[] yourBytes = baos.toByteArray();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}
