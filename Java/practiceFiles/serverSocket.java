import java.io.*;
import java.net.*;
import java.util.*;

class serverSocket

{
    static int numClients=0;
    static ServerThread threads[]=new ServerThread[100];
    static Socket clients[]=new Socket[100];
    //this is going to be a char server that will be established between 2 clients

    /*one important idea is that multiple clients can be handled only when you have
    2 threads for 2 clients. A single thread cannot handle 2 clients.
    Another important idea is that when we use reading and writing for 2 clients then we essentially would also need 2 threads one for reading and one for writing.
    */
    static final int port=1337;

    public static void main(String args[])throws IOException
    {
        /*
        The ServerSocket class is used for creating a socket for the server.
        */

        ServerSocket serverSoc=new ServerSocket(port); 
        //here the port number to be listening is given

        /*
        Socket client1=null;
        Socket client2=null;
        */

        //So we are allowing a max number of 100 clients per group.

        //since this program actually can handle multiple clients, it can accept multiple socket connections and so whenever a client wants to join, 
        //it should be allowed to join and each such time we are gonna call the accept() function.
        int i;
        while(numClients<100)
        {
            try
            {
                        
                clients[numClients]=serverSoc.accept();

                
                //once a client has joined we can create a thread immediately.
                
                /*
                accept function will listen to connections made to this socket and will accept it if any.
                */
                for(i=0;i<numClients;i++)
                {
                    threads[i].destroy();
                    threads[i]=new ServerThread(i,clients[i], clients);
                    threads[i].start();

                }

                threads[numClients]=new ServerThread(numClients,clients[numClients], clients);
                threads[numClients].start();//starts running the thread
                numClients++;
                
                
            }
            catch(IOException e)
            {
                System.out.println("Error:");
                e.printStackTrace();
            }
            //ServerThread is a class that we have written capable of starting a new thread and finally 
            //being able to handle all communications with that client.
            
            //after 2 threads (basically 2 clients no more clients will be added or in other words no more users will be added)

            
        }
        

        

    }
    



}