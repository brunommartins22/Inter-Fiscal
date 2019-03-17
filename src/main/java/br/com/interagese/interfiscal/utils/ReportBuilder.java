package br.com.interagese.interfiscal.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class ReportBuilder {

    private InputStream inputStream;
    private Map<String, Object> parameters = new HashMap();

    public ReportBuilder(InputStream relatorio) {
        inputStream = relatorio;
    }

    public ReportBuilder addParameter(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    public JRViewer buildJRViewer(List beanList) throws JRException {

        JasperPrint print = null;

        if (beanList != null) {
            print = JasperFillManager.fillReport(inputStream, parameters, new JRBeanCollectionDataSource(beanList));
        } else {
            print = JasperFillManager.fillReport(inputStream, parameters, new JREmptyDataSource());
        }

        JRViewer viewer = new JRViewer(print);

        return viewer;
    }

    public JDialog buildJDialog(List beanList, Window parent) throws JRException {

        JDialog dialog = new JDialog(parent);
        dialog.setLayout(new BorderLayout());

        JRViewer viewer = buildJRViewer(beanList);
        dialog.add(viewer, BorderLayout.CENTER);
        dialog.pack();
//        dialog.setSize(900,700);
        dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        dialog.setModal(true);
        return dialog;

    }

    public JFrame buildJFrame(List beanList) throws JRException {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JRViewer viewer = buildJRViewer(beanList);

        frame.add(viewer, BorderLayout.CENTER);

        return frame;

    }

}
