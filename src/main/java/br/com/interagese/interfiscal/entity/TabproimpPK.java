/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bruno Martins
 */
@Embeddable
public class TabproimpPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codpro",updatable = false)
    private String codpro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codfil",updatable = false)
    private int codfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tpimpos",updatable = false)
    private String tpimpos;

    public TabproimpPK() {
    }

    public TabproimpPK(String codpro, int codfil, String tpimpos) {
        this.codpro = codpro;
        this.codfil = codfil;
        this.tpimpos = tpimpos;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public int getCodfil() {
        return codfil;
    }

    public void setCodfil(int codfil) {
        this.codfil = codfil;
    }

    public String getTpimpos() {
        return tpimpos;
    }

    public void setTpimpos(String tpimpos) {
        this.tpimpos = tpimpos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpro != null ? codpro.hashCode() : 0);
        hash += (int) codfil;
        hash += (tpimpos != null ? tpimpos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabproimpPK)) {
            return false;
        }
        TabproimpPK other = (TabproimpPK) object;
        if ((this.codpro == null && other.codpro != null) || (this.codpro != null && !this.codpro.equals(other.codpro))) {
            return false;
        }
        if (this.codfil != other.codfil) {
            return false;
        }
        if ((this.tpimpos == null && other.tpimpos != null) || (this.tpimpos != null && !this.tpimpos.equals(other.tpimpos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.app.TabproimpPK[ codpro=" + codpro + ", codfil=" + codfil + ", tpimpos=" + tpimpos + " ]";
    }
    
}
