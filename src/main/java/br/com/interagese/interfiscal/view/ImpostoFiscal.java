/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.view;

import br.com.interagese.interfiscal.business.FireTabproBusinessBean;
import br.com.interagese.interfiscal.business.FireTabprofilBusinessBean;
import br.com.interagese.interfiscal.business.FireTabproimpBusinessBean;
import br.com.interagese.interfiscal.business.FireTabfilBusinessBean;
import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.Log;
import br.com.interagese.interfiscal.entity.Tabfil;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.entity.Tabprofil;
import br.com.interagese.interfiscal.entity.Tabproimp;
import br.com.interagese.interfiscal.table.ItensTableModel;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.Utils;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalButtonUI;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import br.com.interagese.interfiscal.business.FireTabfilBusiness;

/**
 *
 * @author bruno
 */
public class ImpostoFiscal extends JInternalFrame {

    private static JFrame jFramePrincipal;
    private final Actions a;
    private ItensTableModel tableModel;
    private final FireTabproBusinessBean fireTabproBusiness;
    private final FireTabproimpBusinessBean fireTabproimpBusinessBean;
    private final FireTabprofilBusinessBean fireTabprofilBusinessBean;
    private final FireTabfilBusiness tabfilBusiness;
    private Integer codfil;
    private Integer tipo;
    private boolean exportAll;

    /**
     * Creates new form JfrmPrincipal
     *
     * @param pai
     */
    public ImpostoFiscal(javax.swing.JFrame pai) {
        jFramePrincipal = pai;
        this.exportAll = false;
        this.tipo = null;
        this.codfil = null;
        this.tabfilBusiness = new FireTabfilBusinessBean();
        this.fireTabprofilBusinessBean = new FireTabprofilBusinessBean();
        this.fireTabproimpBusinessBean = new FireTabproimpBusinessBean();
        this.fireTabproBusiness = new FireTabproBusinessBean();
        this.tableModel = null;
        this.a = new Actions(jFramePrincipal);

        initComponents();
        definirFormulario();
    }

    public void definirFormulario() {
        this.setTitle("Importação Fiscal");
        a.iconApplicationInternalFrame(this);

        tipo = jCbxTipo.getSelectedIndex();
        jCbxFilial.setModel(new DefaultComboBoxModel(tabfilBusiness.getAll().toArray(new Tabfil[]{})));
        codfil = jCbxFilial.getItemAt(0).getCodfil();
        carregarImpostos();

    }

