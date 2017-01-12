import java.io.*;
import java.net.*;
import java.util.*;

class serverSocket

{
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

        ServerSocket serverSoc=new ServerSocket(port); //here the port number to be listening is given
        
        Socket client1=null;
        Socket client2=null;

        //since this program actually can handle multiple clients, it can accept multiple socket connections and so whenever a client wants to join, 
        //it should be allowed to join and each such time we are gonna call the accept() function.
        int numClients=0;
        while(true)
        {
            try
            {
                if (numClients==0)
                {
                    client1=serverSoc.accept();
                    numClients++;
                }
                else if(numClients==1)
                {
                    client2=serverSoc.accept();
                    numClients++;
                }
                
                
                
                
            }
            catch(IOException e)
            {
                System.out.println("error:");
                e.printStackTrace();
            }
            //ServerThread is a class that we have written capable of starting a new thread and finally 
            //being able to handle all communications with that client.
            while(numClients==2)
            {
                
                ServerThread1 thread1=new ServerThread1(client1,client2);
                thread1.start();//starts running the thread

                ServerThread2 thread2=new ServerThread2(client1,client2);
                thread2.start();

                numClients++;
                break;

                
            }
            //after 2 threads (basically 2 clients no more clients will be added or in other words no more users will be added)

            
        }
        

        

    }
    



}