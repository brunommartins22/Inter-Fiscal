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
public class TabproimpePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_produto",updatable = false)
    private String codigoProduto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_filial",updatable = false)
    private int codigoFilial;

    public TabproimpePK() {
    }

    public TabproimpePK(String codigoProduto, int codigoFilial) {
        this.codigoProduto = codigoProduto;
        this.codigoFilial = codigoFilial;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoProduto != null ? codigoProduto.hashCode() : 0);
        hash += (int) codigoFilial;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabproimpePK)) {
            return false;
        }
        TabproimpePK other = (TabproimpePK) object;
        if ((this.codigoProduto == null && other.codigoProduto != null) || (this.codigoProduto != null && !this.codigoProduto.equals(other.codigoProduto))) {
            return false;
        }
        if (this.codigoFilial != other.codigoFilial) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.interagese.interfiscal.app.TabproimpePK[ codigoProduto=" + codigoProduto + ", codigoFilial=" + codigoFilial + " ]";
    }
    
}
