package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.SincronizadorTable;
import java.util.List;
import java.util.Objects;

public class SincronizadorTableModel extends AbstractTableModel<SincronizadorTable> {

    public String[] columns = new String[]{"stprod", "codbarun", "codfil", "descpro", "pratpro", "prvapro"};

    public SincronizadorTableModel(List<SincronizadorTable> list) {
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
        SincronizadorTable i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getStprodDesc();
            }
            case 1: {
                return i.getCodbarun();
            }
            case 2: {
                return i.getCodfil();
            }
            case 3: {
                return i.getDescpro();
            }
            case 4: {
                return i.getPratpro();
            }
            case 5: {
                return i.getPrvapro();
            }

            default:
        }
        return null;
    }

    public SincronizadorTable getFindCodList(String codbarun) {
        return this.list.stream().filter((SincronizadorTable t) -> {
            return Objects.equals(t.getCodbarun(), codbarun); //To change body of generated lambdas, choose Tools | Templates.
        }).findFirst().orElse(null);
    }

}
