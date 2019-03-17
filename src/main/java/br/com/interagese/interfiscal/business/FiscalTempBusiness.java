package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.entity.Tabfil;
import java.util.Date;
import java.util.List;

public interface FiscalTempBusiness extends BusinessCrud<Fiscaltemp> {

    public List<Fiscaltemp> getListagemRelatorio(String codbar, Integer tipo, Date dataIncial, Date dataFinal);
    
    public List<Fiscaltemp> getCarregarFiscalTemp(Date inicioOperacao);
    
    public void getGerarTributos(List<Fiscaltemp> resultTemp,Tabfil filial);
    
    public void getRestoreTributacaoMixFiscal(List<Fiscaltemp> resultRestore);
}
