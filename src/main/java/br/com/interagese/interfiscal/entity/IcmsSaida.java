/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.math.BigInteger;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bruno
 */
@SqlResultSetMapping(name = "carregar-icmssaida", classes = @ConstructorResult(targetClass = IcmsSaida.class, columns = {
    @ColumnResult(name = "codigo")
    ,
    @ColumnResult(name = "codigo_produto")
    ,
    @ColumnResult(name = "ean")
    ,
    @ColumnResult(name = "re_29560")
    ,
    @ColumnResult(name = "cest")
    ,
    @ColumnResult(name = "sac_cst")
    ,
    @ColumnResult(name = "sac_alq")
    ,
    @ColumnResult(name = "sac_alqst")
    ,
    @ColumnResult(name = "sac_rbc")
    ,
    @ColumnResult(name = "sac_rbcst")
    ,
    @ColumnResult(name = "sas_cst")
    ,
    @ColumnResult(name = "sas_alq")
    ,
    @ColumnResult(name = "sas_alqst")
    ,
    @ColumnResult(name = "sas_rbc")
    ,
    @ColumnResult(name = "sas_rbcst")
    ,
    @ColumnResult(name = "svc_cst")
    ,
    @ColumnResult(name = "svc_alq")
    ,
    @ColumnResult(name = "svc_alqst")
    ,
    @ColumnResult(name = "svc_rbc")
    ,
    @ColumnResult(name = "svc_rbcst")
    ,
    @ColumnResult(name = "snc_cst")
    ,
    @ColumnResult(name = "snc_alq")
    ,
    @ColumnResult(name = "snc_alqst")
    ,
    @ColumnResult(name = "snc_rbc")
    ,
    @ColumnResult(name = "snc_rbcst")
    ,
    @ColumnResult(name = "sss_csosn")
    ,
    @ColumnResult(name = "svc_csosn")
    ,
    @ColumnResult(name = "snc_csosn")
    ,
    @ColumnResult(name = "fecp")
    ,
    @ColumnResult(name = "fundamento_legal")
    ,
    @ColumnResult(name = "descpro")
}))
@Entity
@Table(name = "mxf_tmp_icms_saida")
@NamedQueries({
    @NamedQuery(name = "IcmsSaida.findAll", query = "SELECT i FROM IcmsSaida i")})
public class IcmsSaida implements Model {

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
    @Column(name = "re_29560")
    private Integer re29560;
    @Size(max = 10)
    @Column(name = "cest")
    private String cest;
    @Size(max = 3)
    @Column(name = "sac_cst")
    private String sacCst;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sac_alq")
    private Double sacAlq;
    @Column(name = "sac_alqst")
    private Double sacAlqst;
    @Column(name = "sac_rbc")
    private Double sacRbc;
    @Column(name = "sac_rbcst")
    private Double sacRbcst;
    @Size(max = 3)
    @Column(name = "sas_cst")
    private String sasCst;
    @Column(name = "sas_alq")
    private Double sasAlq;
    @Column(name = "sas_alqst")
    private Double sasAlqst;
    @Column(name = "sas_rbc")
    private Double sasRbc;
    @Column(name = "sas_rbcst")
    private Double sasRbcst;
    @Size(max = 3)
    @Column(name = "svc_cst")
    private String svcCst;
    @Column(name = "svc_alq")
    private Double svcAlq;
    @Column(name = "svc_alqst")
    private Double svcAlqst;
    @Column(name = "svc_rbc")
    private Double svcRbc;
    @Column(name = "svc_rbcst")
    private Double svcRbcst;
    @Size(max = 3)
    @Column(name = "snc_cst")
    private String sncCst;
    @Column(name = "snc_alq")
    private Double sncAlq;
    @Column(name = "snc_alqst")
    private Double sncAlqst;
    @Column(name = "snc_rbc")
    private Double sncRbc;
    @Column(name = "snc_rbcst")
    private Double sncRbcst;
    @Size(max = 4)
    @Column(name = "sss_csosn")
    private String sssCsosn;
    @Size(max = 4)
    @Column(name = "svc_csosn")
    private String svcCsosn;
    @Size(max = 4)
    @Column(name = "snc_csosn")
    private String sncCsosn;
    @Column(name = "fecp")
    private Double fecp;
    @Size(max = 500)
    @Column(name = "fundamento_legal")
    private String fundamentoLegal;
    @Transient
    private String nmProduto;

    public IcmsSaida() {
    }

