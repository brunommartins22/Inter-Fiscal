package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.Tabpro;
import java.util.List;

public class ProdutosTableModel extends AbstractTableModel<Tabpro> {

    public String[] columns = new String[]{"Icódigo Produto", "Código Barras", "Produto"};

    public ProdutosTableModel(List<Tabpro> list) {
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
        Tabpro i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getIcodpro();
            }
            case 1: {
                return i.getCodbarun();
            }
            case 2: {
                return i.getDescpro();
            }
            default:
        }
        return null;
    }

}
