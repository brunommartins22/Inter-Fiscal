package br.com.interagese.interfiscal.message;

import java.net.URL;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.metal.MetalButtonUI;

public class JDlgInput extends javax.swing.JDialog {

    private Object value;

    public JDlgInput(boolean modal) {
        super(new JFrame(), modal);
        initComponents();
        definirFormulario();
    }

    public Object getValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlPrincipal = new javax.swing.JPanel();
        jPnlNorte = new javax.swing.JPanel();
        jLblIcon = new javax.swing.JLabel();
        jLblTitle = new javax.swing.JLabel();
        jLblMensagem = new javax.swing.JLabel();
        jFldValor = new javax.swing.JFormattedTextField();
        jPnlSul = new javax.swing.JPanel();
        jBtnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPnlPrincipal.setLayout(new java.awt.BorderLayout());

        jPnlNorte.setBackground(new java.awt.Color(243, 239, 191));

        jLblIcon.setBackground(new java.awt.Color(255, 255, 255));

        jLblTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLblTitle.setForeground(new java.awt.Color(52, 84, 135));
        jLblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblTitle.setText("Mensagem");

        jLblMensagem.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLblMensagem.setForeground(new java.awt.Color(52, 84, 135));
        jLblMensagem.setText("APENAS TEXTO");

        jFldValor.setBackground(new java.awt.Color(240, 240, 240));
        jFldValor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFldValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFldValor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jFldValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFldValorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPnlNorteLayout = new javax.swing.GroupLayout(jPnlNorte);
        jPnlNorte.setLayout(jPnlNorteLayout);
        jPnlNorteLayout.setHorizontalGroup(
            jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(jFldValor)
                    .addComponent(jLblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnlNorteLayout.setVerticalGroup(
            jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlNorteLayout.createSequentialGroup()
                        .addComponent(jLblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPnlPrincipal.add(jPnlNorte, java.awt.BorderLayout.CENTER);

        jPnlSul.setBackground(new java.awt.Color(204, 204, 204));
        jPnlSul.setPreferredSize(new java.awt.Dimension(445, 40));
        jPnlSul.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jBtnConfirmar.setBackground(new java.awt.Color(0, 136, 204));
        jBtnConfirmar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBtnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnConfirmar.setText("CONFIRMAR");
        jBtnConfirmar.setPreferredSize(new java.awt.Dimension(120, 30));
        jBtnConfirmar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBtnConfirmarFocusLost(evt);
            }
        });
        jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmarActionPerformed(evt);
            }
        });
        jBtnConfirmar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnConfirmarKeyPressed(evt);
            }
        });
        jPnlSul.add(jBtnConfirmar);

        jPnlPrincipal.add(jPnlSul, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPnlPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnConfirmarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBtnConfirmarFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnConfirmarFocusLost

    private void jBtnConfirmarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnConfirmarKeyPressed
        if (evt.getKeyCode() == evt.VK_RIGHT || evt.getKeyCode() == evt.VK_LEFT) {
            evt.getComponent().transferFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {
            ((AbstractButton) evt.getComponent()).doClick();
        }
    }//GEN-LAST:event_jBtnConfirmarKeyPressed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        try {
            jFldValor.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(JDlgInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        value = jFldValor.getValue();
        dispose();
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jFldValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFldValorKeyPressed
        
        if (evt.getKeyCode() == evt.VK_ENTER) {
            value = jFldValor.getValue();
            jBtnConfirmar.doClick();
        }
    }//GEN-LAST:event_jFldValorKeyPressed

    private void definirFormulario() {

        //dispose();

        //setUndecorated(true);

        setLocationRelativeTo(null);

        setIcon(getClass().getResource("/imagens/exclamacao.png"));

        jBtnConfirmar.setUI(new MetalButtonUI());

    }

    public void setIcon(URL url) {
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);

            icon.setImage(icon.getImage().getScaledInstance(jLblIcon.getWidth(), jLblIcon.getHeight(), 100));

            jLblIcon.setIcon(icon);
        }

    }

    public void setMensagem(String texto) {
        jLblMensagem.setText(texto);
        pack();
        setLocationRelativeTo(null);

    }

    public void setTitulo(String titulo) {
        jLblTitle.setText(titulo);
        pack();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDlgInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgInput dialog = new JDlgInput(true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JFormattedTextField jFldValor;
    private javax.swing.JLabel jLblIcon;
    private javax.swing.JLabel jLblMensagem;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JPanel jPnlNorte;
    private javax.swing.JPanel jPnlPrincipal;
    private javax.swing.JPanel jPnlSul;
    // End of variables declaration//GEN-END:variables
}
