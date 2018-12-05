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
public class ImportacaoImp {

    private String nomeproduto;
    private String codigoproduto;
    private String codigobarra;
    private String genero;
    private String ncm;
    private String cfop;
    private String cest;
    private String cst;
    private Double icmsSaida;
    private String pisentrada;
    private String pissaida;
    private String cofinsentrada;
    private String cofinssaida;
    private String naturezaproduto;
    private Double aliquotaPis;
    private Double aliquotaCofins;

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

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public Double getIcmsSaida() {
        if (icmsSaida == null) {
            icmsSaida = 0.0;
        }
        return icmsSaida;
    }

    public void setIcmsSaida(Double icmsSaida) {
        this.icmsSaida = icmsSaida;
    }

    public String getPisentrada() {
       
        return pisentrada;
    }

    public void setPisentrada(String pisentrada) {
        this.pisentrada = pisentrada;
    }

    public String getPissaida() {
        
        return pissaida;
    }

    public void setPissaida(String pissaida) {
        this.pissaida = pissaida;
    }

    public String getCofinsentrada() {
       
        return cofinsentrada;
    }

    public void setCofinsentrada(String cofinsentrada) {
        this.cofinsentrada = cofinsentrada;
    }

    public String getCofinssaida() {
      
        return cofinssaida;
    }

    public void setCofinssaida(String cofinssaida) {
        this.cofinssaida = cofinssaida;
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

    public Double getAliquotaPis() {
        if (aliquotaPis == null) {
            aliquotaCofins = 0.0;
        }
        return aliquotaPis;
    }

    public void setAliquotaPis(Double aliquotaPis) {
        this.aliquotaPis = aliquotaPis;
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
