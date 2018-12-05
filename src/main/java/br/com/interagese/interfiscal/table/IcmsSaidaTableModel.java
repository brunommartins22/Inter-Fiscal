package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.IcmsSaida;
import java.util.List;
import java.util.Objects;

public class IcmsSaidaTableModel extends AbstractTableModel<IcmsSaida> {

    public String[] columns = new String[]{"Produto", "Cód Produto", "Cód Barra", "Re 29560", "Cest", "Sac Cst", "Sac Alíquota", "Sac Alíquota ST", "Sac Rbc", "Sac Rbc ST", "Sas Cst", "Sas Alíquota", "Sas Alíquota ST", "Sas Rbc", "Sas Rbc ST", "Svc Cst", "Svc Alíquota","Svc Alíquota ST", "Svc Rbc", "Svc Rbc ST", "Snc Cst", "Snc Alíquota","Snc Alíquota ST","Snc Rbc", "Snc Rbc ST", "Sss Csosn","Svc Csosn", "Fecp", "Fundamento Legal"};

    public IcmsSaidaTableModel(List<IcmsSaida> list) {
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

//"Produto", "Código Produto", "Código Barras", "Re 29560", "Cest", "Sac Cst", 
//"Sac Alíquota", "Sac Alíquota ST", "Sac Rbc", "Sac Rbc ST", "Sas Cst", 
//"Sas Alíquota", "Sas Alíquota ST", "Sas Rbc", "Sas Rbc ST", "Svc Cst", 
//"Svc Alíquota","Svc Alíquota ST", "Svc Rbc", "Svc Rbc ST", "Snc Cst", 
//"Snc Alíquota","Snc Alíquota ST","Snc Rbc", "Snc Rbc ST", "Sss Csosn",
//"Svc Csosn", "Fecp", "Fundamento Legal"
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IcmsSaida i = getItem(rowIndex);
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
                return i.getRe29560();
            }
            case 4: {
                return i.getCest();
            }
            case 5: {
                return i.getSacCst();
            }
            case 6: {
                return i.getSacAlq();
            }
            case 7: {
                return i.getSacAlqst();
            }
            case 8: {
                return i.getSacRbc();
            }
            case 9: {
                return i.getSacRbcst();
            }
            case 10: {
                return i.getSasCst();
            }
            case 11: {
                return i.getSasAlq();
            }
            case 12: {
                return i.getSasAlqst();
            }
            case 13: {
                return i.getSasRbc();
            }
            case 14: {
                return i.getSasRbcst();
            }
            case 15: {
                return i.getSvcCst();
            }
            case 16:{
                return i.getSvcAlq();
            }
            case 17:{
                return i.getSvcAlqst();
            }
            case 18:{
                return i.getSvcRbc();
            }
            case 19:{
                return i.getSvcRbcst();
            }
            case 20:{
                return i.getSncCst();
            }
            case 21:{
                return i.getSncAlq();
            }
            case 22:{
                return i.getSncAlqst();
            }
            case 23:{
                return i.getSncRbc();
            }
            case 24:{
                return i.getSncRbcst();
            }
            case 25:{
                return i.getSssCsosn();
            }
            case 26:{
                return i.getSvcCsosn();
            }
            case 27:{
                return i.getFecp();
            }
            case 28:{
                return i.getFundamentoLegal();
            }
            default:
        }
        return null;
    }
    
    

}