    public IcmsSaida(Integer codigo,
            BigInteger codigo_Produto,
            BigInteger ean,
            Integer re_29560,
            String cest,
            String sac_Cst,
            Double sac_Alq,
            Double sac_Alqst,
            Double sac_Rbc,
            Double sac_Rbcst,
            String sas_Cst,
            Double sas_Alq,
            Double sas_Alqst,
            Double sas_Rbc,
            Double sas_Rbcst,
            String svc_Cst,
            Double svc_Alq,
            Double svc_Alqst,
            Double svc_Rbc,
            Double svc_Rbcst,
            String snc_Cst,
            Double snc_Alq,
            Double snc_Alqst,
            Double snc_Rbc,
            Double snc_Rbcst,
            String sss_Csosn,
            String svc_Csosn,
            String snc_Csosn,
            Double fecp,
            String fundamento_Legal,
            String descpro) {
        this.codigo = codigo;
        this.codigoProduto = codigo_Produto;
        this.ean = ean;
        this.re29560 = re_29560;
        this.cest = cest;
        this.sacCst = sac_Cst;
        this.sacAlq = sac_Alq;
        this.sacAlqst = sac_Alqst;
        this.sacRbc = sac_Rbc;
        this.sacRbcst = sac_Rbcst;
        this.sasCst = sas_Cst;
        this.sasAlq = sas_Alq;
        this.sasAlqst = sas_Alqst;
        this.sasRbc = sas_Rbc;
        this.sasRbcst = sas_Rbcst;
        this.svcCst = svc_Cst;
        this.svcAlq = svc_Alq;
        this.svcAlqst = svc_Alqst;
        this.svcRbc = svc_Rbc;
        this.svcRbcst = svc_Rbcst;
        this.sncCst = snc_Cst;
        this.sncAlq = snc_Alq;
        this.sncAlqst = snc_Alqst;
        this.sncRbc = snc_Rbc;
        this.sncRbcst = snc_Rbcst;
        this.sssCsosn = sss_Csosn;
        this.svcCsosn = svc_Csosn;
        this.sncCsosn = snc_Csosn;
        this.fecp = fecp;
        this.fundamentoLegal = fundamento_Legal;
        this.nmProduto = descpro;
    }

    public IcmsSaida(Integer codigo) {
        this.codigo = codigo;
    }

    public IcmsSaida(Integer codigo, BigInteger codigoProduto) {
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

    public Integer getRe29560() {
        return re29560;
    }

    public void setRe29560(Integer re29560) {
        this.re29560 = re29560;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getSacCst() {
        return sacCst;
    }

    public void setSacCst(String sacCst) {
        this.sacCst = sacCst;
    }

    public Double getSacAlq() {
        return sacAlq;
    }

    public void setSacAlq(Double sacAlq) {
        this.sacAlq = sacAlq;
    }

    public Double getSacAlqst() {
        return sacAlqst;
    }

    public void setSacAlqst(Double sacAlqst) {
        this.sacAlqst = sacAlqst;
    }

    public Double getSacRbc() {
        return sacRbc;
    }

    public void setSacRbc(Double sacRbc) {
        this.sacRbc = sacRbc;
    }

    public Double getSacRbcst() {
        return sacRbcst;
    }

    public void setSacRbcst(Double sacRbcst) {
        this.sacRbcst = sacRbcst;
    }

    public String getSasCst() {
        return sasCst;
    }

    public void setSasCst(String sasCst) {
        this.sasCst = sasCst;
    }

    public Double getSasAlq() {
        return sasAlq;
    }

    public void setSasAlq(Double sasAlq) {
        this.sasAlq = sasAlq;
    }

    public Double getSasAlqst() {
        return sasAlqst;
    }

    public void setSasAlqst(Double sasAlqst) {
        this.sasAlqst = sasAlqst;
    }

    public Double getSasRbc() {
        return sasRbc;
    }

    public void setSasRbc(Double sasRbc) {
        this.sasRbc = sasRbc;
    }

    public Double getSasRbcst() {
        return sasRbcst;
    }

    public void setSasRbcst(Double sasRbcst) {
        this.sasRbcst = sasRbcst;
    }

    public String getSvcCst() {
        return svcCst;
    }

    public void setSvcCst(String svcCst) {
        this.svcCst = svcCst;
    }

    public Double getSvcAlq() {
        return svcAlq;
    }

    public void setSvcAlq(Double svcAlq) {
        this.svcAlq = svcAlq;
    }

    public Double getSvcAlqst() {
        return svcAlqst;
    }

    public void setSvcAlqst(Double svcAlqst) {
        this.svcAlqst = svcAlqst;
    }

    public Double getSvcRbc() {
        return svcRbc;
    }

    public void setSvcRbc(Double svcRbc) {
        this.svcRbc = svcRbc;
    }

    public Double getSvcRbcst() {
        return svcRbcst;
    }

    public void setSvcRbcst(Double svcRbcst) {
        this.svcRbcst = svcRbcst;
    }

    public String getSncCst() {
        return sncCst;
    }

    public void setSncCst(String sncCst) {
        this.sncCst = sncCst;
    }

    public Double getSncAlq() {
        return sncAlq;
    }

    public void setSncAlq(Double sncAlq) {
        this.sncAlq = sncAlq;
    }

    public Double getSncAlqst() {
        return sncAlqst;
    }

    public void setSncAlqst(Double sncAlqst) {
        this.sncAlqst = sncAlqst;
    }

    public Double getSncRbc() {
        return sncRbc;
    }

    public void setSncRbc(Double sncRbc) {
        this.sncRbc = sncRbc;
    }

    public Double getSncRbcst() {
        return sncRbcst;
    }

    public void setSncRbcst(Double sncRbcst) {
        this.sncRbcst = sncRbcst;
    }

    public String getSssCsosn() {
        return sssCsosn;
    }

    public void setSssCsosn(String sssCsosn) {
        this.sssCsosn = sssCsosn;
    }

    public String getSvcCsosn() {
        return svcCsosn;
    }

    public void setSvcCsosn(String svcCsosn) {
        this.svcCsosn = svcCsosn;
    }

    public String getSncCsosn() {
        return sncCsosn;
    }

    public void setSncCsosn(String sncCsosn) {
        this.sncCsosn = sncCsosn;
    }

    public Double getFecp() {
        return fecp;
    }

    public void setFecp(Double fecp) {
        this.fecp = fecp;
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
        if (!(object instanceof IcmsSaida)) {
            return false;
        }
        IcmsSaida other = (IcmsSaida) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.entity.IcmsSaida[ codigo=" + codigo + " ]";
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
