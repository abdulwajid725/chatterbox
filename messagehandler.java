import java.net.*;
import java.util.*;
import java.io.*;
class messagehandler extends Thread
{
	Socket soc;
	DataInputStream objdis;
	public messagehandler(Socket so)
	{
		soc=so;
	}	
	public void run()
	{
	try
	{
		objdis=new DataInputStream(soc.getInputStream());
		while(true)
		{
			String msg=objdis.readUTF();
			System.out.println(msg);
		}
	}
	catch(Exception e)
	{
		//e.Printstacktrace();
		//Systm.out.println("abdul wajid nasar");
	}
	}
}
