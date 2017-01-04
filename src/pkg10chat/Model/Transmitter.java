/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg10chat.Model;

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
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class Transmitter extends Observable implements ServerClientInterface
{
  final Object[] options = {"Server","Client"};
  private Boolean ready;
  private Boolean server;
  private Boolean connected;
  private String rcvMsg;
  private Thread thd;
  private Socket s;
  private BufferedReader reader;
  private PrintWriter writer;

  public Transmitter()
  {
    int wahl = JOptionPane.showOptionDialog(null, "Do you want to be Server, or Client?",
                                            "Connection",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                            null,options,options[0]);
    
    this.server = (wahl == JOptionPane.YES_OPTION);
    connect();
    rcvMsg = "";
    thd = new Thread(this);
    thd.start();
  }

  @Override
  public synchronized void connect()
  {
    if (server)
    {
      try
      {
        ServerSocket ss;
        ss = new ServerSocket(PORT);
        this.s = ss.accept();
      }
      catch (IOException ex)
      {
        Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    else
    {
      try
      {
        s = new Socket(IP, PORT);
      }
      catch (IOException ex)
      {
        Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    try
    {
    OutputStream os = s.getOutputStream();
    this.writer = new PrintWriter(os);
    InputStream is = s.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    this.reader = new BufferedReader(isr);
    connected = true;
    notifyAll();
    this.setChanged();
    this.notifyObservers();
    }
    catch(Exception ex)
    {
      System.err.println(ex.toString());
    }
  }

  @Override
  public void disconnect()
  {
    try
    {
      s.close();
      writer.close();
      reader.close();
      connected = false;
    }
    catch (IOException ex)
    {
      Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
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
    String msg = "";
    try
    {
      msg = this.reader.readLine();
    }
    catch (IOException ex)
    {
      Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
    }
    return msg;
  }

  @Override
  public Boolean getConnected()
  {
    return connected;
  }

  @Override
  public void run()
  {
    while (true)
    {
        this.rcvMsg = this.receive();
        this.setChanged();
        this.notifyObservers();
    }

  }

  private synchronized void parallelWork() throws InterruptedException
  {
    while (!ready)
    {
      wait();
    }
    while (!connected)
    {
      this.connect();
    }
    if (connected)
    {
      this.rcvMsg = this.receive();
      this.setChanged();
      this.notifyObservers();
    }
  }

  public Boolean getServer()
  {
    return server;
  }

  public synchronized void setServer(Boolean server)
  {
    this.server = server;
    this.ready = true;
    notifyAll();
  }

  public String getRcvMsg()
  {
    return rcvMsg;
  }

  public Boolean getReady()
  {
    return ready;
  }

  public void setReady(Boolean ready)
  {
    this.ready = ready;
  }

}
