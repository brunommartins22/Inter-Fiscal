package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.utils.Actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataBase(getType = DataBase.dataBaseType.FIREBIRD)
public class FireTabproDao extends AbstractDaoCrud<Tabpro> {

    private Actions a = new Actions(null);

    @Override
    public Integer getMaxRowCount() {
        return 50;
    }

    public Object countImportacao(Integer codfil, Integer tipo) {
        try {
            String sql = "SELECT COUNT(*) FROM tabpro tp "
                    + "INNER JOIN tabprofil tpf ON tpf.codpro = tp.codpro "
                    + "LEFT JOIN tabproimp tpi ON tpi.codfil = tpf.codfil and tpi.codpro = tpf.codpro "
                    + "LEFT JOIN tabproimpe tpe on tpe.CODIGO_PRODUTO = tp.codpro and tpf.codfil = tpe.CODIGO_FILIAL "
                    + "WHERE tpf.codfil = :codfil and tp.rgevento <> '3' and tp.stprod = 'A'";

            switch (tipo) {
                case 1: {
                    sql += " and (tpi.mixfiscal <>'S' or tpi.mixfiscal is null) and (tpf.mixfiscal <>'S' or tpf.mixfiscal is null)";
                    break;
                }
                case 2: {
                    sql += " and tpi.mixfiscal ='S' and tpf.mixfiscal='S'";
                    break;
                }
            }

            return getEntityManager().createNativeQuery(sql).setParameter("codfil", codfil).getSingleResult();
        } finally {
            getEntityManager().close();
        }
    }

    public List<ImportacaoImp> getImportacaoImpostosAll(Integer codfil, Integer tipo) {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao(codfil, tipo)).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            String fields = "tp.descpro as nomeProduto,"
                    + "tp.codpro as codigoProduto,"
                    + "tp.codbarun as codigoBarra,"
                    + "tp.codgen as genero,"
                    + "tp.clasfiscal as ncm,"
                    + "tpi.cfop as cfop,"
                    + "tp.cest as cest,"
                    + "tpe.es_cst as cstIcmsEntrada,"
                    + "tpe.es_alq as aliquotaIcmsEntrada,"
                    + "tpe.es_alqst as aliquotaSTIcmsEntrada,"
                    + "tpe.es_rbc as rbcIcmsEntrada,"
                    + "tpe.es_rbcst as rbcSTIcmsEntrada,"
                    + "tpi.icmscst as cstIcmsSaida,"
                    + "tpi.icmspicms as aliquotaIcmsSaida,"
                    + "tpi.icmspicmsst as aliquotaSTIcmsSaida,"
                    + "tpi.icmspredbc as rbcIcmsSaida,"
                    + "tpi.icmspredbcst as rbcSTIcmsSaida,"
                    + "tpf.cstpise as cstPisEntrada,"
                    + "tpf.fatorpiscom as aliquotaPisEntrada,"
                    + "tpi.piscst as cstPisSaida,"
                    + "tpi.pisppis as aliquotaPisSaida,"
                    + "tpf.cstcofinse as cstCofinsEntrada,"
                    + "tpf.fatorcofinscom as aliquotaCofinsEntrada,"
                    + "tpi.cofinscst as cstCofinsSaida,"
                    + "tpi.cofinspcofins as aliquotaCofinsSaida,"
                    + "tp.natpro as naturezaProduto,"
                    + "tpf.rgdata as dataAtualizacao ";

            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + "from tabpro tp "
                        + "inner join tabprofil tpf on tpf.codpro = tp.codpro "
                        + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro "
                        + "left join tabproimpe tpe on tpe.CODIGO_PRODUTO = tp.codpro and tpf.codfil = tpe.CODIGO_FILIAL "
                        + "where tpf.codfil = :codfil and tp.rgevento <> '3' and tp.stprod = 'A'";

                switch (tipo) {
                    case 1: {
                        sql += " and (tpi.mixfiscal <>'S' or tpi.mixfiscal is null) and (tpf.mixfiscal <>'S' or tpf.mixfiscal is null)";
                        break;
                    }
                    case 2: {
                        sql += " and tpi.mixfiscal ='S' and tpf.mixfiscal='S'";
                        break;
                    }
                }

