package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.business.FireTabproBusinessBean;
import br.com.interagese.interfiscal.business.FireTabprofilBusinessBean;
import br.com.interagese.interfiscal.business.FireTabproimpBusinessBean;
import br.com.interagese.interfiscal.business.FireTabproimpeBusinessBean;
import br.com.interagese.interfiscal.business.IcmssaidaBusinessBean;
import br.com.interagese.interfiscal.business.PisCofinsBusinessBean;
import br.com.interagese.interfiscal.business.FireTabfilBusinessBean;
import br.com.interagese.interfiscal.business.TabproBusinessBean;
import br.com.interagese.interfiscal.business.TabprofilBusinessBean;
import br.com.interagese.interfiscal.business.TabproimpBusinessBean;
import br.com.interagese.interfiscal.business.TabproimpeBusinessBean;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.ImpTemp;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.entity.RestoreImp;
import br.com.interagese.interfiscal.entity.Tabfil;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.entity.Tabprofil;
import br.com.interagese.interfiscal.entity.Tabproimpe;
import br.com.interagese.interfiscal.utils.Actions;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.TypedQuery;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class fiscaltempDao extends AbstractDaoCrud<Fiscaltemp> {

    private Actions a = new Actions(null);
    //*************************** Firebird *************************************
    private FireTabproBusinessBean fireTabproBusiness = new FireTabproBusinessBean();
    private FireTabprofilBusinessBean fireTabprofilBusiness = new FireTabprofilBusinessBean();
    private FireTabproimpBusinessBean fireTabproimpBusiness = new FireTabproimpBusinessBean();
    private FireTabfilBusinessBean tabfilBusiness = new FireTabfilBusinessBean();
    private FireTabproimpeBusinessBean fireTabproimpeBusiness = new FireTabproimpeBusinessBean();
    //*************************** PostgreSql ***********************************
    private TabproBusinessBean tabproBusiness = new TabproBusinessBean();
    private TabprofilBusinessBean tabprofilBusiness = new TabprofilBusinessBean();
    private TabproimpBusinessBean tabproimpBusiness = new TabproimpBusinessBean();
    private TabproimpeBusinessBean tabproimpeBusiness = new TabproimpeBusinessBean();

    private PisCofinsBusinessBean pisCofinsBusiness = new PisCofinsBusinessBean();
    private IcmssaidaBusinessBean icmssaidaBusiness = new IcmssaidaBusinessBean();

    @Override
    public List<Fiscaltemp> getAll() {
        try {
            List<Fiscaltemp> result = super.getAll();

            Collections.sort(result, (o1, o2) -> {
                return o1.getCodigoProduto().compareTo(o2.getCodigoProduto()); //To change body of generated lambdas, choose Tools | Templates.
            });

            return result;
        } finally {
            getEntityManager().close();
        }
    }

    public List<Fiscaltemp> getListagemRelatorio(String codbar, Integer tipo, Date dataIncial, Date dataFinal) {
        try {
            SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = formata.format(dataIncial);
            String date2 = formata.format(dataFinal);
            String sql = "SELECT * FROM fiscaltemp ft WHERE";

            if (tipo != null) {
                switch (tipo) {
                    case 1: {
                        sql += " (ft.data_registro between '" + date1 + "' and '" + date2 + "') and ft.pis_cofins = '1' and ft.icms_entrada <> '1' and ft.icms_saida <> '1'";
                        break;
                    }
                    case 2: {
                        sql += " (ft.data_registro between '" + date1 + "' and '" + date2 + "') and ft.pis_cofins <> '1' and ft.icms_entrada = '1' and ft.icms_saida <> '1'";
                        break;
                    }
                    case 3: {
                        sql += " (ft.data_registro between '" + date1 + "' and '" + date2 + "') and ft.pis_cofins <> '1' and ft.icms_entrada <> '1' and ft.icms_saida = '1'";
                        break;
                    }
                    default: {
                        sql += " (ft.data_registro between '" + date1 + "' and '" + date2 + "')";
                        break;
                    }
                }
            }

            if (codbar != null && !codbar.isEmpty()) {
                sql += " and ft.ean='" + codbar + "'";
            }

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getEntityManager().createNativeQuery(sql + " order by ft.nome_produto", Fiscaltemp.class);
            System.out.println("sql = " + sql);

            return result.getResultList().isEmpty() ? null : result.getResultList();
        } finally {
            getEntityManager().close();
        }
    }

    public Fiscaltemp getExistItem(String codpro) {
        try {
            String sql = "SELECT * FROM fiscaltemp o WHERE o.codigo_produto = :codpro";

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getEntityManager().createNativeQuery(sql, Fiscaltemp.class).setParameter("codpro", codpro);

            if (result.getResultList().size() > 1) {

            }

            return result.getResultList().isEmpty() ? null : result.getResultList().get(0);
        } finally {
            getEntityManager().close();
        }
    }

    public List<Tabpro> getListTabproByCod(Integer codPro) {
        try {
            String sql = "SELECT * FROM tabpro o WHERE o.icodpro= :icodpro ";

            TypedQuery<Tabpro> query = (TypedQuery<Tabpro>) fireTabproBusiness.getDao().getEntityManager().createNativeQuery(sql, Tabpro.class).setParameter("icodpro", codPro);

            return query.getResultList();
        } finally {
            fireTabproBusiness.getDao().getEntityManager().close();
        }
    }

    public List<Tabprofil> getListTabprofilByCodProduto(Integer codfil, String codPro) {
        try {
            TypedQuery<Tabprofil> query = (TypedQuery<Tabprofil>) fireTabprofilBusiness.getDao().getEntityManager().createNativeQuery("SELECT * FROM Tabprofil o WHERE o.codfil= :codfil and o.codpro= :codpro", Tabprofil.class);
            query.setParameter("codfil", codfil);
            query.setParameter("codpro", codPro);

            return query.getResultList();
        } finally {
            fireTabprofilBusiness.getDao().getEntityManager().close();
        }
    }

    public List<ImpTemp> getExistTabproimp(String codpro) {
        try {

            String sql = "select tf.codfil ,"
                    + "(select count(*) from tabproimp tpi where tpi.codpro = :codpro and tpi.codfil=tf.codfil and tpi.tpimpos='A') as TpA,"
                    + "(select count(*) from tabproimp tpi where tpi.codpro = :codpro and tpi.codfil=tf.codfil and tpi.tpimpos='D') as TpD "
                    + "from tabfil tf where tf.mixfiscal='S'";

            List<Object[]> resultObject = fireTabproimpBusiness.getDao().getEntityManager().createNativeQuery(sql)
                    .setParameter("codpro", codpro).getResultList();

            List<ImpTemp> resultImp = new ArrayList<>();
            for (Object[] o : resultObject) {
                ImpTemp it = new ImpTemp();
                it.setCodfil(((Number) o[0]).intValue());
                it.setValorTpA(((Number) o[1]).intValue());
                it.setValorTpD(((Number) o[2]).intValue());
                resultImp.add(it);
                if (it.getValorTpA() != 0 && it.getValorTpD() != 0) {
                    it.setResultTpImpos("A,D");
                } else if (it.getValorTpA() != 0 && it.getValorTpD() == 0) {
                    it.setResultTpImpos("A");
                } else if (it.getValorTpA() == 0 && it.getValorTpD() != 0) {
                    it.setResultTpImpos("D");
                } else {
                    it.setResultTpImpos("");
                }
            }

            return resultImp;
        } finally {
            fireTabproimpBusiness.getDao().getEntityManager().close();
        }
    }

    public List<ImpTemp> getExistTabproimpE(String codpro) {
        try {
            String sql = "select tf.codfil as filial,"
                    + "(SELECT COUNT(*) FROM tabproimpe o where o.codigo_produto = :codpro and o.codigo_filial=tf.codfil) as valor"
                    + " from tabfil tf"
                    + " where tf.mixfiscal='S'";

            List<Object[]> resultObject = fireTabproimpeBusiness.getDao().getEntityManager().createNativeQuery(sql).setParameter("codpro", codpro).getResultList();

            List<ImpTemp> resultImpe = new ArrayList<>();

            for (Object[] o : resultObject) {
                ImpTemp it = new ImpTemp();
                it.setCodfil((Integer) o[0]);
                it.setValorImpe(((Number) o[1]).intValue());
                resultImpe.add(it);
            }

            return resultImpe;
        } finally {
            fireTabproimpeBusiness.getDao().getEntityManager().close();
        }
    }

    public Integer getCountExistTabproimpE(String codpro, Integer codfil) {
        try {
            String sql = "SELECT COUNT(*) FROM tabproimpe o where o.codigoProduto = :codpro and o.codigoFilial= :codfil";

            TypedQuery<Integer> result = (TypedQuery<Integer>) fireTabproimpeBusiness.getDao().getEntityManager().createNativeQuery(sql, Integer.class
            )
                    .setParameter("codpro", codpro)
                    .setParameter("codfil", codfil);

            return result.getSingleResult();

        } finally {
            fireTabproimpeBusiness.getDao().getEntityManager().close();
        }
    }

    public List<Fiscaltemp> getCarregarFiscalTemp(Date inicioOperacao) {
        try {

            SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String data1 = formata.format(inicioOperacao);

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getEntityManager().createNativeQuery("select * from fiscaltemp f where f.data_registro >= '" + data1 + "'", Fiscaltemp.class
            );

            return result.getResultList().isEmpty() ? null : result.getResultList();

        } finally {
            getEntityManager().close();
        }
    }

    public void getRestoreTributacaoMixFiscal(List<Fiscaltemp> resultRestore) {
        List<Piscofins> resultPisCofins = new ArrayList<>();
        List<IcmsSaida> resultIcmsSaida = new ArrayList<>();
        for (Fiscaltemp f : resultRestore) {
            if (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins()) {
                Piscofins p = new Piscofins();
                p.setCodigoProduto(new BigInteger(f.getCodigoProduto()));
                p.setEan(new BigInteger(f.getEan()));
                p.setNcm(f.getNcm());
                p.setCodNaturezaReceita(f.getCodNaturezaReceita());
                p.setPisCstE(f.getPisCstE());
                p.setPisCstS(f.getPisCstS());
                p.setPisAlqE(f.getPisAlqE());
                p.setPisAlqS(f.getPisAlqS());
                p.setCofinsCstE(f.getCofinsCstE());
                p.setCofinsCstS(f.getCofinsCstS());
                p.setCofinsAlqE(f.getCofinsAlqE());
                p.setCofinsAlqS(f.getCofinsAlqS());
                resultPisCofins.add(p);
            }
            if (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) {
                IcmsSaida is = new IcmsSaida();
                is.setCodigoProduto(new BigInteger(f.getCodigoProduto()));
                is.setEan(new BigInteger(f.getEan()));
                is.setSncCst(f.getSncCst());
                is.setSncAlq(f.getSncAlq());
                is.setSncAlqst(f.getSncAlqst());
                is.setSncRbc(f.getSncRbc());
                is.setSncRbcst(f.getSncRbcst());
                is.setSncCsosn(f.getSncCsosn());

                resultIcmsSaida.add(is);
            }
        }

        pisCofinsBusiness.insertList(resultPisCofins);
        icmssaidaBusiness.insertList(resultIcmsSaida);

    }

    public List<RestoreImp> getResultImpTemp(Integer codfil) {
        try {
            List<RestoreImp> result = new ArrayList<>();
            List<Object[]> resultImp = fireTabproimpBusiness.getDao().getEntityManager().createNativeQuery("select i.codpro,i.piscst,i.pisppis,i.cofinscst,i.cofinspcofins from tabproimp i where i.codfil = '" + codfil + "' and i.tpimpos='D'").getResultList();

            resultImp.forEach((o) -> {
                RestoreImp imp = new RestoreImp();
                imp.setCodpro(o[0] != null ? (String) o[0] : "");
                imp.setPisCst(o[1] != null ? (String) o[1] : "");
                imp.setAliquotaPis(o[2] != null ? (Double) o[2] : 0.0);
                imp.setCofinsCst(o[3] != null ? (String) o[3] : "");
                imp.setAliquotaCofins(o[4] != null ? (Double) o[4] : 0.0);
                result.add(imp);
            });

            return result;
        } finally {
            fireTabproimpBusiness.getDao().getEntityManager().close();
        }
    }

}
