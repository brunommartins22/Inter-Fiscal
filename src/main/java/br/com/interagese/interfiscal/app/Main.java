package br.com.interagese.interfiscal.app;

import br.com.interagese.interfiscal.entity.Sessao;
import br.com.interagese.interfiscal.utils.AppLock;
import br.com.interagese.interfiscal.view.JDlgSplashScreen;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) throws Exception {

        if (AppLock.lock()) {
            File LogImportacao = new File("C:\\InterageSe\\inter-fiscal\\Log-Importacao");
            File LogMixFiscal = new File("C:\\InterageSe\\inter-fiscal\\Log-MixFiscal");
            File Config = new File("C:\\InterageSe\\inter-fiscal\\Configuracao");

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
            File arquivoLog = new File(LogMixFiscal.getPath() + "\\log.txt");

            if (!arquivoConf.exists()) {
                arquivoConf.createNewFile();
                FileWriter fw = new FileWriter(arquivoConf);
                PrintWriter pw = new PrintWriter(fw);
                pw.printf("H1=00:00 \n H2=00:00");
                fw.close();
                System.out.println("Arquivo de Configuração gerado com sucesso !!");
            }

            if (!arquivoLog.exists()) {
                arquivoLog.createNewFile();
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

            SystemTray tray = SystemTray.getSystemTray();
            PopupMenu menu = new PopupMenu();
            int i = 0;
            while (i < 2) {
                MenuItem item = new MenuItem();
                switch (i) {
                    case 0: {
                        item.setLabel("Abrir");
                        item.addActionListener((e) -> {
                            Sessao.frame.setVisible(true);
                        });
                        menu.add(item);
                        break;
                    }
                    case 1: {
                        item.setLabel("Desligar");
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
            tray.add(ti);
            ti.displayMessage("Inter-Fiscal", "Aplicação Iniciada", TrayIcon.MessageType.INFO);

        } else {
            JOptionPane.showMessageDialog(null, "A aplicação já se encontra em execução!", "Inter-Fiscal", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

}
