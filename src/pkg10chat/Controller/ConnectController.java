/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pkg10chat.Model.Transmitter;
import pkg10chat.View.ChatView;

/**
 *
 * @author kevin
 */
public class ConnectController implements ActionListener
{
  private Transmitter model;
  private ChatView view;
  
  public ConnectController(ChatView view, Transmitter model)
  {
    this.model = model;
    this.view = view;
   
  }
  public void registerEvents()
  {
    this.view.getMiConnect().addActionListener(this);
    this.view.getMiDisconnect().addActionListener(this);
    this.view.getMiClose().addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == this.view.getMiConnect()) 
    {
      this.model.connect();
    }
    if(e.getSource() == this.view.getMiClose())
    {
      int wahl = JOptionPane.showConfirmDialog(this.view, "Do you really want to close this app?", "Close", JOptionPane.OK_CANCEL_OPTION);
      if(wahl == JOptionPane.OK_OPTION)
      {
        System.exit(0);
      }
    }
    if(e.getSource() == this.view.getMiDisconnect())
    {
      this.model.disconnect();
    }
  }
  
}
