package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.BaseInterage;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.persistence.FirefiscaltempDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.TypedQuery;

public class FireFiscalTempBusinessBean extends AbstractBusinessCrud<Fiscaltemp, FirefiscaltempDao> implements FireFiscalTempBusiness {

    //**************************** postgres *******************************
    private TabprofilBusiness tabprofilBusiness = new TabprofilBusinessBean();
    private TabproimpBusiness tabproimpBusiness = new TabproimpBusinessBean();
    private TabproimpeBusiness tabproimpeBusiness = new TabproimpeBusinessBean();
    private TabproBusiness tabproBusiness = new TabproBusinessBean();
    //*************************** firebird ********************************
    private FireTabproBusinessBean fireTabproBusiness = new FireTabproBusinessBean();
    private FireTabprofilBusinessBean fireTabprofilBusiness = new FireTabprofilBusinessBean();
    private FireTabproimpBusinessBean fireTabproimpBusines = new FireTabproimpBusinessBean();
    private FireTabproimpeBusinessBean fireTabproimpeBusiness = new FireTabproimpeBusinessBean();

//    public void insertPisCofins(List<Piscofins> pcs) {
//
//        //**********************************************************************
//        List<Tabpro> resulttabpro = new ArrayList<>();
//        List<Tabprofil> resultTabprofilUpdate = new ArrayList<>();
//        List<Tabproimp> resultTabproimpInsert = new ArrayList<>();
//        List<Tabproimp> resultTabproimpUpdate = new ArrayList<>();
//        List<Fiscaltemp> resultFiscaltempInsert = new ArrayList<>();
//        List<Fiscaltemp> resultFiscaltempUpdate = new ArrayList<>();
//
//        if (!pcs.isEmpty()) {
//            for (Piscofins pc : pcs) {
//                List<Tabpro> produtos = getListTabproByCod(pc.getCodigoProduto().intValue());
//                if (!produtos.isEmpty()) {
//                    boolean exist = true;
//                    Fiscaltemp f = getExistItem(pc.getCodigoProduto().toString());
//                    if (f == null) {
//                        f = new Fiscaltemp();
//                        exist = false;
//                    }
//                    for (int i = 0; i < produtos.size(); i++) {
//                        Tabpro t = produtos.get(i);
//
//                        f.setNcmBefore(t.getClasfiscal());
//                        t.setClasfiscal(pc.getNcm() != null && !pc.getNcm().equals("") ? pc.getNcm() : t.getClasfiscal());
//
//                        t.setCodbarun(pc.getEan() != null ? pc.getEan().toString() : "");
//
//                        f.setPisAlqEBefore(t.getFatorpis());
//                        t.setFatorpis(pc.getPisAlqE());
//
//                        f.setCofinsAlqEBefore(t.getFatorcofins());
//                        t.setFatorcofins(pc.getCofinsAlqE());
//
////                    tabproBusiness.update(t);
////                    fireTabproBusiness.update(t);
//                        resulttabpro.add(t);
//                        List<Tabprofil> filiais = getListTabprofilByCodProduto(t.getCodpro());
//                        for (int a = 0; a < filiais.size(); a++) {
//                            Tabprofil fil = filiais.get(a);
//
//                            f.setCodNaturezaReceitaBefore(fil.getNatRec());
//                            fil.setNatRec(pc.getCodNaturezaReceita());
//
//                            f.setPisCstEBefore(fil.getCstpise());
//                            fil.setCstpise(pc.getPisCstE());
//
//                            f.setPisAlqEBefore(fil.getFatorpiscom());
//                            fil.setFatorpiscom(pc.getPisAlqE());
//
//                            f.setCofinsCstEBefore(fil.getCstcofinse());
//                            fil.setCstcofinse(pc.getCofinsCstE());
//
//                            f.setCofinsAlqEBefore(fil.getFatorcofinscom());
//                            fil.setFatorcofinscom(pc.getCofinsAlqE());
//
//                            fil.setMixfiscal("S");
//
////                        tabprofilBusiness.update(fil);
////                        fireTabprofilBusiness.update(fil);
//                            resultTabprofilUpdate.add(fil);
//
//                            List<Tabproimp> imps = getExistTabproimp(t.getCodpro(), fil.getTabprofilPK().getCodfil(), "A", "D");
//                            Tabproimp imp = null;
//                            if (imps.isEmpty()) {
//                                int r = 0;
//                                while (r < 2) {
//                                    imp = new Tabproimp(t.getCodpro(), fil.getTabprofilPK().getCodfil(), r == 0 ? "A" : "D");
//
//                                    imp.setNcm(pc.getNcm());
//
//                                    f.setPisCstSBefore(imp.getPiscst());
//                                    imp.setPiscst(pc.getPisCstS());
//
//                                    f.setPisAlqSBefore(imp.getPisppis());
//                                    imp.setPisppis(pc.getPisAlqS());
//
//                                    f.setCofinsCstSBefore(imp.getCofinscst());
//                                    imp.setCofinscst(pc.getCofinsCstS());
//
//                                    f.setCofinsAlqSBefore(imp.getCofinspcofins());
//                                    imp.setCofinspcofins(pc.getCofinsAlqS());
//
//                                    imp.setMixfiscal("S");
////                                tabproimpBusiness.insert(imp);
////                                fireTabproimpBusines.insert(imp);
//                                    resultTabproimpInsert.add(imp);
//                                    r++;
//                                }
//                            } else {
//                                for (int b = 0; b < imps.size(); b++) {
//                                    imp = imps.get(b);
//
//                                    imp.setNcm(pc.getNcm());
//
//                                    f.setPisCstSBefore(imp.getPiscst());
//                                    imp.setPiscst(pc.getPisCstS());
//
//                                    f.setPisAlqSBefore(imp.getPisppis());
//                                    imp.setPisppis(pc.getPisAlqS());
//
//                                    f.setCofinsCstSBefore(imp.getCofinscst());
//                                    imp.setCofinscst(pc.getCofinsCstS());
//
//                                    f.setCofinsAlqSBefore(imp.getCofinspcofins());
//                                    imp.setCofinspcofins(pc.getCofinsAlqS());
//
//                                    imp.setMixfiscal("S");
////                                tabproimpBusiness.update(imp);
////                                fireTabproimpBusines.update(imp);
//                                    resultTabproimpUpdate.add(imp);
//                                }
//                            }
//
//                        }
//
//                    }
//
//                    f.setNomeProduto(pc.getNmProduto());
//                    f.setCodigoProduto(pc.getCodigoProduto().toString());
//                    f.setEan(pc.getEan() != null ? pc.getEan().toString() : "");
//                    f.setNcm(pc.getNcm());
//                    f.setNcmEx(pc.getNcmEx());
//                    f.setCodNaturezaReceita(pc.getCodNaturezaReceita());
//                    f.setCreditoPresumido(pc.getCreditoPresumido());
//                    f.setPisCstE(pc.getPisCstE());
//                    f.setPisCstS(pc.getPisCstS());
//                    f.setPisAlqE(pc.getPisAlqE());
//                    f.setPisAlqS(pc.getPisAlqS());
//                    f.setCofinsCstE(pc.getCofinsCstE());
//                    f.setCofinsCstS(pc.getCofinsCstS());
//                    f.setCofinsAlqE(pc.getCofinsAlqE());
//                    f.setCofinsAlqS(pc.getCofinsAlqS());
//                    f.setFundamentoLegal(pc.getFundamentoLegal());
//                    f.setPisCofins(true);
//                    f.setAtualizacaoPiscofins(new Date());
//
//                    if (!exist) {
////                    super.insert(f);
//                        resultFiscaltempInsert.add(f);
//                    } else {
////                    super.update(f);
//                        resultFiscaltempUpdate.add(f);
//                    }
//                }
//            }
//
//            tabproBusiness.updateList(resulttabpro);
//            tabprofilBusiness.updateList(resultTabprofilUpdate);
//            tabproimpBusiness.insertList(resultTabproimpInsert);
//            tabproimpBusiness.updateList(resultTabproimpUpdate);
//
//            fireTabproBusiness.updateList(resulttabpro);
//            fireTabproimpBusines.updateList(resultTabproimpUpdate);
//            fireTabproimpBusines.insertList(resultTabproimpInsert);
//            fireTabprofilBusiness.updateList(resultTabprofilUpdate);
//
//            super.insertList(resultFiscaltempInsert);
//            super.updateList(resultFiscaltempUpdate);
//
//        }
//    }
//
//    public void insertIcmsEntrada(List<IcmsEntrada> ies) {
//
//        List<Tabproimpe> resultTabproimpeInsert = new ArrayList<>();
//        List<Tabproimpe> resultTabproimpeUpdate = new ArrayList<>();
//        List<Fiscaltemp> resultFiscaltempInsert = new ArrayList<>();
//        List<Fiscaltemp> resultFiscaltempUpdate = new ArrayList<>();
//        if (!ies.isEmpty()) {
//            for (IcmsEntrada ie : ies) {
//
//                boolean exist = true;
//                Fiscaltemp f = getExistItem(ie.getCodigoProduto().toString());
//                if (f == null) {
//                    f = new Fiscaltemp();
//                    exist = false;
//                }
//
//                String codpro = getListTabproByCod(ie.getCodigoProduto().intValue()).get(0).getCodpro();
//                List<Tabprofil> filiais = getListTabprofilByCodProduto(codpro);
//
//                for (int a = 0; a < filiais.size(); a++) {
//                    Tabprofil fil = filiais.get(a);
//                    List<Tabproimpe> impes = getExistTabproimpE(codpro, fil.getTabprofilPK().getCodfil());
//                    Tabproimpe impe = null;
//                    if (impes.isEmpty()) {
//
//                        impe = new Tabproimpe(codpro, fil.getTabprofilPK().getCodfil());
//
//                        impe.setEan(ie.getEan() == null ? null : ie.getEan().toString());
//
//                        f.setTipoMvaBefore(impe.getTipoMva());
//                        impe.setTipoMva(ie.getTipoMva());
//
//                        f.setMvaBefore(impe.getMva());
//                        impe.setMva(ie.getMva());
//
//                        f.setMvaDistribuidorBefore(impe.getMvaDistribuidor());
//                        impe.setMvaDistribuidor(ie.getMvaDistribuidor());
//
//                        f.setMvaDataIniBefore(impe.getMvaDataIni());
//                        impe.setMvaDataIni(ie.getMvaDataIni());
//
//                        f.setMvaDataFimBefore(impe.getMvaDataFim());
//                        impe.setMvaDataFim(ie.getMvaDataFim());
//
//                        f.setCreditoOutorgadoBefore(impe.getCreditoOutorgado());
//                        impe.setCreditoOutorgado(ie.getCreditoOutorgado());
//
//                        f.setEiCstBefore(impe.getEiCst());
//                        impe.setEiCst(ie.getEiCst());
//
//                        f.setEiAlqBefore(impe.getEiAlq());
//                        impe.setEiAlq(ie.getEiAlq());
//
//                        f.setEiAlqstBefore(impe.getEiAlqst());
//                        impe.setEiAlqst(ie.getEiAlqst());
//
//                        f.setEiRbcBefore(impe.getEiRbc());
//                        impe.setEiRbc(ie.getEiRbc());
//
//                        f.setEiRbcstBefore(impe.getEiRbcst());
//                        impe.setEiRbcst(ie.getEiRbcst());
//
//                        f.setEdCstBefore(impe.getEdCst());
//                        impe.setEdCst(ie.getEdCst());
//
//                        f.setEdAlqBefore(impe.getEdAlq());
//                        impe.setEdAlq(ie.getEdAlq());
//
//                        f.setEdAlqstBefore(impe.getEdAlqst());
//                        impe.setEdAlqst(ie.getEdAlqst());
//
//                        f.setEdRbcBefore(impe.getEdRbc());
//                        impe.setEdRbc(ie.getEdRbc());
//
//                        f.setEdRbcstBefore(impe.getEdRbcst());
//                        impe.setEdRbcst(ie.getEdRbcst());
//
//                        f.setEsCstBefore(impe.getEsCst());
//                        impe.setEsCst(ie.getEsCst());
//
//                        f.setEsAlqBefore(impe.getEsAlq());
//                        impe.setEsAlq(ie.getEsAlq());
//
//                        f.setEsAlqBefore(impe.getEsAlq());
//                        impe.setEsAlqst(ie.getEsAlqst());
//
//                        f.setEsRbcBefore(impe.getEsRbc());
//                        impe.setEsRbc(ie.getEsRbc());
//
//                        f.setEsRbcstBefore(impe.getEsRbcst());
//                        impe.setEsRbcst(ie.getEsRbcst());
//
//                        f.setNfiCstBefore(impe.getNfiCst());
//                        impe.setNfiCst(ie.getNfiCst());
//
//                        f.setNfdCstBefore(impe.getNfdCst());
//                        impe.setNfdCst(ie.getNfdCst());
//
//                        f.setNfsCsosnBefore(impe.getNfsCsosn());
//                        impe.setNfsCsosn(ie.getNfsCsosn());
//
//                        f.setNfAlqBefore(impe.getNfAlq());
//                        impe.setNfAlq(ie.getNfAlq());
//
//                        impe.setFundamentoLegal(ie.getFundamentoLegal());
//
//                        impe.setMixfiscal("S");
//
////                    tabproimpeBusiness.insert(impe);
////                    fireTabproimpeBusiness.insert(impe);
//                        resultTabproimpeInsert.add(impe);
//
//                    } else {
//                        for (int s = 0; s < impes.size(); s++) {
//                            impe = impes.get(s);
//
//                            f.setTipoMvaBefore(impe.getTipoMva());
//                            impe.setTipoMva(ie.getTipoMva());
//
//                            f.setMvaBefore(impe.getMva());
//                            impe.setMva(ie.getMva());
//
//                            f.setMvaDistribuidorBefore(impe.getMvaDistribuidor());
//                            impe.setMvaDistribuidor(ie.getMvaDistribuidor());
//
//                            f.setMvaDataIniBefore(impe.getMvaDataIni());
//                            impe.setMvaDataIni(ie.getMvaDataIni());
//
//                            f.setMvaDataFimBefore(impe.getMvaDataFim());
//                            impe.setMvaDataFim(ie.getMvaDataFim());
//
//                            f.setCreditoOutorgadoBefore(impe.getCreditoOutorgado());
//                            impe.setCreditoOutorgado(ie.getCreditoOutorgado());
//
//                            f.setEiCstBefore(impe.getEiCst());
//                            impe.setEiCst(ie.getEiCst());
//
//                            f.setEiAlqBefore(impe.getEiAlq());
//                            impe.setEiAlq(ie.getEiAlq());
//
//                            f.setEiAlqstBefore(impe.getEiAlqst());
//                            impe.setEiAlqst(ie.getEiAlqst());
//
//                            f.setEiRbcBefore(impe.getEiRbc());
//                            impe.setEiRbc(ie.getEiRbc());
//
//                            f.setEiRbcstBefore(impe.getEiRbcst());
//                            impe.setEiRbcst(ie.getEiRbcst());
//
//                            f.setEdCstBefore(impe.getEdCst());
//                            impe.setEdCst(ie.getEdCst());
//
//                            f.setEdAlqBefore(impe.getEdAlq());
//                            impe.setEdAlq(ie.getEdAlq());
//
//                            f.setEdAlqstBefore(impe.getEdAlqst());
//                            impe.setEdAlqst(ie.getEdAlqst());
//
//                            f.setEdRbcBefore(impe.getEdRbc());
//                            impe.setEdRbc(ie.getEdRbc());
//
//                            f.setEdRbcstBefore(impe.getEdRbcst());
//                            impe.setEdRbcst(ie.getEdRbcst());
//
//                            f.setEsCstBefore(impe.getEsCst());
//                            impe.setEsCst(ie.getEsCst());
//
//                            f.setEsAlqBefore(impe.getEsAlq());
//                            impe.setEsAlq(ie.getEsAlq());
//
//                            f.setEsAlqBefore(impe.getEsAlq());
//                            impe.setEsAlqst(ie.getEsAlqst());
//
//                            f.setEsRbcBefore(impe.getEsRbc());
//                            impe.setEsRbc(ie.getEsRbc());
//
//                            f.setEsRbcstBefore(impe.getEsRbcst());
//                            impe.setEsRbcst(ie.getEsRbcst());
//
//                            f.setNfiCstBefore(impe.getNfiCst());
//                            impe.setNfiCst(ie.getNfiCst());
//
//                            f.setNfdCstBefore(impe.getNfdCst());
//                            impe.setNfdCst(ie.getNfdCst());
//
//                            f.setNfsCsosnBefore(impe.getNfsCsosn());
//                            impe.setNfsCsosn(ie.getNfsCsosn());
//
//                            f.setNfAlqBefore(impe.getNfAlq());
//                            impe.setNfAlq(ie.getNfAlq());
//
//                            impe.setFundamentoLegal(ie.getFundamentoLegal());
//
//                            impe.setMixfiscal("S");
//
//                            resultTabproimpeUpdate.add(impe);
//                        }
//                    }
//                }
//
//                f.setNomeProduto(ie.getNmProduto());
//                f.setCodigoProduto(ie.getCodigoProduto().toString());
//                f.setEan(ie.getEan() != null ? ie.getEan().toString() : "");
//                f.setTipoMva(ie.getTipoMva());
//                f.setMva(ie.getMva());
//                f.setMvaDistribuidor(ie.getMvaDistribuidor());
//                f.setMvaDataIni(ie.getMvaDataIni());
//                f.setMvaDataFim(ie.getMvaDataFim());
//                f.setCreditoOutorgado(ie.getCreditoOutorgado());
//                f.setEiCst(ie.getEiCst());
//                f.setEiAlq(ie.getEiAlq());
//                f.setEiAlqst(ie.getEiAlqst());
//                f.setEiRbc(ie.getEiRbc());
//                f.setEiRbcst(ie.getEiRbcst());
//                f.setEdCst(ie.getEdCst());
//                f.setEdAlq(ie.getEdAlq());
//                f.setEdAlqst(ie.getEdAlqst());
//                f.setEdRbc(ie.getEdRbc());
//                f.setEdRbcst(ie.getEdRbcst());
//                f.setEsCst(ie.getEsCst());
//                f.setEsAlq(ie.getEsAlq());
//                f.setEsAlqst(ie.getEsRbc());
//                f.setEsRbc(ie.getEsRbc());
//                f.setEsRbcst(ie.getEsRbcst());
//                f.setNfiCst(ie.getNfiCst());
//                f.setNfdCst(ie.getNfdCst());
//                f.setNfsCsosn(ie.getNfsCsosn());
//                f.setNfAlq(ie.getNfAlq());
//                f.setFundamentoLegal(ie.getFundamentoLegal());
//                f.setIcmsEntrada(true);
//                f.setAtualizacaoIcmsentrada(new Date());
//
//                if (!exist) {
////                super.insert(f);
//                    resultFiscaltempInsert.add(f);
//                } else {
////                super.update(f);
//                    resultFiscaltempUpdate.add(f);
//                }
//            }
//
//            tabproimpeBusiness.insertList(resultTabproimpeInsert);
//            tabproimpeBusiness.updateList(resultTabproimpeUpdate);
//
//            fireTabproimpeBusiness.insertList(resultTabproimpeInsert);
//            fireTabproimpeBusiness.updateList(resultTabproimpeUpdate);
//
//            super.insertList(resultFiscaltempInsert);
//            super.updateList(resultFiscaltempUpdate);
//        }
//    }
//
//    public void insertIcmsSaida(List<IcmsSaida> iss) {
//
//        List<Tabpro> resulttabpro = new ArrayList<>();
//        List<Tabproimp> resultTabproimpInsert = new ArrayList<>();
//        List<Tabproimp> resultTabproimpUpdate = new ArrayList<>();
//        List<Fiscaltemp> resultFiscaltempInsert = new ArrayList<>();
//        List<Fiscaltemp> resultFiscaltempUpdate = new ArrayList<>();
//
//        //**********************************************************************
//        if (!iss.isEmpty()) {
//            for (IcmsSaida is : iss) {
//
//                List<Tabpro> produtos = getListTabproByCod(is.getCodigoProduto().intValue());
//                if (!produtos.isEmpty()) {
//                    boolean exist = true;
//                    Fiscaltemp f = getExistItem(is.getCodigoProduto().toString());
//                    if (f == null) {
//                        f = new Fiscaltemp();
//                        exist = false;
//                    }
//                    for (int i = 0; i < produtos.size(); i++) {
//                        Tabpro t = produtos.get(i);
//                        t.setCodbarun(is.getEan() != null ? is.getEan().toString().trim() : "");
//
//                        f.setCestBefore(t.getCest());
//                        t.setCest((is.getCest() != null && !is.getCest().isEmpty()) ? is.getCest().replace(".", "") : t.getCest());
//
//                        f.setSncCsosnBefore(t.getCst());
//                        f.setSncCstBefore(t.getCst());
//                        t.setCst(is.getSncCsosn() != null && !is.getSncCsosn().equals("") ? is.getSncCsosn() : is.getSncCst());
//
//                        t.setIndice((is.getSncCsosn() != null && !is.getSncCsosn().equals("") && is.getSncCsosn().equals("500")) || (is.getSncCst() != null && !is.getSncCst().equals("") && (is.getSncCst().equals("60") || is.getSncCst().equals("70"))) ? "SS" : (is.getSncCsosn() != null && (is.getSncCsosn().equals("400") || is.getSncCsosn().equals("401"))) || (is.getSncCst() != null && (is.getSncCst().equals("40") || is.getSncCst().equals("41"))) ? "II" : "PT");
////                    tabproBusiness.update(t);
////                    fireTabproBusiness.update(t);
//                        resulttabpro.add(t);
//
//                        List<Tabprofil> filiais = getListTabprofilByCodProduto(t.getCodpro());
//                        for (int a = 0; a < filiais.size(); a++) {
//                            Tabprofil fil = filiais.get(a);
//
//                            List<Tabproimp> imps = getExistTabproimp(t.getCodpro(), fil.getTabprofilPK().getCodfil(), "A", "D");
//                            Tabproimp imp = null;
//
//                            if (imps.isEmpty()) {
//                                int r = 0;
//                                while (r < 2) {
//                                    imp = new Tabproimp(t.getCodpro(), fil.getTabprofilPK().getCodfil(), r == 0 ? "A" : "D");
//
//                                    imp.setIcmscst(is.getSncCsosn() != null && !is.getSncCsosn().equals("") ? is.getSncCsosn() : is.getSncCst());
//
//                                    f.setSncAlqBefore(imp.getIcmspicms());
//                                    imp.setIcmspicms(is.getSncAlq());
//
//                                    f.setSncAlqstBefore(imp.getIcmspicmsst());
//                                    imp.setIcmspicmsst(is.getSncAlqst());
//
//                                    f.setSncRbcBefore(imp.getIcmspredbc());
//                                    imp.setIcmspredbc(is.getSncRbc());
//
//                                    f.setSncRbcstBefore(imp.getIcmspredbcst());
//                                    imp.setIcmspredbcst(is.getSncRbcst());
//                                    imp.setMixfiscal("S");
//
////                                tabproimpBusiness.insert(imp);
////                                fireTabproimpBusines.insert(imp);
//                                    resultTabproimpInsert.add(imp);
//                                    r++;
//                                }
//                            } else {
//                                for (int b = 0; b < imps.size(); b++) {
//                                    imp = imps.get(b);
//
//                                    imp.setIcmscst(is.getSncCsosn() != null && !is.getSncCsosn().equals("") ? is.getSncCsosn() : is.getSncCst());
//
//                                    f.setSncAlqBefore(imp.getIcmspicms());
//                                    imp.setIcmspicms(is.getSncAlq());
//
//                                    f.setSncAlqstBefore(imp.getIcmspicmsst());
//                                    imp.setIcmspicmsst(is.getSncAlqst());
//
//                                    f.setSncRbcBefore(imp.getIcmspredbc());
//                                    imp.setIcmspredbc(is.getSncRbc());
//
//                                    f.setSncRbcstBefore(imp.getIcmspredbcst());
//                                    imp.setIcmspredbcst(is.getSncRbcst());
//                                    imp.setMixfiscal("S");
//
////                                tabproimpBusiness.update(imp);
////                                fireTabproimpBusines.update(imp);
//                                    resultTabproimpUpdate.add(imp);
//
//                                }
//
//                            }
//
//                        }
//                    }
//
//                    f.setNomeProduto(is.getNmProduto());
//                    f.setCodigoProduto(is.getCodigoProduto().toString());
//                    f.setEan(is.getEan() != null ? is.getEan().toString().trim() : "");
//                    f.setRe29560(is.getRe29560() != null ? is.getRe29560().intValue() : null);
//                    f.setCest(is.getCest());
//                    f.setSacCst(is.getSacCst());
//                    f.setSacAlq(is.getSacAlq());
//                    f.setSacAlqst(is.getSacAlqst());
//                    f.setSacRbc(is.getSacRbc());
//                    f.setSacRbcst(is.getSacRbcst());
//                    f.setSasCst(is.getSasCst());
//                    f.setSasAlq(is.getSasAlq());
//                    f.setSasAlqst(is.getSasAlqst());
//                    f.setSasRbc(is.getSasRbc());
//                    f.setSasRbcst(is.getSasRbcst());
//                    f.setSvcCst(is.getSvcCst());
//                    f.setSvcAlq(is.getSvcAlq());
//                    f.setSvcAlqst(is.getSvcAlqst());
//                    f.setSvcRbc(is.getSvcRbc());
//                    f.setSvcRbcst(is.getSvcRbcst());
//                    f.setSncCst(is.getSncCst());
//                    f.setSncAlq(is.getSncAlq());
//                    f.setSncAlqst(is.getSncAlqst());
//                    f.setSncRbc(is.getSncRbc());
//                    f.setSncRbcst(is.getSncRbcst());
//                    f.setSssCsosn(is.getSssCsosn());
//                    f.setSvcCsosn(is.getSvcCsosn());
//                    f.setSncCsosn(is.getSncCsosn());
//                    f.setFecp(is.getFecp());
//                    f.setFundamentoLegal(is.getFundamentoLegal());
//                    f.setIcmsSaida(true);
//                    f.setAtualizacaoIcmssaida(new Date());
//
//                    if (!exist) {
////                    super.insert(f);
//                        resultFiscaltempInsert.add(f);
//                    } else {
////                    super.update(f);
//                        resultFiscaltempUpdate.add(f);
//                    }
//                }
//            }
//
//            tabproBusiness.updateList(resulttabpro);
//            tabproimpBusiness.insertList(resultTabproimpInsert);
//            tabproimpBusiness.updateList(resultTabproimpUpdate);
//
//            fireTabproBusiness.updateList(resulttabpro);
//            fireTabproimpBusines.insertList(resultTabproimpInsert);
//            fireTabproimpBusines.updateList(resultTabproimpUpdate);
//
//            super.insertList(resultFiscaltempInsert);
//            super.updateList(resultFiscaltempUpdate);
//        }
//    }
    public List<BaseInterage> getSearchAllBaseInterage(int position, List<BaseInterage> list) {
        if (list.isEmpty()) {
            list = new ArrayList<>();
        }
        for (Fiscaltemp ft : getSearchAll(position)) {
            BaseInterage bi = getFindCodList(ft.getCodigoProduto(), list);
            if (bi == null) {
                bi = new BaseInterage();
                bi.setCodBarras(ft.getEan());
                bi.setCodProduto(ft.getCodigoProduto());
                bi.setNmProduto(ft.getNomeProduto());
                list.add(bi);
            }
            bi.setAtualizacaoPisCofins(ft.getAtualizacaoPiscofins());
            bi.setAtualizacaoIcmsEntrada(ft.getAtualizacaoIcmsentrada());
            bi.setAtualizacaoIcmsSaida(ft.getAtualizacaoIcmssaida());
            bi.setPisCofins(ft.getPisCofins() == null ? false : ft.getPisCofins());
            bi.setIcmsEntrada(ft.getIcmsEntrada() == null ? false : ft.getIcmsEntrada());
            bi.setIcmsSaida(ft.getIcmsSaida() == null ? false : ft.getIcmsSaida());
            
        }
        return list;
    }
    
