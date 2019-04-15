package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.Fiscaltemp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class ServiceTableModel extends AbstractTableModel<Fiscaltemp> {

    public String[] columns = new String[]{"Cód Produto", "Cód Barra", "Produto", "Pis/Cofins", "Icms Entrada", "Icms Saída", "Atualização Pis/Cofins", "Atualização IcmsEntrada", "Atualização IcmsSaida"};

    public ServiceTableModel(List<Fiscaltemp> list) {
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
        Fiscaltemp i = getItem(rowIndex);
        switch (columnIndex) {
            case 0: {
                return i.getCodigoProduto() == null ? "" : i.getCodigoProduto();
            }
            case 1: {
                return i.getEan() == null ? "" : i.getEan();
            }
            case 2: {
                return i.getNomeProduto() == null ? "" : i.getNomeProduto();
            }
            case 3: {
                return i.getPisCofins() != null && i.getPisCofins() ? "Alterado" : "Normal";
            }
            case 4: {
                return i.getIcmsEntrada() != null && i.getIcmsEntrada() ? "Alterado" : "Normal";
            }
            case 5: {
                return i.getIcmsSaida() != null && i.getIcmsSaida() ? "Alterado" : "Normal";
            }
            case 6: {
                return i.getAtualizacaoPiscofins() == null || i.getAtualizacaoPiscofins().equals("") ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getAtualizacaoPiscofins());
            }
            case 7: {
                return i.getAtualizacaoIcmsentrada() == null || i.getAtualizacaoIcmsentrada().equals("") ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getAtualizacaoIcmsentrada());
            }
            case 8: {
                return i.getAtualizacaoIcmssaida() == null || i.getAtualizacaoIcmssaida().equals("") ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(i.getAtualizacaoIcmssaida());
            }

            default:
        }
        return null;
    }

    public Fiscaltemp getFindCodList(String cod) {
        return this.list.stream().filter((Fiscaltemp t) -> {
            return Objects.equals(t.getCodigoProduto(), cod);
        }).findFirst().orElse(null);
    }

}
