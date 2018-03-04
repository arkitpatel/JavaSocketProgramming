package javasocketprogramming;

import java.net.*;
import java.io.*;

public class Server
{
    private static ServerSocket server   = null;
    private MessageSynchronizer ms = new MessageSynchronizer();

    public Server(int port)
    {
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            ClientSocket socket = new ClientSocket(server, ms);
            socket.start();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}
