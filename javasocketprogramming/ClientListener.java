

package javasocketprogramming;

import java.net.*;
import java.io.*;

public class ClientListener extends Thread{

    private Socket socket = null;
    private DataInputStream in =  null;
    ClientListener(Socket socket, DataInputStream in){
        this.socket = socket;
        this.in = in;
    }

    public void run(){

                System.out.println("Connected");

                String line = "";

                while (!line.equals("Over"))
                {
                    try
                    {
                        line = in.readUTF();
                        System.out.println(line);
                    }
                    catch(IOException i)
                    {
                        System.exit(1);
                    }
                }
    }
}
