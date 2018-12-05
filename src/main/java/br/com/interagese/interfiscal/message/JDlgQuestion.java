package br.com.interagese.interfiscal.message;

import java.net.URL;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalButtonUI;

public class JDlgQuestion extends javax.swing.JDialog {

    private int option;
    
    public JDlgQuestion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        definirFormulario();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlPrincipal = new javax.swing.JPanel();
        jPnlNorte = new javax.swing.JPanel();
        jLblIcon = new javax.swing.JLabel();
        jLblTitle = new javax.swing.JLabel();
        jLblMensagem = new javax.swing.JLabel();
        jPnlSul = new javax.swing.JPanel();
        jBtnNao = new javax.swing.JButton();
        jBtnSim = new javax.swing.JButton();

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

        javax.swing.GroupLayout jPnlNorteLayout = new javax.swing.GroupLayout(jPnlNorte);
        jPnlNorte.setLayout(jPnlNorteLayout);
        jPnlNorteLayout.setHorizontalGroup(
            jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnlNorteLayout.setVerticalGroup(
            jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlNorteLayout.createSequentialGroup()
                        .addComponent(jLblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPnlPrincipal.add(jPnlNorte, java.awt.BorderLayout.CENTER);

        jPnlSul.setBackground(new java.awt.Color(204, 204, 204));
        jPnlSul.setPreferredSize(new java.awt.Dimension(445, 40));
        jPnlSul.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jBtnNao.setBackground(new java.awt.Color(0, 136, 204));
        jBtnNao.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBtnNao.setForeground(new java.awt.Color(255, 255, 255));
        jBtnNao.setText("NÂO");
        jBtnNao.setPreferredSize(new java.awt.Dimension(90, 30));
        jBtnNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNaoActionPerformed(evt);
            }
        });
        jBtnNao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnNaoKeyPressed(evt);
            }
        });
        jPnlSul.add(jBtnNao);

        jBtnSim.setBackground(new java.awt.Color(0, 136, 204));
        jBtnSim.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBtnSim.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSim.setText("SIM");
        jBtnSim.setPreferredSize(new java.awt.Dimension(90, 30));
        jBtnSim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBtnSimFocusLost(evt);
            }
        });
        jBtnSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSimActionPerformed(evt);
            }
        });
        jBtnSim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnSimKeyPressed(evt);
            }
        });
        jPnlSul.add(jBtnSim);

        jPnlPrincipal.add(jPnlSul, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPnlPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSimFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBtnSimFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSimFocusLost

    private void jBtnSimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnSimKeyPressed
        if (evt.getKeyCode() == evt.VK_RIGHT || evt.getKeyCode() == evt.VK_LEFT) {
            evt.getComponent().transferFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {
            option = JOptionPane.YES_OPTION;
            ((AbstractButton) evt.getComponent()).doClick();
        }
    }//GEN-LAST:event_jBtnSimKeyPressed

    private void jBtnNaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnNaoKeyPressed
        if (evt.getKeyCode() == evt.VK_RIGHT || evt.getKeyCode() == evt.VK_LEFT) {
            evt.getComponent().transferFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {
            
            ((AbstractButton) evt.getComponent()).doClick();
        }
    }//GEN-LAST:event_jBtnNaoKeyPressed

    private void jBtnNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNaoActionPerformed
        option = JOptionPane.NO_OPTION;
        dispose();
    }//GEN-LAST:event_jBtnNaoActionPerformed

    private void jBtnSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSimActionPerformed
        option = JOptionPane.YES_OPTION;
        dispose();
    }//GEN-LAST:event_jBtnSimActionPerformed

    private void definirFormulario(){
       
        dispose();
        
        setUndecorated(true);
        
        setLocationRelativeTo(null);

        setIcon(getClass().getResource("/imagens/exclamacao.png"));


        jBtnSim.setUI(new MetalButtonUI());
        jBtnNao.setUI(new MetalButtonUI());
        
    }
    
    public void setIcon(URL url){
        ImageIcon icon = new ImageIcon(url);

        icon.setImage(icon.getImage().getScaledInstance(jLblIcon.getWidth(), jLblIcon.getHeight(), 100));

        jLblIcon.setIcon(icon);
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
            java.util.logging.Logger.getLogger(JDlgQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgQuestion dialog = new JDlgQuestion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBtnNao;
    private javax.swing.JButton jBtnSim;
    private javax.swing.JLabel jLblIcon;
    private javax.swing.JLabel jLblMensagem;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JPanel jPnlNorte;
    private javax.swing.JPanel jPnlPrincipal;
    private javax.swing.JPanel jPnlSul;
    // End of variables declaration//GEN-END:variables

    public int getOption() {
        return option;
    }
    
}
