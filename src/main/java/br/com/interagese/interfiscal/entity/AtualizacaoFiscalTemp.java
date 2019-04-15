/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bruno Martins
 */
public class AtualizacaoFiscalTemp {

    private String codpro;
    private String codfil;
    /**
     * tabpro && tabproimp
     */
    private String clasFiscal;
    /**
     * tabpro && tabprofil
     */
    private Double fatorPis;
    /**
     * tabpro && tabprofil
     */
    private String cstPis;
    /**
     * tabpro && tabprofil
     */
    private Double fatorCofins;
    /**
     * tabpro && tabprofil
     */
    private String cstCofins;
    /**
     * tabpro && tabproimp
     */
    private Double icmsRegime;
    /**
     * tabpro && tabproimp
     */
    private Double icmsSimples;
    /**
     * tabpro && tabproimp
     */
    private String cst;
    /**
     * tabpro
     */
    private String cest;
    /**
     * tabpro
     */
    private String indice;
    /**
     * tabprofil
     */
    private String natRec;
    /**
     * tabpro && tabprofil
     */
    private Date rgdata;

    private List<ImpTemp> resultImpTemp;

    /**
     * tabproimp
     */
    private String pisCst;
    /**
     * tabproimp
     */
    private Double pispPis;
    /**
     * tabproimp
     */
    private String cofinsCst;
    /**
     * tabproimp
     */
    private Double cofinspCofins;
    /**
     * tabproimp
     */
    private Double icmspIcmsSt;
    /**
     * tabproimp
     */
    private Double icmspRedBc;
    /**
     * tabproimp
     */
    private Double icmspRedBcSt;
    /**
     * tabproimp
     */
    private String icmsModBc;

    private List<ImpTemp> resultImpe;
    /**
     * tabproimpe
     */
    private String ean;
    /**
     * tabproimpe
     */
    private String tipoMva;
    /**
     * tabproimpe
     */
    private Double mva;
    /**
     * tabproimpe
     */
    private Double mvaDistribuidor;
    /**
     * tabproimpe
     */
    private Date mvaDataIni;
    /**
     * tabproimpe
     */
    private Date mvaDataFim;
    /**
     * tabproimpe
     */
    private Integer creditoOutorgado;
    /**
     * tabproimpe
     */
    private String eiCst;
    /**
     * tabproimpe
     */
    private Double eiAlq;
    /**
     * tabproimpe
     */
    private Double eiAlqst;
    /**
     * tabproimpe
     */
    private Double eiRbc;
    /**
     * tabproimpe
     */
    private Double eiRbcst;
    /**
     * tabproimpe
     */
    private String edCst;
    /**
     * tabproimpe
     */
    private Double edAlq;
    /**
     * tabproimpe
     */
    private Double edAlqst;
    /**
     * tabproimpe
     */
    private Double edRbc;
    /**
     * tabproimpe
     */
    private Double edRbcst;
    /**
     * tabproimpe
     */
    private String esCst;
    /**
     * tabproimpe
     */
    private Double esAlq;
    /**
     * tabproimpe
     */
    private Double esAlqst;
    /**
     * tabproimpe
     */
    private Double esRbc;
    /**
     * tabproimpe
     */
    private Double esRbcst;
    /**
     * tabproimpe
     */
    private String nfiCst;
    /**
     * tabproimpe
     */
    private String nfdCst;
    /**
     * tabproimpe
     */
    private String nfsCsosn;
    /**
     * tabproimpe
     */
    private Double nfAlq;
    /**
     * tabproimpe
     */
    private String fundamentoLegal;

    private boolean isExistPisCofins;
    private boolean isExistIcmsEntrada;
    private boolean isExistIcmsSaida;

    private final SimpleDateFormat dateFormat;
    private String sql;

