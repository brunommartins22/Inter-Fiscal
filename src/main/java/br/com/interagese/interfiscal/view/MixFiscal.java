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
import br.com.interagese.interfiscal.business.FireTabfilBusinessBean;
import br.com.interagese.interfiscal.business.TabproBusiness;
import br.com.interagese.interfiscal.business.TabproBusinessBean;
import br.com.interagese.interfiscal.entity.AtualizacaoFiscalTemp;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.ImpTemp;
import br.com.interagese.interfiscal.entity.Log;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.entity.Sessao;
import br.com.interagese.interfiscal.entity.Tabfil;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.table.BaseInterageTableModel;
import br.com.interagese.interfiscal.table.IcmsEntradaTableModel;
import br.com.interagese.interfiscal.table.IcmsSaidaTableModel;
import br.com.interagese.interfiscal.table.piscofinsTableModel;
import br.com.interagese.interfiscal.utils.Actions;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TrayIcon;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.plaf.metal.MetalButtonUI;
import br.com.interagese.interfiscal.business.FireTabfilBusiness;
import br.com.interagese.interfiscal.business.TabNcmBusiness;
import br.com.interagese.interfiscal.business.TabNcmBusinessBean;

/**
 *
 * @author bruno
 */
public class MixFiscal extends JInternalFrame {
    
    private static JFrame jfrmPrincipal;
    private final Actions a;
    private final PisCofinsBusiness pisCofinsBusiness;
    private final IcmsentradaBusiness icmsentradaBusiness;
    private final IcmssaidaBusiness icmssaidaBusiness;
    private final FiscalTempBusinessBean fiscalTempBusinessBean;
    private final TabproBusiness tabproBusiness;
    private piscofinsTableModel tableModelPisCofins;
    private IcmsEntradaTableModel tableModelIcmsEntrada;
    private IcmsSaidaTableModel tableModelIcmsSaida;
    private BaseInterageTableModel baseInterageTableModel;
    private final FireTabproBusiness fireTabproBusiness;
    private final FireTabfilBusiness tabfilBusiness;
    private final TabNcmBusiness tabNcmBusiness;
    private Integer contUpdate;
    private Integer infoHoras;

