package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.Tabfil;
import java.util.List;

public class filialTableModel extends AbstractTableModel<Tabfil> {

    public String[] columns = new String[]{"Código Filial", "CRT", "CPF/CNPJ", "Filial",};

    public filialTableModel(List<Tabfil> list) {
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
        Tabfil i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getCodfil() == null ? "" : i.getCodfil();
            }
            case 1: {
                return i.getCrt() != null ? i.getCrt().toString().equals("1") ? "Simples Nacional" : "Regime Normal" : "";
            }
            case 2: {
                return i.getCnpjfil() != null ? i.getCnpjfil() : "";
            }
            case 3: {
                return i.getNomfil() != null ? i.getNomfil() : "";
            }
            default:
        }
        return null;
    }

}
