/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.util.Date;

/**
 *
 * @author bruno
 */
public class Log {

    private Date dataLog;
    private Integer qtdPisConfins;
    private Integer qtdIcmsEntrada;
    private Integer qtdIcmsSaida;
    private String error;

    public Log() {
        dataLog = new Date();
    }

    /**
     * @return the dataLog
     */
    public Date getDataLog() {
        return dataLog;
    }

    /**
     * @param dataLog the dataLog to set
     */
    public void setDataLog(Date dataLog) {
        this.dataLog = dataLog;
    }

    /**
     * @return the qtdPisConfins
     */
    public Integer getQtdPisConfins() {
        return qtdPisConfins;
    }

    /**
     * @param qtdPisConfins the qtdPisConfins to set
     */
    public void setQtdPisConfins(Integer qtdPisConfins) {
        this.qtdPisConfins = qtdPisConfins;
    }

    /**
     * @return the qtdIcmsEntrada
     */
    public Integer getQtdIcmsEntrada() {
        return qtdIcmsEntrada;
    }

    /**
     * @param qtdIcmsEntrada the qtdIcmsEntrada to set
     */
    public void setQtdIcmsEntrada(Integer qtdIcmsEntrada) {
        this.qtdIcmsEntrada = qtdIcmsEntrada;
    }

    /**
     * @return the qtdIcmsSaida
     */
    public Integer getQtdIcmsSaida() {
        return qtdIcmsSaida;
    }

    /**
     * @param qtdIcmsSaida the qtdIcmsSaida to set
     */
    public void setQtdIcmsSaida(Integer qtdIcmsSaida) {
        this.qtdIcmsSaida = qtdIcmsSaida;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
