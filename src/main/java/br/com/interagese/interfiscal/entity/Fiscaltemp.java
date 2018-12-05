/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "fiscaltemp")
@NamedQueries({
    @NamedQuery(name = "Fiscaltemp.findAll", query = "SELECT f FROM Fiscaltemp f")})
public class Fiscaltemp implements Model {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", columnDefinition = "serial")
    private Integer codigo;
    @Size(max = 200)
    @Column(name = "nome_produto")
    private String nomeProduto;
    @Size(max = 6)
    @Column(name = "codigo_produto")
    private String codigoProduto;
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
    @Column(name = "codigo_filial")
    private Integer codigoFilial;
    @Size(max = 20)
    @Column(name = "ean")
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
    @Column(name = "re_29560")
    private Integer re29560;
    @Size(max = 10)
    @Column(name = "cest")
    private String cest;
    @Size(max = 3)
    @Column(name = "sac_cst")
    private String sacCst;
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
    @Column(name = "pis_cofins")
    private Boolean pisCofins;
    @Column(name = "icms_entrada")
    private Boolean icmsEntrada;
    @Column(name = "icms_saida")
    private Boolean icmsSaida;
    @Column(name = "atualizacao_piscofins")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoPiscofins;
    @Column(name = "atualizacao_icmsentrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoIcmsentrada;
    @Column(name = "atualizacao_icmssaida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoIcmssaida;
    @Size(max = 10)
    @Column(name = "ncm_before")
    private String ncmBefore;
    @Size(max = 3)
    @Column(name = "ncm_ex_before")
    private String ncmExBefore;
    @Size(max = 4)
    @Column(name = "cod_natureza_receita_before")
    private String codNaturezaReceitaBefore;
    @Column(name = "credito_presumido_before")
    private Integer creditoPresumidoBefore;
    @Size(max = 3)
    @Column(name = "pis_cst_e_before")
    private String pisCstEBefore;
    @Size(max = 3)
    @Column(name = "pis_cst_s_before")
    private String pisCstSBefore;
    @Column(name = "pis_alq_e_before")
    private Double pisAlqEBefore;
    @Column(name = "pis_alq_s_before")
    private Double pisAlqSBefore;
    @Size(max = 3)
    @Column(name = "cofins_cst_e_before")
    private String cofinsCstEBefore;
    @Size(max = 3)
    @Column(name = "cofins_cst_s_before")
    private String cofinsCstSBefore;
    @Column(name = "cofins_alq_e_before")
    private Double cofinsAlqEBefore;
    @Column(name = "cofins_alq_s_before")
    private Double cofinsAlqSBefore;
    @Size(max = 2)
    @Column(name = "tipo_mva_before")
    private String tipoMvaBefore;
    @Column(name = "mva_before")
    private Double mvaBefore;
    @Column(name = "mva_distribuidor_before")
    private Double mvaDistribuidorBefore;
    @Column(name = "mva_data_ini_before")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvaDataIniBefore;
    @Column(name = "mva_data_fim_before")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvaDataFimBefore;
    @Column(name = "credito_outorgado_before")
    private Integer creditoOutorgadoBefore;
    @Size(max = 3)
    @Column(name = "ei_cst_before")
    private String eiCstBefore;
    @Column(name = "ei_alq_before")
    private Double eiAlqBefore;
    @Column(name = "ei_alqst_before")
    private Double eiAlqstBefore;
    @Column(name = "ei_rbc_before")
    private Double eiRbcBefore;
    @Column(name = "ei_rbcst_before")
    private Double eiRbcstBefore;
    @Size(max = 3)
    @Column(name = "ed_cst_before")
    private String edCstBefore;
    @Column(name = "ed_alq_before")
    private Double edAlqBefore;
    @Column(name = "ed_alqst_before")
    private Double edAlqstBefore;
    @Column(name = "ed_rbc_before")
    private Double edRbcBefore;
    @Column(name = "ed_rbcst_before")
    private Double edRbcstBefore;
    @Size(max = 3)
    @Column(name = "es_cst_before")
    private String esCstBefore;
    @Column(name = "es_alq_before")
    private Double esAlqBefore;
    @Column(name = "es_alqst_before")
    private Double esAlqstBefore;
    @Column(name = "es_rbc_before")
    private Double esRbcBefore;
    @Column(name = "es_rbcst_before")
    private Double esRbcstBefore;
    @Size(max = 3)
    @Column(name = "nfi_cst_before")
    private String nfiCstBefore;
    @Size(max = 3)
    @Column(name = "nfd_cst_before")
    private String nfdCstBefore;
    @Size(max = 4)
    @Column(name = "nfs_csosn_before")
    private String nfsCsosnBefore;
    @Column(name = "nf_alq_before")
    private Double nfAlqBefore;
    @Column(name = "re_29560_before")
    private Integer re29560Before;
    @Size(max = 10)
    @Column(name = "cest_before")
    private String cestBefore;
    @Size(max = 3)
    @Column(name = "sac_cst_before")
    private String sacCstBefore;
    @Column(name = "sac_alq_before")
    private Double sacAlqBefore;
    @Column(name = "sac_alqst_before")
    private Double sacAlqstBefore;
    @Column(name = "sac_rbc_before")
    private Double sacRbcBefore;
    @Column(name = "sac_rbcst_before")
    private Double sacRbcstBefore;
    @Size(max = 3)
    @Column(name = "sas_cst_before")
    private String sasCstBefore;
    @Column(name = "sas_alq_before")
    private Double sasAlqBefore;
    @Column(name = "sas_alqst_before")
    private Double sasAlqstBefore;
    @Column(name = "sas_rbc_before")
    private Double sasRbcBefore;
    @Column(name = "sas_rbcst_before")
    private Double sasRbcstBefore;
    @Size(max = 3)
    @Column(name = "svc_cst_before")
    private String svcCstBefore;
    @Column(name = "svc_alq_before")
    private Double svcAlqBefore;
    @Column(name = "svc_alqst_before")
    private Double svcAlqstBefore;
    @Column(name = "svc_rbc_before")
    private Double svcRbcBefore;
    @Column(name = "svc_rbcst_before")
    private Double svcRbcstBefore;
    @Size(max = 3)
    @Column(name = "snc_cst_before")
    private String sncCstBefore;
    @Column(name = "snc_alq_before")
    private Double sncAlqBefore;
    @Column(name = "snc_alqst_before")
    private Double sncAlqstBefore;
    @Column(name = "snc_rbc_before")
    private Double sncRbcBefore;
    @Column(name = "snc_rbcst_before")
    private Double sncRbcstBefore;
    @Size(max = 4)
    @Column(name = "sss_csosn_before")
    private String sssCsosnBefore;
    @Size(max = 4)
    @Column(name = "svc_csosn_before")
    private String svcCsosnBefore;
    @Size(max = 4)
    @Column(name = "snc_csosn_before")
    private String sncCsosnBefore;
    @Column(name = "fecp_before")
    private Double fecpBefore;

    public Fiscaltemp() {
    }

    public Fiscaltemp(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
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

    public Integer getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(Integer codigoFilial) {
        this.codigoFilial = codigoFilial;
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

    public Boolean getPisCofins() {
        return pisCofins;
    }

    public void setPisCofins(Boolean pisCofins) {
        this.pisCofins = pisCofins;
    }

    public Boolean getIcmsEntrada() {
        return icmsEntrada;
    }

    public void setIcmsEntrada(Boolean icmsEntrada) {
        this.icmsEntrada = icmsEntrada;
    }

    public Boolean getIcmsSaida() {
        return icmsSaida;
    }

    public void setIcmsSaida(Boolean icmsSaida) {
        this.icmsSaida = icmsSaida;
    }

    public Date getAtualizacaoPiscofins() {
        return atualizacaoPiscofins;
    }

    public void setAtualizacaoPiscofins(Date atualizacaoPiscofins) {
        this.atualizacaoPiscofins = atualizacaoPiscofins;
    }

    public Date getAtualizacaoIcmsentrada() {
        return atualizacaoIcmsentrada;
    }

    public void setAtualizacaoIcmsentrada(Date atualizacaoIcmsentrada) {
        this.atualizacaoIcmsentrada = atualizacaoIcmsentrada;
    }

    public Date getAtualizacaoIcmssaida() {
        return atualizacaoIcmssaida;
    }

    public void setAtualizacaoIcmssaida(Date atualizacaoIcmssaida) {
        this.atualizacaoIcmssaida = atualizacaoIcmssaida;
    }

    public String getNcmBefore() {
        return ncmBefore;
    }

    public void setNcmBefore(String ncmBefore) {
        this.ncmBefore = ncmBefore;
    }

    public String getNcmExBefore() {
        return ncmExBefore;
    }

    public void setNcmExBefore(String ncmExBefore) {
        this.ncmExBefore = ncmExBefore;
    }

    public String getCodNaturezaReceitaBefore() {
        return codNaturezaReceitaBefore;
    }

    public void setCodNaturezaReceitaBefore(String codNaturezaReceitaBefore) {
        this.codNaturezaReceitaBefore = codNaturezaReceitaBefore;
    }

    public Integer getCreditoPresumidoBefore() {
        return creditoPresumidoBefore;
    }

    public void setCreditoPresumidoBefore(Integer creditoPresumidoBefore) {
        this.creditoPresumidoBefore = creditoPresumidoBefore;
    }

    public String getPisCstEBefore() {
        return pisCstEBefore;
    }

    public void setPisCstEBefore(String pisCstEBefore) {
        this.pisCstEBefore = pisCstEBefore;
    }

    public String getPisCstSBefore() {
        return pisCstSBefore;
    }

    public void setPisCstSBefore(String pisCstSBefore) {
        this.pisCstSBefore = pisCstSBefore;
    }

    public Double getPisAlqEBefore() {
        return pisAlqEBefore;
    }

    public void setPisAlqEBefore(Double pisAlqEBefore) {
        this.pisAlqEBefore = pisAlqEBefore;
    }

    public Double getPisAlqSBefore() {
        return pisAlqSBefore;
    }

    public void setPisAlqSBefore(Double pisAlqSBefore) {
        this.pisAlqSBefore = pisAlqSBefore;
    }

    public String getCofinsCstEBefore() {
        return cofinsCstEBefore;
    }

    public void setCofinsCstEBefore(String cofinsCstEBefore) {
        this.cofinsCstEBefore = cofinsCstEBefore;
    }

    public String getCofinsCstSBefore() {
        return cofinsCstSBefore;
    }

    public void setCofinsCstSBefore(String cofinsCstSBefore) {
        this.cofinsCstSBefore = cofinsCstSBefore;
    }

    public Double getCofinsAlqEBefore() {
        return cofinsAlqEBefore;
    }

    public void setCofinsAlqEBefore(Double cofinsAlqEBefore) {
        this.cofinsAlqEBefore = cofinsAlqEBefore;
    }

    public Double getCofinsAlqSBefore() {
        return cofinsAlqSBefore;
    }

    public void setCofinsAlqSBefore(Double cofinsAlqSBefore) {
        this.cofinsAlqSBefore = cofinsAlqSBefore;
    }

    public String getTipoMvaBefore() {
        return tipoMvaBefore;
    }

    public void setTipoMvaBefore(String tipoMvaBefore) {
        this.tipoMvaBefore = tipoMvaBefore;
    }

    public Double getMvaBefore() {
        return mvaBefore;
    }

    public void setMvaBefore(Double mvaBefore) {
        this.mvaBefore = mvaBefore;
    }

    public Double getMvaDistribuidorBefore() {
        return mvaDistribuidorBefore;
    }

    public void setMvaDistribuidorBefore(Double mvaDistribuidorBefore) {
        this.mvaDistribuidorBefore = mvaDistribuidorBefore;
    }

    public Date getMvaDataIniBefore() {
        return mvaDataIniBefore;
    }

    public void setMvaDataIniBefore(Date mvaDataIniBefore) {
        this.mvaDataIniBefore = mvaDataIniBefore;
    }

    public Date getMvaDataFimBefore() {
        return mvaDataFimBefore;
    }

    public void setMvaDataFimBefore(Date mvaDataFimBefore) {
        this.mvaDataFimBefore = mvaDataFimBefore;
    }

    public Integer getCreditoOutorgadoBefore() {
        return creditoOutorgadoBefore;
    }

    public void setCreditoOutorgadoBefore(Integer creditoOutorgadoBefore) {
        this.creditoOutorgadoBefore = creditoOutorgadoBefore;
    }

    public String getEiCstBefore() {
        return eiCstBefore;
    }

    public void setEiCstBefore(String eiCstBefore) {
        this.eiCstBefore = eiCstBefore;
    }

    public Double getEiAlqBefore() {
        return eiAlqBefore;
    }

    public void setEiAlqBefore(Double eiAlqBefore) {
        this.eiAlqBefore = eiAlqBefore;
    }

    public Double getEiAlqstBefore() {
        return eiAlqstBefore;
    }

    public void setEiAlqstBefore(Double eiAlqstBefore) {
        this.eiAlqstBefore = eiAlqstBefore;
    }

    public Double getEiRbcBefore() {
        return eiRbcBefore;
    }

    public void setEiRbcBefore(Double eiRbcBefore) {
        this.eiRbcBefore = eiRbcBefore;
    }

    public Double getEiRbcstBefore() {
        return eiRbcstBefore;
    }

    public void setEiRbcstBefore(Double eiRbcstBefore) {
        this.eiRbcstBefore = eiRbcstBefore;
    }

    public String getEdCstBefore() {
        return edCstBefore;
    }

    public void setEdCstBefore(String edCstBefore) {
        this.edCstBefore = edCstBefore;
    }

    public Double getEdAlqBefore() {
        return edAlqBefore;
    }

    public void setEdAlqBefore(Double edAlqBefore) {
        this.edAlqBefore = edAlqBefore;
    }

    public Double getEdAlqstBefore() {
        return edAlqstBefore;
    }

    public void setEdAlqstBefore(Double edAlqstBefore) {
        this.edAlqstBefore = edAlqstBefore;
    }

    public Double getEdRbcBefore() {
        return edRbcBefore;
    }

    public void setEdRbcBefore(Double edRbcBefore) {
        this.edRbcBefore = edRbcBefore;
    }

    public Double getEdRbcstBefore() {
        return edRbcstBefore;
    }

    public void setEdRbcstBefore(Double edRbcstBefore) {
        this.edRbcstBefore = edRbcstBefore;
    }

    public String getEsCstBefore() {
        return esCstBefore;
    }

    public void setEsCstBefore(String esCstBefore) {
        this.esCstBefore = esCstBefore;
    }

    public Double getEsAlqBefore() {
        return esAlqBefore;
    }

    public void setEsAlqBefore(Double esAlqBefore) {
        this.esAlqBefore = esAlqBefore;
    }

    public Double getEsAlqstBefore() {
        return esAlqstBefore;
    }

    public void setEsAlqstBefore(Double esAlqstBefore) {
        this.esAlqstBefore = esAlqstBefore;
    }

    public Double getEsRbcBefore() {
        return esRbcBefore;
    }

    public void setEsRbcBefore(Double esRbcBefore) {
        this.esRbcBefore = esRbcBefore;
    }

    public Double getEsRbcstBefore() {
        return esRbcstBefore;
    }

    public void setEsRbcstBefore(Double esRbcstBefore) {
        this.esRbcstBefore = esRbcstBefore;
    }

    public String getNfiCstBefore() {
        return nfiCstBefore;
    }

    public void setNfiCstBefore(String nfiCstBefore) {
        this.nfiCstBefore = nfiCstBefore;
    }

    public String getNfdCstBefore() {
        return nfdCstBefore;
    }

    public void setNfdCstBefore(String nfdCstBefore) {
        this.nfdCstBefore = nfdCstBefore;
    }

    public String getNfsCsosnBefore() {
        return nfsCsosnBefore;
    }

    public void setNfsCsosnBefore(String nfsCsosnBefore) {
        this.nfsCsosnBefore = nfsCsosnBefore;
    }

    public Double getNfAlqBefore() {
        return nfAlqBefore;
    }

    public void setNfAlqBefore(Double nfAlqBefore) {
        this.nfAlqBefore = nfAlqBefore;
    }

    public Integer getRe29560Before() {
        return re29560Before;
    }

    public void setRe29560Before(Integer re29560Before) {
        this.re29560Before = re29560Before;
    }

    public String getCestBefore() {
        return cestBefore;
    }

    public void setCestBefore(String cestBefore) {
        this.cestBefore = cestBefore;
    }

    public String getSacCstBefore() {
        return sacCstBefore;
    }

    public void setSacCstBefore(String sacCstBefore) {
        this.sacCstBefore = sacCstBefore;
    }

    public Double getSacAlqBefore() {
        return sacAlqBefore;
    }

    public void setSacAlqBefore(Double sacAlqBefore) {
        this.sacAlqBefore = sacAlqBefore;
    }

    public Double getSacAlqstBefore() {
        return sacAlqstBefore;
    }

    public void setSacAlqstBefore(Double sacAlqstBefore) {
        this.sacAlqstBefore = sacAlqstBefore;
    }

    public Double getSacRbcBefore() {
        return sacRbcBefore;
    }

    public void setSacRbcBefore(Double sacRbcBefore) {
        this.sacRbcBefore = sacRbcBefore;
    }

    public Double getSacRbcstBefore() {
        return sacRbcstBefore;
    }

    public void setSacRbcstBefore(Double sacRbcstBefore) {
        this.sacRbcstBefore = sacRbcstBefore;
    }

    public String getSasCstBefore() {
        return sasCstBefore;
    }

    public void setSasCstBefore(String sasCstBefore) {
        this.sasCstBefore = sasCstBefore;
    }

    public Double getSasAlqBefore() {
        return sasAlqBefore;
    }

    public void setSasAlqBefore(Double sasAlqBefore) {
        this.sasAlqBefore = sasAlqBefore;
    }

    public Double getSasAlqstBefore() {
        return sasAlqstBefore;
    }

    public void setSasAlqstBefore(Double sasAlqstBefore) {
        this.sasAlqstBefore = sasAlqstBefore;
    }

    public Double getSasRbcBefore() {
        return sasRbcBefore;
    }

    public void setSasRbcBefore(Double sasRbcBefore) {
        this.sasRbcBefore = sasRbcBefore;
    }

    public Double getSasRbcstBefore() {
        return sasRbcstBefore;
    }

    public void setSasRbcstBefore(Double sasRbcstBefore) {
        this.sasRbcstBefore = sasRbcstBefore;
    }

    public String getSvcCstBefore() {
        return svcCstBefore;
    }

    public void setSvcCstBefore(String svcCstBefore) {
        this.svcCstBefore = svcCstBefore;
    }

    public Double getSvcAlqBefore() {
        return svcAlqBefore;
    }

    public void setSvcAlqBefore(Double svcAlqBefore) {
        this.svcAlqBefore = svcAlqBefore;
    }

    public Double getSvcAlqstBefore() {
        return svcAlqstBefore;
    }

    public void setSvcAlqstBefore(Double svcAlqstBefore) {
        this.svcAlqstBefore = svcAlqstBefore;
    }

    public Double getSvcRbcBefore() {
        return svcRbcBefore;
    }

    public void setSvcRbcBefore(Double svcRbcBefore) {
        this.svcRbcBefore = svcRbcBefore;
    }

    public Double getSvcRbcstBefore() {
        return svcRbcstBefore;
    }

    public void setSvcRbcstBefore(Double svcRbcstBefore) {
        this.svcRbcstBefore = svcRbcstBefore;
    }

    public String getSncCstBefore() {
        return sncCstBefore;
    }

    public void setSncCstBefore(String sncCstBefore) {
        this.sncCstBefore = sncCstBefore;
    }

    public Double getSncAlqBefore() {
        return sncAlqBefore;
    }

    public void setSncAlqBefore(Double sncAlqBefore) {
        this.sncAlqBefore = sncAlqBefore;
    }

    public Double getSncAlqstBefore() {
        return sncAlqstBefore;
    }

    public void setSncAlqstBefore(Double sncAlqstBefore) {
        this.sncAlqstBefore = sncAlqstBefore;
    }

    public Double getSncRbcBefore() {
        return sncRbcBefore;
    }

    public void setSncRbcBefore(Double sncRbcBefore) {
        this.sncRbcBefore = sncRbcBefore;
    }

    public Double getSncRbcstBefore() {
        return sncRbcstBefore;
    }

    public void setSncRbcstBefore(Double sncRbcstBefore) {
        this.sncRbcstBefore = sncRbcstBefore;
    }

    public String getSssCsosnBefore() {
        return sssCsosnBefore;
    }

    public void setSssCsosnBefore(String sssCsosnBefore) {
        this.sssCsosnBefore = sssCsosnBefore;
    }

    public String getSvcCsosnBefore() {
        return svcCsosnBefore;
    }

    public void setSvcCsosnBefore(String svcCsosnBefore) {
        this.svcCsosnBefore = svcCsosnBefore;
    }

    public String getSncCsosnBefore() {
        return sncCsosnBefore;
    }

    public void setSncCsosnBefore(String sncCsosnBefore) {
        this.sncCsosnBefore = sncCsosnBefore;
    }

    public Double getFecpBefore() {
        return fecpBefore;
    }

    public void setFecpBefore(Double fecpBefore) {
        this.fecpBefore = fecpBefore;
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
        if (!(object instanceof Fiscaltemp)) {
            return false;
        }
        Fiscaltemp other = (Fiscaltemp) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.entity.Fiscaltemp[ codigo=" + codigo + " ]";
    }

    @Override
    public Object getId() {
        return codigo;
    }

    @Override
    public void setId(Object object) {
        this.codigo = (Integer) object;
    }

}
