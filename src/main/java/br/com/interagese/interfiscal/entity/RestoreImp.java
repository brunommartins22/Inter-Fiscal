/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

/**
 *
 * @author Bruno Martins
 */
public class RestoreImp {

    private String codpro;
    private String pisCst;
    private Double aliquotaPis;
    private String cofinsCst;
    private Double aliquotaCofins;

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public String getPisCst() {
        return pisCst;
    }

    public void setPisCst(String pisCst) {
        this.pisCst = pisCst;
    }

    public Double getAliquotaPis() {
        if (aliquotaPis == null) {
            aliquotaPis = 0.0;
        }
        return aliquotaPis;
    }

    public void setAliquotaPis(Double aliquotaPis) {
        this.aliquotaPis = aliquotaPis;
    }

    public String getCofinsCst() {
        return cofinsCst;
    }

    public void setCofinsCst(String cofinsCst) {
        this.cofinsCst = cofinsCst;
    }

    public Double getAliquotaCofins() {
        if (aliquotaCofins == null) {
            aliquotaCofins = 0.0;
        }
        return aliquotaCofins;
    }

    public void setAliquotaCofins(Double aliquotaCofins) {
        this.aliquotaCofins = aliquotaCofins;
    }
}
