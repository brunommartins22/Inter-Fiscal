package br.com.interagese.interfiscal.message;

import java.net.URL;
import java.util.concurrent.Callable;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.plaf.metal.MetalButtonUI;

public class JDlgMessage extends javax.swing.JDialog {

    Callable<Void> metodo;
    
    public JDlgMessage(boolean modal) {
        super(new JFrame(), modal);
        initComponents();
        definirFormulario();

    }

    public JDlgMessage(Callable<Void> metodo) {
        super(new JFrame(), true);
        initComponents();
        definirFormulario();
        this.metodo = metodo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPnlNorte = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLblTitle = new javax.swing.JLabel();
        jLblMensagem = new javax.swing.JLabel();
        jLblIcon = new javax.swing.JLabel();
        jPnlSul = new javax.swing.JPanel();
        jBtnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLblTitle.setBackground(new java.awt.Color(255, 255, 255));
        jLblTitle.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLblTitle.setForeground(new java.awt.Color(0, 0, 0));
        jLblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLblTitle.setText("Mensagem");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLblMensagem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLblMensagem.setForeground(new java.awt.Color(0, 0, 0));

        jLblIcon.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPnlNorteLayout = new javax.swing.GroupLayout(jPnlNorte);
        jPnlNorte.setLayout(jPnlNorteLayout);
        jPnlNorteLayout.setHorizontalGroup(
            jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlNorteLayout.setVerticalGroup(
            jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlNorteLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPnlNorte, java.awt.BorderLayout.CENTER);

        jPnlSul.setPreferredSize(new java.awt.Dimension(445, 40));

        jBtnConfirmar.setBackground(new java.awt.Color(0, 136, 204));
        jBtnConfirmar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jBtnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnConfirmar.setText("OK");
        jBtnConfirmar.setPreferredSize(new java.awt.Dimension(70, 30));
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

        jPanel1.add(jPnlSul, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnConfirmarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnConfirmarKeyPressed
        if (evt.getKeyCode() == evt.VK_RIGHT || evt.getKeyCode() == evt.VK_LEFT) {
            evt.getComponent().transferFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {
            ((AbstractButton) evt.getComponent()).doClick();
        }
    }//GEN-LAST:event_jBtnConfirmarKeyPressed

    private void jBtnConfirmarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBtnConfirmarFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnConfirmarFocusLost

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        dispose();
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void definirFormulario() {

        setTitle("Aviso");

        dispose();

        setUndecorated(true);

        setLocationRelativeTo(null);

        setIcon(getClass().getResource("/imagens/information.png"));

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

    private JPanel getjPnlSul() {
        return jPnlSul;
    }
    
    public void showDialog(boolean b){
        if (this.metodo != null) {
            Runnable r = new ShowAndWait();
            r.run();
        } else {
            super.setVisible(b);
            super.pack();
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDlgMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgMessage dialog = new JDlgMessage(true);
                dialog.setMensagem("Por favor, aguarde um instante ou veja o dialog se expandir...");

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
    private javax.swing.JLabel jLblIcon;
    private javax.swing.JLabel jLblMensagem;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPnlNorte;
    private javax.swing.JPanel jPnlSul;
    // End of variables declaration//GEN-END:variables

    public class ShowAndWait implements Runnable {

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                metodo.call();
                return null;
            }

        };

        @Override
        public void run() {

            
            getjPnlSul().setVisible(false);

            pack();
            
            worker.addPropertyChangeListener((evt) -> {
                if (evt.getPropertyName().equals("state")) {
                    if (evt.getNewValue().equals(SwingWorker.StateValue.DONE)) {
                        dispose();
                    }
                }
            });

            worker.execute();

            setVisible(true);

        }

    }

}
