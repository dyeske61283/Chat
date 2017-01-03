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
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Transmitter extends Observable implements Runnable
{
  private Boolean server;
  private String recvMessage;
  private Boolean connected;
  private PrintWriter writer;
  private BufferedReader reader;
  private Socket s;
  final static int PORT = 8468;
  final static String IPADRESSE = "127.0.0.1";

  public Transmitter()
  {
    server = false;
    //OhmLogger.getLogger().log(Level.INFO, "Reached end of constructor of model");
  }

  public void connect()
  {
    if (!this.connected)
    {
      Thread thd = new Thread(this);
      thd.start();
    }
  }

  private void initServerClient()
  {
    if (server)
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
      catch (Exception ex)
      {
        OhmLogger.getLogger().severe("failed to establish server-side of the connection");
        OhmLogger.getLogger().severe(ex.toString());
      }
    }
    else
    {
      try
      {
        s = new Socket(IPADRESSE, PORT);
        this.connected = s.isConnected();
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        this.reader = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        this.writer = new PrintWriter(os);

      }
      catch (Exception ex)
      {
        OhmLogger.getLogger().severe("failed to establish client-side of the connection");
        OhmLogger.getLogger().severe(ex.getMessage());
      }
    }
  }

  @Override
  public void run()
  {
    OhmLogger.getLogger().log(Level.INFO, "right before connect call");
    this.initServerClient();
    OhmLogger.getLogger().log(Level.INFO, "right after connect");
    this.setChanged();
    this.notifyObservers(connected);
    while (this.connected)
    {
      this.recvMessage = this.receive();
      this.setChanged();
      this.notifyObservers();

    }
    try
    {
      this.reader.close();
    }
    catch (IOException ex)
    {
      Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.writer.close();
    this.disconnect();
  }

  public Boolean getConnected()
  {
    return this.connected;
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

  public String getRecvMessage()
  {
    return recvMessage;
  }

  public void disconnect()
  {
    try
    {
      s.close();
      this.connected = false;
      OhmLogger.getLogger().info("Verbindung geschlossen.");
      this.setChanged();
      this.notifyObservers(connected);
    }
    catch (IOException ex)
    {
      OhmLogger.getLogger().log(Level.SEVERE, null, ex);
    }
  }

  public void send(String message)
  {
    this.writer.println(message);
    this.writer.flush();
  }

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