                List<Object[]> query = getEntityManager().createNativeQuery(sql).setParameter("codfil", codfil).setFirstResult(i * 1000).setMaxResults(1000).getResultList();

                if (!query.isEmpty()) {
                    query.forEach((o) -> {
                        ImportacaoImp imp = new ImportacaoImp();

                        imp.setNomeproduto(o[0] != null ? (String) o[0] : "");
                        imp.setCodigoproduto(o[1] != null ? (String) o[1] : "");
                        imp.setCodigobarra(o[2] != null ? (String) o[2] : "");
                        imp.setGenero(o[3] != null ? (String) o[3] : "");
                        imp.setNcm(o[4] != null ? (String) o[4] : "");
                        imp.setCfop(o[5] != null ? (String) o[5] : "");
                        imp.setCest(o[6] != null ? (String) o[6] : "");
                        imp.setCstIcmsEntrada(o[7] != null ? (String) o[7] : "");
                        imp.setAliquotaIcmsEntrada(o[8] != null ? (Double) o[8] : 0.0);
                        imp.setAliquotaSTIcmsEntrada(o[9] != null ? (Double) o[9] : 0.0);
                        imp.setRbcIcmsEntrada(o[10] != null ? (Double) o[10] : 0.0);
                        imp.setRbcSTIcmsEntrada(o[11] != null ? (Double) o[11] : 0.0);
                        imp.setCstIcmsSaida(o[12] != null ? (String) o[12] : "");
                        imp.setAliquotaIcmsSaida(o[13] != null ? (Double) o[13] : 0.0);
                        imp.setAliquotaSTIcmsSaida(o[14] != null ? (Double) o[14] : 0.0);
                        imp.setRbcIcmsSaida(o[15] != null ? (Double) o[15] : 0.0);
                        imp.setRbcSTIcmsSaida(o[16] != null ? (Double) o[16] : 0.0);
                        imp.setCstPisEntrada(o[17] != null ? (String) o[17] : "");
                        imp.setAliquotaPisEntrada(o[18] != null ? (Double) o[18] : 0.0);
                        imp.setCstPisSaida(o[19] != null ? (String) o[19] : "");
                        imp.setAliquotaPisSaida(o[20] != null ? (Double) o[20] : 0.0);
                        imp.setCstCofinsEntrada(o[21] != null ? (String) o[21] : "");
                        imp.setAliquotaCofinsEntrada(o[22] != null ? (Double) o[22] : 0.0);
                        imp.setCstCofinsSaida(o[23] != null ? (String) o[23] : "");
                        imp.setAliquotaCofinsSaida(o[24] != null ? (Double) o[24] : 0.0);
                        imp.setNaturezaproduto(o[25] != null ? (String) o[25] : "");
                        imp.setDataAtualizacao(o[26] != null ? (Date) o[26] : null);

                        result.add(imp);
                    });
                }
            }

            return result;
        } finally {
            getEntityManager().close();
        }
    }

    public List<ImportacaoImp> getImportacaoImpostosLimit(Integer codfil, Integer tipo) {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            String sql = "select tp.descpro as nomeProduto,"
                    + "tp.codpro as codigoProduto,"
                    + "tp.codbarun as codigoBarra,"
                    + "tp.codgen as genero,"
                    + "tp.clasfiscal as ncm,"
                    + "tpi.cfop as cfop,"
                    + "tp.cest as cest,"
                    + "tpe.es_cst as cstIcmsEntrada,"
                    + "tpe.es_alq as aliquotaIcmsEntrada,"
                    + "tpe.es_alqst as aliquotaSTIcmsEntrada,"
                    + "tpe.es_rbc as rbcIcmsEntrada,"
                    + "tpe.es_rbcst as rbcSTIcmsEntrada,"
                    + "tpi.icmscst as cstIcmsSaida,"
                    + "tpi.icmspicms as aliquotaIcmsSaida,"
                    + "tpi.icmspicmsst as aliquotaSTIcmsSaida,"
                    + "tpi.icmspredbc as rbcIcmsSaida,"
                    + "tpi.icmspredbcst as rbcSTIcmsSaida,"
                    + "tpf.cstpise as cstPisEntrada,"
                    + "tpf.fatorpiscom as aliquotaPisEntrada,"
                    + "tpi.piscst as cstPisSaida,"
                    + "tpi.pisppis as aliquotaPisSaida,"
                    + "tpf.cstcofinse as cstCofinsEntrada,"
                    + "tpf.fatorcofinscom as aliquotaCofinsEntrada,"
                    + "tpi.cofinscst as cstCofinsSaida,"
                    + "tpi.cofinspcofins as aliquotaCofinsSaida,"
                    + "tp.natpro as naturezaProduto,"
                    + "tpf.rgdata as dataAtualizacao "
                    + "from tabpro tp "
                    + "inner join tabprofil tpf on tpf.codpro = tp.codpro "
                    + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro "
                    + "left join tabproimpe tpe on tpe.CODIGO_PRODUTO = tp.codpro and tpf.codfil = tpe.CODIGO_FILIAL "
                    + "where tpf.codfil = :codfil and tp.rgevento <> '3' and tp.stprod = 'A'";

            switch (tipo) {
                case 1: {
                    sql += " and (tpi.mixfiscal <>'S' or tpi.mixfiscal is null) and (tpf.mixfiscal <>'S' or tpf.mixfiscal is null)";
                    break;
                }
                case 2: {
                    sql += " and tpi.mixfiscal ='S' and tpf.mixfiscal='S'";
                    break;
                }
            }
            List<Object[]> query = getEntityManager().createNativeQuery(sql).setParameter("codfil", codfil).setMaxResults(1000).getResultList();

            if (!query.isEmpty()) {
                query.forEach((o) -> {
                    ImportacaoImp imp = new ImportacaoImp();

                    imp.setNomeproduto(o[0] != null ? (String) o[0] : "");
                    imp.setCodigoproduto(o[1] != null ? (String) o[1] : "");
                    imp.setCodigobarra(o[2] != null ? (String) o[2] : "");
                    imp.setGenero(o[3] != null ? (String) o[3] : "");
                    imp.setNcm(o[4] != null ? (String) o[4] : "");
                    imp.setCfop(o[5] != null ? (String) o[5] : "");
                    imp.setCest(o[6] != null ? (String) o[6] : "");
                    imp.setCstIcmsEntrada(o[7] != null ? (String) o[7] : "");
                    imp.setAliquotaIcmsEntrada(o[8] != null ? (Double) o[8] : 0.0);
                    imp.setAliquotaSTIcmsEntrada(o[9] != null ? (Double) o[9] : 0.0);
                    imp.setRbcIcmsEntrada(o[10] != null ? (Double) o[10] : 0.0);
                    imp.setRbcSTIcmsEntrada(o[11] != null ? (Double) o[11] : 0.0);
                    imp.setCstIcmsSaida(o[12] != null ? (String) o[12] : "");
                    imp.setAliquotaIcmsSaida(o[13] != null ? (Double) o[13] : 0.0);
                    imp.setAliquotaSTIcmsSaida(o[14] != null ? (Double) o[14] : 0.0);
                    imp.setRbcIcmsSaida(o[15] != null ? (Double) o[15] : 0.0);
                    imp.setRbcSTIcmsSaida(o[16] != null ? (Double) o[16] : 0.0);
                    imp.setCstPisEntrada(o[17] != null ? (String) o[17] : "");
                    imp.setAliquotaPisEntrada(o[18] != null ? (Double) o[18] : 0.0);
                    imp.setCstPisSaida(o[19] != null ? (String) o[19] : "");
                    imp.setAliquotaPisSaida(o[20] != null ? (Double) o[20] : 0.0);
                    imp.setCstCofinsEntrada(o[21] != null ? (String) o[21] : "");
                    imp.setAliquotaCofinsEntrada(o[22] != null ? (Double) o[22] : 0.0);
                    imp.setCstCofinsSaida(o[23] != null ? (String) o[23] : "");
                    imp.setAliquotaCofinsSaida(o[24] != null ? (Double) o[24] : 0.0);
                    imp.setNaturezaproduto(o[25] != null ? (String) o[25] : "");
                    imp.setDataAtualizacao(o[26] != null ? (Date) o[26] : null);

                    result.add(imp);
                });
            }

            return result;

        } finally {
            getEntityManager().close();
        }
    }

    public void getGerarEanbyCodigo() {
        try {
            String sql = "insert into tabprocod(codpro, codigo, unid, qtdun, vldesconto)"
                    + " select codpro, codbarun as codigo, unidade as unid, 1 as qtdun, 0 as vldesconto from tabpro"
                    + " where codbarun is not null and codbarun <> '' and codbarun not in(select distinct(codigo) from tabprocod)";
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery(sql).executeUpdate();
            getEntityManager().getTransaction().commit();
        } finally {
            getEntityManager().close();
        }
    }

    public Tabpro getProdutoByCod(Integer icodpro) {
        try {
            return (Tabpro) getEntityManager().createNativeQuery("Select * from tabpro tp where tp.icodpro =" + icodpro, Tabpro.class).getSingleResult();
        } finally {
            getEntityManager().close();
        }
    }

    public List<Object[]> getProdutobyDescorCod(Object obj, Integer codfil, Integer tipo) {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao(codfil, tipo)).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            String fields = "tp.descpro as nomeProduto,"
                    + "tp.codpro as codigoProduto,"
                    + "tp.codbarun as codigoBarra,"
                    + "tp.codgen as genero,"
                    + "tp.clasfiscal as ncm,"
                    + "tpi.cfop as cfop,"
                    + "tp.cest as cest,"
                    + "tpe.es_cst as cstIcmsEntrada,"
                    + "tpe.es_alq as aliquotaIcmsEntrada,"
                    + "tpe.es_alqst as aliquotaSTIcmsEntrada,"
                    + "tpe.es_rbc as rbcIcmsEntrada,"
                    + "tpe.es_rbcst as rbcSTIcmsEntrada,"
                    + "tpi.icmscst as cstIcmsSaida,"
                    + "tpi.icmspicms as aliquotaIcmsSaida,"
                    + "tpi.icmspicmsst as aliquotaSTIcmsSaida,"
                    + "tpi.icmspredbc as rbcIcmsSaida,"
                    + "tpi.icmspredbcst as rbcSTIcmsSaida,"
                    + "tpf.cstpise as cstPisEntrada,"
                    + "tpf.fatorpiscom as aliquotaPisEntrada,"
                    + "tpi.piscst as cstPisSaida,"
                    + "tpi.pisppis as aliquotaPisSaida,"
                    + "tpf.cstcofinse as cstCofinsEntrada,"
                    + "tpf.fatorcofinscom as aliquotaCofinsEntrada,"
                    + "tpi.cofinscst as cstCofinsSaida,"
                    + "tpi.cofinspcofins as aliquotaCofinsSaida,"
                    + "tp.natpro as naturezaProduto,"
                    + "tpf.rgdata as dataAtualizacao ";

            List<Object[]> query = new ArrayList<>();
            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + "from tabpro tp "
                        + "inner join tabprofil tpf on tpf.codpro = tp.codpro "
                        + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro "
                        + "left join tabproimpe tpe on tpe.CODIGO_PRODUTO = tp.codpro and tpf.codfil = tpe.CODIGO_FILIAL "
                        + "where tpf.codfil = :codfil and tp.rgevento <> '3' and tp.stprod = 'A'";

                if (!obj.toString().equals("")) {
                    boolean resp = a.possuiLetra(obj.toString());
                    if (!resp && obj.toString().length() <= 6) {
                        sql += " and tp.codpro ='" + obj.toString() + "'";
                    } else {
                        sql += " and (tp.codbarun ='" + obj.toString() + "' or tp.descpro like '%" + obj.toString().toUpperCase() + "%')";
                    }
                }

                switch (tipo) {
                    case 1: {
                        sql += " and (tpi.mixfiscal <>'S' or tpi.mixfiscal is null) and (tpf.mixfiscal <>'S' or tpf.mixfiscal is null)";
                        break;
                    }
                    case 2: {
                        sql += " and tpi.mixfiscal ='S' and tpf.mixfiscal='S'";
                        break;
                    }
                }

                sql += " order by tp.descpro";
                System.out.println("sql=" + sql);
                query.addAll(getEntityManager().createNativeQuery(sql).setParameter("codfil", codfil).setFirstResult(i * 1000).setMaxResults(1000).getResultList());
            }

            return query;
        } finally {
            getEntityManager().close();
        }
    }

}
