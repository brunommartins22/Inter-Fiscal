    package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.BaseInterage;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class BaseInterageTableModel extends AbstractTableModel<BaseInterage> {

    public String[] columns = new String[]{"Produto", "Cód Produto", "Cód Barra", "Pis/Cofins", "Icms Entrada", "Icms Saída", "Atualização Pis/Cofins", "Atualização IcmsEntrada", "Atualização IcmsSaida"};

    public BaseInterageTableModel(List<BaseInterage> list) {
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
        BaseInterage i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getNmProduto();
            }
            case 1: {
                return i.getCodProduto();
            }
            case 2: {
                return i.getCodBarras();
            }
            case 3: {
                return i.isPisCofins() ? "Alterado" : "Normal";
            }
            case 4: {
                return i.isIcmsEntrada() ? "Alterado" : "Normal";
            }
            case 5: {
                return i.isIcmsSaida() ? "Alterado" : "Normal";
            }
            case 6: {
                return i.getAtualizacaoPisCofins() == null ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getAtualizacaoPisCofins());
            }
            case 7: {
                return i.getAtualizacaoIcmsEntrada() == null ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getAtualizacaoIcmsEntrada());
            }
            case 8: {
                return i.getAtualizacaoIcmsSaida() == null ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getAtualizacaoIcmsSaida());
            }

            default:
        }
        return null;
    }

    public BaseInterage getFindCodList(String cod) {
        return this.list.stream().filter((BaseInterage t) -> {
            return Objects.equals(t.getCodProduto(), cod); //To change body of generated lambdas, choose Tools | Templates.
        }).findFirst().orElse(null);
    }

}
