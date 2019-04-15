/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Martins
 */
public class ImpTemp {

    private Integer codfil;
    private String resultTpImpos;
    /**
     * tabproimp inserir nos 2 tpImpos=('A' && 'D') && tabproimpe
     */
    private boolean isInsertTemp;
    /**
     * tabproimp inserir nos 2 tpImpos=('A' && 'D') && tabproimpe
     */
    private boolean isUpdateTemp;
    /**
     * tabproimp - P(Apenas Pis/Cofins),I(Apenas Icms Saída) e PI(Ambos)
     */
    private String tpUpdate;
    /**
     * tabproimp
     */
    private String tpImpos;

    private Integer valorTpA;
    private Integer valorTpD;
    private Integer valorImpe;

    //**************************** Constructor *********************************
    public ImpTemp() {
        this.isInsertTemp = false;
        this.isUpdateTemp = false;
        resultTpImpos = "";
        this.tpUpdate = "";
        this.tpImpos = "";
        this.valorTpA = 0;
        this.valorTpD = 0;
        this.valorImpe = 0;

    }

    //**************************************************************************
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
     * @return the resultTpImpos
     */
    public String getResultTpImpos() {

        return resultTpImpos;
    }

    /**
     * @param resultTpImpos the resultTpImpos to set
     */
    public void setResultTpImpos(String resultTpImpos) {
        this.resultTpImpos = resultTpImpos;
    }

    /**
     * tabproimp inserir nos 2 tpImpos=('A' && 'D')
     *
     * @return the isInsertTemp
     */
    public boolean isIsInsertTemp() {
        return isInsertTemp;
    }

    /**
     * tabproimp inserir nos 2 tpImpos=('A' && 'D')
     *
     * @param isInsertTemp the isInsertTemp to set
     */
    public void setIsInsertTemp(boolean isInsertTemp) {
        this.isInsertTemp = isInsertTemp;
    }

    /**
     * tabproimp inserir nos 2 tpImpos=('A' && 'D')
     *
     * @return the isUpdateTemp
     */
    public boolean isIsUpdateTemp() {
        return isUpdateTemp;
    }

    /**
     * tabproimp inserir nos 2 tpImpos=('A' && 'D')
     *
     * @param isUpdateTemp the isUpdateTemp to set
     */
    public void setIsUpdateTemp(boolean isUpdateTemp) {
        this.isUpdateTemp = isUpdateTemp;
    }

    /**
     * tabproimp - P(Apenas Pis/Cofins),I(Apenas Icms Saída) e PI(Ambos)
     *
     * @return the tpUpdate
     */
    public String getTpUpdate() {
        return tpUpdate;
    }

    /**
     * tabproimp - P(Apenas Pis/Cofins),I(Apenas Icms Saída) e PI(Ambos)
     *
     * @param tpUpdate the tpUpdate to set
     */
    public void setTpUpdate(String tpUpdate) {
        this.tpUpdate = tpUpdate;
    }

    /**
     * tabproimp
     *
     * @return the tpImpos
     */
    public String getTpImpos() {
        return tpImpos;
    }

    /**
     * tabproimp
     *
     * @param tpImpos the tpImpos to set
     */
    public void setTpImpos(String tpImpos) {
        this.tpImpos = tpImpos;
    }

    /**
     * @return the valorTpA
     */
    public Integer getValorTpA() {
        return valorTpA;
    }

    /**
     * @param valorTpA the valorTpA to set
     */
    public void setValorTpA(Integer valorTpA) {
        this.valorTpA = valorTpA;
    }

    /**
     * @return the valorTpD
     */
    public Integer getValorTpD() {
        return valorTpD;
    }

    /**
     * @param valorTpD the valorTpD to set
     */
    public void setValorTpD(Integer valorTpD) {
        this.valorTpD = valorTpD;
    }

    /**
     * @return the valorImpe
     */
    public Integer getValorImpe() {
        return valorImpe;
    }

    /**
     * @param valorImpe the valorImpe to set
     */
    public void setValorImpe(Integer valorImpe) {
        this.valorImpe = valorImpe;
    }

}
