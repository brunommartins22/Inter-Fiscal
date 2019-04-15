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
@Table(name = "tabproimpe")
@NamedQueries({
    @NamedQuery(name = "Tabproimpe.findAll", query = "SELECT t FROM Tabproimpe t")})
public class Tabproimpe implements Model {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TabproimpePK tabproimpePK;
    @Size(max = 20)
    @Column(name = "ean", updatable = false)
    private String ean;
    @Size(max = 2)
    @Column(name = "tipo_mva")
    private String tipoMva;
    @Column(name = "mva")
    private Double mva;
    @Column(name = "mva_distribuidor")
    private Double mvaDistribuidor;
    @Column(name = "mva_data_ini")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvaDataIni;
    @Column(name = "mva_data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvaDataFim;
    @Column(name = "credito_outorgado")
    private Integer creditoOutorgado;
    @Size(max = 3)
    @Column(name = "ei_cst")
    private String eiCst;
    @Column(name = "ei_alq")
    private Double eiAlq;
    @Column(name = "ei_alqst")
    private Double eiAlqst;
    @Column(name = "ei_rbc")
    private Double eiRbc;
    @Column(name = "ei_rbcst")
    private Double eiRbcst;
    @Size(max = 3)
    @Column(name = "ed_cst")
    private String edCst;
    @Column(name = "ed_alq")
    private Double edAlq;
    @Column(name = "ed_alqst")
    private Double edAlqst;
    @Column(name = "ed_rbc")
    private Double edRbc;
    @Column(name = "ed_rbcst")
    private Double edRbcst;
    @Size(max = 3)
    @Column(name = "es_cst")
    private String esCst;
    @Column(name = "es_alq")
    private Double esAlq;
    @Column(name = "es_alqst")
    private Double esAlqst;
    @Column(name = "es_rbc")
    private Double esRbc;
    @Column(name = "es_rbcst")
    private Double esRbcst;
    @Size(max = 3)
    @Column(name = "nfi_cst")
    private String nfiCst;
    @Size(max = 3)
    @Column(name = "nfd_cst")
    private String nfdCst;
    @Size(max = 4)
    @Column(name = "nfs_csosn")
    private String nfsCsosn;
    @Column(name = "nf_alq")
    private Double nfAlq;
    @Size(max = 500)
    @Column(name = "fundamento_legal")
    private String fundamentoLegal;
    @Size(max = 1)
    @Column(name = "mixfiscal")
    private String mixfiscal;
    @Column(name = "dtenvserv", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtenvserv;

    public Tabproimpe() {
    }

    public Tabproimpe(TabproimpePK tabproimpePK) {
        this.tabproimpePK = tabproimpePK;
    }

    public Tabproimpe(String codigoProduto, int codigoFilial) {
        this.tabproimpePK = new TabproimpePK(codigoProduto, codigoFilial);
    }

    public TabproimpePK getTabproimpePK() {
        return tabproimpePK;
    }

    public void setTabproimpePK(TabproimpePK tabproimpePK) {
        this.tabproimpePK = tabproimpePK;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getTipoMva() {
        return tipoMva;
    }

    public void setTipoMva(String tipoMva) {
        this.tipoMva = tipoMva;
    }

    public Double getMva() {
        return mva;
    }

    public void setMva(Double mva) {
        this.mva = mva;
    }

    public Double getMvaDistribuidor() {
        return mvaDistribuidor;
    }

    public void setMvaDistribuidor(Double mvaDistribuidor) {
        this.mvaDistribuidor = mvaDistribuidor;
    }

    public Date getMvaDataIni() {
        return mvaDataIni;
    }

    public void setMvaDataIni(Date mvaDataIni) {
        this.mvaDataIni = mvaDataIni;
    }

    public Date getMvaDataFim() {
        return mvaDataFim;
    }

    public void setMvaDataFim(Date mvaDataFim) {
        this.mvaDataFim = mvaDataFim;
    }

    public Integer getCreditoOutorgado() {
        return creditoOutorgado;
    }

    public void setCreditoOutorgado(Integer creditoOutorgado) {
        this.creditoOutorgado = creditoOutorgado;
    }

    public String getEiCst() {
        return eiCst;
    }

    public void setEiCst(String eiCst) {
        this.eiCst = eiCst;
    }

    public Double getEiAlq() {
        return eiAlq;
    }

    public void setEiAlq(Double eiAlq) {
        this.eiAlq = eiAlq;
    }

    public Double getEiAlqst() {
        return eiAlqst;
    }

    public void setEiAlqst(Double eiAlqst) {
        this.eiAlqst = eiAlqst;
    }

    public Double getEiRbc() {
        return eiRbc;
    }

    public void setEiRbc(Double eiRbc) {
        this.eiRbc = eiRbc;
    }

    public Double getEiRbcst() {
        return eiRbcst;
    }

    public void setEiRbcst(Double eiRbcst) {
        this.eiRbcst = eiRbcst;
    }

    public String getEdCst() {
        return edCst;
    }

    public void setEdCst(String edCst) {
        this.edCst = edCst;
    }

    public Double getEdAlq() {
        return edAlq;
    }

    public void setEdAlq(Double edAlq) {
        this.edAlq = edAlq;
    }

    public Double getEdAlqst() {
        return edAlqst;
    }

    public void setEdAlqst(Double edAlqst) {
        this.edAlqst = edAlqst;
    }

    public Double getEdRbc() {
        return edRbc;
    }

    public void setEdRbc(Double edRbc) {
        this.edRbc = edRbc;
    }

    public Double getEdRbcst() {
        return edRbcst;
    }

    public void setEdRbcst(Double edRbcst) {
        this.edRbcst = edRbcst;
    }

    public String getEsCst() {
        return esCst;
    }

    public void setEsCst(String esCst) {
        this.esCst = esCst;
    }

    public Double getEsAlq() {
        return esAlq;
    }

    public void setEsAlq(Double esAlq) {
        this.esAlq = esAlq;
    }

    public Double getEsAlqst() {
        return esAlqst;
    }

    public void setEsAlqst(Double esAlqst) {
        this.esAlqst = esAlqst;
    }

    public Double getEsRbc() {
        return esRbc;
    }

    public void setEsRbc(Double esRbc) {
        this.esRbc = esRbc;
    }

    public Double getEsRbcst() {
        return esRbcst;
    }

    public void setEsRbcst(Double esRbcst) {
        this.esRbcst = esRbcst;
    }

    public String getNfiCst() {
        return nfiCst;
    }

    public void setNfiCst(String nfiCst) {
        this.nfiCst = nfiCst;
    }

    public String getNfdCst() {
        return nfdCst;
    }

    public void setNfdCst(String nfdCst) {
        this.nfdCst = nfdCst;
    }

    public String getNfsCsosn() {
        return nfsCsosn;
    }

    public void setNfsCsosn(String nfsCsosn) {
        this.nfsCsosn = nfsCsosn;
    }

    public Double getNfAlq() {
        return nfAlq;
    }

    public void setNfAlq(Double nfAlq) {
        this.nfAlq = nfAlq;
    }

    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

    public String getMixfiscal() {
        return mixfiscal;
    }

    public void setMixfiscal(String mixfiscal) {
        this.mixfiscal = mixfiscal;
    }

    public Date getDtenvserv() {
        return dtenvserv;
    }

    public void setDtenvserv(Date dtenvserv) {
        this.dtenvserv = dtenvserv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tabproimpePK != null ? tabproimpePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabproimpe)) {
            return false;
        }
        Tabproimpe other = (Tabproimpe) object;
        if ((this.tabproimpePK == null && other.tabproimpePK != null) || (this.tabproimpePK != null && !this.tabproimpePK.equals(other.tabproimpePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.app.Tabproimpe[ tabproimpePK=" + tabproimpePK + " ]";
    }

    @Override
    public Object getId() {
        return tabproimpePK;
    }

    @Override
    public void setId(Object object) {
        this.tabproimpePK = (TabproimpePK) object;
    }

}
