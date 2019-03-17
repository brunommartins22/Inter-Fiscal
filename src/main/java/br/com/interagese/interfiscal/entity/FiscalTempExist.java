/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

/**
 *
 * @author bruno
 */
public class FiscalTempExist {
    private Fiscaltemp fiscaltemp;
    private boolean existInList;

    /**
     * @return the fiscaltemp
     */
    public Fiscaltemp getFiscaltemp() {
        return fiscaltemp;
    }

    /**
     * @param fiscaltemp the fiscaltemp to set
     */
    public void setFiscaltemp(Fiscaltemp fiscaltemp) {
        this.fiscaltemp = fiscaltemp;
    }

    /**
     * @return the existInList
     */
    public boolean isExistInList() {
        return existInList;
    }

    /**
     * @param existInList the existInList to set
     */
    public void setExistInList(boolean existInList) {
        this.existInList = existInList;
    }
    
}
