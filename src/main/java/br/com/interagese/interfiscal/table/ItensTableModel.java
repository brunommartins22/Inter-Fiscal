package br.com.interagese.interfiscal.table;

import br.com.interagese.interfiscal.entity.ImportacaoImp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ItensTableModel extends AbstractTableModel<ImportacaoImp> {

    public String[] columns = new String[]{"Cenário", "Código Produto", "Código Barras", "Produto", "Gênero", "NCM", "CFOP", "CEST", "CST Icms Entrada", "Alq. Icms Entrada", "Alq. ST Icms Entrada", "Rbc Icms Entrada", "Rbc ST Icms Entrada", "CST Icms Saída", "Alq. Icms Saída", "Alq. ST Icms Saída", "Rbc Icms Saída", "Rbc ST Icms Saída", "CST Pis Entrada", "Alq. Pis Entrada", "CST Pis Saída", "Alq. Pis Saída", "CST Cofins Entrada", "Alq. Cofins Entrada", "CST Cofins Saída", "Alq. Cofins Saída", "Natureza Produto", "Data Atualização"};

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
                return i.getTpImposDesc();
            }
            case 1: {
                return i.getCodigoproduto();
            }
            case 2: {
                return i.getCodigobarra();
            }
            case 3: {

                return i.getNomeproduto();
            }
            case 4: {
                return i.getGenero();
            }
            case 5: {
                return i.getNcm();
            }
            case 6: {
                return i.getCfop();
            }
            case 7: {
                return i.getCest();
            }
            case 8: {
                return i.getCstIcmsEntrada();
            }
            case 9: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaIcmsEntrada());
            }
            case 10: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaSTIcmsEntrada());
            }
            case 11: {
                return new DecimalFormat("#,##0.00").format(i.getRbcIcmsEntrada());
            }
            case 12: {
                return new DecimalFormat("#,##0.00").format(i.getRbcSTIcmsEntrada());
            }
            case 13: {
                return i.getCstIcmsSaida();
            }
            case 14: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaIcmsSaida());
            }
            case 15: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaSTIcmsSaida());
            }
            case 16: {
                return new DecimalFormat("#,##0.00").format(i.getRbcIcmsSaida());
            }
            case 17: {
                return new DecimalFormat("#,##0.00").format(i.getRbcSTIcmsSaida());
            }
            case 18: {
                return i.getCstPisEntrada();
            }
            case 19: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPisEntrada());
            }
            case 20: {
                return i.getCstPisSaida();
            }
            case 21: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPisSaida());
            }
            case 22: {
                return i.getCstCofinsEntrada();
            }
            case 23: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofinsEntrada());
            }
            case 24: {
                return i.getCstCofinsSaida();
            }
            case 25: {
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofinsSaida());
            }
            case 26: {
                return i.getNaturezaproduto();
            }
            case 27: {
                return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(i.getDataAtualizacao());
            }

            default:
        }
        return null;
    }

}
