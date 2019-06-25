/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bruno Martins
 */
public class SincronizadorTable {

    private Character stprod;
    private String codbarun;
    private Integer codfil;
    private String descpro;
    private Double pratpro;
    private Double prvapro;

    public String updateTabprofil() {
        String sql = "update tabprofil "
                + "set prvapro = '" + this.getPrvapro() + "',"
                + "pratpro = '" + this.getPratpro() + "',"
                + "rgdata = '" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "',"
                + "rgevento = '2' "
                + "where codpro in (select codpro from tabpro where codbarun ='" + this.getCodbarun() + "') and codfil = '2' ;";

        return sql;
    }

    public String getStprodDesc() {
        String result = "";
        if (stprod == null) {
            result = "";
        } else {
            result = stprod.toString().equals("A") ? "ATIVO" : "INATIVO";
        }
        return result;
    }

    /**
     * @return the codfil
     */
    public Integer getCodfil() {
        return codfil;
    }

    /**
     * @param codfil the codfil to set
     */
    public void setCodfil(Integer codfil) {
        this.codfil = codfil;
    }

    /**
     * @return the descpro
     */
    public String getDescpro() {
        return descpro;
    }

    /**
     * @param descpro the descpro to set
     */
    public void setDescpro(String descpro) {
        this.descpro = descpro;
    }

    /**
     * @return the pratpro
     */
    public Double getPratpro() {
        if (pratpro == null) {
            pratpro = 0.0;
        }
        return pratpro;
    }

    /**
     * @param pratpro the pratpro to set
     */
    public void setPratpro(Double pratpro) {
        this.pratpro = pratpro;
    }

    /**
     * @return the prvapro
     */
    public Double getPrvapro() {
        if (prvapro == null) {
            prvapro = 0.0;
        }
        return prvapro;
    }

    /**
     * @param prvapro the prvapro to set
     */
    public void setPrvapro(Double prvapro) {
        this.prvapro = prvapro;
    }

    /**
     * @return the codbarun
     */
    public String getCodbarun() {
        return codbarun;
    }

    /**
     * @param codbarun the codbarun to set
     */
    public void setCodbarun(String codbarun) {
        this.codbarun = codbarun;
    }

    /**
     * @return the stprod
     */
    public Character getStprod() {
        return stprod;
    }

    /**
     * @param stprod the stprod to set
     */
    public void setStprod(Character stprod) {
        this.stprod = stprod;
    }

}
