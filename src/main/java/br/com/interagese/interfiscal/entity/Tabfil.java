/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "TABFIL")
@NamedQueries({
    @NamedQuery(name = "Tabfil.findAll", query = "SELECT t FROM Tabfil t")})
public class Tabfil implements Model {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFIL")
    private Integer codfil;
    @Column(name = "NOMFIL")
    private String nomfil;
    @Column(name = "ENDFIL")
    private String endfil;
    @Column(name = "BAIFIL")
    private String baifil;
    @Column(name = "CIDFIL")
    private String cidfil;
    @Column(name = "UFFIL")
    private String uffil;
    @Column(name = "CNPJFIL")
    private String cnpjfil;
    @Column(name = "IEFIL")
    private String iefil;
    @Column(name = "TELFIL")
    private String telfil;
    @Column(name = "RGCODUSU")
    private Integer rgcodusu;
    @Column(name = "RGUSUARIO")
    private String rgusuario;
    @Column(name = "RGDATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rgdata;
    @Column(name = "RGEVENTO")
    private String rgevento;
    @Column(name = "TPFILIAL")
    private String tpfilial;
    @Column(name = "CAMSERVER")
    private String camserver;
    @Column(name = "SITUACAO")
    private String situacao;
    @Column(name = "CAMTEMPOR")
    private String camtempor;
    @Column(name = "CAMTRANSM")
    private String camtransm;
    @Column(name = "CAMLOGIN")
    private String camlogin;
    @Column(name = "CONTACAIXA")
    private Integer contacaixa;
    @Column(name = "PLANOAVISTA")
    private Integer planoavista;
    @Column(name = "PLANOTITBX")
    private Integer planotitbx;
    @Column(name = "PLANOSANGS")
    private Integer planosangs;
    @Column(name = "PLANOSANGE")
    private Integer planosange;
    @Column(name = "CONTASANG")
    private Integer contasang;
    @Column(name = "PLANOVALE")
    private Integer planovale;
    @Column(name = "NRNOTA")
    private Integer nrnota;
    @Column(name = "FANFIL")
    private String fanfil;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "INSCMUN")
    private String inscmun;
    @Column(name = "CNAEFIL")
    private String cnaefil;
    @Column(name = "COMPFIL")
    private String compfil;
    @Column(name = "CEPFIL")
    private String cepfil;
    @Column(name = "NRLOTE")
    private Integer nrlote;
    @Column(name = "NRNFSCAN")
    private Integer nrnfscan;
    @Column(name = "NRNFSERIE2")
    private Integer nrnfserie2;
    @Column(name = "IESUBST")
    private String iesubst;
    @Column(name = "CFOPD")
    private String cfopd;
    @Column(name = "CFOPF")
    private String cfopf;
    @Column(name = "CFOPE")
    private String cfope;
    @Column(name = "CRT")
    private Character crt;
    @Column(name = "CONTFIL")
    private String contfil;
    @Column(name = "FAXFIL")
    private String faxfil;
    @Column(name = "EMAILSERVERFIL")
    private Character emailserverfil;
    @Column(name = "EMAILSMTPFIL")
    private String emailsmtpfil;
    @Column(name = "EMAILUSUARIOFIL")
    private String emailusuariofil;
    @Column(name = "SUFRAMA")
    private String suframa;
    @Column(name = "EMAILFIL")
    private String emailfil;
    @Column(name = "EMAILPORTA")
    private String emailporta;
    @Column(name = "EMAILSENHA")
    private String emailsenha;
    @Column(name = "TIPOCERTIF")
    private String tipocertif;
    @Column(name = "PATHSERIECERT")
    private String pathseriecert;
    @Column(name = "SENHACERT")
    private String senhacert;
    @Column(name = "ESCRIPI")
    private Character escripi;
    @Column(name = "ESCRSUBS")
    private Character escrsubs;
    @Column(name = "ESCRPIS")
    private Character escrpis;
    @Column(name = "ESCRCOFINS")
    private Character escrcofins;
    @Column(name = "CRTNAC")
    private Character crtnac;
    @Column(name = "REGFISC")
    private Character regfisc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PERCIMP")
    private Double percimp;
    @Column(name = "NOMECONT")
    private String nomecont;
    @Column(name = "CPFCONT")
    private String cpfcont;
    @Column(name = "CRCCONT")
    private String crccont;
    @Column(name = "CNPJCONT")
    private String cnpjcont;
    @Column(name = "CEPCONT")
    private String cepcont;
    @Column(name = "ENDERECOCONT")
    private String enderecocont;
    @Column(name = "NUMEROCONT")
    private String numerocont;
    @Column(name = "COMPLCONT")
    private String complcont;
    @Column(name = "BAIRROCONT")
    private String bairrocont;
    @Column(name = "FONECONT")
    private String fonecont;
    @Column(name = "FAXCONT")
    private String faxcont;
    @Column(name = "EMAILCONT")
    private String emailcont;
    @Column(name = "COD_MUNCONT")
    private String codMuncont;
    @Column(name = "FATORIRPJ")
    private Double fatorirpj;
    @Column(name = "FATORCSLL")
    private Double fatorcsll;
    @Column(name = "NRSERIE")
    private String nrserie;
    @Column(name = "PLANOCREDCLI")
    private Integer planocredcli;
//    @Column(name = "MTEOPT")
//    private String mteopt;
//    @Column(name = "DTENVSERV")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dtenvserv;
//    @Column(name = "CODSCANN")
//    private String codscann;
//    @Column(name = "SCANNATIVO")
//    private String scannativo;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabfil", fetch = FetchType.EAGER)
//    private List<Tabprofil> tabprofilList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabfil", fetch = FetchType.EAGER)
//    private List<Tabproimp> tabproimpList;

