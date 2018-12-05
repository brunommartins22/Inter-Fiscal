package br.com.interagese.interfiscal.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TABPRO")
@NamedQueries({
    @NamedQuery(name = "Tabpro.findAll", query = "SELECT t FROM Tabpro t")})
public class Tabpro implements Model {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CODPRO",updatable = false)
    private String codpro;
    @Size(max = 50)
    @Column(name = "DESCPRO",updatable = false)
    private String descpro;
    @Size(max = 200)
    @Column(name = "DETALHE",updatable = false)
    private String detalhe;
    @Size(max = 20)
    @Column(name = "CODBARUN")
    private String codbarun;
    @Size(max = 20)
    @Column(name = "CODBARCX",updatable = false)
    private String codbarcx;
    @Size(max = 20)
    @Column(name = "CODREFER",updatable = false)
    private String codrefer;
    @Size(max = 2)
    @Column(name = "UNEMB",updatable = false)
    private String unemb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QTDEMB",updatable = false)
    private Double qtdemb;
    @Size(max = 2)
    @Column(name = "UNIDADE",updatable = false)
    private String unidade;
    @Size(max = 6)
    @Column(name = "CODVAS",updatable = false)
    private String codvas;
    @Column(name = "VLCOMI",updatable = false)
    private Double vlcomi;
    @Size(max = 6)
    @Column(name = "GRUPRO",updatable = false)
    private String grupro;
    @Column(name = "FABPRO",updatable = false)
    private Integer fabpro;
    @Column(name = "FORPRO01",updatable = false)
    private Integer forpro01;
    @Column(name = "FORPRO02",updatable = false)
    private Integer forpro02;
    @Column(name = "FORPRO03",updatable = false)
    private Integer forpro03;
    @Size(max = 10)
    @Column(name = "DOCFOR01",updatable = false)
    private String docfor01;
    @Size(max = 10)
    @Column(name = "DOCFOR02",updatable = false)
    private String docfor02;
    @Size(max = 10)
    @Column(name = "DOCFOR03",updatable = false)
    private String docfor03;
    @Column(name = "QTDULTENT01",updatable = false)
    private Double qtdultent01;
    @Column(name = "QTDULTENT02",updatable = false)
    private Double qtdultent02;
    @Column(name = "QTDULTENT03",updatable = false)
    private Double qtdultent03;
    @Column(name = "ULTPRCOM01",updatable = false)
    private Double ultprcom01;
    @Column(name = "ULTPRCOM02",updatable = false)
    private Double ultprcom02;
    @Column(name = "ULTPRCOM03",updatable = false)
    private Double ultprcom03;
    @Column(name = "DTULTCOM01",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtultcom01;
    @Column(name = "DTULTCOM02",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtultcom02;
    @Column(name = "DTULTCOM03",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtultcom03;
    @Column(name = "DTCHEGADA",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtchegada;
    @Column(name = "DTSAIPRO",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtsaipro;
    @Column(name = "ICMS",updatable = false)
    private Double icms;
    @Size(max = 2)
    @Column(name = "INDICE")
    private String indice;
    @Column(name = "PESO",updatable = false)
    private Character peso;
    @Column(name = "LARGURA",updatable = false)
    private Double largura;
    @Column(name = "ALTURA",updatable = false)
    private Double altura;
    @Column(name = "MASSACOMPR",updatable = false)
    private Character massacompr;
    @Column(name = "CONTRQUANT",updatable = false)
    private Character contrquant;
    @Column(name = "CONTRREFER",updatable = false)
    private Character contrrefer;
    @Column(name = "CONTRCOMP",updatable = false)
    private Character contrcomp;
    @Column(name = "CONTRPONTO",updatable = false)
    private Character contrponto;
    @Column(name = "BAIXAPONTO",updatable = false)
    private Integer baixaponto;
    @Column(name = "STPROD",updatable = false)
    private Character stprod;
    @Column(name = "QUALPROD",updatable = false)
    private Character qualprod;
    @Column(name = "RGCODUSU",updatable = false)
    private Integer rgcodusu;
    @Size(max = 8)
    @Column(name = "RGUSUARIO",updatable = false)
    private String rgusuario;
    @Column(name = "RGDATA",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rgdata;
    @Size(max = 1)
    @Column(name = "RGEVENTO",updatable = false)
    private String rgevento;
    @Column(name = "FATORPIS")
    private Double fatorpis;
    @Column(name = "FATORCOFINS")
    private Double fatorcofins;
    @Column(name = "SUBSTRIB",updatable = false)
    private Character substrib;
    @Column(name = "IPI",updatable = false)
    private Character ipi;
    @Column(name = "PERCIPI",updatable = false)
    private Double percipi;
    @Column(name = "MEIOAMEIO",updatable = false)
    private Character meioameio;
    @Column(name = "TPSUBEST",updatable = false)
    private Character tpsubest;
    @Size(max = 100)
    @Column(name = "MODELO",updatable = false)
    private String modelo;
    @Size(max = 100)
    @Column(name = "TAMANHO",updatable = false)
    private String tamanho;
    @Column(name = "PERCLUCRO",updatable = false)
    private Double perclucro;
//    @Column(name = "REMCONTR")
//    private Character remcontr;
    @Column(name = "LIBQUANT",updatable = false)
    private Character libquant;
    @Column(name = "VLCOMIAVVA",updatable = false)
    private Double vlcomiavva;
    @Column(name = "VLCOMICCVA",updatable = false)
    private Double vlcomiccva;
    @Column(name = "VLCOMIAPVA",updatable = false)
    private Double vlcomiapva;
    @Column(name = "VLCOMIAVAT",updatable = false)
    private Double vlcomiavat;
    @Column(name = "VLCOMICCAT",updatable = false)
    private Double vlcomiccat;
    @Column(name = "VLCOMIAPAT",updatable = false)
    private Double vlcomiapat;
    @Column(name = "DTVENC01",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtvenc01;
    @Column(name = "DTVENC02",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtvenc02;
    @Size(max = 20)
    @Column(name = "LTVENC01",updatable = false)
    private String ltvenc01;
    @Size(max = 20)
    @Column(name = "LTVENC02",updatable = false)
    private String ltvenc02;
    @Column(name = "BALANCA",updatable = false)
    private Character balanca;
    @Size(max = 8)
    @Column(name = "CLASFISCAL")
    private String clasfiscal;
    @Column(name = "CODALUSER",updatable = false)
    private Integer codaluser;
    @Column(name = "CODCOLECAO",updatable = false)
    private Integer codcolecao;
    @Column(name = "QUANTPED",updatable = false)
    private Double quantped;
    @Column(name = "DTCHEGPED",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtchegped;
    @Size(max = 10)
    @Column(name = "CORCONSUL",updatable = false)
    private String corconsul;
    @Column(name = "CASADEC",updatable = false)
    private Integer casadec;
    @Size(max = 4)
    @Column(name = "CST")
    private String cst;
    @Size(max = 250)
    @Column(name = "CORES",updatable = false)
    private String cores;
    @Column(name = "TRIBMONO",updatable = false)
    private Character tribmono;
    @Column(name = "PESOBRUTO",updatable = false)
    private Double pesobruto;
    @Column(name = "PESOLIQUIDO",updatable = false)
    private Double pesoliquido;
    @Column(name = "LIBDESCONTO",updatable = false)
    private Character libdesconto;
    @Size(max = 2)
    @Column(name = "TIPOITEM",updatable = false)
    private String tipoitem;
    @Size(max = 3)
    @Column(name = "EXIPI",updatable = false)
    private String exipi;
    @Size(max = 2)
    @Column(name = "CODGEN")
    private String codgen;
    @Size(max = 4)
    @Column(name = "CODLST",updatable = false)
    private String codlst;
    @Size(max = 9)
    @Column(name = "CODANP",updatable = false)
    private String codanp;
    @Size(max = 21)
    @Column(name = "CODIF",updatable = false)
    private String codif;
    @Size(max = 1)
    @Column(name = "CLASTERAP",updatable = false)
    private String clasterap;
    @Size(max = 1)
    @Column(name = "USOPROLONG",updatable = false)
    private String usoprolong;
    @Size(max = 13)
    @Column(name = "REGMSMED",updatable = false)
    private String regmsmed;
    @Size(max = 1)
    @Column(name = "UNIDMED",updatable = false)
    private String unidmed;
    @Size(max = 5)
    @Column(name = "CODDCBINS",updatable = false)
    private String coddcbins;
    @Size(max = 1)
    @Column(name = "CESTABAS",updatable = false)
    private String cestabas;
    @Size(max = 1)
    @Column(name = "DESONFOLHA",updatable = false)
    private String desonfolha;
    @Column(name = "DIASVENC",updatable = false)
    private Integer diasvenc;
    @Size(max = 1)
    @Column(name = "ORIGPROD",updatable = false)
    private String origprod;
    @Column(name = "ICODPRO",updatable = false)
    private Integer icodpro;
    @Size(max = 1)
    @Column(name = "FARMAPOP",updatable = false)
    private String farmapop;
    @Column(name = "QUANTUND",updatable = false)
    private Double quantund;
    @Size(max = 10)
    @Column(name = "UNIDEMB",updatable = false)
    private String unidemb;
    @Size(max = 1)
    @Column(name = "CHAMATEC",updatable = false)
    private String chamatec;
    @Column(name = "DETALHE2",updatable = false)
    private String detalhe2 = "";
    @Size(max = 7)
    @Column(name = "CEST")
    private String cest;
    @Size(max = 5)
    @Column(name = "CODDCB",updatable = false)
    private String coddcb;
    @Size(max = 2)
    @Column(name = "CODLISTA",updatable = false)
    private String codlista;
    @Size(max = 1)
    @Column(name = "IMPCUPOM",updatable = false)
    private String impcupom;
    @Size(max = 20)
    @Column(name = "CODSPED",updatable = false)
    private String codsped;
    @Size(max = 1)
    @Column(name = "TPVENCIMNETO",updatable = false)
    private String tpvencimneto;
    @Size(max = 13)
    @Column(name = "CODANVISA",updatable = false)
    private String codanvisa;
    @Column(name = "DTULTPEDIDO",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtultpedido;
    @Size(max = 1)
    @Column(name = "MEDPOSNEG",updatable = false)
    private String medposneg;
    @Size(max = 2)
    @Column(name = "UNIDPED",updatable = false)
    private String unidped;
    @Size(max = 1)
    @Column(name = "INDESCALA",updatable = false)
    private String indescala;
    @Size(max = 14)
    @Column(name = "CNPJFAB",updatable = false)
    private String cnpjfab;
    @Size(max = 10)
    @Column(name = "CBENEF",updatable = false)
    private String cbenef;
    @Column(name = "PDIF",updatable = false)
    private Double pdif;
    @Size(max = 95)
    @Column(name = "COMBDESCANP",updatable = false)
    private String combdescanp;

    @Size(max = 1)
    @Column(name = "QUARTOBOV",updatable = false)
    private String quartobov;

    @Size(max = 2)
    @Column(name = "UNIDADEQUARTO",updatable = false)
    private String unidadequarto;

    @Column(name = "NATPRO")
    private String natpro;

    public Tabpro() {
    }

    public Tabpro(String codpro) {
        this.codpro = codpro;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public String getDescpro() {
        return descpro;
    }

    public void setDescpro(String descpro) {
        this.descpro = descpro;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getCodbarun() {
        return codbarun;
    }

    public void setCodbarun(String codbarun) {
        this.codbarun = codbarun;
    }

    public String getCodbarcx() {
        return codbarcx;
    }

    public void setCodbarcx(String codbarcx) {
        this.codbarcx = codbarcx;
    }

    public String getCodrefer() {
        return codrefer;
    }

    public void setCodrefer(String codrefer) {
        this.codrefer = codrefer;
    }

    public String getUnemb() {
        return unemb;
    }

    public void setUnemb(String unemb) {
        this.unemb = unemb;
    }

    public Double getQtdemb() {
        return qtdemb;
    }

    public void setQtdemb(Double qtdemb) {
        this.qtdemb = qtdemb;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCodvas() {
        return codvas;
    }

    public void setCodvas(String codvas) {
        this.codvas = codvas;
    }

    public Double getVlcomi() {
        return vlcomi;
    }

    public void setVlcomi(Double vlcomi) {
        this.vlcomi = vlcomi;
    }

    public String getGrupro() {
        return grupro;
    }

    public void setGrupro(String grupro) {
        this.grupro = grupro;
    }

    public Integer getFabpro() {
        return fabpro;
    }

    public void setFabpro(Integer fabpro) {
        this.fabpro = fabpro;
    }

    public Integer getForpro01() {
        return forpro01;
    }

    public void setForpro01(Integer forpro01) {
        this.forpro01 = forpro01;
    }

    public Integer getForpro02() {
        return forpro02;
    }

    public void setForpro02(Integer forpro02) {
        this.forpro02 = forpro02;
    }

    public Integer getForpro03() {
        return forpro03;
    }

    public void setForpro03(Integer forpro03) {
        this.forpro03 = forpro03;
    }

    public String getDocfor01() {
        return docfor01;
    }

    public void setDocfor01(String docfor01) {
        this.docfor01 = docfor01;
    }

    public String getDocfor02() {
        return docfor02;
    }

    public void setDocfor02(String docfor02) {
        this.docfor02 = docfor02;
    }

    public String getDocfor03() {
        return docfor03;
    }

    public void setDocfor03(String docfor03) {
        this.docfor03 = docfor03;
    }

    public Double getQtdultent01() {
        return qtdultent01;
    }

    public void setQtdultent01(Double qtdultent01) {
        this.qtdultent01 = qtdultent01;
    }

    public Double getQtdultent02() {
        return qtdultent02;
    }

    public void setQtdultent02(Double qtdultent02) {
        this.qtdultent02 = qtdultent02;
    }

    public Double getQtdultent03() {
        return qtdultent03;
    }

    public void setQtdultent03(Double qtdultent03) {
        this.qtdultent03 = qtdultent03;
    }

    public Double getUltprcom01() {
        return ultprcom01;
    }

    public void setUltprcom01(Double ultprcom01) {
        this.ultprcom01 = ultprcom01;
    }

    public Double getUltprcom02() {
        return ultprcom02;
    }

    public void setUltprcom02(Double ultprcom02) {
        this.ultprcom02 = ultprcom02;
    }

    public Double getUltprcom03() {
        return ultprcom03;
    }

    public void setUltprcom03(Double ultprcom03) {
        this.ultprcom03 = ultprcom03;
    }

    public Date getDtultcom01() {
        return dtultcom01;
    }

    public void setDtultcom01(Date dtultcom01) {
        this.dtultcom01 = dtultcom01;
    }

    public Date getDtultcom02() {
        return dtultcom02;
    }

    public void setDtultcom02(Date dtultcom02) {
        this.dtultcom02 = dtultcom02;
    }

    public Date getDtultcom03() {
        return dtultcom03;
    }

    public void setDtultcom03(Date dtultcom03) {
        this.dtultcom03 = dtultcom03;
    }

    public Date getDtchegada() {
        return dtchegada;
    }

    public void setDtchegada(Date dtchegada) {
        this.dtchegada = dtchegada;
    }

    public Date getDtsaipro() {
        return dtsaipro;
    }

    public void setDtsaipro(Date dtsaipro) {
        this.dtsaipro = dtsaipro;
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public Character getPeso() {
        return peso;
    }

    public void setPeso(Character peso) {
        this.peso = peso;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Character getMassacompr() {
        return massacompr;
    }

    public void setMassacompr(Character massacompr) {
        this.massacompr = massacompr;
    }

    public Character getContrquant() {
        return contrquant;
    }

    public void setContrquant(Character contrquant) {
        this.contrquant = contrquant;
    }

    public Character getContrrefer() {
        return contrrefer;
    }

    public void setContrrefer(Character contrrefer) {
        this.contrrefer = contrrefer;
    }

    public Character getContrcomp() {
        return contrcomp;
    }

    public void setContrcomp(Character contrcomp) {
        this.contrcomp = contrcomp;
    }

    public Character getContrponto() {
        return contrponto;
    }

    public void setContrponto(Character contrponto) {
        this.contrponto = contrponto;
    }

    public Integer getBaixaponto() {
        return baixaponto;
    }

    public void setBaixaponto(Integer baixaponto) {
        this.baixaponto = baixaponto;
    }

    public Character getStprod() {
        return stprod;
    }

    public void setStprod(Character stprod) {
        this.stprod = stprod;
    }

    public Character getQualprod() {
        return qualprod;
    }

    public void setQualprod(Character qualprod) {
        this.qualprod = qualprod;
    }

    public Integer getRgcodusu() {
        return rgcodusu;
    }

    public void setRgcodusu(Integer rgcodusu) {
        this.rgcodusu = rgcodusu;
    }

    public String getRgusuario() {
        return rgusuario;
    }

    public void setRgusuario(String rgusuario) {
        this.rgusuario = rgusuario;
    }

    public Date getRgdata() {
        return rgdata;
    }

    public void setRgdata(Date rgdata) {
        this.rgdata = rgdata;
    }

    public String getRgevento() {
        return rgevento;
    }

    public void setRgevento(String rgevento) {
        this.rgevento = rgevento;
    }

    public Double getFatorpis() {
        return fatorpis;
    }

    public void setFatorpis(Double fatorpis) {
        this.fatorpis = fatorpis;
    }

    public Double getFatorcofins() {
        return fatorcofins;
    }

    public void setFatorcofins(Double fatorcofins) {
        this.fatorcofins = fatorcofins;
    }

    public Character getSubstrib() {
        return substrib;
    }

    public void setSubstrib(Character substrib) {
        this.substrib = substrib;
    }

    public Character getIpi() {
        return ipi;
    }

    public void setIpi(Character ipi) {
        this.ipi = ipi;
    }

    public Double getPercipi() {
        return percipi;
    }

    public void setPercipi(Double percipi) {
        this.percipi = percipi;
    }

    public Character getMeioameio() {
        return meioameio;
    }

    public void setMeioameio(Character meioameio) {
        this.meioameio = meioameio;
    }

    public Character getTpsubest() {
        return tpsubest;
    }

    public void setTpsubest(Character tpsubest) {
        this.tpsubest = tpsubest;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPerclucro() {
        return perclucro;
    }

    public void setPerclucro(Double perclucro) {
        this.perclucro = perclucro;
    }

//    public Character getRemcontr() {
//        return remcontr;
//    }
//
//    public void setRemcontr(Character remcontr) {
//        this.remcontr = remcontr;
//    }
    public Character getLibquant() {
        return libquant;
    }

    public void setLibquant(Character libquant) {
        this.libquant = libquant;
    }

    public Double getVlcomiavva() {
        return vlcomiavva;
    }

    public void setVlcomiavva(Double vlcomiavva) {
        this.vlcomiavva = vlcomiavva;
    }

    public Double getVlcomiccva() {
        return vlcomiccva;
    }

    public void setVlcomiccva(Double vlcomiccva) {
        this.vlcomiccva = vlcomiccva;
    }

    public Double getVlcomiapva() {
        return vlcomiapva;
    }

    public void setVlcomiapva(Double vlcomiapva) {
        this.vlcomiapva = vlcomiapva;
    }

    public Double getVlcomiavat() {
        return vlcomiavat;
    }

    public void setVlcomiavat(Double vlcomiavat) {
        this.vlcomiavat = vlcomiavat;
    }

    public Double getVlcomiccat() {
        return vlcomiccat;
    }

    public void setVlcomiccat(Double vlcomiccat) {
        this.vlcomiccat = vlcomiccat;
    }

    public Double getVlcomiapat() {
        return vlcomiapat;
    }

    public void setVlcomiapat(Double vlcomiapat) {
        this.vlcomiapat = vlcomiapat;
    }

    public Date getDtvenc01() {
        return dtvenc01;
    }

    public void setDtvenc01(Date dtvenc01) {
        this.dtvenc01 = dtvenc01;
    }

    public Date getDtvenc02() {
        return dtvenc02;
    }

    public void setDtvenc02(Date dtvenc02) {
        this.dtvenc02 = dtvenc02;
    }

    public String getLtvenc01() {
        return ltvenc01;
    }

    public void setLtvenc01(String ltvenc01) {
        this.ltvenc01 = ltvenc01;
    }

    public String getLtvenc02() {
        return ltvenc02;
    }

    public void setLtvenc02(String ltvenc02) {
        this.ltvenc02 = ltvenc02;
    }

    public Character getBalanca() {
        return balanca;
    }

    public void setBalanca(Character balanca) {
        this.balanca = balanca;
    }

    public String getClasfiscal() {
        return clasfiscal;
    }

    public void setClasfiscal(String clasfiscal) {
        this.clasfiscal = clasfiscal;
    }

    public Integer getCodaluser() {
        return codaluser;
    }

    public void setCodaluser(Integer codaluser) {
        this.codaluser = codaluser;
    }

    public Integer getCodcolecao() {
        return codcolecao;
    }

    public void setCodcolecao(Integer codcolecao) {
        this.codcolecao = codcolecao;
    }

    public Double getQuantped() {
        return quantped;
    }

    public void setQuantped(Double quantped) {
        this.quantped = quantped;
    }

    public Date getDtchegped() {
        return dtchegped;
    }

    public void setDtchegped(Date dtchegped) {
        this.dtchegped = dtchegped;
    }

    public String getCorconsul() {
        return corconsul;
    }

    public void setCorconsul(String corconsul) {
        this.corconsul = corconsul;
    }

    public Integer getCasadec() {
        return casadec;
    }

    public void setCasadec(Integer casadec) {
        this.casadec = casadec;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public Character getTribmono() {
        return tribmono;
    }

    public void setTribmono(Character tribmono) {
        this.tribmono = tribmono;
    }

    public Double getPesobruto() {
        return pesobruto;
    }

    public void setPesobruto(Double pesobruto) {
        this.pesobruto = pesobruto;
    }

    public Double getPesoliquido() {
        return pesoliquido;
    }

    public void setPesoliquido(Double pesoliquido) {
        this.pesoliquido = pesoliquido;
    }

    public Character getLibdesconto() {
        return libdesconto;
    }

    public void setLibdesconto(Character libdesconto) {
        this.libdesconto = libdesconto;
    }

    public String getTipoitem() {
        return tipoitem;
    }

    public void setTipoitem(String tipoitem) {
        this.tipoitem = tipoitem;
    }

    public String getExipi() {
        return exipi;
    }

    public void setExipi(String exipi) {
        this.exipi = exipi;
    }

    public String getCodgen() {
        return codgen;
    }

    public void setCodgen(String codgen) {
        this.codgen = codgen;
    }

    public String getCodlst() {
        return codlst;
    }

    public void setCodlst(String codlst) {
        this.codlst = codlst;
    }

    public String getCodanp() {
        return codanp;
    }

    public void setCodanp(String codanp) {
        this.codanp = codanp;
    }

    public String getCodif() {
        return codif;
    }

    public void setCodif(String codif) {
        this.codif = codif;
    }

    public String getClasterap() {
        return clasterap;
    }

    public void setClasterap(String clasterap) {
        this.clasterap = clasterap;
    }

    public String getUsoprolong() {
        return usoprolong;
    }

    public void setUsoprolong(String usoprolong) {
        this.usoprolong = usoprolong;
    }

    public String getRegmsmed() {
        return regmsmed;
    }

    public void setRegmsmed(String regmsmed) {
        this.regmsmed = regmsmed;
    }

    public String getUnidmed() {
        return unidmed;
    }

    public void setUnidmed(String unidmed) {
        this.unidmed = unidmed;
    }

    public String getCoddcbins() {
        return coddcbins;
    }

    public void setCoddcbins(String coddcbins) {
        this.coddcbins = coddcbins;
    }

    public String getCestabas() {
        return cestabas;
    }

    public void setCestabas(String cestabas) {
        this.cestabas = cestabas;
    }

    public String getDesonfolha() {
        return desonfolha;
    }

    public void setDesonfolha(String desonfolha) {
        this.desonfolha = desonfolha;
    }

    public Integer getDiasvenc() {
        return diasvenc;
    }

    public void setDiasvenc(Integer diasvenc) {
        this.diasvenc = diasvenc;
    }

    public String getOrigprod() {
        return origprod;
    }

    public void setOrigprod(String origprod) {
        this.origprod = origprod;
    }

    public Integer getIcodpro() {
        return icodpro;
    }

    public void setIcodpro(Integer icodpro) {
        this.icodpro = icodpro;
    }

    public String getFarmapop() {
        return farmapop;
    }

    public void setFarmapop(String farmapop) {
        this.farmapop = farmapop;
    }

    public Double getQuantund() {
        return quantund;
    }

    public void setQuantund(Double quantund) {
        this.quantund = quantund;
    }

    public String getUnidemb() {
        return unidemb;
    }

    public void setUnidemb(String unidemb) {
        this.unidemb = unidemb;
    }

    public String getChamatec() {
        return chamatec;
    }

    public void setChamatec(String chamatec) {
        this.chamatec = chamatec;
    }

    public String getDetalhe2() {
        return detalhe2;
    }

    public void setDetalhe2(String detalhe2) {
        this.detalhe2 = detalhe2;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getCoddcb() {
        return coddcb;
    }

    public void setCoddcb(String coddcb) {
        this.coddcb = coddcb;
    }

    public String getCodlista() {
        return codlista;
    }

    public void setCodlista(String codlista) {
        this.codlista = codlista;
    }

    public String getImpcupom() {
        return impcupom;
    }

    public void setImpcupom(String impcupom) {
        this.impcupom = impcupom;
    }

    public String getCodsped() {
        return codsped;
    }

    public void setCodsped(String codsped) {
        this.codsped = codsped;
    }

    public String getTpvencimneto() {
        return tpvencimneto;
    }

    public void setTpvencimneto(String tpvencimneto) {
        this.tpvencimneto = tpvencimneto;
    }

    public String getCodanvisa() {
        return codanvisa;
    }

    public void setCodanvisa(String codanvisa) {
        this.codanvisa = codanvisa;
    }

    public Date getDtultpedido() {
        return dtultpedido;
    }

    public void setDtultpedido(Date dtultpedido) {
        this.dtultpedido = dtultpedido;
    }

    public String getMedposneg() {
        return medposneg;
    }

    public void setMedposneg(String medposneg) {
        this.medposneg = medposneg;
    }

    public String getUnidped() {
        return unidped;
    }

    public void setUnidped(String unidped) {
        this.unidped = unidped;
    }

    public String getIndescala() {
        return indescala;
    }

    public void setIndescala(String indescala) {
        this.indescala = indescala;
    }

    public String getCnpjfab() {
        return cnpjfab;
    }

    public void setCnpjfab(String cnpjfab) {
        this.cnpjfab = cnpjfab;
    }

    public String getCbenef() {
        return cbenef;
    }

    public void setCbenef(String cbenef) {
        this.cbenef = cbenef;
    }

    public Double getPdif() {
        return pdif;
    }

    public void setPdif(Double pdif) {
        this.pdif = pdif;
    }

    public String getCombdescanp() {
        return combdescanp;
    }

    public void setCombdescanp(String combdescanp) {
        this.combdescanp = combdescanp;
    }

    public String getQuartobov() {
        return quartobov;
    }

    public void setQuartobov(String quartobov) {
        this.quartobov = quartobov;
    }

    public String getUnidadequarto() {
        return unidadequarto;
    }

    public void setUnidadequarto(String unidadequarto) {
        this.unidadequarto = unidadequarto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpro != null ? codpro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabpro)) {
            return false;
        }
        Tabpro other = (Tabpro) object;
        if ((this.codpro == null && other.codpro != null) || (this.codpro != null && !this.codpro.equals(other.codpro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.transmissornfe.business.entity.Tabpro[ codpro=" + codpro + " ]";
    }

    @Override
    public Object getId() {
        return codpro;
    }

    @Override
    public void setId(Object object) {
        this.codpro = codpro;
    }

    public String getNatpro() {
        return natpro;
    }

    public void setNatpro(String natPro) {
        this.natpro = natPro;
    }

}
