import java.io.*;
import java.net.*;
import java.util.*;

class clientSocket
{
    public static void main(String args[])throws IOException
    {
        //any ip address can be used here.
        //InetAddress ip=InetAddress.getByName("14.139.242.195");
        InetAddress ip=InetAddress.getByName("localhost");
        Socket client=new Socket(ip,1337);

        OutputStream out=client.getOutputStream();
        InputStream in=client.getInputStream();

        DataInputStream dataIn=new DataInputStream(in);
        DataOutputStream dataOut=new DataOutputStream(out);

        Scanner sc=new Scanner(System.in);
        String message;
        System.out.println("Welcome to Rick's Chat server: start chatting");
        
        ClientThreadRead threadRead=new ClientThreadRead(dataIn);
        ClientThreadWrite threadWrite=new ClientThreadWrite(dataOut);
        threadRead.start();//starts running the thread
        threadWrite.start();

        //client.close();
    }
}