    public void carregarImpostos() {
        jTxtProd.setText("");
        tableModel = new ItensTableModel(fireTabproBusiness.getImportacaoImpostosLimit(codfil, tipo));
        jTable.setModel(tableModel);
        JlblTotal.setText("" + ((Number) fireTabproBusiness.countImportacao(codfil, tipo)).intValue());

        jTable.setAutoResizeMode(jTable.AUTO_RESIZE_OFF);
        a.setColumn(jTable, 0, 100);
        a.setColumn(jTable, 1, 100);
        a.setColumnMinWidth(jTable, 2, 300);
        a.setColumn(jTable, 3, 100);
        a.setColumn(jTable, 4, 100);
        a.setColumn(jTable, 5, 100);
        a.setColumn(jTable, 6, 100);
        a.setColumn(jTable, 7, 100);
        a.setColumn(jTable, 8, 110);
        a.setColumn(jTable, 9, 110);
        a.setColumn(jTable, 10, 110);
        a.setColumn(jTable, 11, 110);
        a.setColumn(jTable, 12, 110);
        a.setColumn(jTable, 13, 110);
        a.setColumn(jTable, 14, 110);
        a.setColumn(jTable, 15, 110);
        a.setColumn(jTable, 16, 110);
        a.setColumn(jTable, 17, 110);
        a.setColumn(jTable, 18, 110);
        a.setColumn(jTable, 19, 110);
        a.setColumn(jTable, 20, 110);
        a.setColumn(jTable, 21, 110);
        a.setColumn(jTable, 22, 110);
        a.setColumn(jTable, 23, 110);
        a.setColumn(jTable, 24, 110);
        a.setColumn(jTable, 25, 110);
        a.setColumn(jTable, 26, 110);

        jBtnImport.setUI(new MetalButtonUI());
        jBtnExpot.setUI(new MetalButtonUI());
        jBtnSearch.setUI(new MetalButtonUI());
        jBtnPrinter.setUI(new MetalButtonUI());
        exportAll = true;
        jTxtProd.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            protected  void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/fundo.png"));
                Image image = icon.getImage();
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }};
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable = new javax.swing.JTable();
            jPanel2 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            JlblTotal = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jSeparator2 = new javax.swing.JSeparator();
            jPanel3 = new javax.swing.JPanel();
            jLabel2 = new javax.swing.JLabel();
            jCbxFilial = new javax.swing.JComboBox<>();
            jLabel3 = new javax.swing.JLabel();
            jTxtProd = new javax.swing.JTextField();
            jLabel4 = new javax.swing.JLabel();
            jCbxTipo = new javax.swing.JComboBox<>();
            jBtnSearch = new javax.swing.JButton();
            jBtnImport = new javax.swing.JButton();
            jBtnExpot = new javax.swing.JButton();
            jBtnPrinter = new javax.swing.JButton();
            jSeparator1 = new javax.swing.JSeparator();

            setClosable(true);
            setIconifiable(true);
            setMaximizable(true);
            setResizable(true);
            getContentPane().setLayout(new java.awt.CardLayout());

            jTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));
            jTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            jTable.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTableMouseClicked(evt);
                }
            });
            jTable.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jTableKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(jTable);

            jPanel2.setBackground(new java.awt.Color(18, 18, 56));
            jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 2, true));

            jLabel1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Registro(s)");

            JlblTotal.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
            JlblTotal.setForeground(new java.awt.Color(255, 255, 255));
            JlblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            JlblTotal.setText("0");

            jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            jLabel5.setText("→");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(JlblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JlblTotal)
                    .addComponent(jLabel5))
            );

            jSeparator2.setForeground(new java.awt.Color(0, 51, 102));
            jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

            jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true), "Quesitos de Pesquisa", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 18), new java.awt.Color(0, 51, 102)))); // NOI18N
            jPanel3.setForeground(new java.awt.Color(0, 51, 102));

            jLabel2.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(0, 51, 102));
            jLabel2.setText("<html><u>Filal:</u></html>");

            jCbxFilial.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
            jCbxFilial.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jCbxFilialItemStateChanged(evt);
                }
            });
            jCbxFilial.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCbxFilialActionPerformed(evt);
                }
            });

            jLabel3.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(0, 51, 102));
            jLabel3.setText("<html><u>Produto:</u></html>");

            jTxtProd.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
            jTxtProd.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jTxtProdKeyPressed(evt);
                }
            });

            jLabel4.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(0, 51, 102));
            jLabel4.setText("<html><u>Tipo:</u></html>");

            jCbxTipo.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
            jCbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Normal", "Mix-Fiscal" }));
            jCbxTipo.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jCbxTipoItemStateChanged(evt);
                }
            });

            jBtnSearch.setBackground(new java.awt.Color(0, 51, 102));
            jBtnSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
            jBtnSearch.setForeground(new java.awt.Color(255, 255, 255));
            jBtnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
            jBtnSearch.setText("Pesquisar");
            jBtnSearch.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
            jBtnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnSearch.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnSearchActionPerformed(evt);
                }
            });

            jBtnImport.setBackground(new java.awt.Color(0, 51, 102));
            jBtnImport.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
            jBtnImport.setForeground(new java.awt.Color(255, 255, 255));
            jBtnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel_150.png"))); // NOI18N
            jBtnImport.setMnemonic('i');
            jBtnImport.setText("Importar");
            jBtnImport.setToolTipText(" [ ALT + I ] - Importar Arquivo(Excel)");
            jBtnImport.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
            jBtnImport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnImport.setFocusable(false);
            jBtnImport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jBtnImport.setMargin(new java.awt.Insets(2, 0, 2, 14));
            jBtnImport.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnImportActionPerformed(evt);
                }
            });

            jBtnExpot.setBackground(new java.awt.Color(0, 51, 102));
            jBtnExpot.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
            jBtnExpot.setForeground(new java.awt.Color(255, 255, 255));
            jBtnExpot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel_150.png"))); // NOI18N
            jBtnExpot.setMnemonic('e');
            jBtnExpot.setText("Exportar");
            jBtnExpot.setToolTipText(" [ ALT + E ] - Exportar Arquivo(Excel)");
            jBtnExpot.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
            jBtnExpot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnExpot.setFocusable(false);
            jBtnExpot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jBtnExpot.setMargin(new java.awt.Insets(2, 0, 2, 14));
            jBtnExpot.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnExpotActionPerformed(evt);
                }
            });

            jBtnPrinter.setBackground(new java.awt.Color(0, 51, 102));
            jBtnPrinter.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
            jBtnPrinter.setForeground(new java.awt.Color(255, 255, 255));
            jBtnPrinter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/printer.png"))); // NOI18N
            jBtnPrinter.setMnemonic('P');
            jBtnPrinter.setText("Imprimir");
            jBtnPrinter.setToolTipText(" [ ALT + P ] - Imprimir Arquivo");
            jBtnPrinter.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
            jBtnPrinter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnPrinter.setFocusable(false);
            jBtnPrinter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jBtnPrinter.setMargin(new java.awt.Insets(2, 0, 2, 14));
            jBtnPrinter.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnPrinterActionPerformed(evt);
                }
            });

            jSeparator1.setBackground(new java.awt.Color(0, 51, 102));
            jSeparator1.setForeground(new java.awt.Color(0, 51, 102));

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbxFilial, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jBtnSearch)
                            .addGap(12, 12, 12)
                            .addComponent(jBtnImport)
                            .addGap(12, 12, 12)
                            .addComponent(jBtnExpot)
                            .addGap(12, 12, 12)
                            .addComponent(jBtnPrinter)))
                    .addContainerGap())
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbxFilial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnSearch)
                        .addComponent(jBtnImport)
                        .addComponent(jBtnExpot)
                        .addComponent(jBtnPrinter))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator2)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)))
                    .addContainerGap())
            );

            getContentPane().add(jPanel1, "card2");

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (evt.getClickCount() == 2) {
            openDialogItens();
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            openDialogItens();
        }
    }//GEN-LAST:event_jTableKeyPressed

    public void openDialogItens() {
        JDlgItensImpostoFiscal dialog = new JDlgItensImpostoFiscal(jFramePrincipal, tableModel.getItem(jTable.getSelectedRow()));
        dialog.setVisible(true);
    }

    private void jBtnExpotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExpotActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    JDlgCarregando carregando = a.carregarJdialog("Exportando...", jFramePrincipal);
                    if (!tableModel.getResultList().isEmpty()) {
                        JFileChooser chooser = new JFileChooser();
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int i = chooser.showSaveDialog(jFramePrincipal);
                        if (i == JFileChooser.APPROVE_OPTION) {
                            carregando.setVisible(true);
                            carregando.loadBarra("Exportando Arquivo Excel...", 0, 0, true);
                            i = 0;
                            String filename = chooser.getSelectedFile().getAbsolutePath() + ".xls";
                            HSSFWorkbook workbook = new HSSFWorkbook();
                            HSSFSheet sheet = workbook.createSheet("FirstSheet");

                            HSSFCellStyle txtStyle1 = (HSSFCellStyle) workbook.createCellStyle();
                            HSSFCellStyle txtStyle2 = (HSSFCellStyle) workbook.createCellStyle();
                            HSSFCellStyle txtStyle3 = (HSSFCellStyle) workbook.createCellStyle();

                            HSSFFont txtFont1 = (HSSFFont) workbook.createFont();
                            HSSFFont txtFont2 = (HSSFFont) workbook.createFont();
                            HSSFFont txtFont3 = (HSSFFont) workbook.createFont();

                            txtFont1.setFontName("Arial");
                            txtFont1.setFontHeightInPoints((short) 18);
                            txtFont1.setBold(true);
                            txtFont1.setColor((short) 003366);
                            txtStyle1.setFont(txtFont1);

                            txtFont2.setFontHeightInPoints((short) 14);
                            txtFont2.setFontName("Arial");
                            txtFont2.setColor((short) 003366);
                            txtStyle2.setFont(txtFont2);

                            txtFont3.setFontHeightInPoints((short) 11);
                            txtFont3.setFontName("Arial");
                            txtFont3.setBold(true);
                            txtFont3.setColor((short) 003366);
                            txtStyle3.setFont(txtFont3);

                            //******************* titulo ************************
                            HSSFRow rowTitulo = sheet.createRow((short) i++);
                            rowTitulo.createCell(4).setCellValue("Importação de Impostos");
                            rowTitulo.getCell(4).setCellStyle(txtStyle1);

                            HSSFRow rowfilial = sheet.createRow((short) i++);
                            rowfilial.createCell(0).setCellValue("Filial: ");
                            rowfilial.createCell(1).setCellValue(((Tabfil) jCbxFilial.getModel().getSelectedItem()).toString());
//                            rowfilial.createCell(5).setCellValue("          Consumidor Final        ");
                            rowfilial.getCell(0).setCellStyle(txtStyle2);
                            rowfilial.getCell(1).setCellStyle(txtStyle2);
//                            rowfilial.getCell(5).setCellStyle(txtStyle2);

                            HSSFRow rowHeader2 = sheet.createRow((short) i++);
                            rowHeader2.createCell(0).setCellValue("Produto");
                            rowHeader2.createCell(1).setCellValue("Cód Produto");
                            rowHeader2.createCell(2).setCellValue("Cód Barras");
                            rowHeader2.createCell(3).setCellValue("Gênero");
                            rowHeader2.createCell(4).setCellValue("NCM");
                            rowHeader2.createCell(5).setCellValue("CFOP");
                            rowHeader2.createCell(6).setCellValue("CEST");
                            rowHeader2.createCell(7).setCellValue("CST");
                            rowHeader2.createCell(8).setCellValue("Icms Saída");
                            rowHeader2.createCell(9).setCellValue("Pis Entrada");
                            rowHeader2.createCell(10).setCellValue("Pis Saída");
                            rowHeader2.createCell(11).setCellValue("Cofins Entrada");
                            rowHeader2.createCell(12).setCellValue("Cofins Saída");
                            rowHeader2.createCell(13).setCellValue("Nat Produto");
                            rowHeader2.createCell(14).setCellValue("Alíquota Pis");
                            rowHeader2.createCell(15).setCellValue("Alíquota Cofins");
                            rowHeader2.getCell(0).setCellStyle(txtStyle3);
                            rowHeader2.getCell(1).setCellStyle(txtStyle3);
                            rowHeader2.getCell(2).setCellStyle(txtStyle3);
                            rowHeader2.getCell(3).setCellStyle(txtStyle3);
                            rowHeader2.getCell(4).setCellStyle(txtStyle3);
                            rowHeader2.getCell(5).setCellStyle(txtStyle3);
                            rowHeader2.getCell(6).setCellStyle(txtStyle3);
                            rowHeader2.getCell(7).setCellStyle(txtStyle3);
                            rowHeader2.getCell(8).setCellStyle(txtStyle3);
                            rowHeader2.getCell(9).setCellStyle(txtStyle3);
                            rowHeader2.getCell(10).setCellStyle(txtStyle3);
                            rowHeader2.getCell(11).setCellStyle(txtStyle3);
                            rowHeader2.getCell(12).setCellStyle(txtStyle3);
                            rowHeader2.getCell(13).setCellStyle(txtStyle3);
                            rowHeader2.getCell(14).setCellStyle(txtStyle3);
                            rowHeader2.getCell(15).setCellStyle(txtStyle3);

                            //***************************************************
                            DecimalFormat Formata = new DecimalFormat("#,##0.00000");
                            int cont = 1;
                            List<ImportacaoImp> resultImp = null;
                            if (exportAll) {
                                resultImp = fireTabproBusiness.getImportacaoImpostosAll(codfil, tipo);
                            } else {
                                resultImp = tableModel.getItens();
                            }
                            for (ImportacaoImp item : resultImp) {
                                carregando.loadBarra("Exportando Tributação ", cont, resultImp.size(), false);
                                HSSFRow rowHeader3 = sheet.createRow(i++);
                                rowHeader3.createCell(0).setCellValue(item.getNomeproduto());
                                rowHeader3.createCell(1).setCellValue(item.getCodigoproduto());
                                rowHeader3.createCell(2).setCellValue(item.getCodigobarra());
                                rowHeader3.createCell(3).setCellValue(item.getGenero());
                                rowHeader3.createCell(4).setCellValue(item.getNcm());
                                rowHeader3.createCell(5).setCellValue(item.getCfop());
                                rowHeader3.createCell(6).setCellValue(item.getCest());
                                rowHeader3.createCell(7).setCellValue(item.getCstIcmsSaida());
                                rowHeader3.createCell(8).setCellValue(item.getAliquotaIcmsSaida());
                                rowHeader3.createCell(9).setCellValue(item.getCstPisEntrada());
                                rowHeader3.createCell(10).setCellValue(item.getCstPisSaida());
                                rowHeader3.createCell(11).setCellValue(item.getCstCofinsEntrada());
                                rowHeader3.createCell(12).setCellValue(item.getCstCofinsSaida());
                                rowHeader3.createCell(13).setCellValue(item.getNaturezaproduto());
                                rowHeader3.createCell(14).setCellValue(Formata.format(item.getAliquotaPisSaida()));
                                rowHeader3.createCell(15).setCellValue(Formata.format(item.getAliquotaCofinsSaida()));

                                cont++;
                            }
                            try {
                                FileOutputStream fileOut = new FileOutputStream(filename);
                                workbook.write(fileOut);
                                fileOut.close();
                                workbook.close();
                                carregando.loadBarra("Arquivo Exportado com Sucesso ...", 0, 0, true);
                                carregando.setTexto("Finalizando Exportação Fiscal !!");
                                Thread.sleep(1500);
                                carregando.dispose();

                            } catch (Exception ex) {
                                Log logApp = new Log();
                                StringWriter writer = new StringWriter();
                                PrintWriter pw = new PrintWriter(writer);
                                ex.printStackTrace(pw);
                                logApp.setError("Inter-Fiscal - " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + " → " + writer.toString());
                                a.carregarLog(logApp, 2);
                                JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ex);
                                mensagem.setVisible(true);
                            }

                        } else {
                            JOptionPane.showMessageDialog(jFramePrincipal, "Operação cancelada pelo usuário!!", null, JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
            }).start();
        } catch (Exception ex) {
            JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ex);
            System.out.println(ex.getMessage());
            mensagem.setVisible(true);
        }
    }//GEN-LAST:event_jBtnExpotActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema?", null, JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        boolean resp = jDlgBoasVindas.jDlgBoasVindasIniciar(jFramePrincipal);
    }//GEN-LAST:event_formWindowOpened


    private void jBtnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnImportActionPerformed

        Thread action = null;
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.setFileFilter(new FileNameExtensionFilter("Arquivos do Excel", "xlsx", "xlsm", "xlsb", "xltx", "xltm", "xls", "xlt", "xml", "xlam", "xla", "xlw", "xlr", "csv"));
            int resp = chooser.showOpenDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                for (File f : chooser.getSelectedFiles()) {
                    FileInputStream fips = new FileInputStream(f);
                    HSSFWorkbook workbook = new HSSFWorkbook(fips);
                    HSSFSheet linhas = workbook.getSheetAt(0);

                    for (int i = 0; i < linhas.getPhysicalNumberOfRows(); i++) {
                        Row linha = linhas.getRow(i);
                        if (i == 1) {
                            codfil = Integer.parseInt(linha.getCell(1).getRichStringCellValue().toString().split("-")[0].trim());
                        }
                        if (i > 2) {
                            ImportacaoImp imp = new ImportacaoImp();
//                            imp.setNomeproduto(linha.getCell(0).getCellTypeEnum().name().equals("STRING") ? linha.getCell(0).getRichStringCellValue().toString() : ((Double) linha.getCell(0).getNumericCellValue()).toString().replace(".0", ""));
//                            imp.setNomeproduto(((Double) linha.getCell(0).getNumericCellValue()).toString());
                            imp.setNomeproduto(linha.getCell(0).getCellTypeEnum().name().equals("STRING") ? linha.getCell(0).getRichStringCellValue().toString() : ((Double) linha.getCell(0).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setCodigoproduto(linha.getCell(1).getCellTypeEnum().name().equals("STRING") ? linha.getCell(1).getRichStringCellValue().toString() : ((Double) linha.getCell(1).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setCodigobarra(Utils.retirarCaracteresEspeciais(linha.getCell(2).getCellTypeEnum().name().equals("STRING") ? linha.getCell(2).getRichStringCellValue().toString().replace("E12", "").replace("E7", "").replace(".", "") : ((Double) linha.getCell(2).getNumericCellValue()).toString().replace(".0", "")));
                            imp.setGenero(linha.getCell(3).getCellTypeEnum().name().equals("STRING") ? linha.getCell(3).getRichStringCellValue().toString() : ((Double) linha.getCell(3).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setNcm(Utils.retirarCaracteresEspeciais(linha.getCell(4).getCellTypeEnum().name().equals("STRING") ? linha.getCell(4).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(4).getRichStringCellValue().toString().replace("E12", "").replace("E7", "").replace(".", "") : ((Double) linha.getCell(4).getNumericCellValue()).toString().replace(".0", "")));
                            imp.setCfop(linha.getCell(5).getCellTypeEnum().name().equals("STRING") ? linha.getCell(5).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(5).getRichStringCellValue().toString() : ((Double) linha.getCell(5).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setCest(Utils.retirarCaracteresEspeciais(linha.getCell(6).getCellTypeEnum().name().equals("STRING") ? linha.getCell(6).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(6).getRichStringCellValue().toString() : ((Double) linha.getCell(6).getNumericCellValue()).toString().replace(".0", "")));
                            imp.setCstIcmsSaida(linha.getCell(7).getCellTypeEnum().name().equals("STRING") ? linha.getCell(7).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(7).getRichStringCellValue().toString() : ((Double) linha.getCell(7).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setAliquotaIcmsSaida(linha.getCell(8).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(8).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(8).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(8).getNumericCellValue());
                            imp.setCstPisEntrada(linha.getCell(9).getCellTypeEnum().name().equals("STRING") ? linha.getCell(9).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(9).getRichStringCellValue().toString().trim() : ((Double) linha.getCell(9).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setCstPisSaida(linha.getCell(10).getCellTypeEnum().name().equals("STRING") ? linha.getCell(10).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(10).getRichStringCellValue().toString().trim() : ((Double) linha.getCell(10).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setCstCofinsEntrada(linha.getCell(11).getCellTypeEnum().name().equals("STRING") ? linha.getCell(11).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(11).getRichStringCellValue().toString().trim() : ((Double) linha.getCell(11).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setCstCofinsSaida(linha.getCell(12).getCellTypeEnum().name().equals("STRING") ? linha.getCell(12).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(12).getRichStringCellValue().toString() : ((Double) linha.getCell(12).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setNaturezaproduto(linha.getCell(13).getCellTypeEnum().name().equals("STRING") ? linha.getCell(13).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(13).getRichStringCellValue().toString().trim() : ((Double) linha.getCell(13).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setAliquotaPisSaida(linha.getCell(14).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0.00000").parse(linha.getCell(14).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(14).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(14).getNumericCellValue());
                            imp.setAliquotaCofinsSaida(linha.getCell(15).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0.00000").parse(linha.getCell(15).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(15).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(15).getNumericCellValue());
                            result.add(imp);
                        }
                    }

                    action = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JDlgCarregando carregando = a.carregarJdialog("Atualizando...", jFramePrincipal);
                                carregando.setVisible(true);
                                String texto = "Iniciando Ajustes de Tributação fiscal ...";
                                carregando.loadBarra(texto, 0, 0, true);

                                int i = 1;
                                String notTributation = "";

                                for (ImportacaoImp imp : result) {
                                    carregando.loadBarra("Importando Tributação ", i, result.size(), false);
                                    boolean isExist = false;

                                    if (imp.getCodigobarra() != null && !imp.getCodigobarra().equals("")) {
                                        String query = "SELECT * FROM Tabpro tp WHERE tp.codbarun = '" + imp.getCodigobarra().trim() + "'";

                                        TypedQuery<Tabpro> listTp = (TypedQuery<Tabpro>) fireTabproBusiness.getDao().getEntityManager().createNativeQuery(query, Tabpro.class);
                                        if (!listTp.getResultList().isEmpty()) {
                                            Tabpro tp = listTp.getResultList().get(0);
                                            tp.setCodgen(imp.getGenero().trim());
                                            tp.setClasfiscal(imp.getNcm());
                                            tp.setCest(imp.getCest());
                                            tp.setNatpro(imp.getNaturezaproduto().trim());
                                            fireTabproBusiness.update(tp);
                                            String codPro = tp.getCodpro();

                                            TypedQuery<Tabprofil> listTpf = (TypedQuery<Tabprofil>) fireTabprofilBusinessBean.getDao().getEntityManager().createNativeQuery("select * from tabprofil tpf where tpf.codpro = :codpro and tpf.codfil = :codfil", Tabprofil.class).setParameter("codpro", codPro).setParameter("codfil", codfil);
                                            if (!listTpf.getResultList().isEmpty()) {
                                                Tabprofil tpf = listTpf.getResultList().get(0);
                                                tpf.setCstpise(imp.getCstPisEntrada());
                                                tpf.setCstcofinse(imp.getCstCofinsEntrada());
                                                fireTabprofilBusinessBean.update(tpf);
                                            } else if (imp.getCstPisEntrada() != null || imp.getCstCofinsEntrada() != null) {
                                                Tabprofil fil = new Tabprofil(codPro, codfil);
                                                fil.setCstpise(imp.getCstPisEntrada());
                                                fil.setCstcofinse(imp.getCstCofinsEntrada());
                                                fireTabprofilBusinessBean.insert(fil);
                                            }
                                            TypedQuery<Tabproimp> listTpi = (TypedQuery<Tabproimp>) fireTabproimpBusinessBean.getDao().getEntityManager().createNativeQuery("select * from tabproimp tpi where tpi.codpro = :codpro and tpi.codfil = :codfil and tpi.tpimpos='D'", Tabproimp.class).setParameter("codpro", codPro).setParameter("codfil", codfil);
                                            String crt = tabfilBusiness.findById(codfil).getCrt().toString();
                                            if (!listTpi.getResultList().isEmpty()) {
                                                Tabproimp tpi = listTpi.getResultList().get(0);
                                                tpi.setCfop(imp.getCfop());
                                                tpi.setIcmscst(crt.equals("1") ? imp.getCstIcmsSaida() : imp.getCstIcmsSaida().trim().length() == 3 ? imp.getCstIcmsSaida().trim().substring(1) : imp.getCstIcmsSaida().trim());
                                                tpi.setIcmspicms(imp.getAliquotaIcmsSaida());
                                                tpi.setPiscst(imp.getCstPisSaida());
                                                tpi.setCofinscst(imp.getCstCofinsSaida());
                                                tpi.setPisppis(imp.getAliquotaPisSaida());
                                                tpi.setCofinspcofins(imp.getAliquotaCofinsSaida());

                                                fireTabproimpBusinessBean.update(tpi);
                                            } else if ((imp.getCfop() != null && !imp.getCfop().equals("")) || imp.getCstPisSaida() != null || imp.getCstCofinsSaida() != null) {
                                                Tabproimp tpImp = new Tabproimp(codPro, codfil, "D");
                                                tpImp.setCfop(imp.getCfop());
                                                tpImp.setIcmscst(crt.equals("1") ? imp.getCstIcmsSaida() : imp.getCstIcmsSaida().trim().length() == 3 ? imp.getCstIcmsSaida().trim().substring(1) : imp.getCstIcmsSaida().trim());
                                                if (tpImp.getIcmscst().equals("00")) {
                                                    tpImp.setIcmsmodbc("3");
                                                }
                                                tpImp.setIcmspicms(imp.getAliquotaIcmsSaida());
                                                tpImp.setPiscst(imp.getCstPisSaida());
                                                tpImp.setCofinscst(imp.getCstCofinsSaida());
                                                tpImp.setPisppis(imp.getAliquotaPisSaida());
                                                tpImp.setCofinspcofins(imp.getAliquotaCofinsSaida());
                                                fireTabproimpBusinessBean.insert(tpImp);
                                            }
                                            isExist = true;
                                        }
                                    }

                                    if (!isExist) {
                                        notTributation += ("CodBarra = " + imp.getCodigobarra() + " Produto = " + imp.getNomeproduto() + "\r\n");
                                    }
                                    texto = (!isExist ? "!! Não Encontrado na Base de Dados !! " : "Posição = " + i) + "| codBarra = " + imp.getCodigobarra();
                                    carregando.setTexto(texto);
                                    i++;
                                }

//                                fireTabproBusiness.updateImpostoImp(result, codfil);
                                gerarArquivoNotTributation(notTributation);
                                carregarImpostos();
                                carregando.setVisible(true);
                                carregando.loadBarra("Arquivo Importado com sucesso ...", 0, 0, true);
                                carregando.setTexto("Realizando a Atualização dos produtos ...");
                                Thread.sleep(1500);
                                carregando.setTexto("Finalizando Impotação - Fiscal !!");
                                Thread.sleep(1500);
                                carregando.dispose();

                            } catch (Exception ex) {
                                Log logApp = new Log();
                                StringWriter writer = new StringWriter();
                                PrintWriter pw = new PrintWriter(writer);
                                ex.printStackTrace(pw);
                                logApp.setError("Inter-Fiscal - " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + " → " + writer.toString());
                                a.carregarLog(logApp, 2);
                                JDlgMensagem mensagem = new JDlgMensagem(null, true, ex);
                                mensagem.setVisible(true);
//                                jProgressBar.setVisible(false);
                            }
                        }
                    });
                    action.start();

                }

            } else {
                JOptionPane.showMessageDialog(this, "Operação cancelada pelo usuário!!", null, JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ex);
            mensagem.setVisible(true);
            action.stop();
        }
    }//GEN-LAST:event_jBtnImportActionPerformed

    public void gerarArquivoNotTributation(String texto) throws Exception {

        File arquivo = new File("Log-Importacao\\Produtos Sem Tributacao " + (new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date())) + ".txt");
        arquivo.createNewFile();
        FileWriter arq = new FileWriter(arquivo);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(texto);
        arq.close();
    }

    private void jCbxFilialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCbxFilialItemStateChanged

        codfil = ((Tabfil) jCbxFilial.getSelectedItem()).getCodfil();

    }//GEN-LAST:event_jCbxFilialItemStateChanged

    private void jBtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSearchActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        JDlgCarregando carregando = a.carregarJdialog("", jFramePrincipal);
                        codfil = ((Tabfil) jCbxFilial.getSelectedItem()).getCodfil();
                        tipo = jCbxTipo.getSelectedIndex();
                        carregando.setVisible(true);
                        carregando.loadBarra("Iniciando Pesquisa...", 0, 0, true);
                        if (jTxtProd.getText() != null) {
                            List<Object[]> query = fireTabproBusiness.getProdutobyDescorCod(jTxtProd.getText(), codfil, tipo);
                            tableModel.addItens(null);
                            if (!query.isEmpty()) {
                                for (int i = 0; i <= (query.size() - 1); i++) {
                                    carregando.loadBarra("Carregando Pesquisa ", i, query.size() - 1, false);
                                    Object[] o = query.get(i);
                                    System.out.println("");
                                    ImportacaoImp imp = new ImportacaoImp();
                                    imp.setNomeproduto(o[0] != null ? (String) o[0] : "");
                                    imp.setCodigoproduto(o[1] != null ? (String) o[1] : "");
                                    imp.setCodigobarra(o[2] != null ? (String) o[2] : "");
                                    imp.setGenero(o[3] != null ? (String) o[3] : "");
                                    imp.setNcm(o[4] != null ? (String) o[4] : "");
                                    imp.setCfop(o[5] != null ? (String) o[5] : "");
                                    imp.setCest(o[6] != null ? (String) o[6] : "");
                                    imp.setCstIcmsEntrada(o[7] != null ? (String) o[7] : "");
                                    imp.setAliquotaIcmsEntrada(o[8] != null ? (Double) o[8] : 0.0);
                                    imp.setAliquotaSTIcmsEntrada(o[9] != null ? (Double) o[9] : 0.0);
                                    imp.setRbcIcmsEntrada(o[10] != null ? (Double) o[10] : 0.0);
                                    imp.setRbcSTIcmsEntrada(o[11] != null ? (Double) o[11] : 0.0);
                                    imp.setCstIcmsSaida(o[12] != null ? (String) o[12] : "");
                                    imp.setAliquotaIcmsSaida(o[13] != null ? (Double) o[13] : 0.0);
                                    imp.setAliquotaSTIcmsSaida(o[14] != null ? (Double) o[14] : 0.0);
                                    imp.setRbcIcmsSaida(o[15] != null ? (Double) o[15] : 0.0);
                                    imp.setRbcSTIcmsSaida(o[16] != null ? (Double) o[16] : 0.0);
                                    imp.setCstPisEntrada(o[17] != null ? (String) o[17] : "");
                                    imp.setAliquotaPisEntrada(o[18] != null ? (Double) o[18] : 0.0);
                                    imp.setCstPisSaida(o[19] != null ? (String) o[19] : "");
                                    imp.setAliquotaPisSaida(o[20] != null ? (Double) o[20] : 0.0);
                                    imp.setCstCofinsEntrada(o[21] != null ? (String) o[21] : "");
                                    imp.setAliquotaCofinsEntrada(o[22] != null ? (Double) o[22] : 0.0);
                                    imp.setCstCofinsSaida(o[23] != null ? (String) o[23] : "");
                                    imp.setAliquotaCofinsSaida(o[24] != null ? (Double) o[24] : 0.0);
                                    imp.setNaturezaproduto(o[25] != null ? (String) o[25] : "");
                                    imp.setDataAtualizacao(o[26] != null ? (Date) o[26] : null);
                                    carregando.setTexto(imp.getCodigoproduto() + " - " + imp.getNomeproduto());
                                    tableModel.addItem(imp);
                                    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
                                    tableModel.updateTableListener();
                                }

                            }

                            JlblTotal.setText("" + query.size());
                            exportAll = false;
                        }
//                        else {
//                            carregarImpostos();
//                        }
                        carregando.loadBarra("Dados Carregados com sucesso ...", 0, 0, true);
                        carregando.setTexto("Listando Produtos para visualização !!");
                        Thread.sleep(1500);
                        carregando.dispose();
                    } catch (Exception ex) {
                        Log logApp = new Log();
                        StringWriter writer = new StringWriter();
                        PrintWriter pw = new PrintWriter(writer);
                        ex.printStackTrace(pw);
                        logApp.setError("Inter-Fiscal - " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + " → " + writer.toString());
                        a.carregarLog(logApp, 2);
                        JDlgMensagem m = new JDlgMensagem(jFramePrincipal, true, ex);
                        m.setVisible(true);
                    }
                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ex);
            mensagem.setVisible(true);
        }
    }//GEN-LAST:event_jBtnSearchActionPerformed

    private void jCbxFilialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbxFilialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbxFilialActionPerformed

    private void jTxtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtProdKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jBtnSearch.requestFocus();
        }
    }//GEN-LAST:event_jTxtProdKeyPressed

    private void jCbxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCbxTipoItemStateChanged
        tipo = jCbxTipo.getSelectedIndex();
    }//GEN-LAST:event_jCbxTipoItemStateChanged

    private void jBtnPrinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrinterActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JDlgCarregando carregando = a.carregarJdialog("Processo em manutenção ...", jFramePrincipal);
                        carregando.setVisible(true);
                        carregando.loadBarra("Operação Não Autorizada !!", 0, 0, true);
                        Thread.sleep(1500);
                        carregando.loadBarra("Finalizando Operação ...", 0, 0, true);
                        Thread.sleep(1500);
                        carregando.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ex);
                        mensagem.setVisible(true);
                    }
                }
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ex);
            mensagem.setVisible(true);

        }
    }//GEN-LAST:event_jBtnPrinterActionPerformed

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImpostoFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImpostoFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImpostoFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImpostoFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImpostoFiscal(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlblTotal;
    private javax.swing.JButton jBtnExpot;
    private javax.swing.JButton jBtnImport;
    private javax.swing.JButton jBtnPrinter;
    private javax.swing.JButton jBtnSearch;
    private javax.swing.JComboBox<Tabfil> jCbxFilial;
    private javax.swing.JComboBox<String> jCbxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTxtProd;
    // End of variables declaration//GEN-END:variables
}