    public AtualizacaoFiscalTemp() {
        this.sql = "";
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    //***************************** Methods ************************************
    public String getExecuteTabpro() {
        sql = "UPDATE tabpro SET "
                + "clasfiscal = '" + this.getClasFiscal() + "', "
                + "fatorpis = " + this.getFatorPis() + ", "
                + "cstpis = '" + this.getCstPis() + "', "
                + "fatorcofins =" + this.getFatorCofins() + ", "
                + "cstcofins = '" + this.getCstCofins() + "', "
                + "icms = " + this.getIcmsSimples() + ", "
                + "cst = '" + this.getCst() + "', "
                + "cest = '" + this.getCest() + "', "
                + "indice = '" + this.getIndice() + "', "
                + "rgdata = '" + dateFormat.format(this.getRgdata()) + "' "
                + "where codpro = '" + this.getCodpro() + "';";

        return sql;
    }

    public String getExecuteTabprofil() {
        sql = "UPDATE tabprofil SET "
                + "nat_rec = '" + this.getNatRec() + "', "
                + "fatorpiscom = " + this.getFatorPis() + ", "
                + "cstpise = '" + this.getCstPis() + "', "
                + "cstcofinse = '" + this.getCstCofins() + "', "
                + "fatorcofinscom = " + this.getFatorCofins() + ", "
                + "rgdata = '" + dateFormat.format(this.getRgdata()) + "', "
                + "mixfiscal = 'S' "
                + "where codpro = '" + this.getCodpro() + "' ";

        int cf = this.getCodfil().split(",").length;
        for (int i = 0; i < cf; i++) {
            if (i == 0) {
                sql += "and (codfil = " + Integer.parseInt(codfil.split(",")[i]) + "";
            } else {
                sql += " or codfil = " + Integer.parseInt(codfil.split(",")[i]) + "";
            }
        }
        sql += ");";

        return sql;
    }

    public List<String> getExecuteTabproimp() {
        List<String> result = new ArrayList<>();

        for (ImpTemp impTemp : this.getResultImpTemp()) {
            if (impTemp.isIsInsertTemp()) {
                for (int i = 1; i <= (impTemp.isIsUpdateTemp() ? 1 : 2); i++) {
                    sql = "INSERT INTO tabproimp (codpro,codfil,tpimpos,ncm,piscst,pisppis,cofinscst,cofinspcofins,icmscst,icmspicms,icmspicmsst,icmspredbc,icmspredbcst,icmsmodbc,mixfiscal) "
                            + "VALUES('" + this.getCodpro() + "', "
                            + "" + impTemp.getCodfil() + ", "
                            + "'" + (i == 1 ? (impTemp.getTpImpos().equals("") ? "D" : impTemp.getTpImpos()) : "A") + "', "
                            + "'" + this.getClasFiscal() + "', "
                            + "'" + this.getPisCst() + "', "
                            + "" + this.getPispPis() + ", "
                            + "'" + this.getCofinsCst() +  "', "
                            + "" + this.getCofinspCofins() + ", "
                            + "'" + this.getCst() + "', "
                            + "" + this.getIcmsSimples() + ", "
                            + "" + this.getIcmspIcmsSt() + ", "
                            + "" + this.getIcmspRedBc() + ", "
                            + "" + this.getIcmspRedBcSt() + ", "
                            + "'" + this.getIcmsModBc() + "', "
                            + "'S');";
                    result.add(sql);
                }
            }
            if (impTemp.isIsUpdateTemp()) {
                sql = "UPDATE tabproimp SET ncm = '" + this.getClasFiscal() + "', ";
                if (impTemp.getTpUpdate().contains("P")) {
                    sql += "piscst = '" + this.getPisCst() + "', "
                            + "pisppis = " + this.getPispPis() + ", "
                            + "cofinscst = '" + this.getCofinsCst() + "', "
                            + "cofinspcofins = " + this.getCofinspCofins() + ", ";
                }
                if (impTemp.getTpUpdate().contains("I")) {
                    sql += "icmscst = '" + this.getCst() + "', "
                            + "icmspicms = " + this.getIcmsSimples() + ", "
                            + "icmspicmsst = " + this.getIcmspIcmsSt() + ", "
                            + "icmspredbc = " + this.getIcmspRedBc() + ", "
                            + "icmspredbcst = " + this.getIcmspRedBcSt() + ", "
                            + "icmsmodbc = '" + this.getIcmsModBc() + "', ";
                }
                sql += "mixfiscal= 'S' where codpro = '" + this.getCodpro() + "' and codfil= " + impTemp.getCodfil() + " and (tpimpos='A' or tpimpos='D');";

                result.add(sql);
            }
        }

        return result;
    }

    public List<String> getExecuteTabproimpe() {
        List<String> resultSql = new ArrayList<>();
        for (ImpTemp impTemp : this.getResultImpe()) {

            if (impTemp.isIsInsertTemp()) {
                sql = "INSERT INTO tabproimpe (CODIGO_PRODUTO,CODIGO_FILIAL,EAN,TIPO_MVA,MVA,MVA_DISTRIBUIDOR,MVA_DATA_INI,MVA_DATA_FIM,CREDITO_OUTORGADO,EI_CST,EI_ALQ,EI_ALQST,EI_RBC,EI_RBCST,ED_CST,ED_ALQ,ED_ALQST,ED_RBC,ED_RBCST,ES_CST,ES_ALQ,ES_ALQST,ES_RBC,ES_RBCST,NFI_CST,NFD_CST,NFS_CSOSN,NF_ALQ,FUNDAMENTO_LEGAL,MIXFISCAL) "
                        + "VALUES('" + this.getCodpro() + "', "
                        + "" + impTemp.getCodfil() + ", "
                        + "'" + this.getEan() + "', "
                        + "" + this.getMva() + ", "
                        + "" + this.getMvaDistribuidor() + ", "
                        + "'" + dateFormat.format(this.getMvaDataIni()) + "', "
                        + "'" + dateFormat.format(this.getMvaDataFim()) + "', "
                        + "" + this.getCreditoOutorgado() + ", "
                        + "'" + this.getEiCst() + "', "
                        + "" + this.getEiAlq() + ", "
                        + "" + this.getEiAlqst() + ", "
                        + "" + this.getEiRbc() + ", "
                        + "" + this.getEiRbcst() + ", "
                        + "'" + this.getEdCst() + "', "
                        + "" + this.getEdAlq() + ", "
                        + "" + this.getEdAlqst() + ", "
                        + "" + this.getEdRbc() + ", "
                        + "" + this.getEdRbcst() + ", "
                        + "'" + this.getEsCst() + "', "
                        + "" + this.getEsAlq() + ", "
                        + "" + this.getEsAlqst() + ", "
                        + "" + this.getEsRbc() + ", "
                        + "" + this.getEsRbcst() + ", "
                        + "'" + this.getNfiCst() + "', "
                        + "'" + this.getNfdCst() + "', "
                        + "'" + this.getNfsCsosn() + "', "
                        + "" + this.getNfAlq() + ", "
                        + "'" + this.getFundamentoLegal() + "', "
                        + "'S');";

            } else {
                sql = "UPDATE tabproimpe SET "
                        + "tipo_mva = '" + this.getTipoMva() + "', "
                        + "mva = " + this.getMva() + ", "
                        + "mva_distribuidor = " + this.getMvaDistribuidor() + ", "
                        + "mva_data_ini = '" + dateFormat.format(this.getMvaDataIni()) + "', "
                        + "mva_data_fim = '" + dateFormat.format(this.getMvaDataFim()) + "', "
                        + "credito_outorgado = " + this.getCreditoOutorgado() + ", "
                        + "ei_cst = '" + this.getEiCst() + "', "
                        + "ei_alq = " + this.getEiAlq() + ", "
                        + "ei_alqst = " + this.getEiAlqst() + ", "
                        + "ei_rbc = " + this.getEiRbc() + ", "
                        + "ei_rbcst = " + this.getEiRbcst() + ", "
                        + "ed_cst = '" + this.getEdCst() + "', "
                        + "ed_alq = " + this.getEdAlq() + ", "
                        + "ed_alqst = " + this.getEdAlqst() + ", "
                        + "ed_rbc = " + this.getEdRbc() + ", "
                        + "ed_rbcst = " + this.getEdRbcst() + ", "
                        + "es_cst = '" + this.getEsCst() + "', "
                        + "es_alq = " + this.getEsAlq() + ", "
                        + "es_alqst = " + this.getEsAlqst() + ", "
                        + "es_rbc = " + this.getEsRbc() + ", "
                        + "es_rbcst = " + this.getEsRbcst() + ", "
                        + "nfi_cst = '" + this.getNfiCst() + "', "
                        + "nfd_cst = '" + this.getNfdCst() + "', "
                        + "nfs_csosn = '" + this.getNfsCsosn() + "', "
                        + "nf_alq = " + this.getNfAlq() + ", "
                        + "fundamento_legal = '" + this.getFundamentoLegal() + "', "
                        + "mixfiscal = 'S' "
                        + "where codigo_produto = '" + this.getCodpro() + "' and codigo_filial= " + impTemp.getCodfil() + ";";
            }
            resultSql.add(sql);
        }

        return resultSql;
    }

    //************************** get && setts **********************************
    /**
     * @return the codpro
     */
    public String getCodpro() {
        return codpro;
    }

    /**
     * @param codpro the codpro to set
     */
    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    /**
     * @return the codfil
     */
    public String getCodfil() {
        return codfil;
    }

    /**
     * @param codfil the codfil to set
     */
    public void setCodfil(String codfil) {
        this.codfil = codfil;
    }

    /**
     * tabpro && tabproimp
     *
     * @return the clasFiscal
     */
    public String getClasFiscal() {
        return clasFiscal;
    }

    /**
     * tabpro && tabproimp
     *
     * @param clasFiscal the clasFiscal to set
     */
    public void setClasFiscal(String clasFiscal) {
        this.clasFiscal = clasFiscal;
    }

    /**
     * tabpro && tabprofil
     *
     * @return the fatorPis
     */
    public Double getFatorPis() {
        if (fatorPis == null) {
            fatorPis = 0.0;
        }
        return fatorPis;
    }

    /**
     * tabpro && tabprofil
     *
     * @param fatorPis the fatorPis to set
     */
    public void setFatorPis(Double fatorPis) {
        this.fatorPis = fatorPis;
    }

    /**
     * tabpro && tabprofil
     *
     * @return the cstPis
     */
    public String getCstPis() {
        return cstPis;
    }

    /**
     * tabpro && tabprofil
     *
     * @param cstPis the cstPis to set
     */
    public void setCstPis(String cstPis) {
        this.cstPis = cstPis;
    }

    /**
     * tabpro && tabprofil
     *
     * @return the fatorCofins
     */
    public Double getFatorCofins() {
        if (fatorCofins == null) {
            fatorCofins = 0.0;
        }
        return fatorCofins;
    }

    /**
     * tabpro && tabprofil
     *
     * @param fatorCofins the fatorCofins to set
     */
    public void setFatorCofins(Double fatorCofins) {
        this.fatorCofins = fatorCofins;
    }

    /**
     * tabpro && tabprofil
     *
     * @return the cstCofins
     */
    public String getCstCofins() {
        return cstCofins;
    }

    /**
     * tabpro && tabprofil
     *
     * @param cstCofins the cstCofins to set
     */
    public void setCstCofins(String cstCofins) {
        this.cstCofins = cstCofins;
    }

    /**
     * tabpro && tabproimp
     *
     * @return the icmsSimples
     */
    public Double getIcmsSimples() {
        if (icmsSimples == null) {
            icmsSimples = 0.0;
        }
        return icmsSimples;
    }

    /**
     * tabpro && tabproimp
     *
     * @param icmsSimples the icmsSimples to set
     */
    public void setIcmsSimples(Double icmsSimples) {
        this.icmsSimples = icmsSimples;
    }

    /**
     * tabpro && tabproimp
     *
     * @return the cst
     */
    public String getCst() {
        return cst;
    }

    /**
     * tabpro && tabproimp
     *
     * @param cst the cst to set
     */
    public void setCst(String cst) {
        this.cst = cst;
    }

    /**
     * tabpro
     *
     * @return the cest
     */
    public String getCest() {
        return cest;
    }

    /**
     * tabpro
     *
     * @param cest the cest to set
     */
    public void setCest(String cest) {
        this.cest = cest;
    }

    /**
     * tabpro
     *
     * @return the indice
     */
    public String getIndice() {
        return indice;
    }

    /**
     * tabpro
     *
     * @param indice the indice to set
     */
    public void setIndice(String indice) {
        this.indice = indice;
    }

    /**
     * tabprofil
     *
     * @return the natRec
     */
    public String getNatRec() {
        return natRec;
    }

    /**
     * tabprofil
     *
     * @param natRec the natRec to set
     */
    public void setNatRec(String natRec) {
        this.natRec = natRec;
    }

    /**
     * tabpro && tabprofil
     *
     * @return the rgdata
     */
    public Date getRgdata() {
        return rgdata;
    }

    /**
     * tabpro && tabprofil
     *
     * @param rgdata the rgdata to set
     */
    public void setRgdata(Date rgdata) {
        this.rgdata = rgdata;
    }

    /**
     * tabproimp
     *
     * @return the pisCst
     */
    public String getPisCst() {
        return pisCst;
    }

    /**
     * tabproimp
     *
     * @param pisCst the pisCst to set
     */
    public void setPisCst(String pisCst) {
        this.pisCst = pisCst;
    }

    /**
     * tabproimp
     *
     * @return the pispPis
     */
    public Double getPispPis() {
        if (pispPis == null) {
            pispPis = 0.0;
        }
        return pispPis;
    }

    /**
     * tabproimp
     *
     * @param pispPis the pispPis to set
     */
    public void setPispPis(Double pispPis) {
        this.pispPis = pispPis;
    }

    /**
     * tabproimp
     *
     * @return the cofinsCst
     */
    public String getCofinsCst() {
        return cofinsCst;
    }

    /**
     * tabproimp
     *
     * @param cofinsCst the cofinsCst to set
     */
    public void setCofinsCst(String cofinsCst) {
        this.cofinsCst = cofinsCst;
    }

    /**
     * tabproimp
     *
     * @return the cofinspCofins
     */
    public Double getCofinspCofins() {
        if (cofinspCofins == null) {
            cofinspCofins = 0.0;
        }
        return cofinspCofins;
    }

    /**
     * tabproimp
     *
     * @param cofinspCofins the cofinspCofins to set
     */
    public void setCofinspCofins(Double cofinspCofins) {
        this.cofinspCofins = cofinspCofins;
    }

    /**
     * tabproimp
     *
     * @return the icmspIcmsSt
     */
    public Double getIcmspIcmsSt() {
        if (icmspIcmsSt == null) {
            icmspIcmsSt = 0.0;
        }
        return icmspIcmsSt;
    }

    /**
     * tabproimp
     *
     * @param icmspIcmsSt the icmspIcmsSt to set
     */
    public void setIcmspIcmsSt(Double icmspIcmsSt) {
        this.icmspIcmsSt = icmspIcmsSt;
    }

    /**
     * tabproimp
     *
     * @return the icmspRedBc
     */
    public Double getIcmspRedBc() {
        if (icmspRedBc == null) {
            icmspRedBc = 0.0;
        }
        return icmspRedBc;
    }

    /**
     * tabproimp
     *
     * @param icmspRedBc the icmspRedBc to set
     */
    public void setIcmspRedBc(Double icmspRedBc) {
        this.icmspRedBc = icmspRedBc;
    }

    /**
     * tabproimp
     *
     * @return the icmspRedBcSt
     */
    public Double getIcmspRedBcSt() {
        if (icmspRedBcSt == null) {
            icmspRedBcSt = 0.0;
        }
        return icmspRedBcSt;
    }

    /**
     * tabproimp
     *
     * @param icmspRedBcSt the icmspRedBcSt to set
     */
    public void setIcmspRedBcSt(Double icmspRedBcSt) {
        this.icmspRedBcSt = icmspRedBcSt;
    }

    /**
     * tabproimp
     *
     * @return the icmsModBc
     */
    public String getIcmsModBc() {
        return icmsModBc;
    }

    /**
     * tabproimp
     *
     * @param icmsModBc the icmsModBc to set
     */
    public void setIcmsModBc(String icmsModBc) {
        this.icmsModBc = icmsModBc;
    }

    /**
     * tabproimpe
     *
     * @return the ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * tabproimpe
     *
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * tabproimpe
     *
     * @return the tipoMva
     */
    public String getTipoMva() {
        return tipoMva;
    }

    /**
     * tabproimpe
     *
     * @param tipoMva the tipoMva to set
     */
    public void setTipoMva(String tipoMva) {
        this.tipoMva = tipoMva;
    }

    /**
     * tabproimpe
     *
     * @return the mva
     */
    public Double getMva() {
        if (mva == null) {
            mva = 0.0;
        }
        return mva;
    }

    /**
     * tabproimpe
     *
     * @param mva the mva to set
     */
    public void setMva(Double mva) {
        this.mva = mva;
    }

    /**
     * tabproimpe
     *
     * @return the mvaDistribuidor
     */
    public Double getMvaDistribuidor() {
        if (mvaDistribuidor == null) {
            mvaDistribuidor = 0.0;
        }
        return mvaDistribuidor;
    }

    /**
     * tabproimpe
     *
     * @param mvaDistribuidor the mvaDistribuidor to set
     */
    public void setMvaDistribuidor(Double mvaDistribuidor) {
        this.mvaDistribuidor = mvaDistribuidor;
    }

    /**
     * tabproimpe
     *
     * @return the mvaDataIni
     */
    public Date getMvaDataIni() {
        return mvaDataIni;
    }

    /**
     * tabproimpe
     *
     * @param mvaDataIni the mvaDataIni to set
     */
    public void setMvaDataIni(Date mvaDataIni) {
        this.mvaDataIni = mvaDataIni;
    }

    /**
     * tabproimpe
     *
     * @return the mvaDataFim
     */
    public Date getMvaDataFim() {
        return mvaDataFim;
    }

    /**
     * tabproimpe
     *
     * @param mvaDataFim the mvaDataFim to set
     */
    public void setMvaDataFim(Date mvaDataFim) {
        this.mvaDataFim = mvaDataFim;
    }

    /**
     * tabproimpe
     *
     * @return the creditoOutorgado
     */
    public Integer getCreditoOutorgado() {
        return creditoOutorgado;
    }

    /**
     * tabproimpe
     *
     * @param creditoOutorgado the creditoOutorgado to set
     */
    public void setCreditoOutorgado(Integer creditoOutorgado) {
        this.creditoOutorgado = creditoOutorgado;
    }

    /**
     * tabproimpe
     *
     * @return the eiCst
     */
    public String getEiCst() {
        return eiCst;
    }

    /**
     * tabproimpe
     *
     * @param eiCst the eiCst to set
     */
    public void setEiCst(String eiCst) {
        this.eiCst = eiCst;
    }

    /**
     * tabproimpe
     *
     * @return the eiAlq
     */
    public Double getEiAlq() {
        if (eiAlq == null) {
            eiAlq = 0.0;
        }
        return eiAlq;
    }

    /**
     * tabproimpe
     *
     * @param eiAlq the eiAlq to set
     */
    public void setEiAlq(Double eiAlq) {
        this.eiAlq = eiAlq;
    }

    /**
     * tabproimpe
     *
     * @return the eiAlqst
     */
    public Double getEiAlqst() {
        if (eiAlqst == null) {
            eiAlqst = 0.0;
        }
        return eiAlqst;
    }

    /**
     * tabproimpe
     *
     * @param eiAlqst the eiAlqst to set
     */
    public void setEiAlqst(Double eiAlqst) {
        this.eiAlqst = eiAlqst;
    }

    /**
     * tabproimpe
     *
     * @return the eiRbc
     */
    public Double getEiRbc() {
        if (eiRbc == null) {
            eiRbc = 0.0;
        }
        return eiRbc;
    }

    /**
     * tabproimpe
     *
     * @param eiRbc the eiRbc to set
     */
    public void setEiRbc(Double eiRbc) {
        this.eiRbc = eiRbc;
    }

    /**
     * tabproimpe
     *
     * @return the eiRbcst
     */
    public Double getEiRbcst() {
        if (eiRbcst == null) {
            eiRbcst = 0.0;
        }
        return eiRbcst;
    }

    /**
     * tabproimpe
     *
     * @param eiRbcst the eiRbcst to set
     */
    public void setEiRbcst(Double eiRbcst) {
        this.eiRbcst = eiRbcst;
    }

    /**
     * tabproimpe
     *
     * @return the edCst
     */
    public String getEdCst() {
        return edCst;
    }

    /**
     * tabproimpe
     *
     * @param edCst the edCst to set
     */
    public void setEdCst(String edCst) {
        this.edCst = edCst;
    }

    /**
     * tabproimpe
     *
     * @return the edAlq
     */
    public Double getEdAlq() {
        if (edAlq == null) {
            edAlq = 0.0;
        }
        return edAlq;
    }

    /**
     * tabproimpe
     *
     * @param edAlq the edAlq to set
     */
    public void setEdAlq(Double edAlq) {
        this.edAlq = edAlq;
    }

    /**
     * tabproimpe
     *
     * @return the edAlqst
     */
    public Double getEdAlqst() {
        if (edAlqst == null) {
            edAlqst = 0.0;
        }
        return edAlqst;
    }

    /**
     * tabproimpe
     *
     * @param edAlqst the edAlqst to set
     */
    public void setEdAlqst(Double edAlqst) {
        this.edAlqst = edAlqst;
    }

    /**
     * tabproimpe
     *
     * @return the edRbc
     */
    public Double getEdRbc() {
        if (edRbc == null) {
            edRbc = 0.0;
        }
        return edRbc;
    }

    /**
     * tabproimpe
     *
     * @param edRbc the edRbc to set
     */
    public void setEdRbc(Double edRbc) {
        this.edRbc = edRbc;
    }

    /**
     * tabproimpe
     *
     * @return the edRbcst
     */
    public Double getEdRbcst() {
        if (edRbcst == null) {
            edRbcst = 0.0;
        }
        return edRbcst;
    }

    /**
     * tabproimpe
     *
     * @param edRbcst the edRbcst to set
     */
    public void setEdRbcst(Double edRbcst) {
        this.edRbcst = edRbcst;
    }

    /**
     * tabproimpe
     *
     * @return the esCst
     */
    public String getEsCst() {
        return esCst;
    }

    /**
     * tabproimpe
     *
     * @param esCst the esCst to set
     */
    public void setEsCst(String esCst) {
        this.esCst = esCst;
    }

    /**
     * tabproimpe
     *
     * @return the esAlq
     */
    public Double getEsAlq() {
        if (esAlq == null) {
            esAlq = 0.0;
        }
        return esAlq;
    }

    /**
     * tabproimpe
     *
     * @param esAlq the esAlq to set
     */
    public void setEsAlq(Double esAlq) {
        this.esAlq = esAlq;
    }

    /**
     * tabproimpe
     *
     * @return the esAlqst
     */
    public Double getEsAlqst() {
        if (esAlqst == null) {
            esAlqst = 0.0;
        }
        return esAlqst;
    }

    /**
     * tabproimpe
     *
     * @param esAlqst the esAlqst to set
     */
    public void setEsAlqst(Double esAlqst) {
        this.esAlqst = esAlqst;
    }

    /**
     * tabproimpe
     *
     * @return the esRbc
     */
    public Double getEsRbc() {
        if (esRbc == null) {
            esRbc = 0.0;
        }
        return esRbc;
    }

    /**
     * tabproimpe
     *
     * @param esRbc the esRbc to set
     */
    public void setEsRbc(Double esRbc) {
        this.esRbc = esRbc;
    }

    /**
     * tabproimpe
     *
     * @return the esRbcst
     */
    public Double getEsRbcst() {
        if (esRbcst == null) {
            esRbcst = 0.0;
        }
        return esRbcst;
    }

    /**
     * tabproimpe
     *
     * @param esRbcst the esRbcst to set
     */
    public void setEsRbcst(Double esRbcst) {
        this.esRbcst = esRbcst;
    }

    /**
     * tabproimpe
     *
     * @return the nfiCst
     */
    public String getNfiCst() {
        return nfiCst;
    }

    /**
     * tabproimpe
     *
     * @param nfiCst the nfiCst to set
     */
    public void setNfiCst(String nfiCst) {
        this.nfiCst = nfiCst;
    }

    /**
     * tabproimpe
     *
     * @return the nfdCst
     */
    public String getNfdCst() {
        return nfdCst;
    }

    /**
     * tabproimpe
     *
     * @param nfdCst the nfdCst to set
     */
    public void setNfdCst(String nfdCst) {
        this.nfdCst = nfdCst;
    }

    /**
     * tabproimpe
     *
     * @return the nfsCsosn
     */
    public String getNfsCsosn() {
        return nfsCsosn;
    }

    /**
     * tabproimpe
     *
     * @param nfsCsosn the nfsCsosn to set
     */
    public void setNfsCsosn(String nfsCsosn) {
        this.nfsCsosn = nfsCsosn;
    }

    /**
     * tabproimpe
     *
     * @return the nfAlq
     */
    public Double getNfAlq() {
        if (nfAlq == null) {
            nfAlq = 0.0;
        }
        return nfAlq;
    }

    /**
     * tabproimpe
     *
     * @param nfAlq the nfAlq to set
     */
    public void setNfAlq(Double nfAlq) {
        this.nfAlq = nfAlq;
    }

    /**
     * tabproimpe
     *
     * @return the fundamentoLegal
     */
    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    /**
     * tabproimpe
     *
     * @param fundamentoLegal the fundamentoLegal to set
     */
    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

    /**
     * @return the isExistPisCofins
     */
    public boolean isIsExistPisCofins() {
        return isExistPisCofins;
    }

    /**
     * @param isExistPisCofins the isExistPisCofins to set
     */
    public void setIsExistPisCofins(boolean isExistPisCofins) {
        this.isExistPisCofins = isExistPisCofins;
    }

    /**
     * @return the isExistIcmsEntrada
     */
    public boolean isIsExistIcmsEntrada() {
        return isExistIcmsEntrada;
    }

    /**
     * @param isExistIcmsEntrada the isExistIcmsEntrada to set
     */
    public void setIsExistIcmsEntrada(boolean isExistIcmsEntrada) {
        this.isExistIcmsEntrada = isExistIcmsEntrada;
    }

    /**
     * @return the isExistIcmsSaida
     */
    public boolean isIsExistIcmsSaida() {
        return isExistIcmsSaida;
    }

    /**
     * @param isExistIcmsSaida the isExistIcmsSaida to set
     */
    public void setIsExistIcmsSaida(boolean isExistIcmsSaida) {
        this.isExistIcmsSaida = isExistIcmsSaida;
    }

    public List<ImpTemp> getResultImpTemp() {
        if (resultImpTemp == null) {
            resultImpTemp = new ArrayList<>();
        }
        return resultImpTemp;
    }

    public void setResultImpTemp(List<ImpTemp> resultImpTemp) {
        this.resultImpTemp = resultImpTemp;
    }

    public List<ImpTemp> getResultImpe() {
        if (resultImpe == null) {
            resultImpe = new ArrayList<>();
        }
        return resultImpe;
    }

    public void setResultImpe(List<ImpTemp> resultImpe) {
        this.resultImpe = resultImpe;
    }

    public Double getIcmsRegime() {
        return icmsRegime;
    }

    public void setIcmsRegime(Double icmsRegime) {
        this.icmsRegime = icmsRegime;
    }

}
