
package javasocketprogramming;

import java.net.*;
import java.io.*;

 public class ClientSocket extends Thread{

   private static ServerSocket server   = null;
   private MessageSynchronizer ms = null;

   ClientSocket(ServerSocket server, MessageSynchronizer ms){
     this.server = server;
     this.ms = ms;
   }


   public void startCLient(){
      Socket socket   = null;
      DataInputStream in       =  null;
      try{
          socket = new Socket();
          socket = server.accept();
          ServerOutputStream stream = new ServerOutputStream(socket, ms);
          stream.start();
          System.out.println("Client accepted");
          ClientSocket newClientSocket = new ClientSocket(server, ms);
          newClientSocket.start();
          in = new DataInputStream(
          new BufferedInputStream(socket.getInputStream()));

          String line = "";

          while (!line.equals("Over"))
          {
              try
              {
                  line = in.readUTF();
                  System.out.println(line);
                  ms.updateLatestMessage(line);
              }
              catch(IOException i)
              {
                  System.out.println(i);
                  break;
              }
          }
          System.out.println("Closing connection");

          socket.close();
          in.close();
         }
         catch(IOException i) {
           System.out.println(i);
           i.printStackTrace();
         }
     }
     public void run(){
         startCLient();
     }

 }
