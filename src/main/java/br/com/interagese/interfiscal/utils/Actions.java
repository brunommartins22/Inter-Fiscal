/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.utils;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author bruno
 */
public class Actions {

    /**
     * jtp(JTextPane); typePosition(R-right;L-Left;C-center;J-Justify)
     */
    public void definitionPositionJTextPane(JTextPane jtp, String typePosition) {
        StyledDocument sd = jtp.getStyledDocument();
        SimpleAttributeSet obj = new SimpleAttributeSet();
        StyleConstants.setAlignment(obj, typePosition.equals("L") ? StyleConstants.ALIGN_LEFT : typePosition.equals("R") ? StyleConstants.ALIGN_RIGHT : typePosition.equals("C") ? StyleConstants.ALIGN_CENTER : StyleConstants.ALIGN_JUSTIFIED);
        sd.setParagraphAttributes(0, sd.getLength(), obj, false);
    }

    /**
     * jp(JPanel); cor(ex:0000);
     */
    public void backgroundJPanel(JPanel jp, String cor) {
        jp.setBackground(new Color(Integer.parseInt(cor.substring(0, 1)), Integer.parseInt(cor.substring(1, 2)), Integer.parseInt(cor.substring(2, 3)), Integer.parseInt(cor.substring(3, 4))));
    }

    public void setColumnMinWidth(JTable jt, int columnIndex, int width) {
        jt.getColumnModel().getColumn(columnIndex).setMinWidth(width);
    }

    public void setColumnMaxWidth(JTable jt, int columnIndex, int width) {
        jt.getColumnModel().getColumn(columnIndex).setMaxWidth(width);
    }

    public void setColumnWidth(JTable jt, int columnIndex, int width) {
        jt.getColumnModel().getColumn(columnIndex).setWidth(width);
    }

    public void setColumn(JTable jt, int columnIndex, int width) {
        jt.getColumnModel().getColumn(columnIndex).setWidth(width);
        jt.getColumnModel().getColumn(columnIndex).setMinWidth(width);
        jt.getColumnModel().getColumn(columnIndex).setMaxWidth(width);
    }

    /**
     * jl (JLabel); PathImg(EX:/imagens/logo.png);
     */
    public void settingImgJLabel(JLabel jl, String PathImg, Integer width, Integer height, Integer hights) {
        ImageIcon img = new ImageIcon(getClass().getResource(PathImg));
        img.setImage(img.getImage().getScaledInstance(width, height, hights));
        jl.setIcon(img);
    }

    /**
     * jt(JTable);resp(Ex; block reoordenation table = false: true)
     */
    public void settingReoodenationJTable(JTable jt, boolean resp) {
        jt.getTableHeader().setReorderingAllowed(resp);
    }

    public void mapearTeclas(Window window) {

        if (window.getName().equals("interfiscal")) {

            KeyStroke k1 = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
            ((JFrame) window).getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k1, "esc");
            ((JFrame) window).getRootPane().getActionMap().put("esc", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int result = JOptionPane.showConfirmDialog(null,"Deseja Realmente sair do sistema?",null,JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        window.dispose();
                        System.exit(0);
                    }
                }
            });

        }

        
        
    }

    /**
     * Capturar o tamanho da TELA
     */
    public Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void definirTamnhoCelularJtable(JTable jt, Class c, boolean type) {
        Constructor constructor;
        try {
            constructor = c.getConstructor(null);
            DefaultTableCellRenderer dtcr = (DefaultTableCellRenderer) constructor.newInstance(null);
            if (type) {
                jt.getTableHeader().setDefaultRenderer(dtcr);
            } else {
                jt.setDefaultRenderer(Object.class, dtcr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void iconApplication(Window w) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/icone.png"));
        icon.setImage(icon.getImage().getScaledInstance(32, 32, 100));
        w.setIconImage(icon.getImage());
    }
    public void iconApplicationInternalFrame(JInternalFrame w) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/icone-x4.png"));
        icon.setImage(icon.getImage().getScaledInstance(16, 16, 100));
        w.setFrameIcon(icon);
    }

    /**
     * 0= uppercase; 1=regularCase;2=number;
     */
    public void settingTxt(JTextField field, Integer maxLenght, Integer optionField) {
        field.setDocument(new LimitarField(maxLenght, optionField));
    }

//    public void telaRegistrada(String nameTela) {
//        Sessao.TELA = nameTela;
//    }

    public boolean possuiLetra(String text) {
        boolean temLetra = false;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isAlphabetic(text.charAt(i)) == true) {
                temLetra = true;
                break;
            }
        }
        return temLetra;
    }
    
    public Properties carregarArquivo(String path) throws FileNotFoundException, IOException {

        File database = new File(path);

        Properties props = new Properties();

        if (database.exists() == true) {

            FileInputStream fis = new FileInputStream(database);

            props.load(new StringReader(IOUtils.toString(fis, StandardCharsets.UTF_8).replace("\\", "/")));

            fis.close();

        }

        return props;

    }
}
