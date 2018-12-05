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
import br.com.interagese.interfiscal.business.TabproBusiness;
import br.com.interagese.interfiscal.business.TabproBusinessBean;
import br.com.interagese.interfiscal.entity.BaseInterage;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Log;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.message.OptionPane;
import br.com.interagese.interfiscal.table.BaseInterageTableModel;
import br.com.interagese.interfiscal.table.IcmsEntradaTableModel;
import br.com.interagese.interfiscal.table.IcmsSaidaTableModel;
import br.com.interagese.interfiscal.table.piscofinsTableModel;
import br.com.interagese.interfiscal.utils.Actions;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
    private Actions a = new Actions();
    private PisCofinsBusiness pisCofinsBusiness = new PisCofinsBusinessBean();
    private IcmsentradaBusiness icmsentradaBusiness = new IcmsentradaBusinessBean();
    private IcmssaidaBusiness icmssaidaBusiness = new IcmssaidaBusinessBean();
    private FiscalTempBusinessBean fiscalTempBusinessBean = new FiscalTempBusinessBean();
    private TabproBusiness tabproBusiness = new TabproBusinessBean();
    private piscofinsTableModel tableModelPisCofins;
    private IcmsEntradaTableModel tableModelIcmsEntrada;
    private IcmsSaidaTableModel tableModelIcmsSaida;
    private BaseInterageTableModel baseInterageTableModel;
    private Log logApp = new Log();
    private FireTabproBusiness fireTabproBusiness = new FireTabproBusinessBean();

    /**
     * Creates new form JFrmPrincipal
     */
    public MixFiscal(JFrame pai) {
        jfrmPrincipal = pai;
        initComponents();
        definirFormulario();

    }

    public void definirFormulario() {
        this.setTitle("Mix Fiscal");
        a.iconApplicationInternalFrame(this);

        jLabelPisCofins.setText("PIS/COFINS");
        jLabelIcmsEntrada.setText("ICMS ENTRADA");
        jLabelIcmsSaida.setText("ICMS SAÍDA");
        jLabelBaseInterage.setText("BASE INTERAGE");

        carregarListagem();

        jBtnPisCofins.setUI(new MetalButtonUI());
        jBtnIcmsEntrada.setUI(new MetalButtonUI());
        jBtnccmsSaida.setUI(new MetalButtonUI());
        jBtnAtualizar.setUI(new MetalButtonUI());
        jBtnSincronizar.setUI(new MetalButtonUI());

        TimerTask runnable = new TimerTask() {
            public void run() {
                try {
                    System.out.println("Entrou");
                    Date data = new Date();
                    Properties conf = a.carregarArquivo("C:\\InterageSe\\inter-fiscal\\Configuracao\\conf.cfg");
                    String hora1 = conf.getProperty("H1");
                    System.out.println("hora1 = " + hora1);
                    String hora2 = conf.getProperty("H2");
                    System.out.println("hora2 = " + hora2);
                    SimpleDateFormat formate = new SimpleDateFormat("HH:mm");
                    String dataH = formate.format(data);
                    System.out.println("DataH = " + dataH);
                    if (dataH.equals(hora1.trim()) || dataH.equals(hora2.trim())) {
                        jBtnSincronizar.doClick();
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        Timer t = new Timer();
        t.schedule(runnable, 1000, 60000);

    }

    public void carregarListagem() {
        tableModelPisCofins = new piscofinsTableModel(pisCofinsBusiness.getSearchAll(0, "carregar-piscofins"));
        tableModelIcmsEntrada = new IcmsEntradaTableModel(icmsentradaBusiness.getSearchAll(0, "carregar-icmsentrada"));
        tableModelIcmsSaida = new IcmsSaidaTableModel(icmssaidaBusiness.getSearchAll(0, "carregar-icmssaida"));
        baseInterageTableModel = new BaseInterageTableModel(fiscalTempBusinessBean.getSearchAllBaseInterage(0, new ArrayList<>()));

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
        jLabelPisCofins.setText(PisCofins != null ? ("PIS/COFINS" + "(" + PisCofins + ")") : jLabelPisCofins.getText());
        jLabelIcmsEntrada.setText(IcmsEntrada != null ? ("ICMS ENTRADA" + "(" + IcmsEntrada + ")") : jLabelIcmsEntrada.getText());
        jLabelIcmsSaida.setText(IcmsSaida != null ? ("ICMS SAÍDA" + "(" + IcmsSaida + ")") : jLabelIcmsSaida.getText());
        jLabelBaseInterage.setText(BaseInterage != null ? ("BASE INTERAGE" + "(" + BaseInterage + ")") : jLabelBaseInterage.getText());
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
            jBtnPisCofins = new javax.swing.JButton();
            jPanel3 = new javax.swing.JPanel();
            jLabelIcmsEntrada = new javax.swing.JLabel();
            jBtnIcmsEntrada = new javax.swing.JButton();
            jPanel4 = new javax.swing.JPanel();
            jLabelIcmsSaida = new javax.swing.JLabel();
            jBtnccmsSaida = new javax.swing.JButton();
            jPanel5 = new javax.swing.JPanel();
            jLabelBaseInterage = new javax.swing.JLabel();
            jBtnSincronizar = new javax.swing.JButton();
            jBtnAtualizar = new javax.swing.JButton();

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
            jLabelPisCofins.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelPisCofins.setText(" PIS / COFINS");

            jBtnPisCofins.setBackground(new java.awt.Color(0, 51, 102));
            jBtnPisCofins.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jBtnPisCofins.setForeground(new java.awt.Color(255, 255, 255));
            jBtnPisCofins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/env.png"))); // NOI18N
            jBtnPisCofins.setText("Enviar");
            jBtnPisCofins.setToolTipText("Enviar dados da Tabela Pis/Cofins");
            jBtnPisCofins.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jBtnPisCofins.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnPisCofins.setFocusable(false);
            jBtnPisCofins.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnPisCofinsActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabelPisCofins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnPisCofins, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPisCofins)
                    .addComponent(jBtnPisCofins))
            );

            jPanel3.setBackground(new java.awt.Color(18, 18, 56));
            jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelIcmsEntrada.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelIcmsEntrada.setForeground(new java.awt.Color(255, 255, 255));
            jLabelIcmsEntrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelIcmsEntrada.setText("ICMS ENTRADA");

            jBtnIcmsEntrada.setBackground(new java.awt.Color(0, 51, 102));
            jBtnIcmsEntrada.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jBtnIcmsEntrada.setForeground(new java.awt.Color(255, 255, 255));
            jBtnIcmsEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/env.png"))); // NOI18N
            jBtnIcmsEntrada.setText("Enviar");
            jBtnIcmsEntrada.setToolTipText("Enviar dados da tabela Icms Entrada");
            jBtnIcmsEntrada.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jBtnIcmsEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnIcmsEntrada.setFocusable(false);
            jBtnIcmsEntrada.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnIcmsEntradaActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jLabelIcmsEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnIcmsEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelIcmsEntrada)
                        .addComponent(jBtnIcmsEntrada))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jPanel4.setBackground(new java.awt.Color(18, 18, 56));
            jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelIcmsSaida.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelIcmsSaida.setForeground(new java.awt.Color(255, 255, 255));
            jLabelIcmsSaida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelIcmsSaida.setText("ICMS SAIDA");

            jBtnccmsSaida.setBackground(new java.awt.Color(0, 51, 102));
            jBtnccmsSaida.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jBtnccmsSaida.setForeground(new java.awt.Color(255, 255, 255));
            jBtnccmsSaida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/env.png"))); // NOI18N
            jBtnccmsSaida.setText("Enviar");
            jBtnccmsSaida.setToolTipText("Enviar dados da tabela Icms Saída");
            jBtnccmsSaida.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jBtnccmsSaida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnccmsSaida.setFocusable(false);
            jBtnccmsSaida.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnccmsSaidaActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabelIcmsSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnccmsSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIcmsSaida)
                    .addComponent(jBtnccmsSaida))
            );

            jPanel5.setBackground(new java.awt.Color(18, 18, 56));
            jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabelBaseInterage.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jLabelBaseInterage.setForeground(new java.awt.Color(255, 255, 255));
            jLabelBaseInterage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelBaseInterage.setText("BASE INTERAGE");

            jBtnSincronizar.setBackground(new java.awt.Color(0, 51, 102));
            jBtnSincronizar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
            jBtnSincronizar.setForeground(new java.awt.Color(255, 255, 255));
            jBtnSincronizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/env.png"))); // NOI18N
            jBtnSincronizar.setText("Sincronizar Tudo");
            jBtnSincronizar.setToolTipText("Enviar todos os dados das tabelas");
            jBtnSincronizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jBtnSincronizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jBtnSincronizar.setFocusable(false);
            jBtnSincronizar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtnSincronizarActionPerformed(evt);
                }
            });

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

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jLabelBaseInterage, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBtnSincronizar))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAtualizar)
                    .addComponent(jBtnSincronizar))
                .addComponent(jLabelBaseInterage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(1, 1, 1))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                            .addGap(2, 2, 2)))
                    .addGap(7, 7, 7))
                .addComponent(jSeparator1)
            );

            getContentPane().add(jPanel1, "card2");

            pack();
        }// </editor-fold>//GEN-END:initComponents


    private void jBtnPisCofinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPisCofinsActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    logApp = new Log();
                    logApp.setDataLog(new Date());

                    jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                    baseInterageTableModel.updateTableListener();
                    List<Piscofins> result = pisCofinsBusiness.getAll("carregar-piscofins");
                    Integer size = result.size();

                    JDlgCarregando carregando = carregarJdialog("");
                    carregando.setVisible(true);

                    String texto = size > 0 ? "Iniciando Atualização Pis/Cofins..." : "";
                    carregando.loadBarra(texto, 0, 0, true);
                    logApp.setQtdPisConfins(size);

                    //**********************************************************
                    int max = 30;
                    int calc = size / max;

                    if ((size % max) > 0) {
                        calc++;
                    }
                    int inserts = 0;
                    //**********************************************************
                    for (int p = 0; p < calc; p++) {
                        carregando.loadBarra("Pis/Cofins", inserts, size, false);
                        List<Piscofins> resultCalcPisCofins = new ArrayList<>();
                        for (int e = inserts; e < (inserts + max); e++) {
                            Piscofins i = result.get(e);
                            texto = "Contador Pis_Cofins= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                            BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto().toString());
                            if (b == null) {
                                b = new BaseInterage();
                                b.setCodBarras(i.getEan().toString());
                                b.setCodProduto(i.getCodigoProduto().toString());
                                b.setNmProduto(i.getNmProduto());
                                b.setPisCofins(true);
                                b.setAtualizacaoPisCofins(new Date());
                                baseInterageTableModel.addItem(b);
                            } else {
                                b.setPisCofins(true);
                                b.setAtualizacaoPisCofins(new Date());

                            }
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();
//                            fiscalTempBusinessBean.insertPisCofins(i);
                            resultCalcPisCofins.add(i);
                            carregando.setTexto(texto);
                        }
                        fiscalTempBusinessBean.insertPisCofins(resultCalcPisCofins);
                        inserts = inserts + max;
                    }
                    carregando.setVisible(false);
                    tableModelPisCofins.setItems(null);
                    carregarSizeList(tableModelPisCofins.getRowCount(), null, null, baseInterageTableModel.getRowCount());
                    tableModelPisCofins.updateTableListener();
                    baseInterageTableModel.updateTableListener();
                    pisCofinsBusiness.deleteAll();
                    OptionPane.showMessageDialog("Mensagem", "Dados Enviados com sucesso !!!", null, () -> {

                        Thread.sleep(1000);

                        return null; //To change body of generated lambdas, choose Tools | Templates.
                    });

                }
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);

        }
        carregarLog();
    }//GEN-LAST:event_jBtnPisCofinsActionPerformed

    private void jBtnIcmsEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIcmsEntradaActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    logApp = new Log();
                    logApp.setDataLog(new Date());

                    jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                    baseInterageTableModel.updateTableListener();

                    List<IcmsEntrada> result = icmsentradaBusiness.getAll("carregar-icmsentrada");
                    Integer size = result.size();

                    JDlgCarregando carregando = carregarJdialog("");
                    carregando.setVisible(true);
                    String texto = size > 0 ? "Iniciando Atualização Icms Entrada..." : "";
                    carregando.loadBarra(texto, 0, 0, true);
                    logApp.setQtdIcmsEntrada(size);

                    //**********************************************************
                    int max = 30;
                    int calc = size / max;

                    if ((size % max) > 0) {
                        calc++;
                    }
                    int inserts = 0;
                    //**********************************************************
                    for (int ie = 0; ie < calc; ie++) {
                        carregando.loadBarra("Icms Entrada", inserts, size, false);
                        List<IcmsEntrada> resultCalcIcmsEntrada = new ArrayList<>();
                        for (int e = inserts; e < (inserts + max); e++) {
                            IcmsEntrada i = result.get(e);
                            texto = "Contador Icms_Entrada= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                            BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto().toString());
                            if (b == null) {
                                b = new BaseInterage();
                                b.setCodBarras(i.getEan().toString());
                                b.setCodProduto(i.getCodigoProduto().toString());
                                b.setNmProduto(i.getNmProduto());
                                b.setIcmsEntrada(true);
                                b.setAtualizacaoIcmsEntrada(new Date());
                                baseInterageTableModel.addItem(b);
                            } else {
                                b.setIcmsEntrada(true);
                                b.setAtualizacaoIcmsEntrada(new Date());

                            }
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();
//                            fiscalTempBusinessBean.insertIcmsEntrada(i);
                            resultCalcIcmsEntrada.add(i);
                            carregando.setTexto(texto);
                        }
                        fiscalTempBusinessBean.insertIcmsEntrada(resultCalcIcmsEntrada);
                        inserts = inserts + max;
                    }
                    carregando.setVisible(false);
                    tableModelIcmsEntrada.setItems(null);
                    carregarSizeList(null, tableModelIcmsEntrada.getRowCount(), null, baseInterageTableModel.getRowCount());
                    tableModelIcmsEntrada.updateTableListener();
                    baseInterageTableModel.updateTableListener();
                    icmsentradaBusiness.deleteAll();
                    OptionPane.showMessageDialog("Mensagem", "Dados Enviados com sucesso !!!", null, () -> {

                        Thread.sleep(1000);

                        return null; //To change body of generated lambdas, choose Tools | Templates.
                    });

                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);

        }
        carregarLog();
    }//GEN-LAST:event_jBtnIcmsEntradaActionPerformed

    private void jBtnccmsSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnccmsSaidaActionPerformed
        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    logApp = new Log();
                    logApp.setDataLog(new Date());

                    jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                    baseInterageTableModel.updateTableListener();
                    List<IcmsSaida> result = icmssaidaBusiness.getAll("carregar-icmssaida");
                    Integer size = result.size();

                    JDlgCarregando carregando = carregarJdialog("");
                    carregando.setVisible(true);

                    String texto = size > 0 ? "Iniciando Atualização Icms Saída **********" : "";
                    carregando.loadBarra(texto, 0, 0, true);
                    logApp.setQtdIcmsSaida(size);

                    //**********************************************************
                    int max = 30;
                    int calc = size / max;

                    if ((size % max) > 0) {
                        calc++;
                    }
                    int inserts = 0;
                    //**********************************************************
                    for (int is = 0; is < calc; is++) {
                        carregando.loadBarra("Icms Saída", inserts, size, false);
                        List<IcmsSaida> resultCalcIcmsSaida = new ArrayList<>();
                        for (int e = inserts; e < (inserts + max); e++) {
                            IcmsSaida i = result.get(e);
                            texto = "Contador Icms_Saída= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                            BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto().toString());
                            if (b == null) {
                                b = new BaseInterage();
                                b.setCodBarras(i.getEan().toString());
                                b.setCodProduto(i.getCodigoProduto().toString());
                                b.setNmProduto(i.getNmProduto());
                                b.setIcmsSaida(true);
                                b.setAtualizacaoIcmsSaida(new Date());
                                baseInterageTableModel.addItem(b);
                            } else {
                                b.setIcmsSaida(true);
                                b.setAtualizacaoIcmsSaida(new Date());
                            }
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();
//                            fiscalTempBusinessBean.insertIcmsSaida(i);
                            resultCalcIcmsSaida.add(i);
                            carregando.setTexto(texto);
                        }
                        fiscalTempBusinessBean.insertIcmsSaida(resultCalcIcmsSaida);
                        inserts = inserts + max;
                    }
                    carregando.setVisible(false);
                    tableModelIcmsSaida.setItems(null);
                    carregarSizeList(null, null, tableModelIcmsSaida.getRowCount(), baseInterageTableModel.getRowCount());
                    tableModelIcmsSaida.updateTableListener();
                    baseInterageTableModel.updateTableListener();
                    icmssaidaBusiness.deleteAll();
                    OptionPane.showMessageDialog("Mensagem", "Dados Enviados com sucesso !!!", null, () -> {

                        Thread.sleep(1000);

                        return null; //To change body of generated lambdas, choose Tools | Templates.
                    });

                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);

        }
        carregarLog();
    }//GEN-LAST:event_jBtnccmsSaidaActionPerformed

    public JDlgCarregando carregarJdialog(String txt) {
        JDlgCarregando carregando = new JDlgCarregando(jfrmPrincipal, false, txt);
        return carregando;
    }
    private void jBtnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAtualizarActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JDlgCarregando carregando = carregarJdialog("");
                    carregando.setVisible(true);
                    carregando.loadBarra("Atualizando...", 0, 0, true);
                    carregarListagem();
                    carregando.setVisible(false);
                    if (jfrmPrincipal.isShowing()) {
//                    carregarSizeList(((Number) pisCofinsBusiness.count()).intValue(), ((Number) icmsentradaBusiness.count()).intValue(), ((Number) icmssaidaBusiness.count()).intValue(), ((Number) fiscalTempBusinessBean.count()).intValue());
                        OptionPane.showMessageDialog("Mensagem", "Dados Atualizados com sucesso !!!", null, () -> {

                            Thread.sleep(1000);

                            return null; //To change body of generated lambdas, choose Tools | Templates.
                        });
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
                    JDlgCarregando carregando = carregarJdialog("Carregando...");
                    carregando.setVisible(true);
                    String texto = "Sincronizando Tabelas...";
                    carregando.loadBarra(texto, 0, 0, true);
                    carregarListagem();

                    //*********************** Pis/Cofins ***********************
                    logApp = new Log();
                    logApp.setDataLog(new Date());

                    jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                    baseInterageTableModel.updateTableListener();
                    List<Piscofins> result = pisCofinsBusiness.getAll("carregar-piscofins");
                    Integer size = result.size();
                    texto = size > 0 ? "Iniciando Atualização Pis/Cofins..." : "";
                    carregando.loadBarra(texto, 0, 0, true);
                    logApp.setQtdPisConfins(size);

                    //**********************************************************
                    int max = 200;
                    int calc = size / max;

                    if ((size % max) > 0) {
                        calc++;
                    }
                    int inserts = 0;
                    //**********************************************************
                    for (int p = 0; p < calc; p++) {
                        carregando.loadBarra("Pis/Cofins", inserts, size, false);
                        List<Piscofins> resultCalcPisCofins = new ArrayList<>();
                        for (int e = inserts; e < (inserts + max); e++) {
                            if (e >= size) {
                                break;
                            }
                            Piscofins i = result.get(e);

                            texto = "Contador Pis_Cofins= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                            BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto().toString());
                            if (b == null) {
                                b = new BaseInterage();
                                b.setCodBarras(i.getEan().toString());
                                b.setCodProduto(i.getCodigoProduto().toString());
                                b.setNmProduto(i.getNmProduto());
                                b.setPisCofins(true);
                                b.setAtualizacaoPisCofins(new Date());
                                baseInterageTableModel.addItem(b);
                            } else {
                                b.setPisCofins(true);
                                b.setAtualizacaoPisCofins(new Date());

                            }
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();
//                            fiscalTempBusinessBean.insertPisCofins(i);
                            resultCalcPisCofins.add(i);
                            carregando.setTexto(texto);
                        }
                        fiscalTempBusinessBean.insertPisCofins(resultCalcPisCofins);
                        inserts = inserts + max;
                        carregando.loadBarra("Atualizando Pis/Cofins", 0, 0, true);
                    }
                    tableModelPisCofins.setItems(null);
                    carregarSizeList(tableModelPisCofins.getRowCount(), null, null, ((Number) fiscalTempBusinessBean.count()).intValue());
                   pisCofinsBusiness.deleteAll();
                    tableModelPisCofins.updateTableListener();

                    //*********************** Icms Entrada *********************
                    List<IcmsEntrada> result2 = icmsentradaBusiness.getAll("carregar-icmsentrada");
                    size = result2.size();

                    texto = size > 0 ? "Iniciando Atualização Icms Entrada..." : "";
                    carregando.loadBarra(texto, 0, 0, true);
                    logApp.setQtdIcmsEntrada(size);

                    //**********************************************************
                    calc = size / max;

                    if ((size % max) > 0) {
                        calc++;
                    }
                    inserts = 0;
                    //**********************************************************
                    for (int ie = 0; ie < calc; ie++) {
                        carregando.loadBarra("Icms Entrada", inserts, size, false);
                        List<IcmsEntrada> resultCalcIcmsEntrada = new ArrayList<>();
                        for (int e = inserts; e < (inserts + max); e++) {
                            if (e >= size) {
                                break;
                            }
                            IcmsEntrada i = result2.get(e);

                            texto = "Contador Icms_Entrada= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                            BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto().toString());
                            if (b == null) {
                                b = new BaseInterage();
                                b.setCodBarras(i.getEan().toString());
                                b.setCodProduto(i.getCodigoProduto().toString());
                                b.setNmProduto(i.getNmProduto());
                                b.setIcmsEntrada(true);
                                b.setAtualizacaoIcmsEntrada(new Date());
                                baseInterageTableModel.addItem(b);
                            } else {
                                b.setIcmsEntrada(true);
                                b.setAtualizacaoIcmsEntrada(new Date());

                            }
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();
//                            fiscalTempBusinessBean.insertIcmsEntrada(i);
                            resultCalcIcmsEntrada.add(i);
                            carregando.setTexto(texto);
                        }
                        fiscalTempBusinessBean.insertIcmsEntrada(resultCalcIcmsEntrada);
                        inserts = inserts + max;
                        carregando.loadBarra("Atualizando Icms Entrada", 0, 0, true);
                    }
                    tableModelIcmsEntrada.setItems(null);
                    carregarSizeList(null, tableModelIcmsEntrada.getRowCount(), null, ((Number) fiscalTempBusinessBean.count()).intValue());
                    icmsentradaBusiness.deleteAll();
                    tableModelIcmsEntrada.updateTableListener();

                    //********************** Icms Saída ************************
                    List<IcmsSaida> result3 = icmssaidaBusiness.getAll("carregar-icmssaida");
                    size = result3.size();

                    texto = size > 0 ? "Iniciando Atualização Icms Saída..." : "";
                    carregando.loadBarra(texto, 0, 0, true);
                    logApp.setQtdIcmsSaida(size);

                    //**********************************************************
                    calc = size / max;

                    if ((size % max) > 0) {
                        calc++;
                    }
                    inserts = 0;
                    //**********************************************************
                    for (int is = 0; is < calc; is++) {
                        carregando.loadBarra("Icms Saída", inserts, size, false);
                        List<IcmsSaida> resultCalcIcmsSaida = new ArrayList<>();
                        for (int e = inserts; e < (inserts + max); e++) {
                            if (e >= size) {
                                break;
                            }
                            IcmsSaida i = result3.get(e);

                            texto = "Contador Icms_Saída= " + e + " de " + (size - 1) + " Codigo_Produto= " + i.getCodigoProduto();
                            BaseInterage b = baseInterageTableModel.getFindCodList(i.getCodigoProduto().toString());
                            if (b == null) {
                                b = new BaseInterage();
                                b.setCodBarras(i.getEan().toString());
                                b.setCodProduto(i.getCodigoProduto().toString());
                                b.setNmProduto(i.getNmProduto());
                                b.setIcmsSaida(true);
                                b.setAtualizacaoIcmsSaida(new Date());
                                baseInterageTableModel.addItem(b);
                            } else {
                                b.setIcmsSaida(true);
                                b.setAtualizacaoIcmsSaida(new Date());
                            }
                            jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
                            baseInterageTableModel.updateTableListener();
//                            fiscalTempBusinessBean.insertIcmsSaida(i);
                            resultCalcIcmsSaida.add(i);
                            carregando.setTexto(texto);
                        }
                        fiscalTempBusinessBean.insertIcmsSaida(resultCalcIcmsSaida);
                        inserts = inserts + max;
                        carregando.loadBarra("Atualizando Icms Saída", 0, 0, true);
                    }
                    tableModelIcmsSaida.setItems(null);
                    carregarSizeList(null, null, tableModelIcmsSaida.getRowCount(), ((Number) fiscalTempBusinessBean.count()).intValue());
                    baseInterageTableModel.updateTableListener();
                    tableModelIcmsSaida.updateTableListener();
                    icmssaidaBusiness.deleteAll();
                    //*********************************************************
                    carregando.setVisible(false);

                    if (jfrmPrincipal.isShowing()) {
                        OptionPane.showMessageDialog("Mensagem", "Dados Enviados com sucesso !!!", null, () -> {

                            Thread.sleep(1000);

                            return null; //To change body of generated lambdas, choose Tools | Templates.
                        });
                    }
                    carregarLog();
                }
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);

        }

    }//GEN-LAST:event_jBtnSincronizarActionPerformed

    public void carregarLog() {
        try {
            if (logApp != null) {
                File log = new File("C:\\InterageSe\\inter-fiscal\\Log-MixFiscal\\log.txt");
                BufferedReader buffRead = new BufferedReader(new FileReader(log.getAbsolutePath()));
                String linha = "";
                String quebra = System.getProperty("line.separator");
                while (buffRead.ready()) {
                    linha += buffRead.readLine() + quebra;
                }
                buffRead.close();
                log.delete();
                FileWriter arq = new FileWriter(log);
                PrintWriter gravarArq = new PrintWriter(arq);

                gravarArq.printf(linha);
                String txt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(logApp.getDataLog()) + " -> ";

                if (logApp.getQtdPisConfins() != null) {
                    txt += " PisCofins = " + logApp.getQtdPisConfins() + " itens";
                }
                if (logApp.getQtdIcmsEntrada() != null) {
                    txt += " IcmsEntrada = " + logApp.getQtdIcmsEntrada() + " itens";
                }
                if (logApp.getQtdIcmsSaida() != null) {
                    txt += " IcmsSaida = " + logApp.getQtdIcmsSaida() + " itens ;";
                }

                gravarArq.printf(linha + txt);
                arq.close();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            boolean resp = jDlgBoasVindas.jDlgBoasVindasIniciar(jfrmPrincipal);

        } catch (Exception ex) {
            ex.printStackTrace();
            JDlgMensagem mensagem = new JDlgMensagem(jfrmPrincipal, true, ex);
            mensagem.setVisible(true);
        }
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
    private javax.swing.JButton jBtnIcmsEntrada;
    private javax.swing.JButton jBtnPisCofins;
    private javax.swing.JButton jBtnSincronizar;
    private javax.swing.JButton jBtnccmsSaida;
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
