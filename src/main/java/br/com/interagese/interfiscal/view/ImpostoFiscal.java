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
    private Integer ordenar;
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
        try {
            jTxtProd.setText("");
            tableModel = new ItensTableModel(fireTabproBusiness.getImportacaoImpostosLimit(codfil, tipo));
            jTable.setModel(tableModel);
            JlblTotal.setText("" + ((Number) fireTabproBusiness.countImportacao(codfil, tipo)).intValue());

            jTable.setAutoResizeMode(jTable.AUTO_RESIZE_OFF);

            a.setColumnMinWidth(jTable, 0, 200);
            a.setColumn(jTable, 1, 110);
            a.setColumn(jTable, 2, 110);
            a.setColumnMinWidth(jTable, 3, 300);
            a.setColumn(jTable, 4, 110);
            a.setColumn(jTable, 5, 110);
            a.setColumn(jTable, 6, 110);
            a.setColumn(jTable, 7, 110);
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
            a.setColumn(jTable, 27, 110);

            jBtnImport.setUI(new MetalButtonUI());
            jBtnExpot.setUI(new MetalButtonUI());
            jBtnSearch.setUI(new MetalButtonUI());
            jBtnPrinter.setUI(new MetalButtonUI());
            exportAll = true;
            jTxtProd.requestFocus();
        } catch (InstantiationException | IllegalAccessException ie) {
            JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ie);
            System.out.println(ie.getMessage());
            mensagem.setVisible(true);
        }
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
            jLabel6 = new javax.swing.JLabel();
            jCbxOrdenar = new javax.swing.JComboBox<>();

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

            jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(0, 51, 102));
            jLabel6.setText("<html><u>Ordenar por:</u></html>");

            jCbxOrdenar.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
            jCbxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "NCM" }));
            jCbxOrdenar.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jCbxOrdenarItemStateChanged(evt);
                }
            });

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCbxOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtProd)
                                    .addComponent(jCbxFilial, 0, 268, Short.MAX_VALUE))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jBtnSearch)
                            .addGap(8, 8, 8)
                            .addComponent(jBtnImport)
                            .addGap(8, 8, 8)
                            .addComponent(jBtnExpot)
                            .addGap(8, 8, 8)
                            .addComponent(jBtnPrinter)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbxOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addGap(6, 6, 6)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)))
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
                    try {

                        JDlgCarregando carregando = a.carregarJdialog("Exportando...", jFramePrincipal);
                        if (!tableModel.getResultList().isEmpty()) {
                            JFileChooser chooser = new JFileChooser();
                            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            int i = chooser.showSaveDialog(jFramePrincipal);
                            if (i == JFileChooser.APPROVE_OPTION) {

                                List<ImportacaoImp> resultImp = null;
                                if (exportAll) {
                                    resultImp = fireTabproBusiness.getImportacaoImpostosAll(codfil, tipo);
                                } else {
                                    resultImp = tableModel.getItens();
                                }

                                if (resultImp.size() > 50000) {

                                }

                                carregando.setVisible(true);
                                carregando.loadBarra("Exportando Arquivo Excel...", 0, 0, true);
                                i = 0;
                                String filename = chooser.getSelectedFile().getAbsolutePath() + ".csv";
//                                HSSFWorkbook workbook = new HSSFWorkbook();
//                                HSSFSheet sheet = workbook.createSheet("FirstSheet");
//
//                                HSSFCellStyle txtStyle1 = (HSSFCellStyle) workbook.createCellStyle();
//                                HSSFCellStyle txtStyle2 = (HSSFCellStyle) workbook.createCellStyle();
//                                HSSFCellStyle txtStyle3 = (HSSFCellStyle) workbook.createCellStyle();
//
//                                HSSFFont txtFont1 = (HSSFFont) workbook.createFont();
//                                HSSFFont txtFont2 = (HSSFFont) workbook.createFont();
//                                HSSFFont txtFont3 = (HSSFFont) workbook.createFont();
//
//                                txtFont1.setFontName("Arial");
//                                txtFont1.setFontHeightInPoints((short) 18);
//                                txtFont1.setBold(true);
//                                txtFont1.setColor((short) 003366);
//                                txtStyle1.setFont(txtFont1);
//
//                                txtFont2.setFontHeightInPoints((short) 14);
//                                txtFont2.setFontName("Arial");
//                                txtFont2.setColor((short) 003366);
//                                txtStyle2.setFont(txtFont2);
//
//                                txtFont3.setFontHeightInPoints((short) 11);
//                                txtFont3.setFontName("Arial");
//                                txtFont3.setBold(true);
//                                txtFont3.setColor((short) 003366);
//                                txtStyle3.setFont(txtFont3);
//
////                                //******************* titulo ************************
//                                HSSFRow rowTitulo = sheet.createRow((short) i++);
//                                rowTitulo.createCell(0).setCellValue("Exportação de Impostos || " + ((Tabfil) jCbxFilial.getModel().getSelectedItem()).toString());
//                                rowTitulo.getCell(0).setCellStyle(txtStyle2);
////                                HSSFRow rowfilial = sheet.createRow((short) i++);
////                                rowfilial.createCell(0).setCellValue("Filial: ");
////                                rowfilial.createCell(1).setCellValue();
//////                            rowfilial.createCell(5).setCellValue("          Consumidor Final        ");
////                                rowfilial.getCell(0).setCellStyle(txtStyle2);
////                                rowfilial.getCell(1).setCellStyle(txtStyle2);
//////                            rowfilial.getCell(5).setCellStyle(txtStyle2);
//
//                                HSSFRow rowHeader2 = sheet.createRow((short) i++);
//                                rowHeader2.createCell(0).setCellValue("Cenário");
//                                rowHeader2.createCell(1).setCellValue("Produto");
//                                rowHeader2.createCell(2).setCellValue("Cód Produto");
//                                rowHeader2.createCell(3).setCellValue("Cód Barras");
//                                rowHeader2.createCell(4).setCellValue("Gênero");
//                                rowHeader2.createCell(5).setCellValue("NCM");
//                                rowHeader2.createCell(6).setCellValue("CFOP");
//                                rowHeader2.createCell(7).setCellValue("CEST");
//                                rowHeader2.createCell(13).setCellValue("CST Icms Saída");
//                                rowHeader2.createCell(14).setCellValue("Alq. Icms Saída");
//                                rowHeader2.createCell(20).setCellValue("CST Pis Saída");
//                                rowHeader2.createCell(21).setCellValue("Alq. Pis Saída");
//                                rowHeader2.createCell(22).setCellValue("CST Cofins Entrada");
//                                rowHeader2.createCell(23).setCellValue("Alq. Cofins Entrada");
//                                rowHeader2.createCell(24).setCellValue("CST Cofins Saída");
//                                rowHeader2.createCell(25).setCellValue("Alq. Cofins Saída");
//                                rowHeader2.createCell(26).setCellValue("CST Ipi Saida");
//                                rowHeader2.createCell(26).setCellValue("Alq. Ipi Saida");
//
//                                rowHeader2.getCell(0).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(1).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(2).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(3).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(4).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(5).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(6).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(7).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(8).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(9).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(10).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(11).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(12).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(13).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(14).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(15).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(16).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(17).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(18).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(19).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(20).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(21).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(22).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(23).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(24).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(25).setCellStyle(txtStyle3);
//                                rowHeader2.getCell(26).setCellStyle(txtStyle3);
//
//                                //***************************************************
//                                DecimalFormat Formata = new DecimalFormat("#,##0.00");
//                                int cont = 1;
//
//                                for (ImportacaoImp item : resultImp) {
//                                    carregando.loadBarra("Exportando Tributação ", cont, resultImp.size(), false);
//                                    HSSFRow rowHeader3 = sheet.createRow(i++);
//                                    rowHeader3.createCell(0).setCellValue(item.getTpImposDesc());
//                                    rowHeader3.createCell(1).setCellValue(item.getNomeproduto());
//                                    rowHeader3.createCell(2).setCellValue(Utils.zeroEsquerda(item.getCodigoproduto(), 5));
//                                    rowHeader3.createCell(3).setCellValue(item.getCodigobarra());
//                                    rowHeader3.createCell(4).setCellValue(item.getGenero());
//                                    rowHeader3.createCell(5).setCellValue(item.getNcm());
//                                    rowHeader3.createCell(6).setCellValue(item.getCfop());
//                                    rowHeader3.createCell(7).setCellValue(item.getCest());
//                                    rowHeader3.createCell(8).setCellValue(item.getCstIcmsEntrada());
//                                    rowHeader3.createCell(9).setCellValue(Formata.format(item.getAliquotaIcmsEntrada()));
//                                    rowHeader3.createCell(10).setCellValue(Formata.format(item.getAliquotaSTIcmsEntrada()));
//                                    rowHeader3.createCell(11).setCellValue(Formata.format(item.getRbcIcmsEntrada()));
//                                    rowHeader3.createCell(12).setCellValue(Formata.format(item.getRbcSTIcmsEntrada()));
//                                    rowHeader3.createCell(13).setCellValue(item.getCstIcmsSaida());
//                                    rowHeader3.createCell(14).setCellValue(Formata.format(item.getAliquotaIcmsSaida()));
//                                    rowHeader3.createCell(15).setCellValue(Formata.format(item.getAliquotaSTIcmsSaida()));
//                                    rowHeader3.createCell(16).setCellValue(Formata.format(item.getRbcIcmsSaida()));
//                                    rowHeader3.createCell(17).setCellValue(Formata.format(item.getRbcSTIcmsSaida()));
//                                    rowHeader3.createCell(18).setCellValue(item.getCstPisEntrada());
//                                    rowHeader3.createCell(19).setCellValue(Formata.format(item.getAliquotaPisEntrada()));
//                                    rowHeader3.createCell(20).setCellValue(item.getCstPisSaida());
//                                    rowHeader3.createCell(21).setCellValue(Formata.format(item.getAliquotaPisSaida()));
//                                    rowHeader3.createCell(22).setCellValue(item.getCstCofinsEntrada());
//                                    rowHeader3.createCell(23).setCellValue(Formata.format(item.getAliquotaCofinsEntrada()));
//                                    rowHeader3.createCell(24).setCellValue(item.getCstCofinsSaida());
//                                    rowHeader3.createCell(25).setCellValue(Formata.format(item.getAliquotaCofinsSaida()));
//                                    rowHeader3.createCell(26).setCellValue(item.getNaturezaproduto());
//
//                                    cont++;
//                                }
                                try {
//                                    FileOutputStream fileOut = new FileOutputStream(filename);
//                                    workbook.write(fileOut);
//                                    fileOut.close();
//                                    workbook.close();

                                    String fileCsv = "Cenário; Cod Produto; Ean; Produto; Ncm; Cest; CST Icms Saida; Alq. Icms Saida; CST Pis Saida; ALq. Pis Saida; CST Cofins Saida;Alq. Cofins Saida; CST Ipi Saida;Alq. Ipi Saida\r\n";
                                    int cont = 1;

                                    for (ImportacaoImp item : resultImp) {
                                        carregando.loadBarra("Exportando Tributação ", cont, resultImp.size(), false);

                                        fileCsv += item.getCodTpImposDesc()
                                                + ";" + Utils.zeroEsquerda(item.getCodigoproduto(), 5)
                                                + ";" + item.getCodigobarra()
                                                + ";" + item.getNomeproduto()
                                                + ";" + item.getNcm()
                                                + ";" + item.getCest()
                                                + ";" + item.getCstIcmsSaida()
                                                + ";" + item.getAliquotaIcmsSaida()
                                                + ";" + item.getCstPisSaida()
                                                + ";" + item.getAliquotaPisSaida()
                                                + ";" + item.getCstCofinsSaida()
                                                + ";" + item.getAliquotaCofinsSaida()
                                                + "; "
                                                + "; 0.0"
                                                + "\r\n";

                                        cont++;
                                    }
                                    
                                    try (PrintWriter writer = new PrintWriter(new File(filename))) {
                                        writer.write(fileCsv);
                                    }

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
                    } catch (InstantiationException | IllegalAccessException ie) {
                        JDlgMensagem mensagem = new JDlgMensagem(jFramePrincipal, true, ie);
                        System.out.println(ie.getMessage());
                        mensagem.setVisible(true);
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
                    codfil = ((Tabfil) jCbxFilial.getModel().getSelectedItem()).getCodfil();
                    System.out.println("codfil :" + codfil);

                    for (int i = 0; i < linhas.getPhysicalNumberOfRows(); i++) {
                        Row linha = linhas.getRow(i);

                        if (i > 1) {

                            ImportacaoImp imp = new ImportacaoImp();

                            imp.setTpImposDesc(linha.getCell(0) == null ? "" : linha.getCell(0).getCellTypeEnum().name().equals("STRING") ? linha.getCell(0).getRichStringCellValue().toString() : "");
                            imp.setNomeproduto(linha.getCell(1) == null ? "" : linha.getCell(1).getCellTypeEnum().name().equals("STRING") ? linha.getCell(1).getRichStringCellValue().toString() : "");
                            imp.setCodigoproduto(linha.getCell(2) == null ? "" : linha.getCell(2).getCellTypeEnum().name().equals("STRING") ? linha.getCell(2).getRichStringCellValue().toString() : "");
                            imp.setCodigobarra(Utils.retirarCaracteresEspeciais(linha.getCell(3).getCellTypeEnum().name().equals("STRING") ? linha.getCell(3).getRichStringCellValue().toString().replace("E12", "").replace("E7", "").replace(".", "") : ""));
                            imp.setGenero(linha.getCell(4) == null ? "" : linha.getCell(4).getCellTypeEnum().name().equals("STRING") ? linha.getCell(4).getRichStringCellValue().toString() : ((Double) linha.getCell(4).getNumericCellValue()).toString().replace(".0", ""));
                            imp.setNcm(Utils.retirarCaracteresEspeciais(linha.getCell(5).getCellTypeEnum().name().equals("STRING") ? linha.getCell(5).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(5).getRichStringCellValue().toString().replace("E12", "").replace("E7", "").replace(".", "") : ""));
                            imp.setCfop(linha.getCell(6) == null ? "" : linha.getCell(6).getCellTypeEnum().name().equals("STRING") ? linha.getCell(6).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(6).getRichStringCellValue().toString() : "");
                            imp.setCest(Utils.retirarCaracteresEspeciais(linha.getCell(7).getCellTypeEnum().name().equals("STRING") ? linha.getCell(7).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(7).getRichStringCellValue().toString() : ""));
                            imp.setCstIcmsEntrada(linha.getCell(8) == null ? "" : linha.getCell(8).getCellTypeEnum().name().equals("STRING") ? linha.getCell(8).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(8).getRichStringCellValue().toString() : "");
                            imp.setAliquotaIcmsEntrada(linha.getCell(9).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(9).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(9).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(9).getNumericCellValue());
                            imp.setAliquotaSTIcmsEntrada(linha.getCell(10).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(10).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(10).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(10).getNumericCellValue());
                            imp.setRbcIcmsEntrada(linha.getCell(11).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(11).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(11).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(11).getNumericCellValue());
                            imp.setRbcSTIcmsEntrada(linha.getCell(12).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(12).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(12).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(12).getNumericCellValue());
                            imp.setCstIcmsSaida(linha.getCell(13) == null ? "" : linha.getCell(13).getCellTypeEnum().name().equals("STRING") ? linha.getCell(13).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(13).getRichStringCellValue().toString() : "");
                            imp.setAliquotaIcmsSaida(linha.getCell(14).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(14).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(14).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(14).getNumericCellValue());
                            imp.setAliquotaSTIcmsSaida(linha.getCell(15).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(15).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(15).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(15).getNumericCellValue());
                            imp.setRbcIcmsSaida(linha.getCell(16).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(16).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(16).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(16).getNumericCellValue());
                            imp.setRbcSTIcmsSaida(linha.getCell(17).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0").parse(linha.getCell(17).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(17).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(17).getNumericCellValue());
                            imp.setCstPisEntrada(linha.getCell(18) == null ? "" : linha.getCell(18).getCellTypeEnum().name().equals("STRING") ? linha.getCell(18).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(18).getRichStringCellValue().toString().trim() : "");
                            imp.setAliquotaPisEntrada(linha.getCell(19).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0.00000").parse(linha.getCell(19).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(19).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(19).getNumericCellValue());
                            imp.setCstPisSaida(linha.getCell(20) == null ? "" : linha.getCell(20).getCellTypeEnum().name().equals("STRING") ? linha.getCell(20).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(20).getRichStringCellValue().toString().trim() : "");
                            imp.setAliquotaPisSaida(linha.getCell(21).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0.00000").parse(linha.getCell(21).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(21).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(21).getNumericCellValue());
                            imp.setCstCofinsEntrada(linha.getCell(22) == null ? "" : linha.getCell(22).getCellTypeEnum().name().equals("STRING") ? linha.getCell(22).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(22).getRichStringCellValue().toString().trim() : "");
                            imp.setAliquotaCofinsEntrada(linha.getCell(23).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0.00000").parse(linha.getCell(23).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(23).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(23).getNumericCellValue());
                            imp.setCstCofinsSaida(linha.getCell(24) == null ? "" : linha.getCell(24).getCellTypeEnum().name().equals("STRING") ? linha.getCell(24).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(24).getRichStringCellValue().toString() : "");
                            imp.setAliquotaCofinsSaida(linha.getCell(25).getCellTypeEnum().name().equals("STRING") ? new DecimalFormat("#,##0.00000").parse(linha.getCell(25).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(25).getRichStringCellValue().toString().trim()).doubleValue() : linha.getCell(25).getNumericCellValue());
                            imp.setNaturezaproduto(linha.getCell(26) == null ? "" : linha.getCell(26).getCellTypeEnum().name().equals("STRING") ? linha.getCell(26).getRichStringCellValue().toString() == null ? "0.0" : linha.getCell(26).getRichStringCellValue().toString().trim() : "");

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
                                            tp.setCst(imp.getCstIcmsSaida());
                                            tp.setIcms(imp.getAliquotaIcmsSaida());

                                            tp.setCstpis(imp.getCstPisSaida());
                                            tp.setFatorpis(imp.getAliquotaPisSaida());

                                            tp.setCstcofins(imp.getCstCofinsSaida());
                                            tp.setFatorcofins(imp.getAliquotaCofinsSaida());

                                            //************************* Indice *************************
                                            String tipoTrib = "";
                                            switch (tp.getCst()) {
                                                case "10":
                                                case "30":
                                                case "50":
                                                case "60":
                                                case "70":
                                                case "90":
                                                case "141":
                                                case "201":
                                                case "202":
                                                case "203":
                                                case "500":
                                                case "900":
                                                case "P10":
                                                case "P90": {
                                                    tipoTrib = "SS";
                                                    break;
                                                }
                                                case "40":
                                                case "300": {
                                                    tipoTrib = "II";
                                                    break;
                                                }
                                                case "41":
                                                case "400": {
                                                    tipoTrib = "NN";
                                                    break;
                                                }
                                                default: {
                                                    tipoTrib = "PT";
                                                    break;
                                                }
                                            }

                                            tp.setIndice(tipoTrib);

                                            tp.setRgevento("2");
                                            tp.setRgdata(new Date());

                                            fireTabproBusiness.update(tp);

                                            String codPro = tp.getCodpro();

                                            TypedQuery<Tabprofil> listTpf = (TypedQuery<Tabprofil>) fireTabprofilBusinessBean.getDao().getEntityManager().createNativeQuery("select * from tabprofil tpf where tpf.codpro = :codpro and tpf.codfil = :codfil", Tabprofil.class).setParameter("codpro", codPro).setParameter("codfil", codfil);
                                            if (!listTpf.getResultList().isEmpty()) {
                                                Tabprofil tpf = listTpf.getResultList().get(0);
                                                tpf.setCstpise(imp.getCstPisEntrada());
                                                tpf.setFatorpiscom(imp.getAliquotaPisEntrada());

                                                tpf.setCstcofinse(imp.getCstCofinsEntrada());
                                                tpf.setFatorcofinscom(imp.getAliquotaCofinsEntrada());

                                                tpf.setRgevento("2");
                                                tpf.setRgdata(new Date());

                                                fireTabprofilBusinessBean.update(tpf);

                                            } else if (imp.getCstPisEntrada() != null || imp.getCstCofinsEntrada() != null) {
                                                Tabprofil fil = new Tabprofil(codPro, codfil);

                                                fil.setCstpise(imp.getCstPisEntrada());
                                                fil.setFatorpiscom(imp.getAliquotaPisEntrada());

                                                fil.setCstcofinse(imp.getCstCofinsEntrada());
                                                fil.setFatorcofinscom(imp.getAliquotaCofinsEntrada());

                                                fil.setRgevento("2");
                                                fil.setRgdata(new Date());

                                                fireTabprofilBusinessBean.insert(fil);

                                            }

                                            String crt = tabfilBusiness.findById(codfil).getCrt().toString();
                                            String modBc = "";

                                            switch (imp.getCstIcmsSaida()) {
                                                case "00":
                                                case "10":
                                                case "20":
                                                case "51":
                                                case "70":
                                                case "90":
                                                case "900": {
                                                    modBc = "0";
                                                    break;
                                                }
                                                default: {
                                                    modBc = "";
                                                    break;
                                                }
                                            }

                                            String sql = "select * from tabproimp tpi where tpi.codpro = :codpro and tpi.codfil = :codfil and (tpi.tpimpos='" + (imp.getTpImpos() == null || imp.getTpImpos().isEmpty() ? "D" : imp.getTpImpos()) + "' or tpi.tpimpos='" + (imp.getTpImpos() == null || imp.getTpImpos().isEmpty() ? "A" : imp.getTpImpos()) + "')";
                                            System.out.println("sql = " + sql);

                                            TypedQuery<Tabproimp> listTpi = (TypedQuery<Tabproimp>) fireTabproimpBusinessBean.getDao().getEntityManager().createNativeQuery("select * from tabproimp tpi where tpi.codpro = :codpro and tpi.codfil = :codfil and (tpi.tpimpos='" + (imp.getTpImpos() == null || imp.getTpImpos().isEmpty() ? "D" : imp.getTpImpos()) + "' or tpi.tpimpos='" + (imp.getTpImpos() == null || imp.getTpImpos().isEmpty() ? "A" : imp.getTpImpos()) + "')", Tabproimp.class).setParameter("codpro", codPro).setParameter("codfil", codfil);

                                            if ((imp.getTpImpos() != null && !imp.getTpImpos().isEmpty()) || (listTpi != null && listTpi.getResultList() != null && !listTpi.getResultList().isEmpty())) {

                                                if (!listTpi.getResultList().isEmpty()) {
                                                    Tabproimp tpi = listTpi.getResultList().get(0);
                                                    tpi.setCfop(imp.getCfop());
                                                    tpi.setNcm(tp.getClasfiscal());

                                                    tpi.setIcmscst(crt.equals("1") ? imp.getCstIcmsSaida() : imp.getCstIcmsSaida().trim().length() == 3 ? imp.getCstIcmsSaida().trim().substring(1) : imp.getCstIcmsSaida().trim());
                                                    tpi.setIcmspicms(imp.getAliquotaIcmsSaida());
                                                    tpi.setIcmspicmsst(imp.getAliquotaSTIcmsSaida());
                                                    tpi.setIcmspredbc(imp.getRbcIcmsSaida());
                                                    tpi.setIcmspredbcst(imp.getRbcSTIcmsSaida());

                                                    tpi.setPiscst(imp.getCstPisSaida());
                                                    tpi.setPisppis(imp.getAliquotaPisSaida());

                                                    tpi.setCofinscst(imp.getCstCofinsSaida());
                                                    tpi.setCofinspcofins(imp.getAliquotaCofinsSaida());

                                                    tpi.setIcmsmodbc(modBc);

                                                    fireTabproimpBusinessBean.update(tpi);

                                                }

                                            } else if ((imp.getCfop() != null && !imp.getCfop().equals("")) || imp.getCstPisSaida() != null || imp.getCstCofinsSaida() != null) {

                                                for (int is = 1; is <= 2; is++) {
                                                    Tabproimp tpImp = new Tabproimp(codPro, codfil, (is == 1 ? "D" : "A"));
                                                    tpImp.setCfop(imp.getCfop());
                                                    tpImp.setNcm(tp.getClasfiscal());

                                                    tpImp.setIcmscst(crt.equals("1") ? imp.getCstIcmsSaida() : imp.getCstIcmsSaida().trim().length() == 3 ? imp.getCstIcmsSaida().trim().substring(1) : imp.getCstIcmsSaida().trim());
                                                    tpImp.setIcmspicms(imp.getAliquotaIcmsSaida());
                                                    tpImp.setIcmspicmsst(imp.getAliquotaSTIcmsSaida());
                                                    tpImp.setIcmspredbc(imp.getRbcIcmsSaida());
                                                    tpImp.setIcmspredbcst(imp.getRbcSTIcmsSaida());

                                                    tpImp.setPiscst(imp.getCstPisSaida());
                                                    tpImp.setPisppis(imp.getAliquotaPisSaida());

                                                    tpImp.setCofinscst(imp.getCstCofinsSaida());
                                                    tpImp.setCofinspcofins(imp.getAliquotaCofinsSaida());

                                                    tpImp.setIcmsmodbc(modBc);
                                                    fireTabproimpBusinessBean.insert(tpImp);
                                                }

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
                            List<ImportacaoImp> query = fireTabproBusiness.getProdutobyDescorCod(jTxtProd.getText(), codfil, tipo, ordenar);
                            tableModel.addItens(null);
                            if (!query.isEmpty()) {
                                for (int i = 0; i <= (query.size() - 1); i++) {
                                    carregando.loadBarra("Carregando Pesquisa ", i, query.size() - 1, false);
                                    ImportacaoImp imp = query.get(i);
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

    private void jCbxOrdenarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCbxOrdenarItemStateChanged
        ordenar = jCbxOrdenar.getSelectedIndex();
    }//GEN-LAST:event_jCbxOrdenarItemStateChanged

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
    private javax.swing.JComboBox<String> jCbxOrdenar;
    private javax.swing.JComboBox<String> jCbxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
