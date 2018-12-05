package br.com.interagese.interfiscal.message;

import java.net.URL;
import java.util.concurrent.Callable;
import javax.swing.JFrame;

public class OptionPane {
    
    public static int showQuestionDialog(String mensagem) {
        JDlgQuestion dialog = new JDlgQuestion(new JFrame(), true);
        dialog.setMensagem(mensagem);
        dialog.setVisible(true);
        dialog.setIcon(OptionPane.class.getClass().getResource("/imagens/question.png"));
        return dialog.getOption();
    }
    
    public static void showErrorDialog(String mensagem) {
        JDlgMessage error = new JDlgMessage(null);
        error.setIcon(OptionPane.class.getClass().getResource("/imagens/error.png"));
        error.setMensagem(mensagem);
        error.setTitulo("ERROR");
        error.showDialog(true);
    }
    
    public static void showMessageDialog(String titulo, String mensagem, URL icone, Callable<Void> metodo) {
        JDlgMessage dialog = new JDlgMessage(metodo);
        dialog.setTitulo(titulo);
        dialog.setMensagem(mensagem);
        dialog.setIcon(icone);
        dialog.showDialog(true);
    }
    
    public static void showMessageDialog(String titulo, String mensagem) {
        showMessageDialog(titulo, mensagem, null, null);
    }
    
    public static void showMessageDialog(String titulo, String mensagem, URL icone) {
        showMessageDialog(titulo, mensagem, icone, null);
    }
    
    public static Object showInputDialog(String titulo, String mensagem, URL icone) {
        JDlgInput dialog = new JDlgInput(true);
        dialog.setTitulo(titulo);
        dialog.setMensagem(mensagem);
        dialog.setIcon(icone);
        dialog.setVisible(true);
        return dialog.getValue();
    }
    
    public static Object showInputDialog(String titulo, String mensagem) {
        return showInputDialog(titulo, mensagem, null);
    }
        
}
