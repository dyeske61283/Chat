/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Model;

import java.io.BufferedReader;
import java.net.Socket;

/**
 *
 * @author kevin
 */
public class Server implements ServerClientInterface
{
  private BufferedReader reader;
  private Socket s;
  final static int PORT = 2468;
  
  public Server()
  {

  }

  @Override
  public void connect()
  {
    try
    {
      
    }
    catch(Exception ex)
    {
      
    }
  }

  @Override
  public void disconnect()
  {
  }

  @Override
  public void send()
  {
  }

  @Override
  public String receive()
  {
    return "";
  }

}
