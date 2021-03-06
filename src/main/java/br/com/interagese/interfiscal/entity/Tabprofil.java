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
 * @author Bruno Martins
 */
@Entity
@Table(name = "tabprofil")
@NamedQueries({
    @NamedQuery(name = "Tabprofil.findAll", query = "SELECT t FROM Tabprofil t")})
public class Tabprofil implements Model {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TabprofilPK tabprofilPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qtdpro", updatable = false)
    private Double qtdpro;
    @Column(name = "qtdrespro", updatable = false)
    private Double qtdrespro;
    @Column(name = "qtddevpro", updatable = false)
    private Double qtddevpro;
    @Column(name = "qtdmin", updatable = false)
    private Double qtdmin;
    @Column(name = "qtdmax", updatable = false)
    private Double qtdmax;
    @Column(name = "qtdmeta", updatable = false)
    private Double qtdmeta;
    @Column(name = "qtdultinv", updatable = false)
    private Double qtdultinv;
    @Column(name = "dtultinv", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtultinv;
    @Column(name = "pratpro", updatable = false)
    private Double pratpro;
    @Column(name = "prvapro", updatable = false)
    private Double prvapro;
    @Column(name = "prcupro", updatable = false)
    private Double prcupro;
    @Column(name = "percdescco", updatable = false)
    private Double percdescco;
    @Column(name = "percdescat", updatable = false)
    private Double percdescat;
    @Column(name = "rgcodusu", updatable = false)
    private Integer rgcodusu;
    @Size(max = 8)
    @Column(name = "rgusuario", updatable = false)
    private String rgusuario;
    @Column(name = "rgdata")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rgdata;
    @Size(max = 1)
    @Column(name = "rgevento")
    private String rgevento;
    @Column(name = "outcusto", updatable = false)
    private Double outcusto;
    @Column(name = "marglucat", updatable = false)
    private Double marglucat;
    @Column(name = "marglucva", updatable = false)
    private Double marglucva;
    @Column(name = "pratprosug", updatable = false)
    private Double pratprosug;
    @Column(name = "prvaprosug", updatable = false)
    private Double prvaprosug;
    @Column(name = "prcuprosug", updatable = false)
    private Double prcuprosug;
    @Column(name = "outcustosug", updatable = false)
    private Double outcustosug;
    @Column(name = "marlucsugat", updatable = false)
    private Double marlucsugat;
    @Column(name = "marlucsugva", updatable = false)
    private Double marlucsugva;
    @Column(name = "contreffil", updatable = false)
    private Character contreffil;
    @Column(name = "contrplano", updatable = false)
    private Character contrplano;
    @Column(name = "qtddesco", updatable = false)
    private Integer qtddesco;
    @Size(max = 20)
    @Column(name = "locaprod", updatable = false)
    private String locaprod;
    @Column(name = "prveant", updatable = false)
    private Double prveant;
    @Column(name = "percdesccocc", updatable = false)
    private Double percdesccocc;
    @Column(name = "percdescatcc", updatable = false)
    private Double percdescatcc;
    @Column(name = "percdesccoap", updatable = false)
    private Double percdesccoap;
    @Column(name = "percdescatap", updatable = false)
    private Double percdescatap;
    @Column(name = "qtdminven", updatable = false)
    private Double qtdminven;
    @Column(name = "qtdrom", updatable = false)
    private Double qtdrom;
    @Column(name = "qtdent", updatable = false)
    private Double qtdent;
    @Column(name = "prcompraat", updatable = false)
    private Double prcompraat;
    @Column(name = "prcomprava", updatable = false)
    private Double prcomprava;
    @Column(name = "prcusvar", updatable = false)
    private Double prcusvar;
    @Column(name = "prcomatsug", updatable = false)
    private Double prcomatsug;
    @Column(name = "prcomvasug", updatable = false)
    private Double prcomvasug;
    @Column(name = "prcuvarsug", updatable = false)
    private Double prcuvarsug;
    @Column(name = "qtdestant", updatable = false)
    private Double qtdestant;
    @Column(name = "fornconcil", updatable = false)
    private Integer fornconcil;
    @Column(name = "percconcil", updatable = false)
    private Double percconcil;
    @Column(name = "dtaltercao", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaltercao;
    @Column(name = "codusualt", updatable = false)
    private Integer codusualt;
    @Size(max = 8)
    @Column(name = "nomusualt", updatable = false)
    private String nomusualt;
    @Column(name = "ultprcompra", updatable = false)
    private Double ultprcompra;
    @Column(name = "alicmscom", updatable = false)
    private Double alicmscom;
    @Column(name = "alipicom", updatable = false)
    private Double alipicom;
    @Column(name = "vldescocom", updatable = false)
    private Double vldescocom;
    @Column(name = "vlfretecom", updatable = false)
    private Double vlfretecom;
    @Column(name = "vlsegcom", updatable = false)
    private Double vlsegcom;
    @Column(name = "outdespcom", updatable = false)
    private Double outdespcom;
    @Column(name = "prcustocom", updatable = false)
    private Double prcustocom;
    @Column(name = "fatorsubst", updatable = false)
    private Double fatorsubst;
    @Column(name = "fatorpiscom")
    private Double fatorpiscom;
    @Column(name = "fatorcofinscom")
    private Double fatorcofinscom;
    @Column(name = "prmepro", updatable = false)
    private Double prmepro;
    @Column(name = "perccpp", updatable = false)
    private Double perccpp;
    @Column(name = "perccppsug", updatable = false)
    private Double perccppsug;
    @Column(name = "prvendaantva", updatable = false)
    private Double prvendaantva;
    @Column(name = "prvendaantat", updatable = false)
    private Double prvendaantat;
    @Size(max = 4)
    @Column(name = "cstcom", updatable = false)
    private String cstcom;
    @Column(name = "qtdquabov", updatable = false)
    private Integer qtdquabov;
    @Column(name = "qtdprepro", updatable = false)
    private Double qtdprepro;
    @Column(name = "dtultpedido", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtultpedido;
    @Column(name = "qtdpedido", updatable = false)
    private Double qtdpedido;
    @Size(max = 2)
    @Column(name = "unidped", updatable = false)
    private String unidped;
    @Column(name = "qtddescat", updatable = false)
    private Double qtddescat;
    @Column(name = "qtdminvenat", updatable = false)
    private Double qtdminvenat;
    @Column(name = "prmaxcons", updatable = false)
    private Double prmaxcons;
    @Column(name = "vlajucusto", updatable = false)
    private Double vlajucusto;
    @Size(max = 2)
    @Column(name = "uforigem", updatable = false)
    private String uforigem;
    @Column(name = "pcred", updatable = false)
    private Double pcred;
    @Column(name = "perclucro", updatable = false)
    private Double perclucro;
    @Column(name = "pfcp", updatable = false)
    private Double pfcp;
    @Column(name = "pfcpret", updatable = false)
    private Double pfcpret;
    @Column(name = "pst", updatable = false)
    private Double pst;
    @Column(name = "vbcfcpret", updatable = false)
    private Double vbcfcpret;
    @Column(name = "vbcicmsstret", updatable = false)
    private Double vbcicmsstret;
    @Column(name = "vfcpret", updatable = false)
    private Double vfcpret;
    @Column(name = "vicmsstret", updatable = false)
    private Double vicmsstret;
    @Size(max = 3)
    @Column(name = "nat_rec")
    private String natRec;
    @Size(max = 4)
    @Column(name = "cstipie", updatable = false)
    private String cstipie;
    @Size(max = 4)
    @Column(name = "cstpise")
    private String cstpise;
    @Size(max = 4)
    @Column(name = "cstcofinse")
    private String cstcofinse;
    @Column(name = "dtenvserv", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtenvserv;
    @Size(max = 1)
    @Column(name = "mixfiscal")
    private String mixfiscal;

    public Tabprofil() {
    }

    public Tabprofil(TabprofilPK tabprofilPK) {
        this.tabprofilPK = tabprofilPK;
    }

    public Tabprofil(String codpro, int codfil) {
        this.tabprofilPK = new TabprofilPK(codpro, codfil);
    }

    public TabprofilPK getTabprofilPK() {
        return tabprofilPK;
    }

    public void setTabprofilPK(TabprofilPK tabprofilPK) {
        this.tabprofilPK = tabprofilPK;
    }

    public Double getQtdpro() {
        return qtdpro;
    }

    public void setQtdpro(Double qtdpro) {
        this.qtdpro = qtdpro;
    }

    public Double getQtdrespro() {
        return qtdrespro;
    }

    public void setQtdrespro(Double qtdrespro) {
        this.qtdrespro = qtdrespro;
    }

    public Double getQtddevpro() {
        return qtddevpro;
    }

    public void setQtddevpro(Double qtddevpro) {
        this.qtddevpro = qtddevpro;
    }

    public Double getQtdmin() {
        return qtdmin;
    }

    public void setQtdmin(Double qtdmin) {
        this.qtdmin = qtdmin;
    }

    public Double getQtdmax() {
        return qtdmax;
    }

    public void setQtdmax(Double qtdmax) {
        this.qtdmax = qtdmax;
    }

    public Double getQtdmeta() {
        return qtdmeta;
    }

    public void setQtdmeta(Double qtdmeta) {
        this.qtdmeta = qtdmeta;
    }

    public Double getQtdultinv() {
        return qtdultinv;
    }

    public void setQtdultinv(Double qtdultinv) {
        this.qtdultinv = qtdultinv;
    }

    public Date getDtultinv() {
        return dtultinv;
    }

    public void setDtultinv(Date dtultinv) {
        this.dtultinv = dtultinv;
    }

    public Double getPratpro() {
        return pratpro;
    }

    public void setPratpro(Double pratpro) {
        this.pratpro = pratpro;
    }

    public Double getPrvapro() {
        return prvapro;
    }

    public void setPrvapro(Double prvapro) {
        this.prvapro = prvapro;
    }

    public Double getPrcupro() {
        return prcupro;
    }

    public void setPrcupro(Double prcupro) {
        this.prcupro = prcupro;
    }

    public Double getPercdescco() {
        return percdescco;
    }

    public void setPercdescco(Double percdescco) {
        this.percdescco = percdescco;
    }

    public Double getPercdescat() {
        return percdescat;
    }

    public void setPercdescat(Double percdescat) {
        this.percdescat = percdescat;
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

    public Double getOutcusto() {
        return outcusto;
    }

    public void setOutcusto(Double outcusto) {
        this.outcusto = outcusto;
    }

    public Double getMarglucat() {
        return marglucat;
    }

    public void setMarglucat(Double marglucat) {
        this.marglucat = marglucat;
    }

    public Double getMarglucva() {
        return marglucva;
    }

    public void setMarglucva(Double marglucva) {
        this.marglucva = marglucva;
    }

    public Double getPratprosug() {
        return pratprosug;
    }

    public void setPratprosug(Double pratprosug) {
        this.pratprosug = pratprosug;
    }

    public Double getPrvaprosug() {
        return prvaprosug;
    }

    public void setPrvaprosug(Double prvaprosug) {
        this.prvaprosug = prvaprosug;
    }

    public Double getPrcuprosug() {
        return prcuprosug;
    }

    public void setPrcuprosug(Double prcuprosug) {
        this.prcuprosug = prcuprosug;
    }

    public Double getOutcustosug() {
        return outcustosug;
    }

    public void setOutcustosug(Double outcustosug) {
        this.outcustosug = outcustosug;
    }

    public Double getMarlucsugat() {
        return marlucsugat;
    }

    public void setMarlucsugat(Double marlucsugat) {
        this.marlucsugat = marlucsugat;
    }

    public Double getMarlucsugva() {
        return marlucsugva;
    }

    public void setMarlucsugva(Double marlucsugva) {
        this.marlucsugva = marlucsugva;
    }

    public Character getContreffil() {
        return contreffil;
    }

    public void setContreffil(Character contreffil) {
        this.contreffil = contreffil;
    }

    public Character getContrplano() {
        return contrplano;
    }

    public void setContrplano(Character contrplano) {
        this.contrplano = contrplano;
    }

    public Integer getQtddesco() {
        return qtddesco;
    }

    public void setQtddesco(Integer qtddesco) {
        this.qtddesco = qtddesco;
    }

    public String getLocaprod() {
        return locaprod;
    }

    public void setLocaprod(String locaprod) {
        this.locaprod = locaprod;
    }

    public Double getPrveant() {
        return prveant;
    }

    public void setPrveant(Double prveant) {
        this.prveant = prveant;
    }

    public Double getPercdesccocc() {
        return percdesccocc;
    }

    public void setPercdesccocc(Double percdesccocc) {
        this.percdesccocc = percdesccocc;
    }

    public Double getPercdescatcc() {
        return percdescatcc;
    }

    public void setPercdescatcc(Double percdescatcc) {
        this.percdescatcc = percdescatcc;
    }

    public Double getPercdesccoap() {
        return percdesccoap;
    }

    public void setPercdesccoap(Double percdesccoap) {
        this.percdesccoap = percdesccoap;
    }

    public Double getPercdescatap() {
        return percdescatap;
    }

    public void setPercdescatap(Double percdescatap) {
        this.percdescatap = percdescatap;
    }

    public Double getQtdminven() {
        return qtdminven;
    }

    public void setQtdminven(Double qtdminven) {
        this.qtdminven = qtdminven;
    }

    public Double getQtdrom() {
        return qtdrom;
    }

    public void setQtdrom(Double qtdrom) {
        this.qtdrom = qtdrom;
    }

    public Double getQtdent() {
        return qtdent;
    }

    public void setQtdent(Double qtdent) {
        this.qtdent = qtdent;
    }

    public Double getPrcompraat() {
        return prcompraat;
    }

    public void setPrcompraat(Double prcompraat) {
        this.prcompraat = prcompraat;
    }

    public Double getPrcomprava() {
        return prcomprava;
    }

    public void setPrcomprava(Double prcomprava) {
        this.prcomprava = prcomprava;
    }

    public Double getPrcusvar() {
        return prcusvar;
    }

    public void setPrcusvar(Double prcusvar) {
        this.prcusvar = prcusvar;
    }

    public Double getPrcomatsug() {
        return prcomatsug;
    }

    public void setPrcomatsug(Double prcomatsug) {
        this.prcomatsug = prcomatsug;
    }

    public Double getPrcomvasug() {
        return prcomvasug;
    }

    public void setPrcomvasug(Double prcomvasug) {
        this.prcomvasug = prcomvasug;
    }

    public Double getPrcuvarsug() {
        return prcuvarsug;
    }

    public void setPrcuvarsug(Double prcuvarsug) {
        this.prcuvarsug = prcuvarsug;
    }

    public Double getQtdestant() {
        return qtdestant;
    }

    public void setQtdestant(Double qtdestant) {
        this.qtdestant = qtdestant;
    }

    public Integer getFornconcil() {
        return fornconcil;
    }

    public void setFornconcil(Integer fornconcil) {
        this.fornconcil = fornconcil;
    }

    public Double getPercconcil() {
        return percconcil;
    }

    public void setPercconcil(Double percconcil) {
        this.percconcil = percconcil;
    }

    public Date getDtaltercao() {
        return dtaltercao;
    }

    public void setDtaltercao(Date dtaltercao) {
        this.dtaltercao = dtaltercao;
    }

    public Integer getCodusualt() {
        return codusualt;
    }

    public void setCodusualt(Integer codusualt) {
        this.codusualt = codusualt;
    }

    public String getNomusualt() {
        return nomusualt;
    }

    public void setNomusualt(String nomusualt) {
        this.nomusualt = nomusualt;
    }

    public Double getUltprcompra() {
        return ultprcompra;
    }

    public void setUltprcompra(Double ultprcompra) {
        this.ultprcompra = ultprcompra;
    }

    public Double getAlicmscom() {
        return alicmscom;
    }

    public void setAlicmscom(Double alicmscom) {
        this.alicmscom = alicmscom;
    }

    public Double getAlipicom() {
        return alipicom;
    }

    public void setAlipicom(Double alipicom) {
        this.alipicom = alipicom;
    }

    public Double getVldescocom() {
        return vldescocom;
    }

    public void setVldescocom(Double vldescocom) {
        this.vldescocom = vldescocom;
    }

    public Double getVlfretecom() {
        return vlfretecom;
    }

    public void setVlfretecom(Double vlfretecom) {
        this.vlfretecom = vlfretecom;
    }

    public Double getVlsegcom() {
        return vlsegcom;
    }

    public void setVlsegcom(Double vlsegcom) {
        this.vlsegcom = vlsegcom;
    }

    public Double getOutdespcom() {
        return outdespcom;
    }

    public void setOutdespcom(Double outdespcom) {
        this.outdespcom = outdespcom;
    }

    public Double getPrcustocom() {
        return prcustocom;
    }

    public void setPrcustocom(Double prcustocom) {
        this.prcustocom = prcustocom;
    }

    public Double getFatorsubst() {
        return fatorsubst;
    }

    public void setFatorsubst(Double fatorsubst) {
        this.fatorsubst = fatorsubst;
    }

    public Double getFatorpiscom() {
        return fatorpiscom;
    }

    public void setFatorpiscom(Double fatorpiscom) {
        this.fatorpiscom = fatorpiscom;
    }

    public Double getFatorcofinscom() {
        return fatorcofinscom;
    }

    public void setFatorcofinscom(Double fatorcofinscom) {
        this.fatorcofinscom = fatorcofinscom;
    }

    public Double getPrmepro() {
        return prmepro;
    }

    public void setPrmepro(Double prmepro) {
        this.prmepro = prmepro;
    }

    public Double getPerccpp() {
        return perccpp;
    }

    public void setPerccpp(Double perccpp) {
        this.perccpp = perccpp;
    }

    public Double getPerccppsug() {
        return perccppsug;
    }

    public void setPerccppsug(Double perccppsug) {
        this.perccppsug = perccppsug;
    }

    public Double getPrvendaantva() {
        return prvendaantva;
    }

    public void setPrvendaantva(Double prvendaantva) {
        this.prvendaantva = prvendaantva;
    }

    public Double getPrvendaantat() {
        return prvendaantat;
    }

    public void setPrvendaantat(Double prvendaantat) {
        this.prvendaantat = prvendaantat;
    }

    public String getCstcom() {
        return cstcom;
    }

    public void setCstcom(String cstcom) {
        this.cstcom = cstcom;
    }

    public Integer getQtdquabov() {
        return qtdquabov;
    }

    public void setQtdquabov(Integer qtdquabov) {
        this.qtdquabov = qtdquabov;
    }

    public Double getQtdprepro() {
        return qtdprepro;
    }

    public void setQtdprepro(Double qtdprepro) {
        this.qtdprepro = qtdprepro;
    }

    public Date getDtultpedido() {
        return dtultpedido;
    }

    public void setDtultpedido(Date dtultpedido) {
        this.dtultpedido = dtultpedido;
    }

    public Double getQtdpedido() {
        return qtdpedido;
    }

    public void setQtdpedido(Double qtdpedido) {
        this.qtdpedido = qtdpedido;
    }

    public String getUnidped() {
        return unidped;
    }

    public void setUnidped(String unidped) {
        this.unidped = unidped;
    }

    public Double getQtddescat() {
        return qtddescat;
    }

    public void setQtddescat(Double qtddescat) {
        this.qtddescat = qtddescat;
    }

    public Double getQtdminvenat() {
        return qtdminvenat;
    }

    public void setQtdminvenat(Double qtdminvenat) {
        this.qtdminvenat = qtdminvenat;
    }

    public Double getPrmaxcons() {
        return prmaxcons;
    }

    public void setPrmaxcons(Double prmaxcons) {
        this.prmaxcons = prmaxcons;
    }

    public Double getVlajucusto() {
        return vlajucusto;
    }

    public void setVlajucusto(Double vlajucusto) {
        this.vlajucusto = vlajucusto;
    }

    public String getUforigem() {
        return uforigem;
    }

    public void setUforigem(String uforigem) {
        this.uforigem = uforigem;
    }

    public Double getPcred() {
        return pcred;
    }

    public void setPcred(Double pcred) {
        this.pcred = pcred;
    }

    public Double getPerclucro() {
        return perclucro;
    }

    public void setPerclucro(Double perclucro) {
        this.perclucro = perclucro;
    }

    public Double getPfcp() {
        return pfcp;
    }

    public void setPfcp(Double pfcp) {
        this.pfcp = pfcp;
    }

    public Double getPfcpret() {
        return pfcpret;
    }

    public void setPfcpret(Double pfcpret) {
        this.pfcpret = pfcpret;
    }

    public Double getPst() {
        return pst;
    }

    public void setPst(Double pst) {
        this.pst = pst;
    }

    public Double getVbcfcpret() {
        return vbcfcpret;
    }

    public void setVbcfcpret(Double vbcfcpret) {
        this.vbcfcpret = vbcfcpret;
    }

    public Double getVbcicmsstret() {
        return vbcicmsstret;
    }

    public void setVbcicmsstret(Double vbcicmsstret) {
        this.vbcicmsstret = vbcicmsstret;
    }

    public Double getVfcpret() {
        return vfcpret;
    }

    public void setVfcpret(Double vfcpret) {
        this.vfcpret = vfcpret;
    }

    public Double getVicmsstret() {
        return vicmsstret;
    }

    public void setVicmsstret(Double vicmsstret) {
        this.vicmsstret = vicmsstret;
    }

    public String getNatRec() {
        return natRec;
    }

    public void setNatRec(String natRec) {
        this.natRec = natRec;
    }

    public String getCstipie() {
        return cstipie;
    }

    public void setCstipie(String cstipie) {
        this.cstipie = cstipie;
    }

    public String getCstpise() {
        return cstpise;
    }

    public void setCstpise(String cstpise) {
        this.cstpise = cstpise;
    }

    public String getCstcofinse() {
        return cstcofinse;
    }

    public void setCstcofinse(String cstcofinse) {
        this.cstcofinse = cstcofinse;
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
        hash += (tabprofilPK != null ? tabprofilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabprofil)) {
            return false;
        }
        Tabprofil other = (Tabprofil) object;
        if ((this.tabprofilPK == null && other.tabprofilPK != null) || (this.tabprofilPK != null && !this.tabprofilPK.equals(other.tabprofilPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.app.Tabprofil[ tabprofilPK=" + tabprofilPK + " ]";
    }

    @Override
    public Object getId() {
        return tabprofilPK;
    }

    @Override
    public void setId(Object object) {
        this.tabprofilPK = (TabprofilPK) object;
    }

}
