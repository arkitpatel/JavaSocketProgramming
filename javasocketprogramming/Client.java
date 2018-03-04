

package javasocketprogramming;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client
{
    private Socket socket = null;
    private DataInputStream  input = null;
    private DataOutputStream out = null;
    private DataInputStream in =  null;
    public Client(String address, int port, String user)
    {
        try
        {
            socket = new Socket(address, port);
            input  = new DataInputStream(System.in);
            out    = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(
            new BufferedInputStream(socket.getInputStream()));
            ClientListener listener = new ClientListener(socket, in);
            listener.start();
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        String line = "";

        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF((user + ":" + line));
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        try
        {
            input.close();
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        System.out.println("Enter your username : ");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.next();
        System.out.println("Enter Server ip adress : ");
        String ip = scanner.next();
        Client client = new Client(ip, 5000, user);
    }
}