    public List<BaseInterage> getAllBaseInterage() {
        List<BaseInterage> result = new ArrayList<>();
        List<Fiscaltemp> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            Fiscaltemp ft = list.get(i);
            
            BaseInterage bi = getFindCodList(ft.getCodigoProduto(), result);
            if (bi == null) {
                bi = new BaseInterage();
                bi.setCodBarras(ft.getEan());
                bi.setCodProduto(ft.getCodigoProduto());
                bi.setNmProduto(ft.getNomeProduto());
                result.add(bi);
            }
            bi.setAtualizacaoPisCofins(ft.getAtualizacaoPiscofins());
            bi.setAtualizacaoIcmsEntrada(ft.getAtualizacaoIcmsentrada());
            bi.setAtualizacaoIcmsSaida(ft.getAtualizacaoIcmssaida());
            bi.setPisCofins(ft.getPisCofins() == null ? false : ft.getPisCofins());
            bi.setIcmsEntrada(ft.getIcmsEntrada() == null ? false : ft.getIcmsEntrada());
            bi.setIcmsSaida(ft.getIcmsSaida() == null ? false : ft.getIcmsSaida());
        }
        return result;
    }
    
    public BaseInterage getFindCodList(String cod, List<BaseInterage> result) {
        return result.stream().filter((BaseInterage t) -> {
            return Objects.equals(t.getCodProduto(), cod);
        }).findFirst().orElse(null);
    }

