/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.view;

import br.com.interagese.interfiscal.business.FireTabproBusiness;
import br.com.interagese.interfiscal.business.FireTabproBusinessBean;
import br.com.interagese.interfiscal.business.FiscalTempBusinessBean;
import br.com.interagese.interfiscal.business.IcmsentradaBusiness;
import br.com.interagese.interfiscal.business.IcmsentradaBusinessBean;
import br.com.interagese.interfiscal.business.IcmssaidaBusiness;
import br.com.interagese.interfiscal.business.IcmssaidaBusinessBean;
import br.com.interagese.interfiscal.business.PisCofinsBusiness;
import br.com.interagese.interfiscal.business.PisCofinsBusinessBean;
import br.com.interagese.interfiscal.business.TabfilBusiness;
import br.com.interagese.interfiscal.business.TabfilBusinessBean;
import br.com.interagese.interfiscal.business.TabproBusiness;
import br.com.interagese.interfiscal.business.TabproBusinessBean;
import br.com.interagese.interfiscal.entity.BaseInterage;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Log;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.entity.Sessao;
import br.com.interagese.interfiscal.entity.Tabfil;
import br.com.interagese.interfiscal.table.BaseInterageTableModel;
import br.com.interagese.interfiscal.table.IcmsEntradaTableModel;
import br.com.interagese.interfiscal.table.IcmsSaidaTableModel;
import br.com.interagese.interfiscal.table.piscofinsTableModel;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.Utils;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 *
 * @author bruno
 */
public class MixFiscal extends JInternalFrame {

    private JFrame jfrmPrincipal;
    private Actions a = new Actions(jfrmPrincipal);
    private PisCofinsBusiness pisCofinsBusiness = new PisCofinsBusinessBean();
    private IcmsentradaBusiness icmsentradaBusiness = new IcmsentradaBusinessBean();
    private IcmssaidaBusiness icmssaidaBusiness = new IcmssaidaBusinessBean();
    private FiscalTempBusinessBean fiscalTempBusinessBean = new FiscalTempBusinessBean();
    private TabproBusiness tabproBusiness = new TabproBusinessBean();
    private piscofinsTableModel tableModelPisCofins;
    private IcmsEntradaTableModel tableModelIcmsEntrada;
    private IcmsSaidaTableModel tableModelIcmsSaida;
    private BaseInterageTableModel baseInterageTableModel;
    private FireTabproBusiness fireTabproBusiness = new FireTabproBusinessBean();
    private TabfilBusiness tabfilBusiness = new TabfilBusinessBean();
    private Tabfil filial = null;
    private Integer contUpdate;
    private Integer infoHoras;
    private Integer qtdEnvio;
    private boolean isExecute;

    /**
     * Creates new form JFrmPrincipal
     */
    public MixFiscal(JFrame pai) {
        jfrmPrincipal = pai;
        infoHoras = Sessao.infoHoras * 3600;
        qtdEnvio = Sessao.qtdEnvio;
        initComponents();
        definirFormulario();

    }

