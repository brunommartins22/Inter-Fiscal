package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.Piscofins;
import java.util.List;

public class piscofinsTableModel extends AbstractTableModel<Piscofins> {

    public String[] columns = new String[]{"Produto", "Cód Produto", "Cód Barra", "Ncm", "Ncm Exceção", "Cod Natureza Receita", "Crédito Presumido", "Pis Cst Entrada", "Pis Cst Saída", "Pis Alíquota Entrada", "Pis Alíquota Saída", "Cofins Cst Entrada", "Cofins Cst Saída", "Cofins Alíquota Entrada", "Cofins Alíquota Saída", "Fundamento Legal"};

    public piscofinsTableModel(List<Piscofins> list) {
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
        Piscofins i = getItem(rowIndex);
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
                return i.getNcm();
            }
            case 4: {
                return i.getNcmEx();
            }
            case 5: {
                return i.getCodNaturezaReceita();
            }
            case 6: {
                return i.getCreditoPresumido();
            }
            case 7: {
                return i.getPisCstE();
            }
            case 8: {
                return i.getPisCstS();
            }
            case 9: {
                return i.getPisAlqE();
            }
            case 10: {
                return i.getPisAlqS();
            }
            case 11: {
                return i.getCofinsCstE();
            }
            case 12: {
                return i.getCofinsCstS();
            }
            case 13: {
                return i.getCofinsAlqE();
            }
            case 14: {
                return i.getCofinsAlqS();
            }
            case 15: {
                return i.getFundamentoLegal();
            }
            default:
        }
        return null;
    }

}
