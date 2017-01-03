/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat;

import pkg10chat.Adapter.ReceiveAdapter;
import pkg10chat.Controller.ConnectController;
import pkg10chat.Controller.SendController;
import pkg10chat.Model.Transmitter;
import pkg10chat.View.ChatView;

/**
 * Builder Class
 * @author kevin
 */
public class Start
{
  public Start()
  {
    Transmitter model = new Transmitter();
    ChatView view = new ChatView();
    ConnectController cController = new ConnectController(view, model);
    cController.registerEvents();
    SendController sController = new SendController(model, view);
    sController.registerEvents();
    ReceiveAdapter rAdapter = new ReceiveAdapter(model, view);
    rAdapter.registerObserver();
    view.setVisible(true);
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    new Start();
  }
}
