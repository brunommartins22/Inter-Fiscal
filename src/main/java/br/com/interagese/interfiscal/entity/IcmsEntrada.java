/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bruno
 */
@SqlResultSetMapping(name = "carregar-icmsentrada", classes = @ConstructorResult(targetClass = IcmsEntrada.class, columns = {
    @ColumnResult(name = "codigo")
    ,
    @ColumnResult(name = "codigo_produto")
    ,
    @ColumnResult(name = "ean")
    ,
    @ColumnResult(name = "tipo_mva")
    ,
    @ColumnResult(name = "mva")
    ,
    @ColumnResult(name = "mva_distribuidor")
    ,
    @ColumnResult(name = "mva_data_ini")
    ,
    @ColumnResult(name = "mva_data_fim")
    ,
    @ColumnResult(name = "credito_outorgado")
    ,
    @ColumnResult(name = "ei_cst")
    ,
    @ColumnResult(name = "ei_alq")
    ,
    @ColumnResult(name = "ei_alqst")
    ,
    @ColumnResult(name = "ei_rbc")
    ,
    @ColumnResult(name = "ei_rbcst")
    ,
    @ColumnResult(name = "ed_cst")
    ,
    @ColumnResult(name = "ed_alq")
    ,
    @ColumnResult(name = "ed_alqst")
    ,
    @ColumnResult(name = "ed_rbc")
    ,
    @ColumnResult(name = "ed_rbcst")
    ,
    @ColumnResult(name = "es_cst")
    ,
    @ColumnResult(name = "es_alq")
    ,
    @ColumnResult(name = "es_alqst")
    ,
    @ColumnResult(name = "es_rbc")
    ,
    @ColumnResult(name = "es_rbcst")
    ,
    @ColumnResult(name = "nfi_cst")
    ,
    @ColumnResult(name = "nfd_cst")
    ,
    @ColumnResult(name = "nfs_csosn")
    ,
    @ColumnResult(name = "nf_alq")
    ,
    @ColumnResult(name = "fundamento_legal")
    ,
    @ColumnResult(name = "descpro")
}))
@Entity
@Table(name = "mxf_tmp_icms_entrada")
@NamedQueries({
    @NamedQuery(name = "IcmsEntrada.findAll", query = "SELECT m FROM IcmsEntrada m")})
public class IcmsEntrada implements Model {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_produto")
    private BigInteger codigoProduto;
    @Column(name = "ean")
    private BigInteger ean;
    @Size(max = 2)
    @Column(name = "tipo_mva")
    private String tipoMva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Transient
    private String nmProduto;

    public IcmsEntrada() {
    }

    public IcmsEntrada(Integer codigo,
            BigInteger codigo_Produto,
            BigInteger ean,
            String tipo_Mva,
            Double mva,
            Double mva_Distribuidor,
            Date mva_Data_Ini,
            Date mva_Data_Fim,
            Integer credito_Outorgado,
            String ei_Cst,
            Double ei_Alq,
            Double ei_Alqst,
            Double ei_Rbc,
            Double ei_Rbcst,
            String ed_Cst,
            Double ed_Alq,
            Double ed_Alqst,
            Double ed_Rbc,
            Double ed_Rbcst,
            String es_Cst,
            Double es_Alq,
            Double es_Alqst,
            Double es_Rbc,
            Double es_Rbcst,
            String nfi_Cst,
            String nfd_Cst,
            String nfs_Csosn,
            Double nf_Alq,
            String fundamento_Legal,
            String descpro) {
        this.codigoProduto = codigo_Produto;
        this.ean = ean;
        this.tipoMva = tipo_Mva;
        this.mva = mva;
        this.mvaDistribuidor = mva_Distribuidor;
        this.mvaDataIni = mva_Data_Ini;
        this.mvaDataFim = mva_Data_Fim;
        this.creditoOutorgado = credito_Outorgado;
        this.eiCst = ei_Cst;
        this.eiAlq = ei_Alq;
        this.eiAlqst = ei_Alqst;
        this.eiRbc = ei_Rbc;
        this.eiRbcst = ei_Rbcst;
        this.edCst = ed_Cst;
        this.edAlq = ed_Alq;
        this.edAlqst = ed_Alqst;
        this.edRbc = ed_Rbc;
        this.edRbcst = ed_Rbcst;
        this.esCst = es_Cst;
        this.esAlq = es_Alq;
        this.esAlqst = es_Alqst;
        this.esRbc = es_Rbc;
        this.esRbcst = es_Rbcst;
        this.nfiCst = nfi_Cst;
        this.nfdCst = nfd_Cst;
        this.nfsCsosn = nfs_Csosn;
        this.nfAlq = nf_Alq;
        this.fundamentoLegal = fundamento_Legal;
        this.nmProduto = descpro;
    }

    public IcmsEntrada(Integer codigo) {
        this.codigo = codigo;
    }

    public IcmsEntrada(Integer codigo, BigInteger codigoProduto) {
        this.codigo = codigo;
        this.codigoProduto = codigoProduto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigInteger getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(BigInteger codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public BigInteger getEan() {
        return ean;
    }

    public void setEan(BigInteger ean) {
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IcmsEntrada)) {
            return false;
        }
        IcmsEntrada other = (IcmsEntrada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.entity.MxfTmpIcmsEntrada[ codigo=" + codigo + " ]";
    }

    @Override
    public Object getId() {
        return codigo;
    }

    @Override
    public void setId(Object object) {
        this.codigo = (Integer) object;
    }

    /**
     * @return the nmProduto
     */
    public String getNmProduto() {
        return nmProduto;
    }

    /**
     * @param nmProduto the nmProduto to set
     */
    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

}
