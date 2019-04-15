package br.com.interagese.interfiscal.app;

import br.com.interagese.interfiscal.entity.Sessao;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.AppLock;
import br.com.interagese.interfiscal.view.JDlgMensagem;
import br.com.interagese.interfiscal.view.JDlgSplashScreen;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    private static Actions a = new Actions(null);
    private static JFrame jfrmPrincipal = new JFrame();

    public static void main(String[] args) throws Exception {

        if (AppLock.lock()) {
            File LogImportacao = new File("Log-Importacao");
            File LogMixFiscal = new File("Log-MixFiscal");
            File LogServidorFiscal = new File("Log-ServidorFiscal");
            File Config = new File("Configuracao");

            if (!Config.exists()) {
                Config.mkdirs();
            }
            if (!LogImportacao.exists()) {
                LogImportacao.mkdirs();
            }
            if (!LogMixFiscal.exists()) {
                LogMixFiscal.mkdirs();
            }
            if (!LogServidorFiscal.exists()) {
                LogServidorFiscal.mkdirs();
            }
            File arquivoConf = new File(Config.getPath() + "\\conf.cfg");
            File arquivoLogSucessMix = new File(LogMixFiscal.getPath() + "\\log-sucess-mix.txt");
            File arquivoLogErrorMix = new File(LogMixFiscal.getPath() + "\\log-error-mix.txt");
            File arquivoLogSucessImport = new File(LogImportacao.getPath() + "\\log-sucess-import.txt");
            File arquivoLogErrorImport = new File(LogImportacao.getPath() + "\\log-error-import.txt");
            File arquivoLogSucessServer = new File(LogServidorFiscal.getPath() + "\\log-sucess-server.txt");
            File arquivoLogErrorServer = new File(LogServidorFiscal.getPath() + "\\log-error-server.txt");

            if (!arquivoConf.exists()) {
                arquivoConf.createNewFile();
                Sessao.bdFirebird = "C:\\InterageSE\\DADOS\\INTEGRADO.GDB";
                Sessao.userFirebird = "SYSDBA";
                Sessao.pwFirebird = "masterkey";
                Sessao.ipFirebird = "localhost";
                Sessao.bdPostgres = "integrado";
                Sessao.userPostgres = "postgres";
                Sessao.pwPostgres = "1234";
                Sessao.ipPostgres = "localhost";
                Sessao.mixfiscal = false;
                Sessao.importacaofiscal = false;
                Sessao.servidorfiscal = false;
                FileWriter fw = new FileWriter(arquivoConf);
                PrintWriter pw = new PrintWriter(fw);
                pw.printf("BDFIREBIRD=" + Sessao.bdFirebird + "\r\n"
                        + "USERFIREBIRD=" + Sessao.userFirebird + "\r\n"
                        + "PWFIREBIRD=" + Sessao.pwFirebird + "\r\n"
                        + "BDPOSTGRES=" + Sessao.bdPostgres + "\r\n"
                        + "USERPOSTGRES=" + Sessao.userPostgres + "\r\n"
                        + "PWPOSTGRES=" + Sessao.pwPostgres + "\r\n"
                        + "MANUTENCAO=A\r\n"
                        + "H1=00:00\r\n"
                        + "H2=00:00\r\n"
                        + "INFOHORAS=1\r\n"
                        + "QTDENVIO=300\r\n"
                        + "MIXFISCAL=" + Sessao.mixfiscal + "\r\n"
                        + "IMPORTACAOFISCAL=" + Sessao.importacaofiscal + "\r\n"
                        + "SERVIDORFISCAL=" + Sessao.servidorfiscal + "\r\n"
                        + "PRIMEIROACESSO=1");
                fw.close();

            } else {
                Properties conf = a.carregarArquivo(arquivoConf.getPath());

                Sessao.bdFirebird = conf.getProperty("BDFIREBIRD");
                if (Sessao.bdFirebird.contains(":C:")) {
                    Sessao.ipFirebird = Sessao.bdFirebird.substring(0, Sessao.bdFirebird.indexOf(":C:"));
                } else {
                    Sessao.ipFirebird = "localhost";
                }
                Sessao.userFirebird = conf.getProperty("USERFIREBIRD");
                Sessao.pwFirebird = conf.getProperty("PWFIREBIRD");
                Sessao.bdPostgres = conf.getProperty("BDPOSTGRES");
                if (Sessao.bdPostgres.contains(":")) {
                    Sessao.ipPostgres = Sessao.bdPostgres.split(":")[0];
                } else {
                    Sessao.ipPostgres = "localhost";
                }
                Sessao.userPostgres = conf.getProperty("USERPOSTGRES");
                Sessao.pwPostgres = conf.getProperty("PWPOSTGRES");

                Sessao.mixfiscal = conf.getProperty("MIXFISCAL").equals("true");
                if (Sessao.mixfiscal) {
                    Sessao.manutencao = conf.getProperty("MANUTENCAO");
                    if (Sessao.manutencao.equals("A")) {
                        Sessao.infoHoras = Integer.parseInt(conf.getProperty("INFOHORAS"));
                    }
                    Sessao.qtdEnvio = Integer.parseInt(conf.getProperty("QTDENVIO"));
                }
                Sessao.importacaofiscal = conf.getProperty("IMPORTACAOFISCAL").equals("true");
                Sessao.servidorfiscal = conf.getProperty("SERVIDORFISCAL").equals("true");

            }

            if (!arquivoLogSucessMix.exists()) {
                arquivoLogSucessMix.createNewFile();
            }
            if (!arquivoLogErrorMix.exists()) {
                arquivoLogErrorMix.createNewFile();
            }
            if (!arquivoLogSucessImport.exists()) {
                arquivoLogSucessImport.createNewFile();
            }
            if (!arquivoLogErrorImport.exists()) {
                arquivoLogErrorImport.createNewFile();
            }
            if (!arquivoLogSucessServer.exists()) {
                arquivoLogSucessServer.createNewFile();
            }
            if (!arquivoLogErrorServer.exists()) {
                arquivoLogErrorServer.createNewFile();
            }

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            Runnable runnable = new JDlgSplashScreen.ShowAndWait();
            runnable.run();

            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            boolean isVisible = false;
                            SystemTray tray = SystemTray.getSystemTray();
                            PopupMenu menu = new PopupMenu();
                            int i = 0;
                            while (i < 2) {
                                MenuItem item = new MenuItem();
                                switch (i) {
                                    case 0: {
                                        isVisible = Sessao.frame.isVisible();
                                        String texto = isVisible ? "Minimizar para a bandeja" : "Mostrar Inter-Fiscal";
                                        item.setLabel(texto);
                                        item.addActionListener((e) -> {
                                            Sessao.frame.setVisible(true);
                                        });
                                        menu.add(item);
                                        break;
                                    }
                                    case 1: {
                                        item.setLabel("Fechar");
                                        item.addActionListener((e) -> {
                                            System.exit(0);
                                        });
                                        menu.add(item);
                                        break;
                                    }
                                }
                                i++;

                            }

                            TrayIcon ti = new TrayIcon(new ImageIcon(Main.class.getClass().getResource("/imagens/icone-x4.png")).getImage(), "Inter-Fiscal", menu);
                            ti.setImageAutoSize(true);
                            ti.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Sessao.frame.setVisible(true);
                                }
                            });

                            tray.add(ti);

                            ti.displayMessage("Inter-Fiscal", "Aplicação Iniciada ...", TrayIcon.MessageType.INFO);

                            Sessao.iconInformation = ti;

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
        } else {
            JOptionPane.showMessageDialog(null, "A aplicação já se encontra em execução!", "Inter-Fiscal", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

}
