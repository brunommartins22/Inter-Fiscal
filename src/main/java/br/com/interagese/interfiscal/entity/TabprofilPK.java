
package br.com.interagese.interfiscal.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable

public class TabprofilPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CODPRO")
    private String codpro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODFIL")
    private int codfil;

   

    public TabprofilPK() {
    }

    public TabprofilPK(String codpro, int codfil) {
        this.codpro = codpro;
        this.codfil = codfil;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public Integer getCodfil() {
        return codfil;
    }

    public void setCodfil(Integer codfil) {
        this.codfil = codfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpro != null ? codpro.hashCode() : 0);
        hash += (int) codfil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabprofilPK)) {
            return false;
        }
        TabprofilPK other = (TabprofilPK) object;
        if ((this.codpro == null && other.codpro != null) || (this.codpro != null && !this.codpro.equals(other.codpro))) {
            return false;
        }
        return this.codfil == other.codfil;
    }

    @Override
    public String toString() {
        return "br.com.interagese.transmissornfe.business.entity.TabprofilPK[ codpro=" + codpro + ", codfil=" + codfil + " ]";
    }

  

    public void setCodfil(int codfil) {
        this.codfil = codfil;
    }

   
}
