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
  
  public Transmitter()
  {
    server = false;
  }
  
  public void connect()
  {
    
  }

  @Override
  public void run()
  {
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
