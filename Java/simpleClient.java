import java.io.*;
import java.net.*;
import java.util.*;
class SocketClient
{
	public static void main(String args[])
	{
		try
		{
			//the ipaddress of the system can be simply attained using ipconfig command on Windows machines and ifconfig command on Linux.
			//if the connection is a Wifi then the wireless adapters ip address is really the address of the system. 

			InetAddress inetAd=InetAddress.getByName("172.19.19.220");//setting the object inetAd of class InetAddress

			//getByName function of the InetAddress class is used to get the ip address of the host given its name
			// the name can be the name of the computer itself or it can be simply a textual representation of the ip address

			Socket clientSoc=new Socket(inetAd,1337);

			//constructor of Socket class actually has 2 parameters, 1 of type InetAddress and the other of type int representing the port number

			//Socket client=clientSoc.accept();
			OutputStream outvar=clientSoc.getOutputStream();

			//OutputStream is a super class of all classes representing an output stream of bytes.
			//An output stream simply accepts output bytes and sends them to a sink.
			//getOutputStream is used to get the request from the client			

			DataOutputStream dataoutvar=new DataOutputStream(outvar);
			//dataoutvar.writeUTF("Hi!!!, I wanna connect");

			InputStream invar=clientSoc.getInputStream();
			DataInputStream datainvar=new DataInputStream(invar);

			Scanner sc=new Scanner(System.in);


			String message;

			while (true)
			{

				System.out.print("Message:");

				message=sc.nextLine();

				dataoutvar.writeUTF(message);

				//System.out.print(message);
			}
			
			//System.out.println("Server says:"+datainvar.readUTF());
			clientSoc.close();//closing the client
			
		}
		catch(UnknownHostException e)
		{
			System.out.println("unknown host:localhost");
		}
		catch(IOException e)
		{
			System.out.println("io exception detected");
		}
		
			
	}
	
}
