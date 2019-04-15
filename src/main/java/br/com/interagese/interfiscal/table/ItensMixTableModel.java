package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.TableItensMix;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ItensMixTableModel extends AbstractTableModel<TableItensMix> {
    
    public String[] columns = new String[]{"Atualização", "Conteudo"};
    
    public ItensMixTableModel(List<TableItensMix> list) {
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
        TableItensMix i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getDataAtualizacao());
            }
            case 1: {
                return i.getConteudo();
            }
            default:
        }
        return null;
    }
    
    public TableItensMix getFindCodList(Date cod) {
        return this.list.stream().filter((TableItensMix t) -> {
            return Objects.equals(t.getDataAtualizacao(), cod); //To change body of generated lambdas, choose Tools | Templates.
        }).findFirst().orElse(null);
    }
    
}
