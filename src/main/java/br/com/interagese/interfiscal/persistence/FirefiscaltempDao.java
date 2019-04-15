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
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.entity.Tabprofil;
import br.com.interagese.interfiscal.entity.Tabproimp;
import br.com.interagese.interfiscal.entity.Tabproimpe;
import br.com.interagese.interfiscal.utils.Actions;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

@DataBase(getType = DataBase.dataBaseType.FIREBIRD)
public class FirefiscaltempDao extends AbstractDaoCrud<Fiscaltemp> {

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
            String sql = "SELECT * FROM fiscaltemp ft WHERE";

            if (tipo != null) {
                switch (tipo) {
                    case 1: {
                        sql += " (ft.atualizacao_piscofins between '" + formata.format(dataIncial) + "' and '" + formata.format(dataFinal) + "') and ft.pis_cofins = '1' and ft.icms_entrada <> '1' and ft.icms_saida <> '1'";
                        break;
                    }
                    case 2: {
                        sql += " (ft.atualizacao_icmsentrada between '" + formata.format(dataIncial) + "' and '" + formata.format(dataFinal) + "') and ft.pis_cofins <> '1' and ft.icms_entrada = '1' and ft.icms_saida <> '1'";
                        break;
                    }
                    case 3: {
                        sql += " (ft.atualizacao_icmssaida between '" + formata.format(dataIncial) + "' and '" + formata.format(dataFinal) + "') and ft.pis_cofins <> '1' and ft.icms_entrada <> '1' and ft.icms_saida = '1'";
                        break;
                    }
                    default: {
                        sql += " (ft.atualizacao_piscofins between '" + formata.format(dataIncial) + "' and '" + formata.format(dataFinal) + "') "
                                + "or (ft.atualizacao_icmsentrada between '" + formata.format(dataIncial) + "' and '" + formata.format(dataFinal) + "') "
                                + "or (ft.atualizacao_icmssaida between '" + formata.format(dataIncial) + "' and '" + formata.format(dataFinal) + "')";
                        break;
                    }
                }
            }

            if (codbar != null && !codbar.isEmpty()) {
                sql += " and ft.ean='" + codbar + "'";
            }

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getEntityManager().createNativeQuery(sql + " order by ft.nome_produto", Fiscaltemp.class);
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

    public List<Tabprofil> getListTabprofilByCodProduto(String codPro) {
        try {
            TypedQuery<Tabprofil> query = (TypedQuery<Tabprofil>) fireTabprofilBusiness.getDao().getEntityManager().createNativeQuery("SELECT * FROM Tabprofil o WHERE o.codpro= :codpro", Tabprofil.class);
            query.setParameter("codpro", codPro);

            return query.getResultList();
        } finally {
            fireTabprofilBusiness.getDao().getEntityManager().close();
        }
    }

    public List<Tabproimp> getExistTabproimp(String codpro, Integer codfil, String tpImpA, String tpimpD) {
        try {
            String sql = "SELECT * FROM tabproimp o WHERE o.codfil= :codfil and o.codpro= :codpro and (o.tpimpos= :tpimpA or o.tpimpos= :tpimpD)";

            TypedQuery<Tabproimp> result = (TypedQuery<Tabproimp>) fireTabproimpBusiness.getDao().getEntityManager().createNativeQuery(sql, Tabproimp.class)
                    .setParameter("codfil", codfil)
                    .setParameter("codpro", codpro)
                    .setParameter("tpimpA", tpImpA)
                    .setParameter("tpimpD", tpimpD);

            return result.getResultList();
        } finally {
            fireTabproimpBusiness.getDao().getEntityManager().close();
        }
    }

    public List<Tabproimpe> getExistTabproimpE(String codpro, Integer codfil) {
        try {
            String sql = "SELECT * FROM tabproimpe o where o.codigoProduto = :codpro and o.codigoFilial= :codfil";

            TypedQuery<Tabproimpe> result = (TypedQuery<Tabproimpe>) fireTabproimpeBusiness.getDao().getEntityManager().createNativeQuery(sql, Tabproimpe.class)
                    .setParameter("codpro", codpro)
                    .setParameter("codfil", codfil);

            return result.getResultList();

        } finally {
            fireTabproimpeBusiness.getDao().getEntityManager().close();
        }
    }

