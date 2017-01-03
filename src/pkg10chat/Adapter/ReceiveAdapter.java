/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg10chat.Adapter;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import pkg10chat.Model.Transmitter;
import pkg10chat.View.ChatView;

/**
 *
 * @author kevin
 */
public class ReceiveAdapter implements Observer
{
  private Transmitter model;
  private ChatView view;
  
  public ReceiveAdapter(Transmitter model, ChatView view)
  {
    this.view = view;
    this.model = model;
    this.model.addObserver(this);
  }

  @Override
  public void update(Observable o, Object o1)
  {
    this.view.getRbConnected().setSelected(this.model.getConnected());
    SimpleAttributeSet aSet = new SimpleAttributeSet();
    StyleConstants.setForeground(aSet, Color.blue);
    
    Document doc = this.view.getTpHistory().getDocument();
    try
    {
      doc.insertString(doc.getLength(),this.model.getRecvMessage() + System.lineSeparator() , aSet);
    }
    catch (BadLocationException ex)
    {
      Logger.getLogger(ReceiveAdapter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