    /**
     * Creates new form JFrmPrincipal
     *
     * @param pai
     */
    public MixFiscal(javax.swing.JFrame pai) {
        jfrmPrincipal = pai;
        this.tabfilBusiness = new FireTabfilBusinessBean();
        this.fireTabproBusiness = new FireTabproBusinessBean();
        this.tabproBusiness = new TabproBusinessBean();
        this.fiscalTempBusinessBean = new FiscalTempBusinessBean();
        this.icmssaidaBusiness = new IcmssaidaBusinessBean();
        this.icmsentradaBusiness = new IcmsentradaBusinessBean();
        this.pisCofinsBusiness = new PisCofinsBusinessBean();
        this.tabNcmBusiness = new TabNcmBusinessBean();
        this.a = new Actions(jfrmPrincipal);
        
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
        if (Sessao.manutencao.equals("A")) {
            infoHoras = Sessao.infoHoras * 3600;
            TimerTask runnable = new TimerTask() {
                public void run() {
                    try {
                        if (Sessao.mixfiscal) {
                            infoHoras = (Sessao.infoHoras * 3600);
                            Date data = new Date();
                            Properties conf = a.carregarArquivo("Configuracao\\conf.cfg");
                            String hora1 = conf.getProperty("H1");
                            String hora2 = conf.getProperty("H2");
                            SimpleDateFormat formate = new SimpleDateFormat("HH:mm");
                            String dataH = formate.format(data);

//                            Integer codfil = Integer.parseInt(conf.getProperty("FILIAL"));
//                            filial = tabfilBusiness.findById(codfil);
                            if ((dataH.equals(hora1.trim()) || dataH.equals(hora2.trim())) && !Sessao.isExecuting) {
                                Sessao.isExecuting = true;
                                jBtnSincronizar.doClick();
                            } else if ((Objects.equals(contUpdate, infoHoras)) && (((BigInteger) pisCofinsBusiness.count()).intValue() > 0 || ((BigInteger) icmsentradaBusiness.count()).intValue() > 0 || ((BigInteger) icmssaidaBusiness.count()).intValue() > 0)) {
                                jBtnAtualizar.doClick();
                                TrayIcon ic = Sessao.iconInformation;
                                if (ic != null) {
                                    ic.displayMessage("Inter-Fiscal", "Atualização Disponivel ...", TrayIcon.MessageType.INFO);
                                    contUpdate = 0;
                                }
                            } else if ((!dataH.equals(hora1.trim())) && (!dataH.equals(hora2.trim()))) {
                                Sessao.isExecuting = false;
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
    }
    
    public void carregarListagem() {
        tableModelPisCofins = new piscofinsTableModel(pisCofinsBusiness.getSearchAll(0, "carregar-piscofins"));
        tableModelIcmsEntrada = new IcmsEntradaTableModel(icmsentradaBusiness.getSearchAll(0, "carregar-icmsentrada"));
        tableModelIcmsSaida = new IcmsSaidaTableModel(icmssaidaBusiness.getSearchAll(0, "carregar-icmssaida"));
        baseInterageTableModel = new BaseInterageTableModel(fiscalTempBusinessBean.getSearchAllBaseInterage(0, null));
        
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
            setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
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
                        JDlgCarregando carregando = a.carregarJdialog("Carregando...", jfrmPrincipal);
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
        

    }//GEN-LAST:event_formWindowClosing

    private void jBtnSincronizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSincronizarActionPerformed
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                JDlgCarregando carregando = null;
                Log logApp = null;
                try {
                    
                    carregando = a.carregarJdialog("Sincronizando...", jfrmPrincipal);
                    carregando.setVisible(true);
                    String texto = "Sincronizando Tabelas...";
                    carregando.loadBarra(texto, 0, 0, true);
                    
                    List<Piscofins> resultPisCofins = pisCofinsBusiness.getAll("carregar-piscofins");
                    List<IcmsEntrada> resultIcmsEntrada = icmsentradaBusiness.getAll("carregar-icmsentrada");
                    List<IcmsSaida> resultIcmsSaida = icmssaidaBusiness.getAll("carregar-icmssaida");
                    
                    String mensagem = "";
                    logApp = new Log();
                    if (!resultPisCofins.isEmpty() || !resultIcmsEntrada.isEmpty() || !resultIcmsSaida.isEmpty()) {
                        
                        jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                        baseInterageTableModel.updateTableListener();

                        //******************* filial ***********************
                        String codFilial = "";
                        String regime = "";
                        for (int i = 0; i < Sessao.resultFilial.size(); i++) {
                            Tabfil filial = Sessao.resultFilial.get(i);
                            codFilial += filial.getCodfil() + ((Sessao.resultFilial.size() > 1) ? "," : "");
                            regime = filial.getCrt().toString();
                        }

                        //********************** calc ********************** 
                        Integer size = 0;
                        int max = Sessao.qtdEnvio;
                        int calc = 0;
                        
                        int inserts = 0;
                        //***************************************************
                        texto = "Iniciando Atualização da tabela Histórico...";
                        carregando.loadBarra(texto, 0, 0, true);
                        carregando.setTexto("Atualizando...");
                        Fiscaltemp f = null;
                        
                        int cont = 1;
                        boolean isInsert;
                        
                        Date inicioOperacao = new Date();
                        
                        List<Fiscaltemp> resultFiscaltempCalcInsert = null;
                        List<Fiscaltemp> resultFiscaltempCalcUpdate = null;
                        
                        if (!resultPisCofins.isEmpty()) {
                            
                            System.out.println("list " + resultPisCofins.size() + "log " + logApp);
                            logApp.setQtdPisConfins(resultPisCofins.size());
                            
                            Thread.sleep(1200);
                            texto = "Preparando Tabela Temp - Pis/Cofins Mix Fiscal...";
                            carregando.loadBarra(texto, 0, 0, true);
                            carregando.setTexto("Carregando Tabela Temp - Pis/Cofins Mix Fiscal...");
                            Thread.sleep(1200);
                            
                            size = resultPisCofins.size();
                            calc = size / max;
                            if ((size % max) > 0) {
                                calc++;
                            }
                            inserts = 0;
                            for (int p = 0; p < calc; p++) {
                                carregando.loadBarra("Pis/Cofins", inserts, size, false);
                                resultFiscaltempCalcInsert = new ArrayList<>();
                                resultFiscaltempCalcUpdate = new ArrayList<>();
                                for (int e = inserts; e < (inserts + max); e++) {
                                    if (e >= size) {
                                        break;
                                    }
                                    Piscofins pc = resultPisCofins.get(e);
                                    
                                    f = fiscalTempBusinessBean.findById(pc.getCodigoProduto().toString());
                                    isInsert = false;
                                    if (f == null) {
                                        f = new Fiscaltemp();
                                        isInsert = true;
                                    }
                                    f.setNomeProduto(pc.getNmProduto());
                                    f.setCodigoFilial(codFilial);
                                    f.setCodigoCrt(regime);
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
                                    f.setAtualizacaoPiscofins(inicioOperacao);
                                    f.setDataRegistro(inicioOperacao);
                                    
                                    if (isInsert) {
                                        resultFiscaltempCalcInsert.add(f);
                                    } else {
                                        resultFiscaltempCalcUpdate.add(f);
                                    }
                                    cont++;
                                }
                                
                                inserts = inserts + max;
                                texto = "Atualizando Pis/Cofins no Banco de Dados...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto("Pis/Cofins -> Registrando Lote de " + max + " Registro(s)");
                                if (!resultFiscaltempCalcInsert.isEmpty()) {
                                    fiscalTempBusinessBean.insertList(resultFiscaltempCalcInsert);
                                }
                                if (!resultFiscaltempCalcUpdate.isEmpty()) {
                                    fiscalTempBusinessBean.updateList(resultFiscaltempCalcUpdate);
                                }
                            }
                        }
                        
                        if (!resultIcmsEntrada.isEmpty()) {
                            logApp.setQtdIcmsEntrada(resultIcmsEntrada.size());
                            carregando.limparBufferText();
                            Thread.sleep(500);
                            texto = "Preparando Tabela Temp - Icms Entrada Mix Fiscal...";
                            carregando.loadBarra(texto, 0, 0, true);
                            carregando.setTexto("Carregando Tabela Temp - Icms Entrada Mix Fiscal...");
                            Thread.sleep(1200);
                            
                            cont = 1;
                            size = resultIcmsEntrada.size();
                            calc = size / max;
                            if ((size % max) > 0) {
                                calc++;
                            }
                            inserts = 0;
                            for (int p = 0; p < calc; p++) {
                                carregando.loadBarra("Icms Entrada", inserts, size, false);
                                resultFiscaltempCalcInsert = new ArrayList<>();
                                resultFiscaltempCalcUpdate = new ArrayList<>();
                                for (int e = inserts; e < (inserts + max); e++) {
                                    if (e >= size) {
                                        break;
                                    }
                                    IcmsEntrada ie = resultIcmsEntrada.get(e);
                                    
                                    f = fiscalTempBusinessBean.findById(ie.getCodigoProduto().toString());
                                    isInsert = false;
                                    if (f == null) {
                                        f = new Fiscaltemp();
                                        isInsert = true;
                                    }
                                    f.setNomeProduto(ie.getNmProduto());
                                    f.setCodigoFilial(codFilial);
                                    f.setCodigoCrt(regime);
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
                                    f.setAtualizacaoIcmsentrada(inicioOperacao);
                                    f.setDataRegistro(inicioOperacao);
                                    
                                    if (isInsert) {
                                        resultFiscaltempCalcInsert.add(f);
                                    } else {
                                        resultFiscaltempCalcUpdate.add(f);
                                    }
                                    
                                    cont++;
                                }
                                inserts = inserts + max;
                                texto = "Atualizando Icms Entrada no Banco de Dados...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto("Icms Entrada -> Registrando Lote de " + max + " Registro(s)");
                                if (!resultFiscaltempCalcInsert.isEmpty()) {
                                    fiscalTempBusinessBean.insertList(resultFiscaltempCalcInsert);
                                }
                                if (!resultFiscaltempCalcUpdate.isEmpty()) {
                                    fiscalTempBusinessBean.updateList(resultFiscaltempCalcUpdate);
                                }
                            }
                        }
                        
                        if (!resultIcmsSaida.isEmpty()) {
                            logApp.setQtdIcmsSaida(resultIcmsSaida.size());
                            carregando.limparBufferText();
                            Thread.sleep(500);
                            texto = "Preparando Tabela Temp - Icms Saída Mix Fiscal...";
                            carregando.loadBarra(texto, 0, 0, true);
                            carregando.setTexto("Carregando Tabela Temp - Icms Saída Mix Fiscal...");
                            Thread.sleep(1200);
                            
                            cont = 1;
                            size = resultIcmsSaida.size();
                            calc = size / max;
                            if ((size % max) > 0) {
                                calc++;
                            }
                            inserts = 0;
                            for (int p = 0; p < calc; p++) {
                                carregando.loadBarra("Icms Saída", inserts, size, false);
                                resultFiscaltempCalcInsert = new ArrayList<>();
                                resultFiscaltempCalcUpdate = new ArrayList<>();
                                for (int e = inserts; e < (inserts + max); e++) {
                                    if (e >= size) {
                                        break;
                                    }
                                    
                                    IcmsSaida is = resultIcmsSaida.get(e);
                                    
                                    f = fiscalTempBusinessBean.findById(is.getCodigoProduto().toString());
                                    isInsert = false;
                                    if (f == null) {
                                        f = new Fiscaltemp();
                                        isInsert = true;
                                    }
                                    f.setNomeProduto(is.getNmProduto());
                                    f.setCodigoFilial(codFilial);
                                    f.setCodigoCrt(regime);
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
                                    f.setAtualizacaoIcmssaida(inicioOperacao);
                                    f.setDataRegistro(inicioOperacao);
                                    
                                    if (isInsert) {
                                        resultFiscaltempCalcInsert.add(f);
                                    } else {
                                        resultFiscaltempCalcUpdate.add(f);
                                    }
                                    cont++;
                                }
                                inserts = inserts + max;
                                texto = "Atualizando Icms Saída no Banco de Dados...";
                                carregando.loadBarra(texto, 0, 0, true);
                                carregando.setTexto("Icms Saída -> Registrando Lote de " + max + " Registro(s)");
                                if (!resultFiscaltempCalcInsert.isEmpty()) {
                                    fiscalTempBusinessBean.insertList(resultFiscaltempCalcInsert);
                                }
                                if (!resultFiscaltempCalcUpdate.isEmpty()) {
                                    fiscalTempBusinessBean.updateList(resultFiscaltempCalcUpdate);
                                }
                            }
                        }
                        
                        carregando.limparBufferText();
                        Thread.sleep(500);
                        texto = "Preparando Atualização do Banco de Dados da Interage ...";
                        carregando.loadBarra(texto, 0, 0, true);
                        carregando.setTexto("Carregando Base Interage...");
                        List<Fiscaltemp> resutlFinal = fiscalTempBusinessBean.getCarregarFiscalTemp(inicioOperacao);
                        baseInterageTableModel.addItens(null);
                        Thread.sleep(1000);

                        //**********************************************************
                        size = resutlFinal.size();
                        calc = size / max;
                        
                        if ((size % max) > 0) {
                            calc++;
                        }
                        inserts = 0;
                        cont = 0;
                        //**********************************************************
                        for (int p = 0; p < calc; p++) {
                            
                            carregando.loadBarra("Carregando Tributação", inserts, size, false);
                            List<String> listString = new ArrayList<>();
                            for (int e = inserts; e < (inserts + max); e++) {
                                if (e >= size) {
                                    break;
                                }
                                f = resutlFinal.get(e);
                                texto = f.getCodigoProduto() + " - " + f.getNomeProduto();
                                carregando.setTexto(texto);
                                
                                for (Tabpro produto : fiscalTempBusinessBean.getListTabproByCod(Integer.parseInt(f.getCodigoProduto()))) {
                                    AtualizacaoFiscalTemp aTemp = new AtualizacaoFiscalTemp();
                                    
                                    aTemp.setCodpro(produto.getCodpro());
                                    aTemp.setCodfil(codFilial);

                                    //******** TABPRO **********************************************
                                    //********************** Pis Cofins ****************************
                                    aTemp.setClasFiscal(f.getNcm() != null && !f.getNcm().equals("") ? f.getNcm() : produto.getClasfiscal() == null ? "" : produto.getClasfiscal());
                                    //produto.setCodbarun(f.getEan() != null ? f.getEan() : null);
                                    aTemp.setFatorPis(f.getPisAlqS() == null || f.getPisAlqS() == 0.0 ? (produto.getFatorpis() == null ? 0.0 : produto.getFatorpis()) : f.getPisAlqS());
                                    aTemp.setFatorCofins(f.getCofinsAlqS() == null || f.getCofinsAlqS() == 0.0 ? (produto.getFatorcofins() == null ? 0.0 : produto.getFatorcofins()) : f.getCofinsAlqS());
                                    aTemp.setCstPis(f.getPisCstS() == null || f.getPisCstS().equals("") ? (produto.getCstpis() == null ? "" : produto.getCstpis()) : f.getPisCstS());
                                    aTemp.setCstCofins(f.getCofinsCstS() == null || f.getCofinsCstS().equals("") ? (produto.getCstcofins() == null ? "" : produto.getCstcofins()) : f.getCofinsCstS());

                                    //********************** Icms Saída ****************************
                                    aTemp.setIcmsSimples(f.getSncAlq() == null || f.getSncAlq() == 0.0 ? (produto.getIcms() == null ? 0.0 : produto.getIcms()) : f.getSncAlq());
                                    aTemp.setCest((f.getCest() != null && !f.getCest().equals("")) ? f.getCest().replace(".", "") : (produto.getCest() == null ? "" : produto.getCest()));
                                    aTemp.setCst(regime.equals("1") ? (f.getSncCsosn() == null || f.getSncCsosn().equals("0.0") || f.getSncCsosn().equals("") ? (produto.getCst() == null ? "" : produto.getCst()) : f.getSncCsosn()) : (f.getSncCst() == null || f.getSncCst().equals("0.0") || f.getSncCst().equals("") ? (produto.getCst() == null ? "" : produto.getCst()) : f.getSncCst()));

                                    //********************** Update RgData *************************
                                    aTemp.setRgdata(inicioOperacao);

                                    //************** Gerando Tipo Trib and ModBc *******************
                                    String tipoTrib = "";
                                    String modBc = "";
                                    
                                    if (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) {
                                        
                                        String valueTrib = regime.equals("1") ? f.getSncCsosn() : f.getSncCst();

                                        //************************* Indice *************************
                                        switch (valueTrib) {
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
                                        
                                        aTemp.setIndice(tipoTrib);

                                        //************************* IcmsModBc **********************
                                        switch (valueTrib) {
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
                                    }
                                    //****** TABPROFIL *********************************************
                                    if (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins()) {
                                        //********************* Pis/Cofins *********************
                                        aTemp.setNatRec(f.getCodNaturezaReceita() == null ? "" : f.getCodNaturezaReceita());
                                    }
                                    //****** TABPROIMP *****************************************
                                    List<ImpTemp> resultImps = fiscalTempBusinessBean.getExistTabproimp(aTemp.getCodpro());
                                    for (ImpTemp imps : resultImps) {
                                        if (imps.getResultTpImpos().equals("") && (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) && (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins())) {
                                            imps.setIsInsertTemp(true);
                                            imps.setIsUpdateTemp(false);
                                        } else if ((f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) || (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins())) {
                                            int count = imps.getResultTpImpos().split(",").length;
                                            if (count == 2) {
                                                imps.setIsInsertTemp(false);
                                                imps.setIsUpdateTemp(true);
                                            } else {
                                                imps.setIsInsertTemp(true);
                                                imps.setIsUpdateTemp(true);
                                                imps.setTpImpos(imps.getResultTpImpos().split(",")[0].equals("D") ? "A" : "D");
                                            }
                                            
                                            imps.setTpUpdate("");
                                            //****************** Pis/cofins ********************
                                            if (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins()) {
                                                imps.setTpUpdate("P");
                                            }
                                            //****************** Icms Saída ********************
                                            if (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) {
                                                imps.setTpUpdate(!aTemp.equals("") ? "PI" : "I");
                                            }
                                            
                                        }
                                        aTemp.getResultImpTemp().add(imps);
                                    }

                                    //****************** Pis/cofins ********************
                                    aTemp.setPisCst(f.getPisCstS() == null ? "" : f.getPisCstS());
                                    aTemp.setPispPis(f.getPisAlqS() == null ? 0.0 : f.getPisAlqS());
                                    aTemp.setCofinsCst(f.getCofinsCstS() == null ? "" : f.getCofinsCstS());
                                    aTemp.setCofinspCofins(f.getCofinsAlqS() == null ? 0.0 : f.getCofinsAlqS());
                                    //****************** Icms Saída ********************
                                    aTemp.setIcmspIcmsSt(f.getSncAlqst() == null ? 0.0 : f.getSncAlqst());
                                    aTemp.setIcmspRedBc(f.getSncRbc() == null ? 0.0 : f.getSncRbc());
                                    aTemp.setIcmspRedBcSt(f.getSncRbcst() == null ? 0.0 : f.getSncRbcst());
                                    aTemp.setIcmsModBc(modBc);

                                    //*** TABPROIMPE *******************************************
                                    if (f.getIcmsEntrada() != null && !f.getIcmsEntrada().equals("") && f.getIcmsEntrada()) {
                                        resultImps = fiscalTempBusinessBean.getExistTabproimpE(aTemp.getCodpro());
                                        for (ImpTemp imp : resultImps) {
                                            if (imp.getValorImpe() == 0) {
                                                imp.setIsInsertTemp(true);
                                            } else {
                                                imp.setIsInsertTemp(false);
                                            }
                                            aTemp.getResultImpe().add(imp);
                                        }
                                        
                                        aTemp.setEan(f.getEan());
                                        aTemp.setTipoMva(f.getTipoMva());
                                        aTemp.setMva(f.getMva());
                                        aTemp.setMvaDistribuidor(f.getMvaDistribuidor());
                                        aTemp.setMvaDataIni(f.getMvaDataIni());
                                        aTemp.setMvaDataFim(f.getMvaDataFim());
                                        aTemp.setCreditoOutorgado(f.getCreditoOutorgado());
                                        aTemp.setEiCst(f.getEiCst());
                                        aTemp.setEiAlq(f.getEiAlq());
                                        aTemp.setEiAlqst(f.getEiAlqst());
                                        aTemp.setEiRbc(f.getEiRbc());
                                        aTemp.setEiRbcst(f.getEiRbcst());
                                        aTemp.setEdCst(f.getEdCst());
                                        aTemp.setEdAlq(f.getEdAlq());
                                        aTemp.setEdAlqst(f.getEdAlqst());
                                        aTemp.setEdRbc(f.getEdRbc());
                                        aTemp.setEdRbcst(f.getEdRbcst());
                                        aTemp.setEsCst(f.getEsCst());
                                        aTemp.setEsAlq(f.getEsAlq());
                                        aTemp.setEsAlqst(f.getEsAlqst());
                                        aTemp.setEsRbc(f.getEsRbc());
                                        aTemp.setEsRbcst(f.getEsRbcst());
                                        aTemp.setNfiCst(f.getNfiCst());
                                        aTemp.setNfdCst(f.getNfdCst());
                                        aTemp.setNfsCsosn(f.getNfsCsosn());
                                        aTemp.setNfAlq(f.getNfAlq());
                                        aTemp.setFundamentoLegal(f.getFundamentoLegal());
                                        
                                    }
                                    
                                    if ((f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins()) || (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida())) {
                                        
                                        if (tabNcmBusiness.findById(aTemp.getClasFiscal()) == null) {
                                            aTemp.setIsExistRegraNcm(false);
                                        } else {
                                            aTemp.setIsExistRegraNcm(true);
                                        }

                                        //****************** Tabncm ******************
                                        listString.add(aTemp.getExecuteRegraNcm());

                                        //****************** Tabpro ******************
                                        listString.add(aTemp.getExecuteTabpro());

                                        //****************** Tabprofil ***************
                                        listString.add(aTemp.getExecuteTabprofil());

                                        //****************** Tabproimp ***************
                                        for (String item : aTemp.getExecuteTabproimp()) {
                                            listString.add(item);
                                        }
                                        
                                    }
                                    if (f.getIcmsEntrada() != null && !f.getIcmsEntrada().equals("") && f.getIcmsEntrada()) {
                                        //****************** Täbproimpe ***************
                                        for (String item : aTemp.getExecuteTabproimpe()) {
                                            listString.add(item);
                                        }
                                    }
                                    cont++;
                                    if (cont == 500) {
                                        cont = 0;
                                        carregando.limparBufferText();
                                    }
                                    
                                }
                                
                            }
                            inserts = inserts + max;
                            texto = "Atualizando Banco de Dados...";
                            carregando.loadBarra(texto, 0, 0, true);
                            carregando.setTexto("Registrando...");
                            fireTabproBusiness.executeUpdate(listString);
                            tabproBusiness.executeUpdate(listString);
                        }
                        
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
                    
                    carregando.dispose();
                    
                } catch (Exception ex) {
                    carregando.limparBufferText();
                    carregando.dispose();
                    ex.printStackTrace();
                    StringWriter writer = new StringWriter();
                    PrintWriter pw = new PrintWriter(writer);
                    ex.printStackTrace(pw);
                    logApp.setError("Inter-Fiscal - " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + " → " + writer.toString());
                    JDlgMensagem m = new JDlgMensagem(jfrmPrincipal, true, ex);
                    m.setVisible(true);
                }
                a.carregarLog(logApp, 1);
            }
        }
        ).start();
        

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