    public List<Fiscaltemp> getCarregarFiscalTemp(List<Piscofins> pcs, List<IcmsEntrada> ies, List<IcmsSaida> iss) {
        try {
            List<Fiscaltemp> resultInsert = new ArrayList<>();
            List<Fiscaltemp> resultUpate = new ArrayList<>();

            for (Piscofins pc : pcs) {
                Fiscaltemp f = getExistItem(pc.getCodigoProduto().toString());
                if (f == null) {
                    f = new Fiscaltemp();
                    resultInsert.add(f);
                } else {
                    resultUpate.add(f);
                }
                f.setNomeProduto(pc.getNmProduto());
                f.setCodigoProduto(pc.getCodigoProduto().toString());
                f.setEan(pc.getEan() != null ? pc.getEan().toString() : "");
                f.setNcm(pc.getNcm());
                f.setNcmEx(pc.getNcmEx());
                f.setCodNaturezaReceita(pc.getCodNaturezaReceita());
                f.setCreditoPresumido(pc.getCreditoPresumido());
                f.setPisCstE(pc.getPisCstE());
                f.setPisCstS(pc.getPisCstS());
                f.setPisAlqE(pc.getPisAlqE());
                f.setPisAlqS(pc.getPisAlqS());
                f.setCofinsCstE(pc.getCofinsCstE());
                f.setCofinsCstS(pc.getCofinsCstS());
                f.setCofinsAlqE(pc.getCofinsAlqE());
                f.setCofinsAlqS(pc.getCofinsAlqS());
                f.setFundamentoLegal(pc.getFundamentoLegal());
                f.setPisCofins(true);
                f.setAtualizacaoPiscofins(new Date());
                f.setDataRegistro(new Date());

            }

            for (IcmsEntrada ie : ies) {
                Fiscaltemp f = getExistItem(ie.getCodigoProduto().toString());
                if (f == null) {
                    f = new Fiscaltemp();
                    resultInsert.add(f);
                } else {
                    resultUpate.add(f);
                }

                f.setNomeProduto(ie.getNmProduto());
                f.setCodigoProduto(ie.getCodigoProduto().toString());
                f.setEan(ie.getEan() != null ? ie.getEan().toString() : "");
                f.setTipoMva(ie.getTipoMva());
                f.setMva(ie.getMva());
                f.setMvaDistribuidor(ie.getMvaDistribuidor());
                f.setMvaDataIni(ie.getMvaDataIni());
                f.setMvaDataFim(ie.getMvaDataFim());
                f.setCreditoOutorgado(ie.getCreditoOutorgado());
                f.setEiCst(ie.getEiCst());
                f.setEiAlq(ie.getEiAlq());
                f.setEiAlqst(ie.getEiAlqst());
                f.setEiRbc(ie.getEiRbc());
                f.setEiRbcst(ie.getEiRbcst());
                f.setEdCst(ie.getEdCst());
                f.setEdAlq(ie.getEdAlq());
                f.setEdAlqst(ie.getEdAlqst());
                f.setEdRbc(ie.getEdRbc());
                f.setEdRbcst(ie.getEdRbcst());
                f.setEsCst(ie.getEsCst());
                f.setEsAlq(ie.getEsAlq());
                f.setEsAlqst(ie.getEsRbc());
                f.setEsRbc(ie.getEsRbc());
                f.setEsRbcst(ie.getEsRbcst());
                f.setNfiCst(ie.getNfiCst());
                f.setNfdCst(ie.getNfdCst());
                f.setNfsCsosn(ie.getNfsCsosn());
                f.setNfAlq(ie.getNfAlq());
                f.setFundamentoLegal(ie.getFundamentoLegal());
                f.setIcmsEntrada(true);
                f.setAtualizacaoIcmsentrada(new Date());
                f.setDataRegistro(new Date());

            }

            for (IcmsSaida is : iss) {
                Fiscaltemp f = getExistItem(is.getCodigoProduto().toString());
                if (f == null) {
                    f = new Fiscaltemp();
                    resultInsert.add(f);
                } else {
                    resultUpate.add(f);
                }

                f.setNomeProduto(is.getNmProduto());
                f.setCodigoProduto(is.getCodigoProduto().toString());
                f.setEan(is.getEan() != null ? is.getEan().toString().trim() : "");
                f.setRe29560(is.getRe29560() != null ? is.getRe29560().intValue() : null);
                f.setCest(is.getCest());
                f.setSacCst(is.getSacCst());
                f.setSacAlq(is.getSacAlq());
                f.setSacAlqst(is.getSacAlqst());
                f.setSacRbc(is.getSacRbc());
                f.setSacRbcst(is.getSacRbcst());
                f.setSasCst(is.getSasCst());
                f.setSasAlq(is.getSasAlq());
                f.setSasAlqst(is.getSasAlqst());
                f.setSasRbc(is.getSasRbc());
                f.setSasRbcst(is.getSasRbcst());
                f.setSvcCst(is.getSvcCst());
                f.setSvcAlq(is.getSvcAlq());
                f.setSvcAlqst(is.getSvcAlqst());
                f.setSvcRbc(is.getSvcRbc());
                f.setSvcRbcst(is.getSvcRbcst());
                f.setSncCst(is.getSncCst());
                f.setSncAlq(is.getSncAlq());
                f.setSncAlqst(is.getSncAlqst());
                f.setSncRbc(is.getSncRbc());
                f.setSncRbcst(is.getSncRbcst());
                f.setSssCsosn(is.getSssCsosn());
                f.setSvcCsosn(is.getSvcCsosn());
                f.setSncCsosn(is.getSncCsosn());
                f.setFecp(is.getFecp());
                f.setFundamentoLegal(is.getFundamentoLegal());
                f.setIcmsSaida(true);
                f.setAtualizacaoIcmssaida(new Date());
                f.setDataRegistro(new Date());

            }

            if (!resultInsert.isEmpty()) {
                super.insertList(resultInsert);
            }

            if (!resultUpate.isEmpty()) {
                super.updateList(resultUpate);
            }
            SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
            String data1 = formata.format(new Date()) + " 00:00";
            String data2 = formata.format(new Date()) + " 23:59";

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getEntityManager().createNativeQuery("select * from fiscaltemp f where f.data_registro between '" + data1 + "' and '" + data2 + "'", Fiscaltemp.class);

            return result.getResultList().isEmpty() ? null : result.getResultList();

        } finally {
            getEntityManager().close();
        }
    }

