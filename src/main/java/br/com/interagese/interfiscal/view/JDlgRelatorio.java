/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.view;

import br.com.interagese.interfiscal.business.FiscalTempBusiness;
import br.com.interagese.interfiscal.business.FiscalTempBusinessBean;
import br.com.interagese.interfiscal.business.TabfilBusiness;
import br.com.interagese.interfiscal.business.TabfilBusinessBean;
import br.com.interagese.interfiscal.business.TabproBusiness;
import br.com.interagese.interfiscal.business.TabproBusinessBean;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.Sessao;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.message.OptionPane;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.DadosImpressao;
import br.com.interagese.interfiscal.utils.Utils;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 *
 * @author bruno
 */
public class JDlgRelatorio extends javax.swing.JDialog {

    private TabfilBusiness tabfilBusiness = new TabfilBusinessBean();
    private TabproBusiness tabproBusiness = new TabproBusinessBean();
    private FiscalTempBusiness fiscalTempBusiness = new FiscalTempBusinessBean();
    private JFrame principal = null;
    private Tabpro produto = null;
    private Actions a = new Actions(principal);

    /**
     * Creates new form JDlgMensagem
     */
    public JDlgRelatorio(java.awt.Frame parent, boolean modal, String TipoRelatorio) {
        super(parent, modal);
        principal = (JFrame) parent;
        initComponents();
        definirFormulario(TipoRelatorio);
    }

    public void definirFormulario(String TipoRelatorio) {
        a.iconApplication(this);
        this.setTitle(TipoRelatorio.equals("1") ? "Relatório de Produtos Sintético" : "Relatório de Produtos Analítico");
        this.setLocationRelativeTo(null);
        jLblProduto.setText("* Nenhum Produto Informado!!");
        JbtnSearch.setUI(new MetalButtonUI());
        jBtnPrint.setUI(new MetalButtonUI());
//        jCbxFilial.setModel(new DefaultComboBoxModel(tabfilBusiness.getAll().toArray(new Tabfil[]{})));
        jDateChooser1.getDateEditor().getUiComponent().setEnabled(false);
        jDateChooser2.getDateEditor().getUiComponent().setEnabled(false);
        jDateChooser1.setDate(Utils.getDatePrimeiroDiaDoMes());
        jDateChooser2.setDate(new Date());

    }

    public JDlgCarregando carregarJdialog(String txt) {
        JDlgCarregando carregando = new JDlgCarregando(principal, false, txt);
        return carregando;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JbtnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTxtProd = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jBtnPrint = new javax.swing.JButton();
        jLblProduto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        JbtnSearch.setBackground(new java.awt.Color(0, 51, 102));
        JbtnSearch.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        JbtnSearch.setForeground(new java.awt.Color(255, 255, 255));
        JbtnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
        JbtnSearch.setText("Buscar");
        JbtnSearch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JbtnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel2.setText("Produto:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel5.setText("Data:");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel4.setText("Tipo Tributação:");

        jTxtProd.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTxtProd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtProdKeyPressed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Pis/Cofins", "Icms Entrada", "Icms Saída" }));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel6.setText("<html><u>Inicial</u></html>");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel7.setText("<html><u>Final</u></html>");

        jBtnPrint.setBackground(new java.awt.Color(0, 51, 102));
        jBtnPrint.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jBtnPrint.setForeground(new java.awt.Color(255, 255, 255));
        jBtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/printer.png"))); // NOI18N
        jBtnPrint.setText("Imprimir");
        jBtnPrint.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPrintActionPerformed(evt);
            }
        });

        jLblProduto.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTxtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jLblProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)
                                    .addComponent(JbtnSearch))
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTxtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSearchActionPerformed
        try {
            if (jTxtProd.getText() != null && !jTxtProd.getText().equals("")) {
                if (a.possuiLetra(jTxtProd.getText()) == true) {
                    JDlgProdutos produtos = new JDlgProdutos(principal, true, jTxtProd.getText());
                    produtos.setVisible(true);
                    produto = Sessao.produto;
                } else {
                    produto = tabproBusiness.getProdutoByCod(Integer.parseInt(jTxtProd.getText()));
                }
                if (produto != null) {
                    jTxtProd.setText(produto.getIcodpro().toString());
                    jLblProduto.setText(produto.getDescpro());
                } else {
                    produto = null;
                    jTxtProd.setText("");
                    jLblProduto.setText("* Nenhum Produto Informado!!");
                }
                jComboBox2.requestFocus();
            } else {
                produto = null;
                jLblProduto.setText("* Nenhum Produto Informado!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(null, true, ex);
            mensagem.setVisible(true);
        }
    }//GEN-LAST:event_JbtnSearchActionPerformed

    private void jBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrintActionPerformed
        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JDlgCarregando carregando = carregarJdialog("carregando ...");
                        carregando.setVisible(true);
                        carregando.loadBarra("Processando Validação de Impressao ...", 0, 0, true);
                        //********************** Validar Campos ****************************
                        String mensagem = null;
                        if (jDateChooser1.getDate() == null && jDateChooser2.getDate() == null) {
                            mensagem = "Nenhum Período Informado !!";
                        } else if (jDateChooser1.getDate() == null && jDateChooser2.getDate() != null) {
                            mensagem = "Data Inicial não Informada !!";
                        } else if (jDateChooser1 != null && jDateChooser2.getDate() == null) {
                            mensagem = "Data Final não Informada !!";
                        }
                        if (mensagem != null && !mensagem.isEmpty()) {

                            carregando.loadBarra(mensagem, 0, 0, true);
                            carregando.setTexto("Error...");
                            carregando.setVisible(false);

                            Thread.sleep(1500);

                            return;
                        }
                        Thread.sleep(1500);
                        carregando.loadBarra("Carregando Relatório de Produtos ...", 0, 0, true);
                        List<Fiscaltemp> resultTemp = fiscalTempBusiness.getListagemRelatorio((produto == null ? null : produto.getCodbarun().trim()), jComboBox2.getSelectedIndex(), jDateChooser1.getDate(), jDateChooser2.getDate());
                        if (resultTemp == null || resultTemp.isEmpty()) {
                            carregando.loadBarra("Nenhum Produto localizado na Base de Dados !!", 0, 0, true);
                            carregando.setTexto("Finalizando Operação...");
                            carregando.setVisible(false);

                            Thread.sleep(1500);
                            return;
                        }

                        InputStream is = getClass().getResourceAsStream("/relatorio/relatoriomixfiscal.jasper");
                        DadosImpressao impressao = new DadosImpressao(is);
                        carregando.loadBarra("Gerando Relatório de Produtos ...", 0, 0, true);
                        carregando.setTexto("Carregando Relatorio...");
                        carregando.setVisible(false);
                        Thread.sleep(1500);
                        impressao.buildJDialog(resultTemp, principal).setVisible(true);

                        //******************************************************************
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JDlgMensagem mensagem2 = new JDlgMensagem(principal, true, ex);
                        mensagem2.setVisible(true);
                    }
                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(principal, true, ex);
            mensagem.setVisible(true);

        }
    }//GEN-LAST:event_jBtnPrintActionPerformed

    private void jTxtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtProdKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            JbtnSearch.doClick();
        }
    }//GEN-LAST:event_jTxtProdKeyPressed

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
            java.util.logging.Logger.getLogger(JDlgRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                JDlgRelatorio dialog = new JDlgRelatorio(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JButton JbtnSearch;
    private javax.swing.JButton jBtnPrint;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLblProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtProd;
    // End of variables declaration//GEN-END:variables
}
