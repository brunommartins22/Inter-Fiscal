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
public class ImportacaoImp {

    private String tpImpos;
    private String nomeproduto;
    private String codigoproduto;
    private String codigobarra;
    private String genero;
    private String ncm;
    private String cfop;
    private String cest;
    private String cstIcmsEntrada;
    private Double aliquotaIcmsEntrada;
    private Double aliquotaSTIcmsEntrada;
    private Double rbcIcmsEntrada;
    private Double rbcSTIcmsEntrada;
    private String cstIcmsSaida;
    private Double aliquotaIcmsSaida;
    private Double aliquotaSTIcmsSaida;
    private Double rbcIcmsSaida;
    private Double rbcSTIcmsSaida;
    private String cstPisEntrada;
    private Double aliquotaPisEntrada;
    private String cstPisSaida;
    private Double aliquotaPisSaida;
    private String cstCofinsEntrada;
    private Double aliquotaCofinsEntrada;
    private String cstCofinsSaida;
    private Double aliquotaCofinsSaida;
    private String naturezaproduto;
    private Date dataAtualizacao;

    public String getTpImposDesc() {
        String text = "";
        if (tpImpos != null) {
            switch (tpImpos) {
                case "A": {
                    text = "NFE - Dentro do Estado";
                    break;
                }
                case "D": {
                    text = "NFCe - Consumidor Direto";
                    break;
                }
                default: {
                    text = " - ";
                }
            }
        }

        return text;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public String getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(String codBarra) {
        this.codigoproduto = codBarra;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getCstIcmsSaida() {
        return cstIcmsSaida;
    }

    public void setCstIcmsSaida(String cstIcmsSaida) {
        this.cstIcmsSaida = cstIcmsSaida;
    }

    public Double getAliquotaIcmsSaida() {
        if (aliquotaIcmsSaida == null) {
            aliquotaIcmsSaida = 0.0;
        }
        return aliquotaIcmsSaida;
    }

    public void setAliquotaIcmsSaida(Double aliquotaIcmsSaida) {
        this.aliquotaIcmsSaida = aliquotaIcmsSaida;
    }

    public String getCstPisEntrada() {

        return cstPisEntrada;
    }

    public void setCstPisEntrada(String cstPisEntrada) {
        this.cstPisEntrada = cstPisEntrada;
    }

    public String getCstPisSaida() {

        return cstPisSaida;
    }

    public void setCstPisSaida(String cstPisSaida) {
        this.cstPisSaida = cstPisSaida;
    }

    public String getCstCofinsEntrada() {

        return cstCofinsEntrada;
    }

    public void setCstCofinsEntrada(String cstCofinsEntrada) {
        this.cstCofinsEntrada = cstCofinsEntrada;
    }

    public String getCstCofinsSaida() {

        return cstCofinsSaida;
    }

    public void setCstCofinsSaida(String cstCofinsSaida) {
        this.cstCofinsSaida = cstCofinsSaida;
    }

    public String getNaturezaproduto() {
        return naturezaproduto;
    }

    public void setNaturezaproduto(String naturezaproduto) {
        this.naturezaproduto = naturezaproduto;
    }

    public String getCodigobarra() {
        return codigobarra;
    }

    public void setCodigobarra(String codigobarra) {
        this.codigobarra = codigobarra;
    }

    public Double getAliquotaPisSaida() {
        if (aliquotaPisSaida == null) {
            aliquotaCofinsSaida = 0.0;
        }
        return aliquotaPisSaida;
    }

    public void setAliquotaPisSaida(Double aliquotaPisSaida) {
        this.aliquotaPisSaida = aliquotaPisSaida;
    }

    public Double getAliquotaCofinsSaida() {
        if (aliquotaCofinsSaida == null) {
            aliquotaCofinsSaida = 0.0;
        }
        return aliquotaCofinsSaida;
    }

    public void setAliquotaCofinsSaida(Double aliquotaCofinsSaida) {
        this.aliquotaCofinsSaida = aliquotaCofinsSaida;
    }

    /**
     * @return the cstIcmsEntrada
     */
    public String getCstIcmsEntrada() {
        return cstIcmsEntrada;
    }

    /**
     * @param cstIcmsEntrada the cstIcmsEntrada to set
     */
    public void setCstIcmsEntrada(String cstIcmsEntrada) {
        this.cstIcmsEntrada = cstIcmsEntrada;
    }

    /**
     * @return the aliquotaIcmsEntrada
     */
    public Double getAliquotaIcmsEntrada() {
        if (aliquotaIcmsEntrada == null) {
            aliquotaIcmsEntrada = 0.0;
        }
        return aliquotaIcmsEntrada;
    }

    /**
     * @param aliquotaIcmsEntrada the aliquotaIcmsEntrada to set
     */
    public void setAliquotaIcmsEntrada(Double aliquotaIcmsEntrada) {
        this.aliquotaIcmsEntrada = aliquotaIcmsEntrada;
    }

    /**
     * @return the aliquotaPisEntrada
     */
    public Double getAliquotaPisEntrada() {
        if (aliquotaPisEntrada == null) {
            aliquotaPisEntrada = 0.0;
        }
        return aliquotaPisEntrada;
    }

    /**
     * @param aliquotaPisEntrada the aliquotaPisEntrada to set
     */
    public void setAliquotaPisEntrada(Double aliquotaPisEntrada) {
        this.aliquotaPisEntrada = aliquotaPisEntrada;
    }

    /**
     * @return the aliquotaCofinsEntrada
     */
    public Double getAliquotaCofinsEntrada() {
        if (aliquotaCofinsEntrada == null) {
            aliquotaCofinsEntrada = 0.0;
        }
        return aliquotaCofinsEntrada;
    }

    /**
     * @param aliquotaCofinsEntrada the aliquotaCofinsEntrada to set
     */
    public void setAliquotaCofinsEntrada(Double aliquotaCofinsEntrada) {
        this.aliquotaCofinsEntrada = aliquotaCofinsEntrada;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Double getAliquotaSTIcmsEntrada() {
        if (aliquotaSTIcmsEntrada == null) {
            aliquotaSTIcmsEntrada = 0.0;
        }
        return aliquotaSTIcmsEntrada;
    }

    public void setAliquotaSTIcmsEntrada(Double aliquotaSTIcmsEntrada) {
        this.aliquotaSTIcmsEntrada = aliquotaSTIcmsEntrada;
    }

    public Double getRbcIcmsEntrada() {
        if (rbcIcmsEntrada == null) {
            rbcIcmsEntrada = 0.0;
        }
        return rbcIcmsEntrada;
    }

    public void setRbcIcmsEntrada(Double rbcIcmsEntrada) {
        this.rbcIcmsEntrada = rbcIcmsEntrada;
    }

    public Double getRbcSTIcmsEntrada() {
        if (rbcSTIcmsEntrada == null) {
            rbcSTIcmsEntrada = 0.0;
        }
        return rbcSTIcmsEntrada;
    }

    public void setRbcSTIcmsEntrada(Double rbcSTIcmsEntrada) {
        this.rbcSTIcmsEntrada = rbcSTIcmsEntrada;
    }

    public Double getAliquotaSTIcmsSaida() {
        if (aliquotaSTIcmsSaida == null) {
            aliquotaSTIcmsSaida = 0.0;
        }
        return aliquotaSTIcmsSaida;
    }

    public void setAliquotaSTIcmsSaida(Double aliquotaSTIcmsSaida) {
        this.aliquotaSTIcmsSaida = aliquotaSTIcmsSaida;
    }

    public Double getRbcIcmsSaida() {
        if (rbcIcmsSaida == null) {
            rbcIcmsSaida = 0.0;
        }
        return rbcIcmsSaida;
    }

    public void setRbcIcmsSaida(Double rbcIcmsSaida) {
        this.rbcIcmsSaida = rbcIcmsSaida;
    }

    public Double getRbcSTIcmsSaida() {
        if (rbcSTIcmsSaida == null) {
            rbcSTIcmsSaida = 0.0;
        }
        return rbcSTIcmsSaida;
    }

    public void setRbcSTIcmsSaida(Double rbcSTIcmsSaida) {
        this.rbcSTIcmsSaida = rbcSTIcmsSaida;
    }

    /**
     * @return the tpImpos
     */
    public String getTpImpos() {
        return tpImpos;
    }

    /**
     * @param tpImpos the tpImpos to set
     */
    public void setTpImpos(String tpImpos) {
        this.tpImpos = tpImpos;
    }
}
