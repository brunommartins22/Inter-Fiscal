/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "TABPROIMP")
@NamedQueries({
    @NamedQuery(name = "Tabproimp.findAll", query = "SELECT t FROM Tabproimp t")})
public class Tabproimp implements Model {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TabproimpPK tabproimpPK;
    @Size(max = 3)
    @Column(name = "ICMSCST")
    private String icmscst;
    @Size(max = 1)
    @Column(name = "ICMSORIG",updatable = false)
    private String icmsorig;
    @Size(max = 1)
    @Column(name = "ICMSMODBC",updatable = false)
    private String icmsmodbc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ICMSPREDBC")
    private Double icmspredbc;
    @Column(name = "ICMSPICMS")
    private Double icmspicms;
    @Size(max = 1)
    @Column(name = "ICMSMODBCST",updatable = false)
    private String icmsmodbcst;
    @Column(name = "ICMSPMVAST",updatable = false)
    private Double icmspmvast;
    @Column(name = "ICMSPREDBCST")
    private Double icmspredbcst;
    @Column(name = "ICMSPICMSST")
    private Double icmspicmsst;
    @Size(max = 2)
    @Column(name = "IPIPICST",updatable = false)
    private String ipipicst;
    @Size(max = 3)
    @Column(name = "IPICENQ",updatable = false)
    private String ipicenq;
    @Size(max = 5)
    @Column(name = "IPICLENQ",updatable = false)
    private String ipiclenq;
    @Size(max = 14)
    @Column(name = "IPICNPJPROD",updatable = false)
    private String ipicnpjprod;
    @Size(max = 1)
    @Column(name = "TIPOIPICALC",updatable = false)
    private String tipoipicalc;
    @Column(name = "IPIVUNID",updatable = false)
    private Double ipivunid;
    @Column(name = "IPIPIPI",updatable = false)
    private Double ipipipi;
    @Size(max = 2)
    @Column(name = "PISCST")
    private String piscst;
    @Size(max = 1)
    @Column(name = "TIPOPISCALC",updatable = false)
    private String tipopiscalc;
    @Column(name = "PISPPIS")
    private Double pisppis;
    @Column(name = "VALIQPROD",updatable = false)
    private Double valiqprod;
    @Size(max = 1)
    @Column(name = "TIPOPISSTCALC",updatable = false)
    private String tipopisstcalc;
    @Column(name = "PISPPISST",updatable = false)
    private Double pisppisst;
    @Column(name = "VALIQPRODST",updatable = false)
    private Double valiqprodst;
    @Size(max = 2)
    @Column(name = "COFINSCST")
    private String cofinscst;
    @Size(max = 1)
    @Column(name = "TIPOCOFINSCALC",updatable = false)
    private String tipocofinscalc;
    @Column(name = "COFINSPCOFINS")
    private Double cofinspcofins;
    @Column(name = "COFINSVALIQPROD",updatable = false)
    private Double cofinsvaliqprod;
    @Size(max = 1)
    @Column(name = "TIPOCOFINSSTCALC",updatable = false)
    private String tipocofinsstcalc;
    @Column(name = "COFINSPCOFINSST",updatable = false)
    private Double cofinspcofinsst;
    @Column(name = "COFINSVALIQPRODST",updatable = false)
    private Double cofinsvaliqprodst;
    @Column(name = "ISSVALIQ",updatable = false)
    private Double issvaliq;
    @Size(max = 4)
    @Column(name = "CLISTSERV",updatable = false)
    private String clistserv;
    @Size(max = 3)
    @Column(name = "EXTIPI",updatable = false)
    private String extipi;
    @Size(max = 2)
    @Column(name = "ICMSMOTDESICMS",updatable = false)
    private String icmsmotdesicms;
    @Column(name = "ICMSPCREDSN",updatable = false)
    private Double icmspcredsn;
    @Size(max = 1)
    @Column(name = "CSITTRIB",updatable = false)
    private String csittrib;
    @Column(name = "ICMSPBCOP",updatable = false)
    private Double icmspbcop;
    @Size(max = 3)
    @Column(name = "ICMSCSTINT",updatable = false)
    private String icmscstint;

    @Size(max = 4)
    @Column(name = "CFOP")
    private String cfop;

    @Size(max = 4)
    @Column(name = "CFOPT",updatable = false)
    private String cfopt;

    @Column(name = "CODINFNFE",updatable = false)
    private Integer codinfnfe;

    @Size(max = 8)
    @Column(name = "NCM")
    private String ncm;

