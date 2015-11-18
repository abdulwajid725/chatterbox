import java.net.*;
import java.util.*;
import java.io.*;
class server
{
	static String msg="";
	static int cnt=0;
	static ArrayList<Socket> al=new ArrayList<Socket>();
	int size,index,len;
	int i,fromclientindex=0;
	public static void main(String args[])
	{
	try
	{
		ServerSocket ser=new ServerSocket(5000);
		while(true)
		{
			Socket s=ser.accept();
			DataOutputStream objdos;
			objdos=new DataOutputStream(s.getOutputStream());
			cnt++;
			al.add(s);
			String count=Integer.toString(cnt);
			System.out.println("connection with client " +cnt +" established");
			objdos.writeUTF(count);
			//clienthandler cl=new clienthandler(s,cnt);
			//Thread t=new Thread(cl);
			//t.start();
			multicasthandler mcasthandler=new multicasthandler(s,cnt);
			Thread t1=new Thread(mcasthandler);
			t1.start();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	public void sendtoclient(String message,int clientno,char fromclientno)
	{
		try
		{
			DataOutputStream objdos=new DataOutputStream((al.get(clientno)).getOutputStream());
			int len=message.length();
			String msg=message.substring(8,len-14);
			String sndmsg="FROM CLIENT"+fromclientno +": "+msg;
			objdos.writeUTF(sndmsg);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void broadcast(String message,char fromclientno)
	{
		try
		{
			index=0;
			size=al.size();
			System.out.println("SIZE : "+size);
			i=Character.getNumericValue(fromclientno);
			fromclientindex=i-1;
			while(index<size)
			{
				if(index!=fromclientindex)
				{
				System.out.println("SENDING CLIENT INDEX : "+fromclientindex+ "->" +index);
				DataOutputStream objdos=new DataOutputStream((al.get(index)).getOutputStream());
				len=message.length();
				String msg=message.substring(8,len-1);
				String sndmsg="FROM CLIENT"+fromclientno +": "+msg;
				objdos.writeUTF(sndmsg);
				}
				index++;
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public void multicast(String message,int[] RecArray,char fromclientno,int NoOfRec)
	{
		int socindex=0;
		int l;
		while(socindex<RecArray.length)
		{
			try
			{
			System.out.println(RecArray[socindex]);	
			l=RecArray[socindex];
			l--;
			DataOutputStream objdos=new DataOutputStream((al.get(l)).getOutputStream());
			len=message.length();
			String mcastmsg=message.substring(10,len-1);
			String mcastsndmsg="FROM CLIENT"+fromclientno +": "+mcastmsg;
			objdos.writeUTF(mcastsndmsg);
			socindex++;
			System.out.println("SENT to client : "+socindex );
			}
			catch(Exception e)
			{
				
			}
			
		}
	}
}







