/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Model;

import java.util.Observable;

/**
 *
 * @author kevin
 */
public class Transmitter extends Observable implements Runnable
{
  private Boolean server;
  private ServerClientInterface servClient;
  private Boolean connected;

 
  
  public Transmitter()
  {
    server = false;
    connected = false;
  }
  
  public void connect()
  {
    if(server)
    {
      servClient = new Server();
    }
    else
    {
      servClient = new Client();
    }
     servClient.connect();
     connected = true;
     this.setChanged();
     this.notifyObservers();
  }

  @Override
  public void run()
  {
    servClient.disconnect();
  }
  
  public Boolean getConnected()
  {
    return connected;
  }

  public void setConnected(Boolean connected)
  {
    this.connected = connected;
  }
  
  public Boolean getServer()
  {
    return server;
  }

  public void setServer(Boolean server)
  {
    this.server = server;
  }
  
  
}
