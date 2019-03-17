package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.ImportacaoImp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ItensTableModel extends AbstractTableModel<ImportacaoImp> {

    public String[] columns = new String[]{"Código Produto", "Código Barras", "Produto", "Gênero", "NCM", "CFOP", "CEST", "CST Icms Entrada", "Alq. Icms Entrada", "Alq. ST Icms Entrada", "Rbc Icms Entrada", "Rbc ST Icms Entrada", "CST Icms Saída", "Alq. Icms Saída", "Alq. ST Icms Saída", "Rbc Icms Saída", "Rbc ST Icms Saída", "CST Pis Entrada", "Alq. Pis Entrada", "CST Pis Saída", "Alq. Pis Saída", "CST Cofins Entrada", "Alq. Cofins Entrada", "CST Cofins Saída", "Alq. Cofins Saída", "Natureza Produto", "Data Atualização"};

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
                return i.getCodigoproduto();
            }
            case 1: {
                return i.getCodigobarra();
            }
            case 2: {

                return i.getNomeproduto();
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
                return i.getCstIcmsEntrada();
            }
            case 8: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaIcmsEntrada());
            }
            case 9: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaSTIcmsEntrada());
            }
            case 10: {
                return new DecimalFormat("#,##0.00").format(i.getRbcIcmsEntrada());
            }
            case 11: {
                return new DecimalFormat("#,##0.00").format(i.getRbcSTIcmsEntrada());
            }
            case 12: {
                return i.getCstIcmsSaida();
            }
            case 13: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaIcmsSaida());
            }
            case 14: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaSTIcmsSaida());
            }
            case 15: {
                return new DecimalFormat("#,##0.00").format(i.getRbcIcmsSaida());
            }
            case 16: {
                return new DecimalFormat("#,##0.00").format(i.getRbcSTIcmsSaida());
            }
            case 17: {
                return i.getCstPisEntrada();
            }
            case 18: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPisEntrada());
            }
            case 19: {
                return i.getCstPisSaida();
            }
            case 20: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPisSaida());
            }
            case 21: {
                return i.getCstCofinsEntrada();
            }
            case 22: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofinsEntrada());
            }
            case 23: {
                return i.getCstCofinsSaida();
            }
            case 24: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofinsSaida());
            }
            case 25: {
                return i.getNaturezaproduto();
            }
            case 26: {
                return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(i.getDataAtualizacao());
            }

            default:
        }
        return null;
    }

}
