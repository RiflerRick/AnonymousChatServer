import java.io.*;
import java.util.*;
import java.net.*;

//thread for dataIn1 read operation.

class ServerThread extends Thread
{
    int clientNum;
    Socket clientSoc;
    Socket otherClients[];
    String message;
    

    ServerThread(int clientNum,Socket clientSoc, Socket otherClients[])
    {
        this.clientNum=clientNum;
        this.clientSoc=clientSoc;
        this.otherClients=otherClients;
    }
    ServerThread()
    {

    }
    
    public void run()//this function is essentially overriden, this function is called when the thread is started.
    {

        try
        {
            /*OutputStream out1=clientSoc1.getOutputStream();
            DataOutputStream dataOut1=new DataOutputStream(out1);*/

            
            DataInputStream dataIn;

            OutputStream out[]=new OutputStream[100];
            DataOutputStream dataOut[]=new DataOutputStream[100];

            InputStream in=clientSoc.getInputStream();
            dataIn=new DataInputStream(in);

            //there will be only one input stream but many output streams
            serverSocket obj=new serverSocket();
            int i;
            for(i=0;i<100;i++)
            {
                if(i!=clientNum)
                {
                    out[i]=otherClients[i].getOutputStream();
                    dataOut[i]=new DataOutputStream(out[i]);
                }
                
            }

            //setup complete
            while(true)
            {
                //whenever this client sends a message it has to be received by all other clients
                message=dataIn.readUTF();
                System.out.println("message from some client:"+message);
                for(i=0;i<100;i++)
                {
                    if(i!=clientNum)
                    {
                        dataOut[i].writeUTF(message);
                    }
                }
            }


        }
        catch(Exception e)
        {
            System.out.println("error: ");
            e.printStackTrace();
        }
        
    }
    public void updateThread()
    {
        
    }
}