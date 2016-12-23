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
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author kevin
 */
public class Client implements ServerClientInterface
{
  private PrintWriter writer;
  private BufferedReader reader;
  private Socket s;
  final static int PORT = 24680;
  final static String IPADRESSE = "127.0.0.1";
  
  public Client()
  {

  }

  @Override
  public void connect()
  {
    try
    {
      s = new Socket(IPADRESSE, PORT);
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      this.reader = new BufferedReader(isr);
      OutputStream os = s.getOutputStream();
      this.writer = new PrintWriter(os);
    }
    catch(Exception ex)
    {
      OhmLogger.getLogger().severe("failed to establish client-side of the connection");
      OhmLogger.getLogger().severe(ex.getMessage());
    }
  }

  @Override
  public void disconnect()
  {
    try
    {
      s.close();
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
}
