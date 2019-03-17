/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.utils;

import br.com.interagese.interfiscal.business.TabfilBusiness;
import br.com.interagese.interfiscal.business.TabfilBusinessBean;
import br.com.interagese.interfiscal.business.TabusuBusiness;
import br.com.interagese.interfiscal.business.TabusuBusinessBean;
import br.com.interagese.interfiscal.entity.Tabusu;

import java.awt.Window;
import java.io.InputStream;
import java.util.List;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author bruno
 */
public class DadosImpressao extends ReportBuilder {

    private Tabusu usuario;
    private TabusuBusiness tabusuBusiness = new TabusuBusinessBean();
    private TabfilBusiness tabfilBusiness = new TabfilBusinessBean();

    public DadosImpressao(InputStream pathJasper) {
        super(pathJasper);
    }

    @Override
    public JDialog buildJDialog(List beanList, Window parent) throws JRException {
        addParameter("empresa", tabfilBusiness.findById(1).getNomfil());
        return super.buildJDialog(beanList, parent);

    }

}
