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
@Table(name = "TABPROIMPE")
@NamedQueries({
    @NamedQuery(name = "Tabproimpe.findAll", query = "SELECT t FROM Tabproimpe t")})
public class Tabproimpe implements Model {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TabproimpePK tabproimpePK;
    @Size(max = 20)
    @Column(name = "EAN")
    private String ean;
    @Size(max = 2)
    @Column(name = "TIPO_MVA")
    private String tipoMva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MVA")
    private Double mva;
    @Column(name = "MVA_DISTRIBUIDOR")
    private Double mvaDistribuidor;
    @Column(name = "MVA_DATA_INI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvaDataIni;
    @Column(name = "MVA_DATA_FIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvaDataFim;
    @Column(name = "CREDITO_OUTORGADO")
    private Integer creditoOutorgado;
    @Size(max = 3)
    @Column(name = "EI_CST")
    private String eiCst;
    @Column(name = "EI_ALQ")
    private Double eiAlq;
    @Column(name = "EI_ALQST")
    private Double eiAlqst;
    @Column(name = "EI_RBC")
    private Double eiRbc;
    @Column(name = "EI_RBCST")
    private Double eiRbcst;
    @Size(max = 3)
    @Column(name = "ED_CST")
    private String edCst;
    @Column(name = "ED_ALQ")
    private Double edAlq;
    @Column(name = "ED_ALQST")
    private Double edAlqst;
    @Column(name = "ED_RBC")
    private Double edRbc;
    @Column(name = "ED_RBCST")
    private Double edRbcst;
    @Size(max = 3)
    @Column(name = "ES_CST")
    private String esCst;
    @Column(name = "ES_ALQ")
    private Double esAlq;
    @Column(name = "ES_ALQST")
    private Double esAlqst;
    @Column(name = "ES_RBC")
    private Double esRbc;
    @Column(name = "ES_RBCST")
    private Double esRbcst;
    @Size(max = 3)
    @Column(name = "NFI_CST")
    private String nfiCst;
    @Size(max = 3)
    @Column(name = "NFD_CST")
    private String nfdCst;
    @Size(max = 4)
    @Column(name = "NFS_CSOSN")
    private String nfsCsosn;
    @Column(name = "NF_ALQ")
    private Double nfAlq;
    @Size(max = 500)
    @Column(name = "FUNDAMENTO_LEGAL")
    private String fundamentoLegal;
  
    @Size(max = 1)
    @Column(name = "MIXFISCAL")
    private String mixfiscal;

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
        return "br.com.interagese.transmissornfe.business.entity.Tabproimpe[ tabproimpePK=" + tabproimpePK + " ]";
    }

    @Override
    public Object getId() {
        return tabproimpePK;
    }

    @Override
    public void setId(Object object) {
        this.tabproimpePK = (TabproimpePK) object;
    }

    public String getMixfiscal() {
        return mixfiscal;
    }

    public void setMixfiscal(String mixfiscal) {
        this.mixfiscal = mixfiscal;
    }

}