//    public List<Tabpro> getListTabproByCod(Integer codPro) {
//        try {
//            String sql = "SELECT * FROM tabpro o WHERE o.icodpro= :icodpro ";
//
//            TypedQuery<Tabpro> query = (TypedQuery<Tabpro>) fireTabproBusiness.getDao().getEntityManager().createNativeQuery(sql, Tabpro.class).setParameter("icodpro", codPro);
//
//            return query.getResultList();
//        } finally {
//            fireTabproBusiness.getDao().getEntityManager().close();
//        }
//    }
//    public List<Tabprofil> getListTabprofilByCodProduto(String codPro) {
//        try {
//            TypedQuery<Tabprofil> query = (TypedQuery<Tabprofil>) fireTabprofilBusiness.getDao().getEntityManager().createNativeQuery("SELECT * FROM Tabprofil o WHERE o.codpro= :codpro", Tabprofil.class);
//            query.setParameter("codpro", codPro);
//
//            return query.getResultList();
//        } finally {
//            fireTabprofilBusiness.getDao().getEntityManager().close();
//        }
//    }
    public Fiscaltemp getExistItem(String codpro) {
        try {
            String sql = "SELECT * FROM fiscaltemp o WHERE o.codigo_produto = :codpro";
            
            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getDao().getEntityManager().createNativeQuery(sql, Fiscaltemp.class).setParameter("codpro", codpro);
            
            if (result.getResultList().size() > 1) {
                
            }
            
            return result.getResultList().isEmpty() ? null : result.getResultList().get(0);
        } finally {
            getDao().getEntityManager().close();
        }
    }

