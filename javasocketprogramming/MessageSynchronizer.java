package javasocketprogramming;

import java.io.*;

public class MessageSynchronizer{

    private volatile String latestMessage = "";

    public synchronized void updateLatestMessage(String message){
        this.latestMessage = message;
    }

    public String getLatestMessage(){
        return latestMessage;
    }

}