    public void definirFormulario() {
        this.setTitle("Mix Fiscal");
        a.iconApplicationInternalFrame(this);
        jLabelPisCofins.setText(" → PIS / COFINS");
        jLabelIcmsEntrada.setText(" → ICMS ENTRADA");
        jLabelIcmsSaida.setText(" → ICMS SAÍDA");
        jLabelBaseInterage.setText("HISTÓRICO DE ATUALIZAÇÃO MIX-FISCAL");

        carregarListagem();

        jBtnAtualizar.setUI(new MetalButtonUI());
        jBtnSincronizar.setUI(new MetalButtonUI());
        contUpdate = 1;
        isExecute = false;
        TimerTask runnable = new TimerTask() {
            public void run() {
                try {
                    if (Sessao.mixfiscal) {
                        Date data = new Date();
                        Properties conf = a.carregarArquivo("Configuracao\\conf.cfg");
                        String hora1 = conf.getProperty("H1");
                        String hora2 = conf.getProperty("H2");
                        SimpleDateFormat formate = new SimpleDateFormat("HH:mm");
                        String dataH = formate.format(data);

                        Integer codfil = Integer.parseInt(conf.getProperty("FILIAL"));
                        filial = tabfilBusiness.findById(codfil);
                        System.out.println("Hora1 = " + hora1);
                        System.out.println("Hora2 = " + hora2);
                        System.out.println("DataH = " + dataH);
                        System.out.println("Executable = " + (isExecute ? "true" : "false"));
                        if ((dataH.equals(hora1.trim()) || dataH.equals(hora2.trim())) && !isExecute) {
                            isExecute = true;
                            jBtnSincronizar.doClick();
                        } else if ((contUpdate == infoHoras) && (((BigInteger) pisCofinsBusiness.count()).intValue() > 0 || ((BigInteger) icmsentradaBusiness.count()).intValue() > 0 || ((BigInteger) icmssaidaBusiness.count()).intValue() > 0)) {
                            jBtnAtualizar.doClick();
                            TrayIcon ic = Sessao.iconInformation;
                            if (ic != null) {
                                ic.displayMessage("Inter-Fiscal", "Atualização Disponivel ...", TrayIcon.MessageType.INFO);
                                contUpdate = 0;
                            }
                        } else if ((!dataH.equals(hora1.trim())) && (!dataH.equals(hora2.trim()))) {
                            isExecute = false;
                        }
                        contUpdate++;
                    }
                } catch (Exception ex) {
                    Log logApp = new Log();
                    StringWriter writer = new StringWriter();
                    PrintWriter pw = new PrintWriter(writer);
                    ex.printStackTrace(pw);
                    logApp.setError("Inter-Fiscal - " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + " → " + writer.toString());
                    a.carregarLog(logApp, 1);
                }

            }
        };
        Timer t = new Timer();
        t.schedule(runnable, 1000, 10000);

    }

    public void carregarListagem() {
        tableModelPisCofins = new piscofinsTableModel(pisCofinsBusiness.getSearchAll(0, "carregar-piscofins"));
        tableModelIcmsEntrada = new IcmsEntradaTableModel(icmsentradaBusiness.getSearchAll(0, "carregar-icmsentrada"));
        tableModelIcmsSaida = new IcmsSaidaTableModel(icmssaidaBusiness.getSearchAll(0, "carregar-icmssaida"));
        baseInterageTableModel = new BaseInterageTableModel(fiscalTempBusinessBean.getAllBaseInterage());

        carregarSizeList(((Number) pisCofinsBusiness.count()).intValue(), ((Number) icmsentradaBusiness.count()).intValue(), ((Number) icmssaidaBusiness.count()).intValue(), ((Number) fiscalTempBusinessBean.count()).intValue());

        jTablePisCofins.setModel(tableModelPisCofins);
        jTableIcmsEntrada.setModel(tableModelIcmsEntrada);
        jTableIcmsSaida.setModel(tableModelIcmsSaida);
        jTableBase.setModel(baseInterageTableModel);

        jTablePisCofins.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableIcmsEntrada.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableIcmsSaida.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableBase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        a.setColumnMinWidth(jTablePisCofins, 0, 200);
        a.setColumn(jTablePisCofins, 1, 75);
        a.setColumn(jTablePisCofins, 2, 85);
        a.setColumnMinWidth(jTableIcmsEntrada, 0, 200);
        a.setColumn(jTableIcmsEntrada, 1, 75);
        a.setColumn(jTableIcmsEntrada, 2, 85);
        a.setColumnMinWidth(jTableIcmsSaida, 0, 200);
        a.setColumn(jTableIcmsSaida, 1, 75);
        a.setColumn(jTableIcmsSaida, 2, 85);
        a.setColumnMinWidth(jTableBase, 0, 200);
        a.setColumn(jTableBase, 1, 75);
        a.setColumn(jTableBase, 2, 85);
        a.setColumn(jTableBase, 6, 135);
        a.setColumn(jTableBase, 7, 135);
        a.setColumn(jTableBase, 8, 135);

    }