    public Tabfil() {
    }

    public Tabfil(Integer codfil) {
        this.codfil = codfil;
    }

    public Integer getCodfil() {
        return codfil;
    }

    public void setCodfil(Integer codfil) {
        this.codfil = codfil;
    }

    public String getNomfil() {
        return nomfil;
    }

    public void setNomfil(String nomfil) {
        this.nomfil = nomfil;
    }

    public String getEndfil() {
        return endfil;
    }

    public void setEndfil(String endfil) {
        this.endfil = endfil;
    }

    public String getBaifil() {
        return baifil;
    }

    public void setBaifil(String baifil) {
        this.baifil = baifil;
    }

    public String getCidfil() {
        return cidfil;
    }

    public void setCidfil(String cidfil) {
        this.cidfil = cidfil;
    }

    public String getUffil() {
        return uffil;
    }

    public void setUffil(String uffil) {
        this.uffil = uffil;
    }

    public String getCnpjfil() {
        return cnpjfil;
    }

    public void setCnpjfil(String cnpjfil) {
        this.cnpjfil = cnpjfil;
    }

    public String getIefil() {
        return iefil;
    }

    public void setIefil(String iefil) {
        this.iefil = iefil;
    }

    public String getTelfil() {
        return telfil;
    }

    public void setTelfil(String telfil) {
        this.telfil = telfil;
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

    public String getTpfilial() {
        return tpfilial;
    }

    public void setTpfilial(String tpfilial) {
        this.tpfilial = tpfilial;
    }

    public String getCamserver() {
        return camserver;
    }

    public void setCamserver(String camserver) {
        this.camserver = camserver;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCamtempor() {
        return camtempor;
    }

    public void setCamtempor(String camtempor) {
        this.camtempor = camtempor;
    }

    public String getCamtransm() {
        return camtransm;
    }

    public void setCamtransm(String camtransm) {
        this.camtransm = camtransm;
    }

    public String getCamlogin() {
        return camlogin;
    }

    public void setCamlogin(String camlogin) {
        this.camlogin = camlogin;
    }

    public Integer getContacaixa() {
        return contacaixa;
    }

    public void setContacaixa(Integer contacaixa) {
        this.contacaixa = contacaixa;
    }

    public Integer getPlanoavista() {
        return planoavista;
    }

    public void setPlanoavista(Integer planoavista) {
        this.planoavista = planoavista;
    }

    public Integer getPlanotitbx() {
        return planotitbx;
    }

    public void setPlanotitbx(Integer planotitbx) {
        this.planotitbx = planotitbx;
    }

    public Integer getPlanosangs() {
        return planosangs;
    }

    public void setPlanosangs(Integer planosangs) {
        this.planosangs = planosangs;
    }

    public Integer getPlanosange() {
        return planosange;
    }

    public void setPlanosange(Integer planosange) {
        this.planosange = planosange;
    }

    public Integer getContasang() {
        return contasang;
    }

    public void setContasang(Integer contasang) {
        this.contasang = contasang;
    }

    public Integer getPlanovale() {
        return planovale;
    }

    public void setPlanovale(Integer planovale) {
        this.planovale = planovale;
    }

    public Integer getNrnota() {
        return nrnota;
    }

    public void setNrnota(Integer nrnota) {
        this.nrnota = nrnota;
    }

    public String getFanfil() {
        return fanfil;
    }

    public void setFanfil(String fanfil) {
        this.fanfil = fanfil;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInscmun() {
        return inscmun;
    }

    public void setInscmun(String inscmun) {
        this.inscmun = inscmun;
    }

    public String getCnaefil() {
        return cnaefil;
    }

    public void setCnaefil(String cnaefil) {
        this.cnaefil = cnaefil;
    }

    public String getCompfil() {
        return compfil;
    }

    public void setCompfil(String compfil) {
        this.compfil = compfil;
    }

    public String getCepfil() {
        return cepfil;
    }

    public void setCepfil(String cepfil) {
        this.cepfil = cepfil;
    }

    public Integer getNrlote() {
        return nrlote;
    }

    public void setNrlote(Integer nrlote) {
        this.nrlote = nrlote;
    }

    public Integer getNrnfscan() {
        return nrnfscan;
    }

    public void setNrnfscan(Integer nrnfscan) {
        this.nrnfscan = nrnfscan;
    }

    public Integer getNrnfserie2() {
        return nrnfserie2;
    }

    public void setNrnfserie2(Integer nrnfserie2) {
        this.nrnfserie2 = nrnfserie2;
    }

    public String getIesubst() {
        return iesubst;
    }

    public void setIesubst(String iesubst) {
        this.iesubst = iesubst;
    }

    public String getCfopd() {
        return cfopd;
    }

    public void setCfopd(String cfopd) {
        this.cfopd = cfopd;
    }

    public String getCfopf() {
        return cfopf;
    }

    public void setCfopf(String cfopf) {
        this.cfopf = cfopf;
    }

    public String getCfope() {
        return cfope;
    }

    public void setCfope(String cfope) {
        this.cfope = cfope;
    }

    public Character getCrt() {
        return crt;
    }

    public void setCrt(Character crt) {
        this.crt = crt;
    }

    public String getContfil() {
        return contfil;
    }

    public void setContfil(String contfil) {
        this.contfil = contfil;
    }

    public String getFaxfil() {
        return faxfil;
    }

    public void setFaxfil(String faxfil) {
        this.faxfil = faxfil;
    }

    public Character getEmailserverfil() {
        return emailserverfil;
    }

    public void setEmailserverfil(Character emailserverfil) {
        this.emailserverfil = emailserverfil;
    }

    public String getEmailsmtpfil() {
        return emailsmtpfil;
    }

    public void setEmailsmtpfil(String emailsmtpfil) {
        this.emailsmtpfil = emailsmtpfil;
    }

    public String getEmailusuariofil() {
        return emailusuariofil;
    }

    public void setEmailusuariofil(String emailusuariofil) {
        this.emailusuariofil = emailusuariofil;
    }

    public String getSuframa() {
        return suframa;
    }

    public void setSuframa(String suframa) {
        this.suframa = suframa;
    }

    public String getEmailfil() {
        return emailfil;
    }

    public void setEmailfil(String emailfil) {
        this.emailfil = emailfil;
    }

    public String getEmailporta() {
        return emailporta;
    }

    public void setEmailporta(String emailporta) {
        this.emailporta = emailporta;
    }

    public String getEmailsenha() {
        return emailsenha;
    }

    public void setEmailsenha(String emailsenha) {
        this.emailsenha = emailsenha;
    }

    public String getTipocertif() {
        return tipocertif;
    }

    public void setTipocertif(String tipocertif) {
        this.tipocertif = tipocertif;
    }

    public String getPathseriecert() {
        return pathseriecert;
    }

    public void setPathseriecert(String pathseriecert) {
        this.pathseriecert = pathseriecert;
    }

    public String getSenhacert() {
        return senhacert;
    }

    public void setSenhacert(String senhacert) {
        this.senhacert = senhacert;
    }

    public Character getEscripi() {
        return escripi;
    }

    public void setEscripi(Character escripi) {
        this.escripi = escripi;
    }

    public Character getEscrsubs() {
        return escrsubs;
    }

    public void setEscrsubs(Character escrsubs) {
        this.escrsubs = escrsubs;
    }

    public Character getEscrpis() {
        return escrpis;
    }

    public void setEscrpis(Character escrpis) {
        this.escrpis = escrpis;
    }

    public Character getEscrcofins() {
        return escrcofins;
    }

    public void setEscrcofins(Character escrcofins) {
        this.escrcofins = escrcofins;
    }

    public Character getCrtnac() {
        return crtnac;
    }

    public void setCrtnac(Character crtnac) {
        this.crtnac = crtnac;
    }

    public Character getRegfisc() {
        return regfisc;
    }

    public void setRegfisc(Character regfisc) {
        this.regfisc = regfisc;
    }

    public Double getPercimp() {
        return percimp;
    }

    public void setPercimp(Double percimp) {
        this.percimp = percimp;
    }

    public String getNomecont() {
        return nomecont;
    }

    public void setNomecont(String nomecont) {
        this.nomecont = nomecont;
    }

    public String getCpfcont() {
        return cpfcont;
    }

    public void setCpfcont(String cpfcont) {
        this.cpfcont = cpfcont;
    }

    public String getCrccont() {
        return crccont;
    }

    public void setCrccont(String crccont) {
        this.crccont = crccont;
    }

    public String getCnpjcont() {
        return cnpjcont;
    }

    public void setCnpjcont(String cnpjcont) {
        this.cnpjcont = cnpjcont;
    }

    public String getCepcont() {
        return cepcont;
    }

    public void setCepcont(String cepcont) {
        this.cepcont = cepcont;
    }

    public String getEnderecocont() {
        return enderecocont;
    }

    public void setEnderecocont(String enderecocont) {
        this.enderecocont = enderecocont;
    }

    public String getNumerocont() {
        return numerocont;
    }

    public void setNumerocont(String numerocont) {
        this.numerocont = numerocont;
    }

    public String getComplcont() {
        return complcont;
    }

    public void setComplcont(String complcont) {
        this.complcont = complcont;
    }

    public String getBairrocont() {
        return bairrocont;
    }

    public void setBairrocont(String bairrocont) {
        this.bairrocont = bairrocont;
    }

    public String getFonecont() {
        return fonecont;
    }

    public void setFonecont(String fonecont) {
        this.fonecont = fonecont;
    }

    public String getFaxcont() {
        return faxcont;
    }

    public void setFaxcont(String faxcont) {
        this.faxcont = faxcont;
    }

    public String getEmailcont() {
        return emailcont;
    }

    public void setEmailcont(String emailcont) {
        this.emailcont = emailcont;
    }

    public String getCodMuncont() {
        return codMuncont;
    }

    public void setCodMuncont(String codMuncont) {
        this.codMuncont = codMuncont;
    }

    public Double getFatorirpj() {
        return fatorirpj;
    }

    public void setFatorirpj(Double fatorirpj) {
        this.fatorirpj = fatorirpj;
    }

    public Double getFatorcsll() {
        return fatorcsll;
    }

    public void setFatorcsll(Double fatorcsll) {
        this.fatorcsll = fatorcsll;
    }

    public String getNrserie() {
        return nrserie;
    }

    public void setNrserie(String nrserie) {
        this.nrserie = nrserie;
    }

    public Integer getPlanocredcli() {
        return planocredcli;
    }

    public void setPlanocredcli(Integer planocredcli) {
        this.planocredcli = planocredcli;
    }

//    public String getMteopt() {
//        return mteopt;
//    }
//
//    public void setMteopt(String mteopt) {
//        this.mteopt = mteopt;
//    }
////
//    public Date getDtenvserv() {
//        return dtenvserv;
//    }
//
//    public void setDtenvserv(Date dtenvserv) {
//        this.dtenvserv = dtenvserv;
//    }
//
//    public String getCodscann() {
//        return codscann;
//    }
//
//    public void setCodscann(String codscann) {
//        this.codscann = codscann;
//    }

//    public String getScannativo() {
//        return scannativo;
//    }
//
//    public void setScannativo(String scannativo) {
//        this.scannativo = scannativo;
//    }

//    public List<Tabprofil> getTabprofilList() {
//        return tabprofilList;
//    }
//
//    public void setTabprofilList(List<Tabprofil> tabprofilList) {
//        this.tabprofilList = tabprofilList;
//    }
//
//    public List<Tabproimp> getTabproimpList() {
//        return tabproimpList;
//    }
//
//    public void setTabproimpList(List<Tabproimp> tabproimpList) {
//        this.tabproimpList = tabproimpList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfil != null ? codfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabfil)) {
            return false;
        }
        Tabfil other = (Tabfil) object;
        if ((this.codfil == null && other.codfil != null) || (this.codfil != null && !this.codfil.equals(other.codfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codfil + " - " + nomfil;
    }

    @Override
    public Object getId() {
        return codfil;
    }

    @Override
    public void setId(Object object) {
        this.codfil = (Integer) object;
    }

}
