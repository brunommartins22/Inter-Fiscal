package br.com.interagese.interfiscal.view;

import br.com.interagese.interfiscal.business.FireTabfilBusinessBean;
import br.com.interagese.interfiscal.entity.Sessao;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.JPAUtil;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Window;
import java.io.IOException;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.internal.SessionImpl;
import br.com.interagese.interfiscal.business.FireTabfilBusiness;

public class JDlgSplashScreen extends javax.swing.JDialog {

    private Actions a = new Actions(null);
    private static FireTabfilBusiness tabfilBusiness = new FireTabfilBusinessBean();

    public JDlgSplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public JDlgSplashScreen(Window window, String title, ModalityType type) {
        super(window, title, type);
        initComponents();
        definirFormulario();
    }

    private void definirFormulario() {
        a.iconApplication(this);
//        dispose();

//        setUndecorated(true);
        //**********************************************************************
        a.settingImgJLabel(jLblImg, "/imagens/carregando.png", 620, 207, 100);
//        pack();
        this.setSize(620, 240);
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLblImg.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().add(jLblImg, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Runnable runnable = new ShowAndWait();
        runnable.run();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblImg;
    // End of variables declaration//GEN-END:variables

    public static class ShowAndWait implements Runnable {

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

//                JPAUtil.executeCreateScript();
                Database database = null;
                Liquibase liquibase = null;

                Connection connectionFireBird = JPAUtil.getEntityManager().unwrap(SessionImpl.class).connection();
                database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connectionFireBird));
                liquibase = new Liquibase("liquibase/updateDB.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts(), new LabelExpression());

                Connection connectionPostgres = JPAUtil.getEntityManagerPostgreSql().unwrap(SessionImpl.class).connection();
                database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connectionPostgres));
                liquibase = new Liquibase("liquibase/updateDB.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts(), new LabelExpression());

                return null;
            }

        };

        @Override
        public void run() {

            Window window = SwingUtilities.getWindowAncestor(new JFrame());

            JDlgSplashScreen dialog = new JDlgSplashScreen(window, "Inter-Fiscal", ModalityType.APPLICATION_MODAL);

            worker.addPropertyChangeListener((evt) -> {
                if (evt.getPropertyName().equals("state")) {
                    if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                        JfrmGeral jp = new JfrmGeral();
                        dialog.dispose();

                        Sessao.isExecuting = false;
                        Sessao.jInternalFrameMixfiscal = null;
                        Sessao.resultFilial = tabfilBusiness.carregarFilialOnMixFiscal();
                        if (Sessao.mixfiscal) {
                            jp.getCarregarMixFiscal(true);
                        }
                        Sessao.frame = jp;
                    }
                }
            });

            worker.execute();

            dialog.setVisible(true);

        }

    }

//    public static void verificarLiberacao() {
//
//        ArquivoBusiness arquivoBusiness = new ArquivoBusinessBean();
//
//        //Busca o arquivo de atualização mais recente do banco
//        Arquivo liberacao = arquivoBusiness.getLiberacao();
//
//        try {
//
//            //Descriptografa o texto
//            String texto = descriptografarLiberacao(7, liberacao.getArquivo());
//
//            //Carrega em um properties
//            Properties props = new Properties();
//
//            props.load(new StringReader(texto));
//
//            //Pega a propriedade VERSAOMDFE
//            String versao = props.getProperty("VERSAOESTOQUE");
//
//            //Pega a propriedade LIBMDFE
//            String libMdfe = props.getProperty("LIBESTOQUE");
//
//            //Pega a propriedade VENCIMENTOMDFE
//            String dataTemp = props.getProperty("VENCIMENTOESTOQUE");
//
//            //Se for diferente de null
//            if (libMdfe != null) {
//
//                System.out.println("Versão:" + versao);
//                if (versao.equals("X3") == false) {
//                    JOptionPane.showMessageDialog(null, "Versão não autorizada: '" + versao + "'!\n"
//                            + "Solicite liberação para versão X3.");
//
//                    System.exit(0);
//                }
//
//                //Se a prop libMdfe for 'S' 
//                if (libMdfe.equals("S")) {
//
//                    //Converte a data de vencimento de String para Date
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    Date dataVenc = sdf.parse(dataTemp);
//
//                    //Pega data atual
//                    Date dataAtual = new Date(sdf.parse(sdf.format(System.currentTimeMillis())).getTime());
//
//                    System.out.println("Data Atual:" + dataAtual);
//                    System.out.println("Data Venc:" + dataVenc);
//
//                    //Compara a data atual com o vencimento
//                    if (dataAtual.compareTo(dataVenc) < 0 || dataAtual.compareTo(dataVenc) == 0) {
//                        //Não está vencido
//
//                        if (props.getProperty("CNPJLIBERADO") != null) {
//
//                            String[] cnpjsLib = props.getProperty("CNPJLIBERADO").split(";");
//
//                            //Grava os cnpj's liberados
//                            for (String cnpjLib : cnpjsLib) {
//                                //Sessao.getCnpjs().add(cnpjLib);
//                            }
//
//                        }
//
//                    } else if (dataAtual.compareTo(dataVenc) > 0) {
//                        //Está vencido
//                        JOptionPane.showMessageDialog(null, "Sua licensa de uso expirou.\n"
//                                + "Favor entrar em contato com a INTERAGE\n"
//                                + "www.interagese.com.br - interagesac@hotmail.com\n"
//                                + "Fone: (91)3263-6261 (91)3088-1588 (91)8114-2418");
//                        System.exit(0);
//                    }
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Sistema não liberado para uso!\n"
//                            + "Solicite liberação para este sistema.");
//                    System.exit(0);
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Sistema de Download de notas não liberado para uso!\n"
//                        + "Solicite liberação para este sistema.");
//                System.exit(0);
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(JDlgSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(JDlgSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public static String descriptografarLiberacao(int chave, String texto) throws IOException {

        String resultado = "";

        for (int i = 0; i < texto.length(); i++) {
            resultado += ((char) (((int) texto.charAt(i) - chave) % 255));
        }

        System.out.println("Liberação: " + resultado);

        //System.out.println(resultado);
        return resultado;
    }

}
