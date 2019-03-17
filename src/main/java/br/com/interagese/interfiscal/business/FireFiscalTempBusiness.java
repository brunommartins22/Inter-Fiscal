package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.entity.Piscofins;
import java.util.Date;
import java.util.List;

public interface FireFiscalTempBusiness extends BusinessCrud<Fiscaltemp> {

    public List<Fiscaltemp> getListagemRelatorio(String codbar, Integer tipo, Date dataIncial, Date dataFinal);
    
    public List<Fiscaltemp> getCarregarFiscalTemp(List<Piscofins> pcs, List<IcmsEntrada> ies, List<IcmsSaida> iss);
    
    public void getGerarTributos(List<Fiscaltemp> resultTemp);
    
    public void getRestoreTributacaoMixFiscal(List<Fiscaltemp> resultRestore);
}
