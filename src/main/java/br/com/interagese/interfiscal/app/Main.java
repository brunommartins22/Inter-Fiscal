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
            File arquivoConf = new File(Config.getPath() + "\\conf.cfg");
            File arquivoLogSucessMix = new File(LogMixFiscal.getPath() + "\\log-sucess.txt");
            File arquivoLogErrorMix = new File(LogMixFiscal.getPath() + "\\log-error.txt");
            File arquivoLogSucessImport = new File(LogImportacao.getPath() + "\\log-sucess.txt");
            File arquivoLogErrorImport = new File(LogImportacao.getPath() + "\\log-error.txt");

            if (!arquivoConf.exists()) {
                arquivoConf.createNewFile();
                FileWriter fw = new FileWriter(arquivoConf);
                PrintWriter pw = new PrintWriter(fw);
                pw.printf("H1=00:00\r\nH2=00:00\r\nFILIAL=1\r\nINFOHORAS=1\r\nQTDENVIO=300\r\nMIXFISCAL=false\r\nIMPORTACAOFISCAL=false\r\nPRIMEIROACESSO=1");
                fw.close();
                Sessao.infoHoras = 1;
                Sessao.qtdEnvio = 300;
                Sessao.mixfiscal = false;
                Sessao.importacaofiscal = false;
            } else {
                Properties conf = a.carregarArquivo(arquivoConf.getPath());
//                if (conf.getProperty("PRIMEIROACESSO").equals("1")) {
//                    String text = "H1=" + conf.getProperty("H1") + "\r\nH2=" + conf.getProperty("H2") + "\r\nINFOHORAS=" + conf.getProperty("INFOHORAS") + "\r\nQTDENVIO=" + conf.getProperty("QTDENVIO") + "\r\nMIXFISCAL=" + conf.getProperty("MIXFISCAL") + "\r\nIMPORTACAOFISCAL=" + conf.getProperty("IMPORTACAOFISCAL") + "\r\nPRIMEIROACESSO=0";
//                    arquivoConf.delete();
//                    arquivoConf.createNewFile();
//                    FileWriter f = new FileWriter(arquivoConf);
//                    PrintWriter p = new PrintWriter(f);
//                    p.printf(text);
//                    f.close();
//                }
                Sessao.infoHoras = Integer.parseInt(conf.getProperty("INFOHORAS"));
                Sessao.qtdEnvio = Integer.parseInt(conf.getProperty("QTDENVIO"));
                Sessao.mixfiscal = conf.getProperty("MIXFISCAL").equals("true");
                Sessao.importacaofiscal = conf.getProperty("IMPORTACAOFISCAL").equals("true");

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
