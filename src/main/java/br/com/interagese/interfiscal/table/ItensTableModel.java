package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.ImportacaoImp;
import java.text.DecimalFormat;
import java.util.List;

public class ItensTableModel extends AbstractTableModel<ImportacaoImp> {

    public String[] columns = new String[]{"Produto", "Código Produto", "Código Barras", "Gênero", "Ncm", "Cfop", "Cest", "Cst", "Icms Saída", "Pis Entrada", "Pis Saída", "Cofins Entrada", "Cofins Saída", "Natureza Produto", "Alíquota Pis", "Alíquota Cofins"};

    public ItensTableModel(List<ImportacaoImp> list) {
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ImportacaoImp i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getNomeproduto();
            }
            case 1: {
                return i.getCodigoproduto();
            }
            case 2: {
                return i.getCodigobarra();
            }
            case 3: {
                return i.getGenero();
            }
            case 4: {
                return i.getNcm();
            }
            case 5: {
                return i.getCfop();
            }
            case 6: {
                return i.getCest();
            }
            case 7: {
                return i.getCst();
            }
            case 8: {
                return new DecimalFormat("#,##0.00").format(i.getIcmsSaida());
            }
            case 9: {
                return i.getPisentrada();
            }
            case 10: {
                return i.getPissaida();
            }
            case 11: {
                return i.getCofinsentrada();
            }
            case 12: {
                return i.getCofinssaida();
            }
            case 13: {
                return i.getNaturezaproduto();
            }
            case 14: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPis());
            }
            case 15: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofins());
            }
            default:
        }
        return null;
    }

}