    @Size(max = 1)
    @Column(name = "MIXFISCAL")
    private String mixfiscal;

    public Tabproimp() {
    }

    public Tabproimp(TabproimpPK tabproimpPK) {
        this.tabproimpPK = tabproimpPK;
    }

    public Tabproimp(String codpro, int codfil, String tpimpos) {
        this.tabproimpPK = new TabproimpPK(codpro, codfil, tpimpos);
    }

    public TabproimpPK getTabproimpPK() {
        return tabproimpPK;
    }

    public void setTabproimpPK(TabproimpPK tabproimpPK) {
        this.tabproimpPK = tabproimpPK;
    }

    public String getIcmscst() {
        return icmscst;
    }

    public void setIcmscst(String icmscst) {
        this.icmscst = icmscst;
    }

    public String getIcmsorig() {
        return icmsorig;
    }

    public void setIcmsorig(String icmsorig) {
        this.icmsorig = icmsorig;
    }

    public String getIcmsmodbc() {
        return icmsmodbc;
    }

    public void setIcmsmodbc(String icmsmodbc) {
        this.icmsmodbc = icmsmodbc;
    }

    public Double getIcmspredbc() {
        return icmspredbc;
    }

    public void setIcmspredbc(Double icmspredbc) {
        this.icmspredbc = icmspredbc;
    }

    public Double getIcmspicms() {
        return icmspicms;
    }

    public void setIcmspicms(Double icmspicms) {
        this.icmspicms = icmspicms;
    }

    public String getIcmsmodbcst() {
        return icmsmodbcst;
    }

    public void setIcmsmodbcst(String icmsmodbcst) {
        this.icmsmodbcst = icmsmodbcst;
    }

    public Double getIcmspmvast() {
        return icmspmvast;
    }

    public void setIcmspmvast(Double icmspmvast) {
        this.icmspmvast = icmspmvast;
    }

    public Double getIcmspredbcst() {
        return icmspredbcst;
    }

    public void setIcmspredbcst(Double icmspredbcst) {
        this.icmspredbcst = icmspredbcst;
    }

    public Double getIcmspicmsst() {
        return icmspicmsst;
    }

    public void setIcmspicmsst(Double icmspicmsst) {
        this.icmspicmsst = icmspicmsst;
    }

    public String getIpipicst() {
        return ipipicst;
    }

    public void setIpipicst(String ipipicst) {
        this.ipipicst = ipipicst;
    }

    public String getIpicenq() {
        return ipicenq;
    }

    public void setIpicenq(String ipicenq) {
        this.ipicenq = ipicenq;
    }

    public String getIpiclenq() {
        return ipiclenq;
    }

    public void setIpiclenq(String ipiclenq) {
        this.ipiclenq = ipiclenq;
    }

    public String getIpicnpjprod() {
        return ipicnpjprod;
    }

    public void setIpicnpjprod(String ipicnpjprod) {
        this.ipicnpjprod = ipicnpjprod;
    }

    public String getTipoipicalc() {
        return tipoipicalc;
    }

    public void setTipoipicalc(String tipoipicalc) {
        this.tipoipicalc = tipoipicalc;
    }

    public Double getIpivunid() {
        return ipivunid;
    }

    public void setIpivunid(Double ipivunid) {
        this.ipivunid = ipivunid;
    }

    public Double getIpipipi() {
        return ipipipi;
    }

    public void setIpipipi(Double ipipipi) {
        this.ipipipi = ipipipi;
    }

    public String getPiscst() {
        return piscst;
    }

    public void setPiscst(String piscst) {
        this.piscst = piscst;
    }

    public String getTipopiscalc() {
        return tipopiscalc;
    }

    public void setTipopiscalc(String tipopiscalc) {
        this.tipopiscalc = tipopiscalc;
    }

    public Double getPisppis() {
        return pisppis;
    }

    public void setPisppis(Double pisppis) {
        this.pisppis = pisppis;
    }

    public Double getValiqprod() {
        return valiqprod;
    }

    public void setValiqprod(Double valiqprod) {
        this.valiqprod = valiqprod;
    }

    public String getTipopisstcalc() {
        return tipopisstcalc;
    }

    public void setTipopisstcalc(String tipopisstcalc) {
        this.tipopisstcalc = tipopisstcalc;
    }

    public Double getPisppisst() {
        return pisppisst;
    }

    public void setPisppisst(Double pisppisst) {
        this.pisppisst = pisppisst;
    }

    public Double getValiqprodst() {
        return valiqprodst;
    }

