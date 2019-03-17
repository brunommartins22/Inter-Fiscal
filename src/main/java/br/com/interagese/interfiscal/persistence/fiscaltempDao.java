package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.business.FireTabproBusinessBean;
import br.com.interagese.interfiscal.business.FireTabprofilBusinessBean;
import br.com.interagese.interfiscal.business.FireTabproimpBusinessBean;
import br.com.interagese.interfiscal.business.FireTabproimpeBusinessBean;
import br.com.interagese.interfiscal.business.IcmssaidaBusinessBean;
import br.com.interagese.interfiscal.business.PisCofinsBusinessBean;
import br.com.interagese.interfiscal.business.TabfilBusinessBean;
import br.com.interagese.interfiscal.business.TabproBusinessBean;
import br.com.interagese.interfiscal.business.TabprofilBusinessBean;
import br.com.interagese.interfiscal.business.TabproimpBusinessBean;
import br.com.interagese.interfiscal.business.TabproimpeBusinessBean;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.entity.Tabfil;
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

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class fiscaltempDao extends AbstractDaoCrud<Fiscaltemp> {

    private Actions a = new Actions(null);
    //*************************** Firebird *************************************
    private FireTabproBusinessBean fireTabproBusiness = new FireTabproBusinessBean();
    private FireTabprofilBusinessBean fireTabprofilBusiness = new FireTabprofilBusinessBean();
    private FireTabproimpBusinessBean fireTabproimpBusiness = new FireTabproimpBusinessBean();
    private TabfilBusinessBean tabfilBusiness = new TabfilBusinessBean();
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
            String date1 = formata.format(dataIncial) + " 00:00";
            String date2 = formata.format(dataFinal) + " 23:59";
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

    public List<Fiscaltemp> getCarregarFiscalTemp(Date inicioOperacao) {
        try {

            SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String data1 = formata.format(inicioOperacao);

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getEntityManager().createNativeQuery("select * from fiscaltemp f where f.data_registro >= '" + data1 + "'", Fiscaltemp.class);

            return result.getResultList().isEmpty() ? null : result.getResultList();

        } finally {
            getEntityManager().close();
        }
    }

    public void getGerarTributos(List<Fiscaltemp> resultTemp, Tabfil filial) {

        Integer codfil = filial.getCodfil();
        String regime = filial.getCrt().toString();

        List<Tabpro> resultTabproUpdate = new ArrayList<>();

        List<Tabprofil> resultTabprofilUpdate = new ArrayList<>();

        List<Tabproimp> resultTabproimpInsert = new ArrayList<>();
        List<Tabproimp> resultTabproimpUpdate = new ArrayList<>();

        List<Tabproimpe> resultTabproimpeInsert = new ArrayList<>();
        List<Tabproimpe> resultTabproimpeUpdate = new ArrayList<>();

        Date dataAtualizacao = new Date();

        for (Fiscaltemp f : resultTemp) {
            List<Tabpro> resultTabpro = getListTabproByCod(Integer.parseInt(f.getCodigoProduto()));
            for (Tabpro produto : resultTabpro) {
                String codpro = produto.getCodpro();
                //******** TABPRO **********************************************
                //********************** Pis Cofins ****************************
                produto.setClasfiscal(f.getNcm() != null && !f.getNcm().equals("") ? f.getNcm() : produto.getClasfiscal() == null || produto.getClasfiscal().equals("") ? null : produto.getClasfiscal());
                produto.setCodbarun(f.getEan() != null ? f.getEan() : null);
                produto.setFatorpis(f.getPisAlqE() == null || f.getPisAlqE() == 0.0 ? produto.getFatorpis() : f.getPisAlqE());
                produto.setFatorcofins(f.getCofinsAlqE() == null || f.getCofinsAlqE() == 0.0 ? produto.getFatorcofins() : f.getCofinsAlqE());
                produto.setCstPis(f.getPisCstE() == null || f.getPisCstE().equals("") ? null : f.getPisCstE());

                //********************** Icms Saída ****************************
                produto.setIcms(f.getSncAlq() == null ? 0.0 : f.getSncAlq());
                produto.setCest((f.getCest() != null && !f.getCest().isEmpty()) ? f.getCest().replace(".", "") : produto.getCest() == null || produto.getCest().equals("") ? null : produto.getCest());
                produto.setCst(regime.equals("1") ? (f.getSncCsosn() == null || f.getSncCsosn().equals("0.0") ? (produto.getCst() == null || produto.getCst().equals("") ? null : produto.getCst()) : f.getSncCsosn()) : (f.getSncCst() == null || f.getSncCst().equals("0.0") ? (produto.getCst() == null || produto.getCst().equals("") ? null : produto.getCst()) : f.getSncCst()));

                //********************** Update RgData *************************
                produto.setRgdata(dataAtualizacao);

                //************** Gerando Tipo Trib and ModBc *******************
                String tipoTrib = "";
                String modBc = "";

                if (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) {

                    String valueTrib = regime.equals("1") ? f.getSncCsosn() : f.getSncCst();

                    //************************* Indice *************************
                    switch (valueTrib) {
                        case "10":
                        case "30":
                        case "50":
                        case "60":
                        case "70":
                        case "90":
                        case "141":
                        case "201":
                        case "202":
                        case "203":
                        case "500":
                        case "900":
                        case "P10":
                        case "P90": {
                            tipoTrib = "SS";
                            break;
                        }
                        case "40":
                        case "300": {
                            tipoTrib = "II";
                            break;
                        }
                        case "41":
                        case "400": {
                            tipoTrib = "NN";
                            break;
                        }
                        default: {
                            tipoTrib = "PT";
                            break;
                        }

                    }
                    produto.setIndice(tipoTrib);

                    //***************** Add Prod in ListUpdate *****************
                    resultTabproUpdate.add(produto);

                    //************************* IcmsModBc **********************
                    switch (valueTrib) {
                        case "00":
                        case "10":
                        case "20":
                        case "51":
                        case "70":
                        case "90":
                        case "900": {
                            modBc = "0";
                            break;
                        }
                        default: {
                            modBc = "";
                            break;
                        }
                    }

                }

                //****** TABPROFIL *********************************************
                List<Tabprofil> filiais = getListTabprofilByCodProduto(codpro);

                for (Tabprofil fil : filiais) {

                    if (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins()) {
                        //********************* Pis/Cofins *********************
                        fil.setNatRec(f.getCodNaturezaReceita() == null || f.getCodNaturezaReceita().equals("") ? null : f.getCodNaturezaReceita());
                        fil.setCstpise(f.getPisCstE() == null || f.getPisCstE().equals("") ? null : f.getPisCstE());
                        fil.setFatorpiscom(f.getPisAlqE() == null ? 0.0 : f.getPisAlqE());
                        fil.setCstcofinse(f.getCofinsCstE() == null || f.getCofinsCstE().equals("") ? null : f.getCofinsCstE());
                        fil.setFatorcofinscom(f.getCofinsAlqE() == null ? 0.0 : f.getCofinsAlqE());

                        //********************* Update RgData ******************
                        fil.setRgdata(new Date());

                        //************ Add TabFil in UpdateList ****************
                        fil.setMixfiscal("S");
                        resultTabprofilUpdate.add(fil);

                    }
                    //****** TABPROIMP *****************************************
                    List<Tabproimp> imps = getExistTabproimp(codpro, codfil, "A", "D");
                    Tabproimp imp = null;

                    if (imps.isEmpty() && (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) && (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins())) {
                        int r = 0;
                        while (r < 2) {
                            imp = new Tabproimp(codpro, codfil, r == 0 ? "A" : "D");

                            //****************** Pis/cofins ********************
                            imp.setNcm(f.getNcm());
                            imp.setPiscst(f.getPisCstS() == null || f.getPisCstS().equals("") ? null : f.getPisCstS());
                            imp.setPisppis(f.getPisAlqS() == null ? 0.0 : f.getPisAlqS());
                            imp.setCofinscst(f.getCofinsCstS() == null || f.getCofinsCstS().equals("") ? null : f.getCofinsCstS());
                            imp.setCofinspcofins(f.getCofinsAlqS() == null ? 0.0 : f.getCofinsAlqS());
                            //****************** Icms Saída ********************
                            imp.setIcmscst(regime.equals("1") ? f.getSncCsosn() : f.getSncCst());
                            imp.setIcmspicms(f.getSncAlq() == null ? 0.0 : f.getSncAlq());
                            imp.setIcmspicmsst(f.getSncAlqst() == null ? 0.0 : f.getSncAlqst());
                            imp.setIcmspredbc(f.getSncRbc() == null ? 0.0 : f.getSncRbc());
                            imp.setIcmspredbcst(f.getSncRbcst() == null ? 0.0 : f.getSncRbcst());
                            imp.setIcmsmodbc(modBc);
                            
                            //**************************************************
                            imp.setMixfiscal("S");

                            //********* Add Tabproimp in InsertList ************
                            resultTabproimpInsert.add(imp);

                            r++;
                        }
                    } else if ((f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) || (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins())) {
                        for (int b = 0; b < imps.size(); b++) {
                            imp = imps.get(b);

                            if (f.getPisCofins() != null && !f.getPisCofins().equals("") && f.getPisCofins()) {
                                //************** Pis/cofins ********************
                                imp.setNcm(f.getNcm());
                                imp.setPiscst(f.getPisCstS() == null || f.getPisCstS().equals("") ? null : f.getPisCstS());
                                imp.setPisppis(f.getPisAlqS() == null ? 0.0 : f.getPisAlqS());
                                imp.setCofinscst(f.getCofinsCstS() == null || f.getCofinsCstS().equals("") ? null : f.getCofinsCstS());
                                imp.setCofinspcofins(f.getCofinsAlqS() == null ? 0.0 : f.getCofinsAlqS());
                            }
                            if (f.getIcmsSaida() != null && !f.getIcmsSaida().equals("") && f.getIcmsSaida()) {
                                //************** Icms Saída ********************
                                imp.setIcmscst(regime.equals("1") ? f.getSncCsosn() : f.getSncCst());
                                imp.setIcmspicms(f.getSncAlq() == null ? 0.0 : f.getSncAlq());
                                imp.setIcmspicmsst(f.getSncAlqst() == null ? 0.0 : f.getSncAlqst());
                                imp.setIcmspredbc(f.getSncRbc() == null ? 0.0 : f.getSncRbc());
                                imp.setIcmspredbcst(f.getSncRbcst() == null ? 0.0 : f.getSncRbcst());
                                imp.setIcmsmodbc(modBc);
                            }
                            //**************************************************
                            imp.setMixfiscal("S");

                            resultTabproimpUpdate.add(imp);
//                            fireTabproimpBusiness.update(imp);
//                            tabproimpBusiness.update(imp);
                        }
                    }

                    //*** TABPROIMPE *******************************************
                    if (f.getIcmsEntrada() != null && !f.getIcmsEntrada().equals("") && f.getIcmsEntrada()) {
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
//                            fireTabproimpeBusiness.insert(impe);
//                            tabproimpeBusiness.insert(impe);

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
//                                fireTabproimpeBusiness.update(impe);
//                                tabproimpeBusiness.update(impe);
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
