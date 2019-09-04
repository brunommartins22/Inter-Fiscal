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
                //"Cenário"
                return i.getTpImposDesc();
            }
            case 1: {
                //"Código Produto"
                return i.getCodigoproduto();
            }
            case 2: {
                //"Código Barras"
                return i.getCodigobarra();
            }
            case 3: {
                //"Produto"
                return i.getNomeproduto();
            }
            case 4: {
                //"Gênero"
                return i.getGenero();
            }
            case 5: {
                //"NCM"
                return i.getNcm();
            }
            case 6: {
                //"CFOP"
                return i.getCfop();
            }
            case 7: {
                //"CEST"
                return i.getCest();
            }
            case 8: {
                //"CST Icms Entrada"
                return i.getCstIcmsEntrada();
            }
            case 9: {
                //"Alq. Icms Entrada"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaIcmsEntrada());
            }
            case 10: {
                //"Alq. ST Icms Entrada"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaSTIcmsEntrada());
            }
            case 11: {
                //"Rbc Icms Entrada"
                return new DecimalFormat("#,##0.00").format(i.getRbcIcmsEntrada());
            }
            case 12: {
                //"Rbc ST Icms Entrada"
                return new DecimalFormat("#,##0.00").format(i.getRbcSTIcmsEntrada());
            }
            case 13: {
                //"CST Icms Saída"
                return i.getCstIcmsSaida();
            }
            case 14: {
                //"Alq. Icms Saída"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaIcmsSaida());
            }
            case 15: {
                //"Alq. ST Icms Saída"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaSTIcmsSaida());
            }
            case 16: {
                //"Rbc Icms Saída"
                return new DecimalFormat("#,##0.00").format(i.getRbcIcmsSaida());
            }
            case 17: {
                //"Rbc ST Icms Saída"
                return new DecimalFormat("#,##0.00").format(i.getRbcSTIcmsSaida());
            }
            case 18: {
                //"CST Pis Entrada"
                return i.getCstPisEntrada();
            }
            case 19: {
                //"Alq. Pis Entrada"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPisEntrada());
            }
            case 20: {
                //"CST Pis Saída"
                return i.getCstPisSaida();
            }
            case 21: {
                //"Alq. Pis Saída"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaPisSaida());
            }
            case 22: {
                //"CST Cofins Entrada"
                return i.getCstCofinsEntrada();
            }
            case 23: {
                //"Alq. Cofins Entrada"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofinsEntrada());
            }
            case 24: {
                //"CST Cofins Saída"
                return i.getCstCofinsSaida();
            }
            case 25: {
                //"Alq. Cofins Saída"
                return new DecimalFormat("#,##0.00").format(i.getAliquotaCofinsSaida());
            }
            case 26: {
                //"Natureza Produto"
                return i.getNaturezaproduto();
            }
            case 27: {
                //"Data Atualização"
                return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(i.getDataAtualizacao());
            }

            default:
        }
        return null;
    }

}
