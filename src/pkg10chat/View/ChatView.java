/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg10chat.View;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author kevin
 */
public class ChatView extends javax.swing.JFrame
{

  /**
   * Creates new form ChatView
   */
  public ChatView()
  {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tpHistory = new javax.swing.JTextPane();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    taMessage = new javax.swing.JTextArea();
    btnSend = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    jRadioButton1 = new javax.swing.JRadioButton();
    jMenuBar1 = new javax.swing.JMenuBar();
    mnFile = new javax.swing.JMenu();
    mnConnect = new javax.swing.JMenu();

    jLabel1.setText("jLabel1");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("MyChat");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

    jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 353));

    tpHistory.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
    tpHistory.setEnabled(false);
    tpHistory.setFocusable(false);
    tpHistory.setMinimumSize(new java.awt.Dimension(280, 300));
    tpHistory.setPreferredSize(new java.awt.Dimension(280, 350));
    jScrollPane1.setViewportView(tpHistory);

    getContentPane().add(jScrollPane1);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane2.setAlignmentX(0.0F);
    jScrollPane2.setAlignmentY(0.0F);

    taMessage.setColumns(28);
    taMessage.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
    taMessage.setRows(5);
    taMessage.setAutoscrolls(false);
    jScrollPane2.setViewportView(taMessage);

    jPanel1.add(jScrollPane2, new java.awt.GridBagConstraints());

    btnSend.setText("Send");
    btnSend.setToolTipText("send the message");
    btnSend.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel1.add(btnSend, gridBagConstraints);

    getContentPane().add(jPanel1);

    jRadioButton1.setText("Connected");
    jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jPanel2.add(jRadioButton1);

    getContentPane().add(jPanel2);

    mnFile.setText("File");
    jMenuBar1.add(mnFile);

    mnConnect.setText("Connection");
    jMenuBar1.add(mnConnect);

    setJMenuBar(jMenuBar1);

    setSize(new java.awt.Dimension(310, 530));
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[])
  {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try
    {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
      {
        if ("Nimbus".equals(info.getName()))
        {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException ex)
    {
      java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
      java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
      java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        new ChatView().setVisible(true);
      }
    });
  }

  public JButton getBtnSend()
  {
    return btnSend;
  }

  public JTextArea getTaMessage()
  {
    return taMessage;
  }

  public JMenu getMnConnect()
  {
    return mnConnect;
  }

  public JMenu getMnFile()
  {
    return mnFile;
  }

  public JTextPane getTpHistory()
  {
    return tpHistory;
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnSend;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JRadioButton jRadioButton1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JMenu mnConnect;
  private javax.swing.JMenu mnFile;
  private javax.swing.JTextArea taMessage;
  private javax.swing.JTextPane tpHistory;
  // End of variables declaration//GEN-END:variables
}