    public void setValiqprodst(Double valiqprodst) {
        this.valiqprodst = valiqprodst;
    }

    public String getCofinscst() {
        return cofinscst;
    }

    public void setCofinscst(String cofinscst) {
        this.cofinscst = cofinscst;
    }

    public String getTipocofinscalc() {
        return tipocofinscalc;
    }

    public void setTipocofinscalc(String tipocofinscalc) {
        this.tipocofinscalc = tipocofinscalc;
    }

    public Double getCofinspcofins() {
        return cofinspcofins;
    }

    public void setCofinspcofins(Double cofinspcofins) {
        this.cofinspcofins = cofinspcofins;
    }

    public Double getCofinsvaliqprod() {
        return cofinsvaliqprod;
    }

    public void setCofinsvaliqprod(Double cofinsvaliqprod) {
        this.cofinsvaliqprod = cofinsvaliqprod;
    }

    public String getTipocofinsstcalc() {
        return tipocofinsstcalc;
    }

    public void setTipocofinsstcalc(String tipocofinsstcalc) {
        this.tipocofinsstcalc = tipocofinsstcalc;
    }

    public Double getCofinspcofinsst() {
        return cofinspcofinsst;
    }

    public void setCofinspcofinsst(Double cofinspcofinsst) {
        this.cofinspcofinsst = cofinspcofinsst;
    }

    public Double getCofinsvaliqprodst() {
        return cofinsvaliqprodst;
    }

    public void setCofinsvaliqprodst(Double cofinsvaliqprodst) {
        this.cofinsvaliqprodst = cofinsvaliqprodst;
    }

    public Double getIssvaliq() {
        return issvaliq;
    }

    public void setIssvaliq(Double issvaliq) {
        this.issvaliq = issvaliq;
    }

    public String getClistserv() {
        return clistserv;
    }

    public void setClistserv(String clistserv) {
        this.clistserv = clistserv;
    }

    public String getExtipi() {
        return extipi;
    }

    public void setExtipi(String extipi) {
        this.extipi = extipi;
    }

    public String getIcmsmotdesicms() {
        return icmsmotdesicms;
    }

    public void setIcmsmotdesicms(String icmsmotdesicms) {
        this.icmsmotdesicms = icmsmotdesicms;
    }

    public Double getIcmspcredsn() {
        return icmspcredsn;
    }

    public void setIcmspcredsn(Double icmspcredsn) {
        this.icmspcredsn = icmspcredsn;
    }

    public String getCsittrib() {
        return csittrib;
    }

    public void setCsittrib(String csittrib) {
        this.csittrib = csittrib;
    }

    public Double getIcmspbcop() {
        return icmspbcop;
    }

    public void setIcmspbcop(Double icmspbcop) {
        this.icmspbcop = icmspbcop;
    }

    public String getIcmscstint() {
        return icmscstint;
    }

    public void setIcmscstint(String icmscstint) {
        this.icmscstint = icmscstint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tabproimpPK != null ? tabproimpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabproimp)) {
            return false;
        }
        Tabproimp other = (Tabproimp) object;
        if ((this.tabproimpPK == null && other.tabproimpPK != null) || (this.tabproimpPK != null && !this.tabproimpPK.equals(other.tabproimpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.transmissornfe.business.entity.Tabproimp[ tabproimpPK=" + tabproimpPK + " ]";
    }

    @Override
    public Object getId() {
        return tabproimpPK;
    }

    @Override
    public void setId(Object object) {
        this.tabproimpPK = (TabproimpPK) object;
    }

    /**
     * @return the cfop
     */
    public String getCfop() {
        return cfop;
    }

    /**
     * @param cfop the cfop to set
     */
    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    /**
     * @return the cfopt
     */
    public String getCfopt() {
        return cfopt;
    }

    /**
     * @param cfopt the cfopt to set
     */
    public void setCfopt(String cfopt) {
        this.cfopt = cfopt;
    }

    /**
     * @return the codinfnfe
     */
    public Integer getCodinfnfe() {
        return codinfnfe;
    }

    /**
     * @param codinfnfe the codinfnfe to set
     */
    public void setCodinfnfe(Integer codinfnfe) {
        this.codinfnfe = codinfnfe;
    }

    /**
     * @return the ncm
     */
    public String getNcm() {
        return ncm;
    }

    /**
     * @param ncm the ncm to set
     */
    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getMixfiscal() {
        return mixfiscal;
    }

    public void setMixfiscal(String mixfiscal) {
        this.mixfiscal = mixfiscal;
    }

}
