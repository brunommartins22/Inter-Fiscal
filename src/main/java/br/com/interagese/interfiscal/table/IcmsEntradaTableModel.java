package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.IcmsEntrada;
import java.util.List;

public class IcmsEntradaTableModel extends AbstractTableModel<IcmsEntrada> {

    public String[] columns = new String[]{"Produto", "Cód Produto", "Cód Barra", "Tipo MVA", "MVA", "MVA Distribuidor", "MVA Data Incial", "MVA Data Final", "Credito Outorgado", "Ei Cst", "Ei Alíquota", "Ei Alíquota ST", "Ei Rbc", "Ei Rbc ST", "Ed Cst", "Ed Alíquota","Ed Alíquota ST", "Ed Rbc", "Ed Rbc ST", "Es Cst", "Es Alíquota"," Es Alíquota ST","Es Rbc", "Es Rbc ST", "Nfi Cst", "Nfd Cst", "Nfs Csosn", "Nf Alíquota", "Fundamento Legal"};

    public IcmsEntradaTableModel(List<IcmsEntrada> list) {
        super(list);
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

//"Produto", "Código Produto", "Código Barras", "Tipo MVA", "MVA", "MVA Distribuidor",
//"MVA Data Incial", "MVA Data Final", "Credito Outorgado", "Ei Cst", "Ei Alíquota", "Ei Alíquota ST",
//"Ei Rbc", "Ei Rbc ST", "Ed Cst", "Ed Alíquota","Ed Alíquota ST", "Ed Rbc", "Ed Rbc ST", "Es Cst", 
//"Es Alíquota"," Es Alíquota ST","Es Rbc", "Es Rbc ST", "Nfi Cst", "Nfd Cst", "Nfs Csosn", 
//"Nf Alíquota", "Fundamento Legal"
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IcmsEntrada i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getNmProduto();
            }
            case 1: {
                return i.getCodigoProduto();
            }
            case 2: {
                return i.getEan();
            }
            case 3: {
                return i.getTipoMva();
            }
            case 4: {
                return i.getMva();
            }
            case 5: {
                return i.getMvaDistribuidor();
            }
            case 6: {
                return i.getMvaDataIni();
            }
            case 7: {
                return i.getMvaDataFim();
            }
            case 8: {
                return i.getCreditoOutorgado();
            }
            case 9: {
                return i.getEiCst();
            }
            case 10: {
                return i.getEiAlq();
            }
            case 11: {
                return i.getEiAlqst();
            }
            case 12: {
                return i.getEiRbc();
            }
            case 13: {
                return i.getEiRbcst();
            }
            case 14: {
                return i.getEdCst();
            }
            case 15: {
                return i.getEdAlq();
            }
            case 16:{
                return i.getEdAlqst();
            }
            case 17:{
                return i.getEdRbc();
            }
            case 18:{
                return i.getEdRbcst();
            }
            case 19:{
                return i.getEsCst();
            }
            case 20:{
                return i.getEsAlq();
            }
            case 21:{
                return i.getEsAlqst();
            }
            case 22:{
                return i.getEsRbc();
            }
            case 23:{
                return i.getEsRbcst();
            }
            case 24:{
                return i.getNfiCst();
            }
            case 25:{
                return i.getNfdCst();
            }
            case 26:{
                return i.getNfsCsosn();
            }
            case 27:{
                return i.getNfAlq();
            }
            case 28:{
                return i.getFundamentoLegal();
            }
            default:
        }
        return null;
    }

}
