import java.net.*;
import java.util.*;
import java.io.*;
class multicasthandler extends Thread
{
	Socket soc;
	static int cnt;
	public multicasthandler(Socket so,int cnt)
	{
		soc=so;
		this.cnt=cnt;
	}
	@Override
	public void run()
	{
		ObjectInputStream in;
		char n,sendtype;
		String sndtype="";
		String Message="";
		unicast cast=null;
		int noOfRec=0,castlen=0;
		try
		{
			in=new ObjectInputStream(soc.getInputStream());
			while(true)
			{
				//System.out.println("abdul");
				cast=(unicast)(in.readObject());
				System.out.println("wajid");
				Message=cast.sendmessage;
				System.out.println("message fron sender : "+Message);
				castlen=Message.length();
				noOfRec=cast.NoOfRec;
				sndtype=Message.substring(castlen-1);
				sendtype=sndtype.charAt(0);
				n=Message.charAt(6);
				if(sendtype=='1')
				{
					String l=Message.substring(castlen-2);
					char m=l.charAt(0);
					int index=Character.getNumericValue(m);
					index--;
					//System.out.println("index: "+index);
					server s=new server();
					s.sendtoclient(Message,index,n);
					if(Message.equals("stop"))
					{
						break;
					}
					System.out.println(Message);
				}
				else if(sendtype=='2')
				{	
					int[] RecArray=new int[noOfRec];
					for(int i=0;i<noOfRec;i++)
					{
						RecArray[i]=cast.recArray[i];
						
					}
					server s=new server();
					s.multicast(Message,RecArray,n,noOfRec);
				}
				else if(sendtype=='3')
				{
					server s=new server();
					s.broadcast(Message,n);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
}