import java.net.*;
import java.util.*;
import java.io.*;
class clienthandler extends Thread
{
	Socket soc;
	static int cnt;
	public clienthandler(Socket so,int cnt)
	{
		soc=so;
		this.cnt=cnt;
	}
	@Override
	public void run()
	{
		DataInputStream objdis;
		//ByteArrayInputStream bis;
		//ObjectInputStream in;
		char n,sendtype;
		String sndtype="";
		String msg="";
		String mcastMessage="";
		Byte[] mybytes;
		multicast mcast=null;
		int noOfRec=0,len=0,mcastlen=0;
		try
		{
		objdis=new DataInputStream(soc.getInputStream());
		//bis=new ByteArrayInputStream(mybytes);
		//in=new ObjectInputStream(soc.getInputStream());
		while(true)
		{
				//System.out.println("msg should not be read in multicast above:"+msg);
			msg=objdis.readUTF();
				//System.out.println("msg should not be read in multicast :"+msg);
			len=msg.length();
			sndtype=msg.substring(len-1);
			sendtype=sndtype.charAt(0);
			n=msg.charAt(6);
			
		//	char sendtype=sndtype.charAt(0);
			if(sendtype=='1')
			{
				String l=msg.substring(len-2);
				char m=l.charAt(0);
				int index=Character.getNumericValue(m);
				index--;
				//System.out.println("index: "+index);
				server s=new server();
				s.sendtoclient(msg,index,n);
				if(msg.equals("stop"))
				{
					break;
				}
				System.out.println(msg);
			}
			else if(sendtype=='3')
			{
				
				server s=new server();
				s.broadcast(msg,n);
			}
			
		}
		}
		catch(Exception e)
		{

		}
	}
}