    public void getGerarTributos(List<Fiscaltemp> resultTemp) {

        List<Tabpro> resultTabproUpdate = new ArrayList<>();

        List<Tabprofil> resultTabprofilUpdate = new ArrayList<>();

        List<Tabproimp> resultTabproimpInsert = new ArrayList<>();
        List<Tabproimp> resultTabproimpUpdate = new ArrayList<>();

        List<Tabproimpe> resultTabproimpeInsert = new ArrayList<>();
        List<Tabproimpe> resultTabproimpeUpdate = new ArrayList<>();

        for (Fiscaltemp f : resultTemp) {
            List<Tabpro> resultTabpro = getListTabproByCod(Integer.parseInt(f.getCodigoProduto()));
            for (Tabpro produto : resultTabpro) {
                String codpro = produto.getCodpro();
                //******** TABPRO **********************************************
                //********************** Pis Cofins ****************************
                produto.setClasfiscal(f.getNcm() != null && !f.getNcm().equals("") ? f.getNcm() : produto.getClasfiscal());
                produto.setCodbarun(f.getEan() != null ? f.getEan() : "");
                produto.setFatorpis(f.getPisAlqE());
                produto.setFatorcofins(f.getCofinsAlqE());

                //********************** Icms Saída ****************************
                produto.setCest((f.getCest() != null && !f.getCest().isEmpty()) ? f.getCest().replace(".", "") : produto.getCest());
                produto.setCst(f.getSncCsosn() != null && !f.getSncCsosn().equals("") ? f.getSncCsosn() : f.getSncCst());
                produto.setIndice((f.getSncCsosn() != null && !f.getSncCsosn().equals("") && f.getSncCsosn().equals("500")) || (f.getSncCst() != null && !f.getSncCst().equals("") && (f.getSncCst().equals("60") || f.getSncCst().equals("70"))) ? "SS" : (f.getSncCsosn() != null && (f.getSncCsosn().equals("400") || f.getSncCsosn().equals("401"))) || (f.getSncCst() != null && (f.getSncCst().equals("40") || f.getSncCst().equals("41"))) ? "II" : "PT");

                resultTabproUpdate.add(produto);

                //****** TABPROFIL *********************************************
                List<Tabprofil> filiais = getListTabprofilByCodProduto(codpro);
                for (Tabprofil filial : filiais) {
                    Integer codfil = filial.getTabprofilPK().getCodfil();
                    String regime = tabfilBusiness.findById(codfil).getCrt().toString();

                    if (f.getPisCofins()) {
                        //********************* Pis/Cofins *********************
                        filial.setNatRec(f.getCodNaturezaReceita());
                        filial.setCstpise(f.getPisCstE());
                        filial.setFatorpiscom(f.getPisAlqE());
                        filial.setCstcofinse(f.getCofinsCstE());
                        filial.setFatorcofinscom(f.getCofinsAlqE());

                        filial.setMixfiscal("S");
                        resultTabprofilUpdate.add(filial);
                    }
                    //****** TABPROIMP *****************************************
                    if (f.getIcmsSaida()) {
                        List<Tabproimp> imps = getExistTabproimp(codpro, codfil, "A", "D");
                        Tabproimp imp = null;
                        if (imps.isEmpty()) {
                            int r = 0;
                            while (r < 2) {
                                imp = new Tabproimp(codpro, codfil, r == 0 ? "A" : "D");

                                //************** Pis/cofins ********************
                                imp.setNcm(f.getNcm());
                                imp.setPiscst(f.getPisCstS());
                                imp.setPisppis(f.getPisAlqS());
                                imp.setCofinscst(f.getCofinsCstS());
                                imp.setCofinspcofins(f.getCofinsAlqS());
                                //************** Icms Saída ********************
                                imp.setIcmscst(regime.equals("1") ? f.getSncCsosn() : f.getSncCst());
                                imp.setIcmspicms(f.getSncAlq());
                                imp.setIcmspicmsst(f.getSncAlqst());
                                imp.setIcmspredbc(f.getSncRbc());
                                imp.setIcmspredbcst(f.getSncRbcst());
                                //**********************************************
                                imp.setMixfiscal("S");

                                resultTabproimpInsert.add(imp);
                                r++;
                            }
                        } else {
                            for (int b = 0; b < imps.size(); b++) {
                                imp = imps.get(b);

                                //************** Pis/cofins ********************
                                imp.setNcm(f.getNcm());
                                imp.setPiscst(f.getPisCstS());
                                imp.setPisppis(f.getPisAlqS());
                                imp.setCofinscst(f.getCofinsCstS());
                                imp.setCofinspcofins(f.getCofinsAlqS());
                                //************** Icms Saída ********************
                                imp.setIcmscst(regime.equals("1") ? f.getSncCsosn() : f.getSncCst());
                                imp.setIcmspicms(f.getSncAlq());
                                imp.setIcmspicmsst(f.getSncAlqst());
                                imp.setIcmspredbc(f.getSncRbc());
                                imp.setIcmspredbcst(f.getSncRbcst());
                                //**********************************************
                                imp.setMixfiscal("S");

                                resultTabproimpUpdate.add(imp);
                            }
                        }
                    }
                    //*** TABPROIMPE *******************************************
                    if (f.getIcmsEntrada()) {
                        List<Tabproimpe> impes = getExistTabproimpE(codpro, codfil);
                        Tabproimpe impe = null;
                        if (impes.isEmpty()) {

                            impe = new Tabproimpe(codpro, codfil);
                            impe.setEan(f.getEan());
                            impe.setTipoMva(f.getTipoMva());
                            impe.setMva(f.getMva());
                            impe.setMvaDistribuidor(f.getMvaDistribuidor());
                            impe.setMvaDataIni(f.getMvaDataIni());
                            impe.setMvaDataFim(f.getMvaDataFim());
                            impe.setCreditoOutorgado(f.getCreditoOutorgado());
                            impe.setEiCst(f.getEiCst());
                            impe.setEiAlq(f.getEiAlq());
                            impe.setEiAlqst(f.getEiAlqst());
                            impe.setEiRbc(f.getEiRbc());
                            impe.setEiRbcst(f.getEiRbcst());
                            impe.setEdCst(f.getEdCst());
                            impe.setEdAlq(f.getEdAlq());
                            impe.setEdAlqst(f.getEdAlqst());
                            impe.setEdRbc(f.getEdRbc());
                            impe.setEdRbcst(f.getEdRbcst());
                            impe.setEsCst(f.getEsCst());
                            impe.setEsAlq(f.getEsAlq());
                            impe.setEsAlqst(f.getEsAlqst());
                            impe.setEsRbc(f.getEsRbc());
                            impe.setEsRbcst(f.getEsRbcst());
                            impe.setNfiCst(f.getNfiCst());
                            impe.setNfdCst(f.getNfdCst());
                            impe.setNfsCsosn(f.getNfsCsosn());
                            impe.setNfAlq(f.getNfAlq());
                            impe.setFundamentoLegal(f.getFundamentoLegal());

                            impe.setMixfiscal("S");

                            resultTabproimpeInsert.add(impe);

                        } else {
                            for (int s = 0; s < impes.size(); s++) {
                                impe = impes.get(s);
                                impe.setTipoMva(f.getTipoMva());
                                impe.setMva(f.getMva());
                                impe.setMvaDistribuidor(f.getMvaDistribuidor());
                                impe.setMvaDataIni(f.getMvaDataIni());
                                impe.setMvaDataFim(f.getMvaDataFim());
                                impe.setCreditoOutorgado(f.getCreditoOutorgado());
                                impe.setEiCst(f.getEiCst());
                                impe.setEiAlq(f.getEiAlq());
                                impe.setEiAlqst(f.getEiAlqst());
                                impe.setEiRbc(f.getEiRbc());
                                impe.setEiRbcst(f.getEiRbcst());
                                impe.setEdCst(f.getEdCst());
                                impe.setEdAlq(f.getEdAlq());
                                impe.setEdAlqst(f.getEdAlqst());
                                impe.setEdRbc(f.getEdRbc());
                                impe.setEdRbcst(f.getEdRbcst());
                                impe.setEsCst(f.getEsCst());
                                impe.setEsAlq(f.getEsAlq());
                                impe.setEsAlqst(f.getEsAlqst());
                                impe.setEsRbc(f.getEsRbc());
                                impe.setEsRbcst(f.getEsRbcst());
                                impe.setNfiCst(f.getNfiCst());
                                impe.setNfdCst(f.getNfdCst());
                                impe.setNfsCsosn(f.getNfsCsosn());
                                impe.setNfAlq(f.getNfAlq());
                                impe.setFundamentoLegal(f.getFundamentoLegal());
                                impe.setMixfiscal("S");

                                resultTabproimpeUpdate.add(impe);
                            }
                        }
                    }
                }

            }

        }

        if (!resultTabproUpdate.isEmpty()) {
            //Firebird
            fireTabproBusiness.updateList(resultTabproUpdate);
            //PostgreSql
            tabproBusiness.updateList(resultTabproUpdate);

            if (!resultTabprofilUpdate.isEmpty()) {
                //Firebird
                fireTabprofilBusiness.updateList(resultTabprofilUpdate);
                //PostgreSql
                tabprofilBusiness.updateList(resultTabprofilUpdate);
            }
            if (!resultTabproimpInsert.isEmpty()) {
                //Firebird
                fireTabproimpBusiness.insertList(resultTabproimpInsert);
            }
            if (!resultTabproimpUpdate.isEmpty()) {
                //Firebird
                fireTabproimpBusiness.updateList(resultTabproimpUpdate);
                //PostgreSql
                tabproimpBusiness.updateList(resultTabproimpUpdate);
            }
            if (!resultTabproimpeInsert.isEmpty()) {
                //Firebird
                fireTabproimpeBusiness.insertList(resultTabproimpeInsert);
            }
            if (!resultTabproimpeUpdate.isEmpty()) {
                //Firebird
                fireTabproimpeBusiness.updateList(resultTabproimpeUpdate);
                //PostgreSql
                tabproimpeBusiness.updateList(resultTabproimpeUpdate);
            }

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

}
