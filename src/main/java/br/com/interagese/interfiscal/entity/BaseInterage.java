/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.entity;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bruno
 */
public class BaseInterage {

    private String codProduto;
    private String nmProduto;
    private String codBarras;
    private boolean pisCofins;
    private boolean icmsEntrada;
    private boolean icmsSaida;
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoPisCofins;
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoIcmsEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoIcmsSaida;

    /**
     * @return the codProduto
     */
    public String getCodProduto() {
        return codProduto;
    }

    /**
     * @param codProduto the codProduto to set
     */
    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    /**
     * @return the nmProduto
     */
    public String getNmProduto() {
        return nmProduto;
    }

    /**
     * @param nmProduto the nmProduto to set
     */
    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    /**
     * @return the codBarras
     */
    public String getCodBarras() {
        return codBarras;
    }

    /**
     * @param codBarras the codBarras to set
     */
    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    /**
     * @return the pisCofins
     */
    public boolean isPisCofins() {
        return pisCofins;
    }

    /**
     * @param pisCofins the pisCofins to set
     */
    public void setPisCofins(boolean pisCofins) {
        this.pisCofins = pisCofins;
    }

    /**
     * @return the icmsEntrada
     */
    public boolean isIcmsEntrada() {
        return icmsEntrada;
    }

    /**
     * @param icmsEntrada the icmsEntrada to set
     */
    public void setIcmsEntrada(boolean icmsEntrada) {
        this.icmsEntrada = icmsEntrada;
    }

    /**
     * @return the icmsSaida
     */
    public boolean isIcmsSaida() {
        return icmsSaida;
    }

    /**
     * @param icmsSaida the icmsSaida to set
     */
    public void setIcmsSaida(boolean icmsSaida) {
        this.icmsSaida = icmsSaida;
    }

    /**
     * @return the atualizacaoPisCofins
     */
    public Date getAtualizacaoPisCofins() {
        return atualizacaoPisCofins;
    }

    /**
     * @param atualizacaoPisCofins the atualizacaoPisCofins to set
     */
    public void setAtualizacaoPisCofins(Date atualizacaoPisCofins) {
        this.atualizacaoPisCofins = atualizacaoPisCofins;
    }

    /**
     * @return the atualizacaoIcmsEntrada
     */
    public Date getAtualizacaoIcmsEntrada() {
        return atualizacaoIcmsEntrada;
    }

    /**
     * @param atualizacaoIcmsEntrada the atualizacaoIcmsEntrada to set
     */
    public void setAtualizacaoIcmsEntrada(Date atualizacaoIcmsEntrada) {
        this.atualizacaoIcmsEntrada = atualizacaoIcmsEntrada;
    }

    /**
     * @return the atualizacaoIcmsSaida
     */
    public Date getAtualizacaoIcmsSaida() {
        return atualizacaoIcmsSaida;
    }

    /**
     * @param atualizacaoIcmsSaida the atualizacaoIcmsSaida to set
     */
    public void setAtualizacaoIcmsSaida(Date atualizacaoIcmsSaida) {
        this.atualizacaoIcmsSaida = atualizacaoIcmsSaida;
    }



}
