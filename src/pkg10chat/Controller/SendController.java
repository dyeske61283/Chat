/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg10chat.Controller;

import Logger.OhmLogger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import pkg10chat.Model.Transmitter;
import pkg10chat.View.ChatView;

/**
 *
 * @author kevin
 */
public class SendController implements ActionListener
{
  Transmitter model;
  ChatView view;

  public SendController(Transmitter model, ChatView view)
  {
    this.view = view;
    this.model = model;
  }

  public void registerEvents()
  {
    this.view.getBtnSend().addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (this.model.getConnected())
    {
      this.model.send(this.view.getTaMessage().getText());
      Document doc = this.view.getTpHistory().getDocument();
      try
      {
        doc.insertString(doc.getLength(), this.view.getTaMessage().getText() + System.lineSeparator(), null);
      }
      catch (BadLocationException ex)
      {
        OhmLogger.getLogger().log(Level.SEVERE, null, ex);
      }
      this.view.getTaMessage().setText("");
    }
    this.view.getTaMessage().requestFocusInWindow();
  }
}
