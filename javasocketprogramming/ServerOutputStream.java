
package javasocketprogramming;

import java.net.*;
import java.io.*;

public class ServerOutputStream extends Thread{

    private DataOutputStream out = null;
    private Socket socket = null;
    private MessageSynchronizer ms = null;

    ServerOutputStream(Socket socket, MessageSynchronizer ms){
        this.socket = socket;
        this.ms = ms;
        try{
            out    = new DataOutputStream(this.socket.getOutputStream());
        }
        catch(IOException i) {

            System.out.println(i);
            i.printStackTrace();
        }
    }

    public void run(){
        String line = "";
        String temp = "";

        while (!line.equals("Over"))
        {
            try
            {
                line = ms.getLatestMessage();
                if(!line.equals(temp)){
                    out.writeUTF(line);
                    temp = line;
                }
            }
            catch(IOException i)
            {
                System.out.println(i);
                i.printStackTrace();
            }
        }
    }

}
