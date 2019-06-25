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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bruno Martins
 */
@Entity
@Table(name = "tabncm")
@NamedQueries({
    @NamedQuery(name = "Tabncm.findAll", query = "SELECT t FROM Tabncm t")})
public class Tabncm implements Model {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codncm")
    private String codncm;
    @Size(max = 120)
    @Column(name = "nomncm")
    private String nomncm;
    @Size(max = 2)
    @Column(name = "unincm")
    private String unincm;
    @Column(name = "rgcodusu")
    private Integer rgcodusu;
    @Size(max = 20)
    @Column(name = "rgusuario")
    private String rgusuario;
    @Column(name = "rgdata")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rgdata;
    @Column(name = "rgevento")
    private Character rgevento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "indnacio")
    private Double indnacio;
    @Column(name = "indimport")
    private Double indimport;
    @Size(max = 4)
    @Column(name = "cfopd")
    private String cfopd;
    @Size(max = 4)
    @Column(name = "cfopf")
    private String cfopf;
    @Size(max = 4)
    @Column(name = "cfope")
    private String cfope;
    @Size(max = 8)
    @Column(name = "cest")
    private String cest;
    @Size(max = 512)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 8)
    @Column(name = "cfopc")
    private String cfopc;
    @Size(max = 2)
    @Column(name = "indecf")
    private String indecf;
    @Column(name = "percicms")
    private Double percicms;
    @Column(name = "substrib")
    private Character substrib;
    @Column(name = "perclucro")
    private Double perclucro;
    @Column(name = "tribmono")
    private Character tribmono;
    @Column(name = "fatorpis")
    private Double fatorpis;
    @Column(name = "fatorcofins")
    private Double fatorcofins;
    @Size(max = 3)
    @Column(name = "aicmscst")
    private String aicmscst;
    @Size(max = 3)
    @Column(name = "bicmscst")
    private String bicmscst;
    @Size(max = 3)
    @Column(name = "cicmscst")
    private String cicmscst;
    @Size(max = 3)
    @Column(name = "dicmscst")
    private String dicmscst;
    @Size(max = 3)
    @Column(name = "apiscst")
    private String apiscst;
    @Size(max = 3)
    @Column(name = "bpiscst")
    private String bpiscst;
    @Size(max = 3)
    @Column(name = "cpiscst")
    private String cpiscst;
    @Size(max = 3)
    @Column(name = "dpiscst")
    private String dpiscst;
    @Size(max = 3)
    @Column(name = "acofcst")
    private String acofcst;
    @Size(max = 3)
    @Column(name = "bcofcst")
    private String bcofcst;
    @Size(max = 3)
    @Column(name = "ccofcst")
    private String ccofcst;
    @Size(max = 3)
    @Column(name = "dcofcst")
    private String dcofcst;
    @Size(max = 8)
    @Column(name = "cfopdt")
    private String cfopdt;
    @Size(max = 8)
    @Column(name = "cfopft")
    private String cfopft;
    @Size(max = 8)
    @Column(name = "cfopet")
    private String cfopet;
    @Size(max = 8)
    @Column(name = "cfopct")
    private String cfopct;
    @Size(max = 3)
    @Column(name = "tcstsai")
    private String tcstsai;
    @Size(max = 8)
    @Column(name = "cfopt")
    private String cfopt;
    @Size(max = 2)
    @Column(name = "tpiscst")
    private String tpiscst;
    @Size(max = 2)
    @Column(name = "tcofinscst")
    private String tcofinscst;
    @Size(max = 2)
    @Column(name = "icstsai")
    private String icstsai;
    @Size(max = 8)
    @Column(name = "cfopi")
    private String cfopi;
    @Size(max = 2)
    @Column(name = "ipiscst")
    private String ipiscst;
    @Size(max = 2)
    @Column(name = "icofinscst")
    private String icofinscst;
    @Column(name = "dtenvserv")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtenvserv;

    public Tabncm() {
    }

    public Tabncm(String codncm) {
        this.codncm = codncm;
    }

    public String getCodncm() {
        return codncm;
    }

    public void setCodncm(String codncm) {
        this.codncm = codncm;
    }

    public String getNomncm() {
        return nomncm;
    }

    public void setNomncm(String nomncm) {
        this.nomncm = nomncm;
    }

    public String getUnincm() {
        return unincm;
    }

    public void setUnincm(String unincm) {
        this.unincm = unincm;
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

    public Character getRgevento() {
        return rgevento;
    }

    public void setRgevento(Character rgevento) {
        this.rgevento = rgevento;
    }

    public Double getIndnacio() {
        return indnacio;
    }

    public void setIndnacio(Double indnacio) {
        this.indnacio = indnacio;
    }

    public Double getIndimport() {
        return indimport;
    }

    public void setIndimport(Double indimport) {
        this.indimport = indimport;
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

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCfopc() {
        return cfopc;
    }

    public void setCfopc(String cfopc) {
        this.cfopc = cfopc;
    }

    public String getIndecf() {
        return indecf;
    }

    public void setIndecf(String indecf) {
        this.indecf = indecf;
    }

    public Double getPercicms() {
        return percicms;
    }

    public void setPercicms(Double percicms) {
        this.percicms = percicms;
    }

    public Character getSubstrib() {
        return substrib;
    }

    public void setSubstrib(Character substrib) {
        this.substrib = substrib;
    }

    public Double getPerclucro() {
        return perclucro;
    }

    public void setPerclucro(Double perclucro) {
        this.perclucro = perclucro;
    }

    public Character getTribmono() {
        return tribmono;
    }

    public void setTribmono(Character tribmono) {
        this.tribmono = tribmono;
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

    public String getAicmscst() {
        return aicmscst;
    }

    public void setAicmscst(String aicmscst) {
        this.aicmscst = aicmscst;
    }

    public String getBicmscst() {
        return bicmscst;
    }

    public void setBicmscst(String bicmscst) {
        this.bicmscst = bicmscst;
    }

    public String getCicmscst() {
        return cicmscst;
    }

    public void setCicmscst(String cicmscst) {
        this.cicmscst = cicmscst;
    }

    public String getDicmscst() {
        return dicmscst;
    }

    public void setDicmscst(String dicmscst) {
        this.dicmscst = dicmscst;
    }

    public String getApiscst() {
        return apiscst;
    }

    public void setApiscst(String apiscst) {
        this.apiscst = apiscst;
    }

    public String getBpiscst() {
        return bpiscst;
    }

    public void setBpiscst(String bpiscst) {
        this.bpiscst = bpiscst;
    }

    public String getCpiscst() {
        return cpiscst;
    }

    public void setCpiscst(String cpiscst) {
        this.cpiscst = cpiscst;
    }

    public String getDpiscst() {
        return dpiscst;
    }

    public void setDpiscst(String dpiscst) {
        this.dpiscst = dpiscst;
    }

    public String getAcofcst() {
        return acofcst;
    }

    public void setAcofcst(String acofcst) {
        this.acofcst = acofcst;
    }

    public String getBcofcst() {
        return bcofcst;
    }

    public void setBcofcst(String bcofcst) {
        this.bcofcst = bcofcst;
    }

    public String getCcofcst() {
        return ccofcst;
    }

    public void setCcofcst(String ccofcst) {
        this.ccofcst = ccofcst;
    }

    public String getDcofcst() {
        return dcofcst;
    }

    public void setDcofcst(String dcofcst) {
        this.dcofcst = dcofcst;
    }

    public String getCfopdt() {
        return cfopdt;
    }

    public void setCfopdt(String cfopdt) {
        this.cfopdt = cfopdt;
    }

    public String getCfopft() {
        return cfopft;
    }

    public void setCfopft(String cfopft) {
        this.cfopft = cfopft;
    }

    public String getCfopet() {
        return cfopet;
    }

    public void setCfopet(String cfopet) {
        this.cfopet = cfopet;
    }

    public String getCfopct() {
        return cfopct;
    }

    public void setCfopct(String cfopct) {
        this.cfopct = cfopct;
    }

    public String getTcstsai() {
        return tcstsai;
    }

    public void setTcstsai(String tcstsai) {
        this.tcstsai = tcstsai;
    }

    public String getCfopt() {
        return cfopt;
    }

    public void setCfopt(String cfopt) {
        this.cfopt = cfopt;
    }

    public String getTpiscst() {
        return tpiscst;
    }

    public void setTpiscst(String tpiscst) {
        this.tpiscst = tpiscst;
    }

    public String getTcofinscst() {
        return tcofinscst;
    }

    public void setTcofinscst(String tcofinscst) {
        this.tcofinscst = tcofinscst;
    }

    public String getIcstsai() {
        return icstsai;
    }

    public void setIcstsai(String icstsai) {
        this.icstsai = icstsai;
    }

    public String getCfopi() {
        return cfopi;
    }

    public void setCfopi(String cfopi) {
        this.cfopi = cfopi;
    }

    public String getIpiscst() {
        return ipiscst;
    }

    public void setIpiscst(String ipiscst) {
        this.ipiscst = ipiscst;
    }

    public String getIcofinscst() {
        return icofinscst;
    }

    public void setIcofinscst(String icofinscst) {
        this.icofinscst = icofinscst;
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
        hash += (codncm != null ? codncm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabncm)) {
            return false;
        }
        Tabncm other = (Tabncm) object;
        if ((this.codncm == null && other.codncm != null) || (this.codncm != null && !this.codncm.equals(other.codncm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.entity.Tabncm[ codncm=" + codncm + " ]";
    }

    @Override
    public Object getId() {
        return codncm;
    }

    @Override
    public void setId(Object object) {
        this.codncm = object.toString();
    }

}
