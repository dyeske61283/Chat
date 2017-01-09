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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
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
  }

  public void registerObserver()
  {
    this.model.addObserver(this);
  }

  @Override
  public void update(Observable o, Object o1)
  {
    if(this.model.getConnected())
    {
      this.view.getRbConnected().setSelected(true);
      this.view.getRbConnected().setText("Connected");
      this.view.getMiConnect().setEnabled(false);
      this.view.getMiDisconnect().setEnabled(true);
    }
    else
    {
      this.view.getRbConnected().setSelected(false);
      this.view.getRbConnected().setText("Disconnected");
      this.view.getMiConnect().setEnabled(true);
      this.view.getMiDisconnect().setEnabled(false);
    }
    
    
    
    StyledDocument doc = this.view.getTpHistory().getStyledDocument();
    SimpleAttributeSet attribs = new SimpleAttributeSet();
    Style style = this.view.getTpHistory().addStyle("I'm a Style", null);
    StyleConstants.setForeground(style, Color.blue);
    StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
    int end = doc.getLength();
    try
    {
      doc.insertString(doc.getLength(), this.model.getRcvMsg() + System.lineSeparator(), style);
      doc.setParagraphAttributes(end, doc.getLength(), attribs, false);
      StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
      doc.setParagraphAttributes(doc.getLength(), 0, attribs, false);
    }
    catch (BadLocationException ex)
    {
      Logger.getLogger(ReceiveAdapter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
