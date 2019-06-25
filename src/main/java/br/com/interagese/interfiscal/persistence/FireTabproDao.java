package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.SincronizadorTable;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.TransformNativeQuery;
import java.text.SimpleDateFormat;
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
                    + "left JOIN tabproimp tpi ON tpi.codfil = tpf.codfil and tpi.codpro = tpf.codpro and (tpi.tpimpos='A' or tpi.tpimpos='D') "
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

    public List<ImportacaoImp> getImportacaoImpostosAll(Integer codfil, Integer tipo) throws InstantiationException, IllegalAccessException {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao(codfil, tipo)).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            String fields = "tpi.tpimpos as cenario,"
                    + "tp.descpro as nomeProduto,"
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
                        + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro and (tpi.tpimpos='A' or tpi.tpimpos='D') "
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

                    result.addAll(new TransformNativeQuery(ImportacaoImp.class).execute(query));

                }
            }

            return result;
        } finally {
            getEntityManager().close();
        }
    }

    public List<ImportacaoImp> getImportacaoImpostosLimit(Integer codfil, Integer tipo) throws InstantiationException, IllegalAccessException {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            String sql = "select tpi.tpimpos as cenario,"
                    + "tp.descpro as nomeProduto,"
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
                    + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro and (tpi.tpimpos='A' or tpi.tpimpos='D')  "
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

                result = new TransformNativeQuery(ImportacaoImp.class).execute(query);

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

    public List<ImportacaoImp> getProdutobyDescorCod(Object obj, Integer codfil, Integer tipo) throws InstantiationException, IllegalAccessException {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao(codfil, tipo)).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            String fields = "tpi.tpimpos as cenario"
                    + "tp.descpro as nomeProduto,"
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
                        + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro and tpi.tpimpos='D' "
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

                List<Object[]> query = getEntityManager().createNativeQuery(sql).setParameter("codfil", codfil).setFirstResult(i * 1000).setMaxResults(1000).getResultList();

                if (!query.isEmpty()) {

                    result = new TransformNativeQuery(ImportacaoImp.class).execute(query);

                }
            }

            return result;
        } finally {
            getEntityManager().close();
        }
    }

    public Object countImportacao() {
        try {
            String sql = "select count(*)"
                    + " from tabpro tp join tabprofil tpf on tpf.codfil = '2' and  tpf.codpro = tp.codpro ";

            return getEntityManager().createNativeQuery(sql).getSingleResult();
        } finally {
            getEntityManager().close();
        }
    }

    public List<SincronizadorTable> getCarregarALL() throws InstantiationException, IllegalAccessException {
        try {

            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao()).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            List<SincronizadorTable> result = new ArrayList<>();

            for (int i = 0; i < diference; i++) {

                String sql = "select tp.stprod,tp.codbarun as codbarun,tpf.codfil as codfil,tp.descpro as descpro,tpf.pratpro as pratpro, tpf.prvapro as prvapro"
                        + " from tabpro tp join tabprofil tpf on tpf.codfil = '2' and tpf.codpro = tp.codpro ";

                List<Object[]> query = getEntityManager().createNativeQuery(sql).setFirstResult(i * 1000).setMaxResults(1000).getResultList();

                if (!query.isEmpty()) {
                    result.addAll(new TransformNativeQuery(SincronizadorTable.class).execute(query));
                }

            }

            return result;
        } finally {
            getEntityManager().close();
        }

    }

    public void updateAll(String codbarun, Double prvapro, Double pratpro) {
        try {

            String sql = "update tabprofil "
                    + "set prvapro = '" + prvapro + "',"
                    + "pratpro = '" + pratpro + "',"
                    + "rgdata = '" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "',"
                    + "rgevento = '2' "
                    + "where codpro in (select codpro from tabpro where codbarun ='" + codbarun + "') and codfil = '2'";
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery(sql).executeUpdate();
            getEntityManager().getTransaction().commit();

        } finally {
            getEntityManager().close();
        }
    }

    public SincronizadorTable findByEan(String codbarun) {
        try {
            SincronizadorTable item = null;
            String sql = "select tp.stprod,tp.codbarun,tpf.codfil, tp.descpro,tpf.pratpro,tpf.prvapro from tabprofil tpf "
                    + "join tabpro tp on tp.codpro = tpf.codpro"
                    + "where tp.codbarun = '" + codbarun+ "' and tpf.codfil = '2'";
            return item;

        } finally {
            getEntityManager().close();
        }
    }

}
