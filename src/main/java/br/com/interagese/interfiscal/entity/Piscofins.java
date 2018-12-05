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
@SqlResultSetMapping(name = "carregar-piscofins", classes = @ConstructorResult(targetClass = Piscofins.class, columns = {
    @ColumnResult(name = "codigo")
    ,
    @ColumnResult(name = "codigo_produto")
    ,
    @ColumnResult(name = "ean")
    ,
    @ColumnResult(name = "ncm")
    ,
    @ColumnResult(name = "ncm_ex")
    ,
    @ColumnResult(name = "cod_natureza_receita")
    ,
    @ColumnResult(name = "credito_presumido")
    ,
    @ColumnResult(name = "pis_cst_e")
    ,
    @ColumnResult(name = "pis_cst_s")
    ,
    @ColumnResult(name = "pis_alq_e")
    ,
    @ColumnResult(name = "pis_alq_s")
    ,
    @ColumnResult(name = "cofins_cst_e")
    ,
    @ColumnResult(name = "cofins_cst_s")
    ,
    @ColumnResult(name = "cofins_alq_e")
    ,
    @ColumnResult(name = "cofins_alq_s")
    ,
    @ColumnResult(name = "fundamento_legal")
    ,
    @ColumnResult(name = "descpro")
}))
@Entity
@Table(name = "mxf_tmp_pis_cofins")
@NamedQueries({
    @NamedQuery(name = "Piscofins.findAll", query = "SELECT p FROM Piscofins p")})
public class Piscofins implements Model {

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
    @Size(max = 10)
    @Column(name = "ncm")
    private String ncm;
    @Size(max = 3)
    @Column(name = "ncm_ex")
    private String ncmEx;
    @Size(max = 4)
    @Column(name = "cod_natureza_receita")
    private String codNaturezaReceita;
    @Column(name = "credito_presumido")
    private Integer creditoPresumido;
    @Size(max = 3)
    @Column(name = "pis_cst_e")
    private String pisCstE;
    @Size(max = 3)
    @Column(name = "pis_cst_s")
    private String pisCstS;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pis_alq_e")
    private Double pisAlqE;
    @Column(name = "pis_alq_s")
    private Double pisAlqS;
    @Size(max = 3)
    @Column(name = "cofins_cst_e")
    private String cofinsCstE;
    @Size(max = 3)
    @Column(name = "cofins_cst_s")
    private String cofinsCstS;
    @Column(name = "cofins_alq_e")
    private Double cofinsAlqE;
    @Column(name = "cofins_alq_s")
    private Double cofinsAlqS;
    @Size(max = 500)
    @Column(name = "fundamento_legal")
    private String fundamentoLegal;
    @Transient
    private String nmProduto;

    public Piscofins() {
    }

    public Piscofins(Integer codigo,
            BigInteger codigo_produto,
            BigInteger ean,
            String ncm,
            String ncm_ex,
            String cod_natureza_receita,
            Integer credito_presumido,
            String pis_cst_e,
            String pis_cst_s,
            Double pis_alq_e,
            Double pis_alq_s,
            String cofins_cst_e,
            String cofins_cst_s,
            Double cofins_alq_e,
            Double cofins_alq_s,
            String fundamento_legal,
            String descpro) {
        this.codigo = codigo;
        this.codigoProduto = codigo_produto;
        this.ean = ean;
        this.ncm = ncm;
        this.ncmEx = ncm_ex;
        this.codNaturezaReceita = cod_natureza_receita;
        this.creditoPresumido = credito_presumido;
        this.pisCstE = pis_cst_e;
        this.pisCstS = pis_cst_s;
        this.pisAlqE = pis_alq_e;
        this.pisAlqS = pis_alq_s;
        this.cofinsCstE = cofins_cst_e;
        this.cofinsCstS = cofins_cst_s;
        this.cofinsAlqE = cofins_alq_e;
        this.cofinsAlqS = cofins_alq_s;
        this.fundamentoLegal = fundamento_legal;
        this.nmProduto = descpro;
    }

    public Piscofins(Integer codigo) {
        this.codigo = codigo;
    }

    public Piscofins(Integer codigo, BigInteger codigoProduto) {
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

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getNcmEx() {
        return ncmEx;
    }

    public void setNcmEx(String ncmEx) {
        this.ncmEx = ncmEx;
    }

    public String getCodNaturezaReceita() {
        return codNaturezaReceita;
    }

    public void setCodNaturezaReceita(String codNaturezaReceita) {
        this.codNaturezaReceita = codNaturezaReceita;
    }

    public Integer getCreditoPresumido() {
        return creditoPresumido;
    }

    public void setCreditoPresumido(Integer creditoPresumido) {
        this.creditoPresumido = creditoPresumido;
    }

    public String getPisCstE() {
        return pisCstE;
    }

    public void setPisCstE(String pisCstE) {
        this.pisCstE = pisCstE;
    }

    public String getPisCstS() {
        return pisCstS;
    }

    public void setPisCstS(String pisCstS) {
        this.pisCstS = pisCstS;
    }

    public Double getPisAlqE() {
        return pisAlqE;
    }

    public void setPisAlqE(Double pisAlqE) {
        this.pisAlqE = pisAlqE;
    }

    public Double getPisAlqS() {
        return pisAlqS;
    }

    public void setPisAlqS(Double pisAlqS) {
        this.pisAlqS = pisAlqS;
    }

    public String getCofinsCstE() {
        return cofinsCstE;
    }

    public void setCofinsCstE(String cofinsCstE) {
        this.cofinsCstE = cofinsCstE;
    }

    public String getCofinsCstS() {
        return cofinsCstS;
    }

    public void setCofinsCstS(String cofinsCstS) {
        this.cofinsCstS = cofinsCstS;
    }

    public Double getCofinsAlqE() {
        return cofinsAlqE;
    }

    public void setCofinsAlqE(Double cofinsAlqE) {
        this.cofinsAlqE = cofinsAlqE;
    }

    public Double getCofinsAlqS() {
        return cofinsAlqS;
    }

    public void setCofinsAlqS(Double cofinsAlqS) {
        this.cofinsAlqS = cofinsAlqS;
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
        if (!(object instanceof Piscofins)) {
            return false;
        }
        Piscofins other = (Piscofins) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.entity.MxfTmpPisCofins[ codigo=" + codigo + " ]";
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
