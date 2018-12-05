package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.utils.Actions;
import java.util.ArrayList;
import java.util.List;

@DataBase(getType = DataBase.dataBaseType.FIREBIRD)
public class FireTabproDao extends AbstractDaoCrud<Tabpro> {

    private Actions a = new Actions();

    @Override
    public Integer getMaxRowCount() {
        return 50;
    }

    public Object countImportacao(Integer codfil, Integer tipo) {
        try {
            String sql = "SELECT COUNT(*) FROM tabpro tp "
                    + "INNER JOIN tabprofil tpf ON tpf.codpro = tp.codpro "
                    + "LEFT JOIN tabproimp tpi ON tpi.codfil = tpf.codfil and tpi.codpro = tpf.codpro "
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

            String fields = "tp.descpro as nomeproduto,"
                    + "tp.codpro as codigoproduto,"
                    + "tp.codbarun as codigobarra,"
                    + "tp.codgen as genero,"
                    + "tp.clasfiscal as ncm,"
                    + "tpi.cfop as cfop,"
                    + "tp.cest as cest,"
                    + "tpi.icmscst as cst,"
                    + "tpi.icmspicms as icmssaida,"
                    + "tpf.cstpise as pisentrada,"
                    + "tpi.piscst as pissaida,"
                    + "tpf.cstcofinse as cofinsentrada,"
                    + "tpi.cofinscst as cofinssaida,"
                    + "tp.natpro as naturezaproduto,"
                    + "tpi.pisppis as aliquotapis,"
                    + "tpi.cofinspcofins as aliquotaCofins ";

            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + "from tabpro tp "
                        + "inner join tabprofil tpf on tpf.codpro = tp.codpro "
                        + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro "
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
                        imp.setCst(o[7] != null ? (String) o[7] : "");
                        imp.setIcmsSaida(o[8] != null ? (Double) o[8] : 0.0);
                        imp.setPisentrada(o[9] != null ? (String) o[9] : "");
                        imp.setPissaida(o[10] != null ? (String) o[10] : "");
                        imp.setCofinsentrada(o[11] != null ? (String) o[11] : "");
                        imp.setCofinssaida(o[12] != null ? (String) o[12] : "");
                        imp.setNaturezaproduto(o[13] != null ? (String) o[13] : "");
                        imp.setAliquotaPis(o[14] != null ? (Double) o[14] : 0.0);
                        imp.setAliquotaCofins(o[15] != null ? (Double) o[15] : 0.0);
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
            String sql = "select tp.descpro as nomeproduto,"
                    + "tp.codpro as codigoproduto,"
                    + "tp.codbarun as codigobarra,"
                    + "tp.codgen as genero,"
                    + "tp.clasfiscal as ncm,"
                    + "tpi.cfop as cfop,"
                    + "tp.cest as cest,"
                    + "tpi.icmscst as cst,"
                    + "tpi.icmspicms as icmssaida,"
                    + "tpf.cstpise as pisentrada,"
                    + "tpi.piscst as pissaida,"
                    + "tpf.cstcofinse as cofinsentrada,"
                    + "tpi.cofinscst as cofinssaida,"
                    + "tp.natpro as naturezaproduto,"
                    + "tpi.pisppis as aliquotapis,"
                    + "tpi.cofinspcofins as aliquotaCofins "
                    + "from tabpro tp "
                    + "inner join tabprofil tpf on tpf.codpro = tp.codpro "
                    + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro "
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
                    imp.setCst(o[7] != null ? (String) o[7] : "");
                    imp.setIcmsSaida(o[8] != null ? (Double) o[8] : 0.0);
                    imp.setPisentrada(o[9] != null ? (String) o[9] : "");
                    imp.setPissaida(o[10] != null ? (String) o[10] : "");
                    imp.setCofinsentrada(o[11] != null ? (String) o[11] : "");
                    imp.setCofinssaida(o[12] != null ? (String) o[12] : "");
                    imp.setNaturezaproduto(o[13] != null ? (String) o[13] : "");
                    imp.setAliquotaPis(o[14] != null ? (Double) o[14] : 0.0);
                    imp.setAliquotaCofins(o[15] != null ? (Double) o[15] : 0.0);
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

    public List<ImportacaoImp> getProdutobyDescorCod(Object obj, Integer codfil, Integer tipo) {
        try {
            List<ImportacaoImp> result = new ArrayList<>();
            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao(codfil, tipo)).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            String fields = "tp.descpro as nomeproduto,"
                    + "tp.codpro as codigoproduto,"
                    + "tp.codbarun as codigobarra,"
                    + "tp.codgen as genero,"
                    + "tp.clasfiscal as ncm,"
                    + "tpi.cfop as cfop,"
                    + "tp.cest as cest,"
                    + "tpi.icmscst as cst,"
                    + "tpi.icmspicms as icmssaida,"
                    + "tpf.cstpise as pisentrada,"
                    + "tpi.piscst as pissaida,"
                    + "tpf.cstcofinse as cofinsentrada,"
                    + "tpi.cofinscst as cofinssaida,"
                    + "tp.natpro as naturezaproduto,"
                    + "tpi.pisppis as aliquotapis,"
                    + "tpi.cofinspcofins as aliquotaCofins ";

            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + "from tabpro tp "
                        + "inner join tabprofil tpf on tpf.codpro = tp.codpro "
                        + "left join tabproimp tpi on tpi.codfil = tpf.codfil and tpi.codpro = tp.codpro "
                        + "where tpf.codfil = :codfil and tp.rgevento <> '3' and tp.stprod = 'A'";

                boolean resp = a.possuiLetra(obj.toString());
                if (!resp) {
                    sql += " and tp.codpro =" + Integer.parseInt(obj.toString()) + "";
                } else {
                    sql += " and (tp.codbarun ='" + obj.toString() + "' or tp.descpro like '%" + obj.toString().toUpperCase() + "%')";
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
                        imp.setCst(o[7] != null ? (String) o[7] : "");
                        imp.setIcmsSaida(o[8] != null ? (Double) o[8] : 0.0);
                        imp.setPisentrada(o[9] != null ? (String) o[9] : "");
                        imp.setPissaida(o[10] != null ? (String) o[10] : "");
                        imp.setCofinsentrada(o[11] != null ? (String) o[11] : "");
                        imp.setCofinssaida(o[12] != null ? (String) o[12] : "");
                        imp.setNaturezaproduto(o[13] != null ? (String) o[13] : "");
                        imp.setAliquotaPis(o[14] != null ? (Double) o[14] : 0.0);
                        imp.setAliquotaCofins(o[15] != null ? (Double) o[15] : 0.0);
                        result.add(imp);
                    });
                }
            }

            return result;
        } finally {
            getEntityManager().close();
        }
    }

}
