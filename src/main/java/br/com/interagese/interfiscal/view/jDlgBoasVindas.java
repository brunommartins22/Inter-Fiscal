package br.com.interagese.interfiscal.view;

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class jDlgBoasVindas extends javax.swing.JDialog {

    private boolean resp;

    public jDlgBoasVindas(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        definirFormulario();
    }

    public static boolean jDlgBoasVindasIniciar(JFrame jf) {
        jDlgBoasVindas vindas = new jDlgBoasVindas(jf);
        vindas.setVisible(true);
        return vindas.resp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtContinuar = new javax.swing.JButton();
        jBtCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jBtContinuar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jBtContinuar.setForeground(new java.awt.Color(55, 55, 132));
        jBtContinuar.setText("Continuar");
        jBtContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtContinuarActionPerformed(evt);
            }
        });

        jBtCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jBtCancelar.setForeground(new java.awt.Color(55, 55, 132));
        jBtCancelar.setText("Cancelar");
        jBtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCancelarActionPerformed(evt);
            }
        });

        jEditorPane1.setEditable(false);
        jEditorPane1.setContentType("text/html"); // NOI18N
        jEditorPane1.setForeground(new java.awt.Color(55, 55, 132));
        jEditorPane1.setText("<html>\n<head>\n<style>\nbody {\n        color:#373784\n}\n</style>\n</head>\n<body>\n<b>\n<center><p>Seja Bem-Vindo ao Interage Inter-Fiscal.</p></center>\n</b>\n<p>\nEste módulo fiscal, foi desenvolvido pela empresa Interage Sistemas.\nOnde trás como ferramentas disponivéis os seguintes processos:\n</p>\n</br>\n<p>\n1 - Mix - Fiscal:\n</p>\n<p> \n- Processo este realizado por uma empresa que se responsabiliza pela fiscalização das partes tributárias de sua empresa. \n</p>\n</br>\n<p>\n2 - Imposto - Fiscal:\n</p>\n<p>\n- Processo este realizado para que sejam exportadas tabelas em excel, onde possam ser retiras as informações sobre a parte fiscal da empresa em questao, e com a correção da mesma poderá ser importada para o sistema novamente.\n</p>\n<p>\n3 - Tributação - Fiscal:\n</p>\n<p>\n- Processo este realizado para ajustar as tabelas de tributação, através de uma base de tributos atualizada pela Interage.\n</p>\n<p>\nObs¹: Ao clicar em continuar você terá que entrar com sua senha de administrador, onde será necessário configurar o sistema para uso. \n</p>\n </body>\n</html>");
        jEditorPane1.setFocusable(false);
        jEditorPane1.setSelectedTextColor(new java.awt.Color(41, 121, 174));
        jScrollPane2.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtContinuar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtCancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtContinuar)
                    .addComponent(jBtCancelar))
                .addGap(6, 6, 6))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtContinuarActionPerformed
       dispose();
        resp = true;

    }//GEN-LAST:event_jBtContinuarActionPerformed

    private void jBtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCancelarActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Deseja encerrar a inicialização do sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
//            System.exit(0);
            dispose();
            resp = false;
        }
    }//GEN-LAST:event_jBtCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        emitirBeep();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(jDlgBoasVindas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDlgBoasVindas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDlgBoasVindas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDlgBoasVindas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDlgBoasVindas dialog = new jDlgBoasVindas(new javax.swing.JFrame());
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
    private javax.swing.JButton jBtCancelar;
    private javax.swing.JButton jBtContinuar;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private void definirFormulario() {
        setTitle("Boas-vindas");
        pack();
        setLocationRelativeTo(null);
    }

    private void emitirBeep() {
        //Emite o beep do sistema operacional
        Toolkit.getDefaultToolkit().beep();
    }

}
