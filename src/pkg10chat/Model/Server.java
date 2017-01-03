/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Model;

import Logger.OhmLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author kevin
 */
public class Server implements ServerClientInterface
{
  private Boolean connected;
  private BufferedReader reader;
  private PrintWriter writer;
  private Socket s;
  final static int PORT = 2468;
  
  public Server()
  {
    connected = false;
  }

  @Override
  public void connect()
  {
    try
    {
      ServerSocket ss = new ServerSocket(PORT);
      //OhmLogger.getLogger().info("warte auf Verbindung..");
      this.s = ss.accept();
      this.connected = s.isConnected();
      //OhmLogger.getLogger().info("Verbindung hergestellt.");
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      this.reader = new BufferedReader(isr);
      OutputStream os = s.getOutputStream();
      this.writer = new PrintWriter(os);
      
    }
    catch(Exception ex)
    {
      OhmLogger.getLogger().severe("failed to establish server-side of the connection");
      OhmLogger.getLogger().severe(ex.toString());
    }
  }

  @Override
  public void disconnect()
  {
    try
    {
      s.close();
      this.connected = false;
      OhmLogger.getLogger().info("Verbindung geschlossen.");
    }
    catch (IOException ex)
    {
      OhmLogger.getLogger().log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void send(String message)
  {
    this.writer.println(message);
    this.writer.flush();
  }

  @Override
  public String receive()
  {
    String message = "";
    
    try
    {
      message = this.reader.readLine();
    }
    catch (IOException ex)
    {
      OhmLogger.getLogger().log(Level.SEVERE, null, ex);
    }
    
    return message;
  }

  @Override
  public void setConnected(Boolean connected)
  {
    this.connected = connected;
  }

  @Override
  public Boolean getConnected()
  {
    return this.connected;
  }

}