    public void carregarSizeList(Integer PisCofins, Integer IcmsEntrada, Integer IcmsSaida, Integer BaseInterage) {
        jLabelPisCofins.setText(PisCofins != null ? (" → PIS / COFINS" + "(" + PisCofins + ")") : jLabelPisCofins.getText());
        jLabelIcmsEntrada.setText(IcmsEntrada != null ? (" → ICMS ENTRADA" + "(" + IcmsEntrada + ")") : jLabelIcmsEntrada.getText());
        jLabelIcmsSaida.setText(IcmsSaida != null ? (" → ICMS SAÍDA" + "(" + IcmsSaida + ")") : jLabelIcmsSaida.getText());
        jLabelBaseInterage.setText(BaseInterage != null ? ("HISTÓRICO DE ATUALIZAÇÃO MIX-FISCAL" + "(" + BaseInterage + ")") : jLabelBaseInterage.getText());
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
            jSeparator1 = new javax.swing.JSeparator();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTablePisCofins = new javax.swing.JTable();
            jScrollPane2 = new javax.swing.JScrollPane();
            jTableIcmsEntrada = new javax.swing.JTable();
            jScrollPane3 = new javax.swing.JScrollPane();
            jTableBase = new javax.swing.JTable();
            jScrollPane4 = new javax.swing.JScrollPane();
            jTableIcmsSaida = new javax.swing.JTable();
            jPanel2 = new javax.swing.JPanel();
            jLabelPisCofins = new javax.swing.JLabel();
            jBtnAtualizar = new javax.swing.JButton();
            jBtnSincronizar = new javax.swing.JButton();
            jPanel3 = new javax.swing.JPanel();
            jLabelIcmsEntrada = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            jLabelIcmsSaida = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jLabelBaseInterage = new javax.swing.JLabel();

            setClosable(true);
            setIconifiable(true);
            setMaximizable(true);
            setResizable(true);
            getContentPane().setLayout(new java.awt.CardLayout());

            jPanel1.setBackground(new java.awt.Color(204, 204, 204));
            jPanel1.setPreferredSize(new java.awt.Dimension(1200, 600));

            jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

            jTablePisCofins.setModel(new javax.swing.table.DefaultTableModel(
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
            jScrollPane1.setViewportView(jTablePisCofins);

            jTableIcmsEntrada.setModel(new javax.swing.table.DefaultTableModel(
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
            jScrollPane2.setViewportView(jTableIcmsEntrada);

            jTableBase.setModel(new javax.swing.table.DefaultTableModel(
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
            jScrollPane3.setViewportView(jTableBase);

            jTableIcmsSaida.setModel(new javax.swing.table.DefaultTableModel(
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
            jScrollPane4.setViewportView(jTableIcmsSaida);

            jPanel2.setBackground(new java.awt.Color(18, 18, 56));
            jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelPisCofins.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelPisCofins.setForeground(new java.awt.Color(255, 255, 255));
            jLabelPisCofins.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jLabelPisCofins.setText(" → PIS / COFINS");

            jBtnAtualizar.setBackground(new java.awt.Color(0, 51, 102));
            jBtnAtualizar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jBtnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
            jBtnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relod.png"))); // NOI18N
            jBtnAtualizar.setText("Atualizar");
            jBtnAtualizar.setToolTipText("Atualizar Sistema");
            jBtnAtualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jBtnAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnAtualizar.setFocusable(false);
            jBtnAtualizar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnAtualizarActionPerformed(evt);
                }
            });

            jBtnSincronizar.setBackground(new java.awt.Color(0, 51, 102));
            jBtnSincronizar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jBtnSincronizar.setForeground(new java.awt.Color(255, 255, 255));
            jBtnSincronizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/env.png"))); // NOI18N
            jBtnSincronizar.setText("Sincronizar");
            jBtnSincronizar.setToolTipText("Enviar todos os dados das tabelas");
            jBtnSincronizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jBtnSincronizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnSincronizar.setFocusable(false);
            jBtnSincronizar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnSincronizarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabelPisCofins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnSincronizar))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPisCofins, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnAtualizar)
                        .addComponent(jBtnSincronizar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel3.setBackground(new java.awt.Color(18, 18, 56));
            jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelIcmsEntrada.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelIcmsEntrada.setForeground(new java.awt.Color(255, 255, 255));
            jLabelIcmsEntrada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jLabelIcmsEntrada.setText(" → ICMS ENTRADA");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelIcmsEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelIcmsEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            jPanel4.setBackground(new java.awt.Color(18, 18, 56));
            jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelIcmsSaida.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelIcmsSaida.setForeground(new java.awt.Color(255, 255, 255));
            jLabelIcmsSaida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jLabelIcmsSaida.setText(" → ICMS SAÍDA");

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelIcmsSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelIcmsSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            jPanel5.setBackground(new java.awt.Color(18, 18, 56));
            jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelBaseInterage.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelBaseInterage.setForeground(new java.awt.Color(255, 255, 255));
            jLabelBaseInterage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelBaseInterage.setText("HISTÓRICO DE ATUALIZAÇÃO MIX-FISCAL");

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBaseInterage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBaseInterage, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(1, 1, 1))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                            .addGap(2, 2, 2)))
                    .addGap(7, 7, 7))
                .addComponent(jSeparator1)
            );

            getContentPane().add(jPanel1, "card2");

            pack();
        }// </editor-fold>//GEN-END:initComponents


    private void jBtnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAtualizarActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JDlgCarregando carregando = a.carregarJdialog("Carregando...");
                        carregando.setVisible(true);
                        carregando.loadBarra("Atualizando...", 0, 0, true);
                        carregarListagem();

                        Thread.sleep(1000);
                        carregando.loadBarra("Dados Atualizados com sucesso !!", 0, 0, true);
                        Thread.sleep(500);
                        carregando.setTexto("Finalizando Atualização...");
                        Thread.sleep(1000);
                        carregando.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
                        mensagem.setVisible(true);
                    }

                }
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);
        }
    }//GEN-LAST:event_jBtnAtualizarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        int result = JOptionPane.showConfirmDialog(null, "Deseja Realmente sair do sistema?", null, JOptionPane.YES_NO_OPTION);
