/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Controller;

import javax.swing.JOptionPane;
import pkg10chat.Model.Transmitter;
import pkg10chat.View.ChatView;

/**
 *
 * @author kevin
 */
public class ConnectController
{
  private Transmitter model;
  private ChatView view;
  final Object[] options = {"Server","Client"};
  
  public ConnectController(ChatView view, Transmitter model)
  {
    this.model = model;
    this.view = view;
    
    int wahl = JOptionPane.showOptionDialog(this.view, "Do you want to be Server, or Client?",
                                            "Connection",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                            null,options,options[0]);
    
    this.model.setServer(wahl == JOptionPane.YES_OPTION);
    this.model.connect();
  }
  public void registerEvents()
  {
    
  }
  
}