//    public List<Tabproimp> getExistTabproimp(String codpro, Integer codfil, String tpImpA, String tpimpD) {
//        try {
//            String sql = "SELECT * FROM tabproimp o WHERE o.codfil= :codfil and o.codpro= :codpro and (o.tpimpos= :tpimpA or o.tpimpos= :tpimpD)";
//
//            TypedQuery<Tabproimp> result = (TypedQuery<Tabproimp>) fireTabproimpBusines.getDao().getEntityManager().createNativeQuery(sql, Tabproimp.class)
//                    .setParameter("codfil", codfil)
//                    .setParameter("codpro", codpro)
//                    .setParameter("tpimpA", tpImpA)
//                    .setParameter("tpimpD", tpimpD);
//
//            return result.getResultList();
//        } finally {
//            fireTabproimpBusines.getDao().getEntityManager().close();
//        }
//    }
//    public List<Tabproimpe> getExistTabproimpE(String codpro, Integer codfil) {
//        try {
//            String sql = "SELECT * FROM tabproimpe o where o.codigoProduto = :codpro and o.codigoFilial= :codfil";
//
//            TypedQuery<Tabproimpe> result = (TypedQuery<Tabproimpe>) fireTabproimpeBusiness.getDao().getEntityManager().createNativeQuery(sql, Tabproimpe.class)
//                    .setParameter("codpro", codpro)
//                    .setParameter("codfil", codfil);
//
//            return result.getResultList();
//
//        } finally {
//            fireTabproimpeBusiness.getDao().getEntityManager().close();
//        }
//    }
    @Override
    public List<Fiscaltemp> getListagemRelatorio(String codbar, Integer tipo, Date dataIncial, Date dataFinal) {
        return getDao().getListagemRelatorio(codbar, tipo, dataIncial, dataFinal);
    }
    
    @Override
    public List<Fiscaltemp> getCarregarFiscalTemp(List<Piscofins> pcs, List<IcmsEntrada> ies, List<IcmsSaida> iss) {
        return getDao().getCarregarFiscalTemp(pcs, ies, iss);
    }
    
    @Override
    public void getGerarTributos(List<Fiscaltemp> resultTemp) {
        getDao().getGerarTributos(resultTemp);
    }
    
    @Override
    public void getRestoreTributacaoMixFiscal(List<Fiscaltemp> resultRestore) {
        getDao().getRestoreTributacaoMixFiscal(resultRestore);
    }
    
}