//        if (result == JOptionPane.YES_OPTION) {
//            System.exit(0);
//        }

        this.dispose();

    }//GEN-LAST:event_formWindowClosing

    private void jBtnSincronizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSincronizarActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log logApp = new Log();
                    try {

                        JDlgCarregando carregando = a.carregarJdialog("Sincronizando...");
                        carregando.setVisible(true);
                        String texto = "Sincronizando Tabelas...";
                        carregando.loadBarra(texto, 0, 0, true);

                        List<Piscofins> resultPisCofins = pisCofinsBusiness.getAll("carregar-piscofins");
                        List<IcmsEntrada> resultIcmsEntrada = icmsentradaBusiness.getAll("carregar-icmsentrada");
                        List<IcmsSaida> resultIcmsSaida = icmssaidaBusiness.getAll("carregar-icmssaida");
                        String mensagem = "";
                        if (!resultPisCofins.isEmpty() || !resultIcmsEntrada.isEmpty() || !resultIcmsSaida.isEmpty()) {
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();

                            texto = "Iniciando Atualização da tabela Histórico...";
                            carregando.loadBarra(texto, 0, 0, true);
                            carregando.setTexto("Atualizando...");
                            Fiscaltemp f = null;

                            int cont = 1;
                            boolean isInsert;
                            Date inicioOperacao = new Date();

                            if (!resultPisCofins.isEmpty()) {
                                System.out.println("list " + resultPisCofins.size() + "log " + logApp);
                                logApp.setQtdPisConfins(resultPisCofins.size());

                                Thread.sleep(1200);
                                texto = "Preparando Tabela Temp - Pis/Cofins Mix Fiscal...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto("Carregando Tabela Temp - Pis/Cofins Mix Fiscal...");
                                Thread.sleep(1200);

                                for (Piscofins pc : resultPisCofins) {
                                    carregando.loadBarra("Pis/Cofins ", cont, resultPisCofins.size(), false);
                                    f = fiscalTempBusinessBean.findById(pc.getCodigoProduto().toString());
                                    isInsert = false;
                                    if (f == null) {
                                        f = new Fiscaltemp();
                                        isInsert = true;
                                    }
                                    f.setNomeProduto(pc.getNmProduto());
                                    f.setCodigoProduto(pc.getCodigoProduto().toString());
                                    f.setEan(pc.getEan() != null ? pc.getEan().toString() : "");
                                    f.setNcm(pc.getNcm());
                                    f.setCodNaturezaReceita(pc.getCodNaturezaReceita());

                                    f.setPisCstE(pc.getPisCstE());
                                    f.setPisCstS(pc.getPisCstS());
                                    f.setPisAlqE(pc.getPisAlqE());
                                    f.setPisAlqS(pc.getPisAlqS());

                                    f.setCofinsCstE(pc.getCofinsCstE());
                                    f.setCofinsCstS(pc.getCofinsCstS());
                                    f.setCofinsAlqE(pc.getCofinsAlqE());
                                    f.setCofinsAlqS(pc.getCofinsAlqS());

                                    f.setPisCofins(true);
                                    f.setAtualizacaoPiscofins(new Date());
                                    f.setDataRegistro(new Date());

                                    if (isInsert) {
                                        fiscalTempBusinessBean.insert(f);
                                    } else {
                                        fiscalTempBusinessBean.update(f);
                                    }

                                    texto = "Pis/Cofins =" + cont + " Registro = " + (isInsert ? "Inserido" : "Atualizado");
                                    carregando.setTexto(texto);
                                    cont++;

                                }
                            }

                            if (!resultIcmsEntrada.isEmpty()) {
                                logApp.setQtdIcmsEntrada(resultIcmsEntrada.size());

                                Thread.sleep(500);
                                texto = "Preparando Tabela Temp - Icms Entrada Mix Fiscal...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto("Carregando Tabela Temp - Icms Entrada Mix Fiscal...");
                                Thread.sleep(1200);

                                cont = 1;
                                for (IcmsEntrada ie : resultIcmsEntrada) {
                                    carregando.loadBarra("Icms Entrada ", cont, resultIcmsEntrada.size(), false);
                                    f = fiscalTempBusinessBean.findById(ie.getCodigoProduto().toString());
                                    isInsert = false;
                                    if (f == null) {
                                        f = new Fiscaltemp();
                                        isInsert = true;
                                    }
                                    f.setNomeProduto(ie.getNmProduto());
                                    f.setCodigoProduto(ie.getCodigoProduto().toString());
                                    f.setEan(ie.getEan() != null ? ie.getEan().toString() : "");
                                    f.setTipoMva(ie.getTipoMva());
                                    f.setMva(ie.getMva());
                                    f.setMvaDistribuidor(ie.getMvaDistribuidor());
                                    f.setMvaDataIni(ie.getMvaDataIni());
                                    f.setMvaDataFim(ie.getMvaDataFim());
                                    f.setCreditoOutorgado(ie.getCreditoOutorgado());
                                    f.setEiCst(ie.getEiCst());
                                    f.setEiAlq(ie.getEiAlq());
                                    f.setEiAlqst(ie.getEiAlqst());
                                    f.setEiRbc(ie.getEiRbc());
                                    f.setEiRbcst(ie.getEiRbcst());
                                    f.setEdCst(ie.getEdCst());
                                    f.setEdAlq(ie.getEdAlq());
                                    f.setEdAlqst(ie.getEdAlqst());
                                    f.setEdRbc(ie.getEdRbc());
                                    f.setEdRbcst(ie.getEdRbcst());
                                    f.setEsCst(ie.getEsCst());
                                    f.setEsAlq(ie.getEsAlq());
                                    f.setEsAlqst(ie.getEsRbc());
                                    f.setEsRbc(ie.getEsRbc());
                                    f.setEsRbcst(ie.getEsRbcst());
                                    f.setNfiCst(ie.getNfiCst());
                                    f.setNfdCst(ie.getNfdCst());
                                    f.setNfsCsosn(ie.getNfsCsosn());
                                    f.setNfAlq(ie.getNfAlq());
                                    f.setFundamentoLegal(ie.getFundamentoLegal());
                                    f.setIcmsEntrada(true);
                                    f.setAtualizacaoIcmsentrada(new Date());
                                    f.setDataRegistro(new Date());

                                    if (isInsert) {
                                        fiscalTempBusinessBean.insert(f);
                                    } else {
                                        fiscalTempBusinessBean.update(f);
                                    }

                                    texto = "Icms Entrada =" + cont + " Registro = " + (isInsert ? "Inserido" : "Atualizado");
                                    carregando.setTexto(texto);
                                    cont++;
                                }
                            }

                            if (!resultIcmsSaida.isEmpty()) {
                                logApp.setQtdIcmsSaida(resultIcmsSaida.size());

                                Thread.sleep(500);
                                texto = "Preparando Tabela Temp - Icms Saída Mix Fiscal...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto("Carregando Tabela Temp - Icms Saída Mix Fiscal...");
                                Thread.sleep(1200);

                                cont = 1;
                                for (IcmsSaida is : resultIcmsSaida) {
                                    carregando.loadBarra("Icms Saída ", cont, resultIcmsSaida.size(), false);
                                    f = fiscalTempBusinessBean.findById(is.getCodigoProduto().toString());
                                    isInsert = false;
                                    if (f == null) {
                                        f = new Fiscaltemp();
                                        isInsert = true;
                                    }
                                    f.setNomeProduto(is.getNmProduto());
                                    f.setCodigoProduto(is.getCodigoProduto().toString());
                                    f.setEan(is.getEan() != null ? is.getEan().toString().trim() : "");

                                    f.setCest(is.getCest());

                                    f.setSncCst(is.getSncCst());
                                    f.setSncAlq(is.getSncAlq());
                                    f.setSncAlqst(is.getSncAlqst());
                                    f.setSncRbc(is.getSncRbc());
                                    f.setSncRbcst(is.getSncRbcst());

                                    f.setSssCsosn(is.getSssCsosn());
                                    f.setSvcCsosn(is.getSvcCsosn());
                                    f.setSncCsosn(is.getSncCsosn());
                                    f.setFecp(is.getFecp());

                                    f.setIcmsSaida(true);
                                    f.setAtualizacaoIcmssaida(new Date());
                                    f.setDataRegistro(new Date());

                                    if (isInsert) {
                                        fiscalTempBusinessBean.insert(f);
                                    } else {
                                        fiscalTempBusinessBean.update(f);
                                    }

                                    texto = "Icms Saída =" + cont + " Registro = " + (isInsert ? "Inserido" : "Atualizado");
                                    carregando.setTexto(texto);
                                    cont++;

                                }
                            }

                            List<Fiscaltemp> resutlFinal = new ArrayList<>();

                            Thread.sleep(500);
                            texto = "Preparando Atualização do Banco de Dados da Interage ...";
                            carregando.loadBarra(texto, 0, 0, true);
                            carregando.setTexto("Carregando Base Interage...");
                            resutlFinal = fiscalTempBusinessBean.getCarregarFiscalTemp(inicioOperacao);
                            Thread.sleep(1000);

                            //**********************************************************
                            Integer size = resutlFinal.size();
                            int max = Sessao.qtdEnvio;
                            int calc = size / max;

                            if ((size % max) > 0) {
                                calc++;
                            }
                            int inserts = 0;
                            //**********************************************************
                            for (int p = 0; p < calc; p++) {
                                carregando.loadBarra("Carregando Tributação ", inserts, size, false);
                                List<Fiscaltemp> resultFiscalTempCalc = new ArrayList<>();
                                for (int e = inserts; e < (inserts + max); e++) {
                                    if (e >= size) {
                                        break;
                                    }
                                    Fiscaltemp i = resutlFinal.get(e);
                                    texto = "Base Interage= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                                    BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto());
                                    if (b == null) {
                                        b = new BaseInterage();
                                        b.setCodBarras(i.getEan());
                                        b.setCodProduto(i.getCodigoProduto());
                                        b.setNmProduto(i.getNomeProduto());
                                        b.setPisCofins(true);
                                        b.setAtualizacaoPisCofins(i.getAtualizacaoPiscofins());
                                        baseInterageTableModel.addItem(b);
                                    } else {
                                        b.setPisCofins(true);
                                        b.setAtualizacaoPisCofins(new Date());

                                    }
                                    jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                                    baseInterageTableModel.updateTableListener();
                                    resultFiscalTempCalc.add(i);
                                    carregando.setTexto(texto);
                                }

                                fiscalTempBusinessBean.getGerarTributos(resultFiscalTempCalc, filial);
                                inserts = inserts + max;
                                texto = "Atualizando Banco de Dados...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto(texto);
                            }

//                            tableModelPisCofins.setItems(null);
//                            tableModelIcmsEntrada.setItems(null);
//                            tableModelIcmsSaida.setItems(null);
//
//                            carregarSizeList(tableModelPisCofins.getRowCount(), tableModelIcmsEntrada.getRowCount(), tableModelIcmsSaida.getRowCount(), ((Number) fiscalTempBusinessBean.count()).intValue());
//
//                            tableModelPisCofins.updateTableListener();
//                            tableModelIcmsEntrada.updateTableListener();
//                            tableModelIcmsSaida.updateTableListener();
                            pisCofinsBusiness.deleteAll();
                            icmsentradaBusiness.deleteAll();
                            icmssaidaBusiness.deleteAll();

                            carregarListagem();

                            //*********************************************************
                            mensagem = "Dados Enviados com sucesso !!";
                        } else {
                            mensagem = "Nenhuma Atualização Disponível no momento !!";
                        }

                        Thread.sleep(1000);
                        carregando.loadBarra(mensagem, 0, 0, true);
                        Thread.sleep(500);
                        carregando.setTexto("Finalizando Sincronização ...");
                        Thread.sleep(1000);

                        a.carregarLog(logApp, 1);
                        carregando.dispose();

                    } catch (Exception ex) {
                        StringWriter writer = new StringWriter();
                        PrintWriter pw = new PrintWriter(writer);
                        ex.printStackTrace(pw);
                        logApp.setError("Inter-Fiscal - " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + " → " + writer.toString());
                        a.carregarLog(logApp, 1);
                        JDlgMensagem m = new JDlgMensagem(jfrmPrincipal, true, ex);
                        m.setVisible(true);
                    }
                }
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);

        }


    }//GEN-LAST:event_jBtnSincronizarActionPerformed


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(MixFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MixFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MixFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MixFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MixFiscal(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAtualizar;
    private javax.swing.JButton jBtnSincronizar;
    private javax.swing.JLabel jLabelBaseInterage;
    private javax.swing.JLabel jLabelIcmsEntrada;
    private javax.swing.JLabel jLabelIcmsSaida;
    private javax.swing.JLabel jLabelPisCofins;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableBase;
    private javax.swing.JTable jTableIcmsEntrada;
    private javax.swing.JTable jTableIcmsSaida;
    private javax.swing.JTable jTablePisCofins;
    // End of variables declaration//GEN-END:variables
}
