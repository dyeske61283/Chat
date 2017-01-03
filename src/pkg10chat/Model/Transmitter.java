/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Model;

import Logger.OhmLogger;
import java.util.Observable;
import java.util.logging.Level;

/**
 *
 * @author kevin
 */
public class Transmitter extends Observable implements Runnable
{
  private Boolean server;
  private ServerClientInterface servClient;
  private String recvMessage;
 
  public Transmitter()
  {
    server = false;
    //OhmLogger.getLogger().log(Level.INFO, "Reached end of constructor of model");
  }
  
  public void connect()
  {
    Thread thd = new Thread();
    thd.start();

  }
  
  public void initServerClient()
  {
    if(server)
    {
      servClient = new Server();
    }
    else
    {
      servClient = new Client();
    }
    if(servClient.equals(null))
    {
      OhmLogger.getLogger().severe("Failed to create server, or client!");
    }
  }
  

  @Override
  public void run()
  {
    OhmLogger.getLogger().log(Level.INFO, "right before connect call");
    servClient.connect();
    OhmLogger.getLogger().log(Level.INFO, "right after connect");
    this.setChanged();
    this.notifyObservers();
    while(this.servClient.getConnected())
    {
     this.recvMessage = this.servClient.receive();
     this.setChanged();
     this.notifyObservers();
      
    } 
    servClient.disconnect();
  }
  
  public Boolean getConnected()
  {
    return this.servClient.getConnected();
  }

  public void setConnected(Boolean connected)
  {
    this.servClient.setConnected(connected);
  }
  
  public Boolean getServer()
  {
    return server;
  }

  public void setServer(Boolean server)
  {
    this.server = server;
  }

  public ServerClientInterface getServClient()
  {
    return servClient;
  }

  public String getRecvMessage()
  {
    return recvMessage;
  }
  
}
