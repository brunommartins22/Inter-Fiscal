/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.io.Serializable;
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
 * @author Bruno Martins
 */
@Entity
@Table(name = "tabproimp")
@NamedQueries({
    @NamedQuery(name = "Tabproimp.findAll", query = "SELECT t FROM Tabproimp t")})
public class Tabproimp implements Model {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TabproimpPK tabproimpPK;
    @Size(max = 3)
    @Column(name = "icmscst")
    private String icmscst;
    @Size(max = 1)
    @Column(name = "icmsorig", updatable = false)
    private String icmsorig;
    @Size(max = 1)
    @Column(name = "icmsmodbc")
    private String icmsmodbc;
    @Column(name = "icmspredbc")
    private Double icmspredbc;
    @Column(name = "icmspicms")
    private Double icmspicms;
    @Size(max = 1)
    @Column(name = "icmsmodbcst", updatable = false)
    private String icmsmodbcst;
    @Column(name = "icmspmvast", updatable = false)
    private Double icmspmvast;
    @Column(name = "icmspredbcst")
    private Double icmspredbcst;
    @Column(name = "icmspicmsst")
    private Double icmspicmsst;
    @Size(max = 2)
    @Column(name = "ipipicst", updatable = false)
    private String ipipicst;
    @Size(max = 3)
    @Column(name = "ipicenq", updatable = false)
    private String ipicenq;
    @Size(max = 5)
    @Column(name = "ipiclenq", updatable = false)
    private String ipiclenq;
    @Size(max = 14)
    @Column(name = "ipicnpjprod", updatable = false)
    private String ipicnpjprod;
    @Size(max = 1)
    @Column(name = "tipoipicalc", updatable = false)
    private String tipoipicalc;
    @Column(name = "ipivunid", updatable = false)
    private Double ipivunid;
    @Column(name = "ipipipi", updatable = false)
    private Double ipipipi;
    @Size(max = 2)
    @Column(name = "piscst")
    private String piscst;
    @Size(max = 1)
    @Column(name = "tipopiscalc", updatable = false)
    private String tipopiscalc;
    @Column(name = "pisppis")
    private Double pisppis;
    @Column(name = "valiqprod", updatable = false)
    private Double valiqprod;
    @Size(max = 1)
    @Column(name = "tipopisstcalc", updatable = false)
    private String tipopisstcalc;
    @Column(name = "pisppisst")
    private Double pisppisst;
    @Column(name = "valiqprodst", updatable = false)
    private Double valiqprodst;
    @Size(max = 2)
    @Column(name = "cofinscst")
    private String cofinscst;
    @Size(max = 1)
    @Column(name = "tipocofinscalc", updatable = false)
    private String tipocofinscalc;
    @Column(name = "cofinspcofins")
    private Double cofinspcofins;
    @Column(name = "cofinsvaliqprod", updatable = false)
    private Double cofinsvaliqprod;
    @Size(max = 1)
    @Column(name = "tipocofinsstcalc", updatable = false)
    private String tipocofinsstcalc;
    @Column(name = "cofinspcofinsst")
    private Double cofinspcofinsst;
    @Column(name = "cofinsvaliqprodst", updatable = false)
    private Double cofinsvaliqprodst;
    @Column(name = "issvaliq", updatable = false)
    private Double issvaliq;
    @Size(max = 4)
    @Column(name = "clistserv", updatable = false)
    private String clistserv;
    @Size(max = 3)
    @Column(name = "extipi", updatable = false)
    private String extipi;
    @Size(max = 2)
    @Column(name = "icmsmotdesicms", updatable = false)
    private String icmsmotdesicms;
    @Column(name = "icmspcredsn", updatable = false)
    private Double icmspcredsn;
    @Size(max = 1)
    @Column(name = "csittrib", updatable = false)
    private String csittrib;
    @Column(name = "icmspbcop", updatable = false)
    private Double icmspbcop;
    @Size(max = 3)
    @Column(name = "icmscstint", updatable = false)
    private String icmscstint;
    @Size(max = 4)
    @Column(name = "cfop")
    private String cfop;
    @Size(max = 8)
    @Column(name = "ncm")
    private String ncm;
    @Size(max = 4)
    @Column(name = "cfopt", updatable = false)
    private String cfopt;
    @Column(name = "codinfnfe", updatable = false)
    private Integer codinfnfe;
    @Column(name = "dtenvserv", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtenvserv;
    @Size(max = 1)
    @Column(name = "mixfiscal")
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

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCfopt() {
        return cfopt;
    }

    public void setCfopt(String cfopt) {
        this.cfopt = cfopt;
    }

    public Integer getCodinfnfe() {
        return codinfnfe;
    }

    public void setCodinfnfe(Integer codinfnfe) {
        this.codinfnfe = codinfnfe;
    }

    public Date getDtenvserv() {
        return dtenvserv;
    }

    public void setDtenvserv(Date dtenvserv) {
        this.dtenvserv = dtenvserv;
    }

    public String getMixfiscal() {
        return mixfiscal;
    }

    public void setMixfiscal(String mixfiscal) {
        this.mixfiscal = mixfiscal;
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
        return "br.com.interagese.interfiscal.app.Tabproimp[ tabproimpPK=" + tabproimpPK + " ]";
    }

    @Override
    public Object getId() {
        return tabproimpPK;
    }

    @Override
    public void setId(Object object) {
        this.tabproimpPK = (TabproimpPK) object;
    }

}
