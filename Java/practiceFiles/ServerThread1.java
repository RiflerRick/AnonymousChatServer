import java.io.*;
import java.util.*;
import java.net.*;

//thread for dataIn1 read operation.

class ServerThread1 extends Thread
{
    protected Socket clientSoc1;
    protected Socket clientSoc2;
    protected String message;

    ServerThread1(Socket clientSoc1, Socket clientSoc2)
    {
        this.clientSoc1=clientSoc1;
        this.clientSoc2=clientSoc2;
    }
    public void run()//this function is essentially overriden, this function is called when the thread is started.
    {

        try
        {
            /*OutputStream out1=clientSoc1.getOutputStream();
            DataOutputStream dataOut1=new DataOutputStream(out1);*/

            InputStream in1=clientSoc1.getInputStream();
            DataInputStream dataIn1=new DataInputStream(in1);

            OutputStream out2=clientSoc2.getOutputStream();
            DataOutputStream dataOut2=new DataOutputStream(out2);

            /*InputStream in2=clientSoc2.getInputStream();
            DataInputStream dataIn2=new DataInputStream(in2);*/

        //setup complete

            
            //dataOut.writeUTF("hello, from server");

            while(true)
            {
                //kind of an echo.    
                message=dataIn1.readUTF();
                System.out.println("message from client/s:"+message);
                System.out.println("relaying to other clients");
                dataOut2.writeUTF(message);
                
            }

        }
        catch(Exception e)
        {
            System.out.println("error: ");
            e.printStackTrace();
        }
        
    }
}