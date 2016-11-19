import java.io.*;
import java.net.*;
import java.util.*;

class clientSocket
{
    public static void main(String args[])throws IOException
    {
        InetAddress ip=InetAddress.getByName("172.19.19.220");
        Socket client=new Socket(ip,1337);

        OutputStream out=client.getOutputStream();
        InputStream in=client.getInputStream();

        DataInputStream dataIn=new DataInputStream(in);
        DataOutputStream dataOut=new DataOutputStream(out);

        Scanner sc=new Scanner(System.in);
        String message;
        System.out.println("Welcome to Rick's Chat server: write 'stop' to close the connection");
        
        ClientThreadRead threadRead=new ClientThreadRead(dataIn);
        ClientThreadWrite threadWrite=new ClientThreadWrite(dataOut);
        threadRead.start();//starts running the thread
        threadWrite.start();

        //client.close();
    }
}