/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import br.com.interagese.interfiscal.view.MixFiscal;
import java.awt.TrayIcon;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author bruno
 */
public class Sessao {

    public static JFrame frame;
    public static MixFiscal jInternalFrameMixfiscal;
    public static Tabpro produto;
    public static TrayIcon iconInformation;
    public static Integer infoHoras;
    public static Integer qtdEnvio;
    public static String manutencao;
    public static List<Tabfil> resultFilial;
    public static String bdFirebird;
    public static String userFirebird;
    public static String pwFirebird;
    public static String ipFirebird;
    public static String bdPostgres;
    public static String userPostgres;
    public static String pwPostgres;
    public static String ipPostgres;
    public static boolean mixfiscal;
    public static boolean importacaofiscal;
    public static boolean servidorfiscal;
    public static boolean isExecuting;

}
