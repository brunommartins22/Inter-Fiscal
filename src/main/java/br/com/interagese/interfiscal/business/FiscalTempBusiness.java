package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.ImpTemp;
import br.com.interagese.interfiscal.entity.RestoreImp;
import br.com.interagese.interfiscal.entity.Tabpro;
import java.util.Date;
import java.util.List;

public interface FiscalTempBusiness extends BusinessCrud<Fiscaltemp> {

    public List<Fiscaltemp> getListagemRelatorio(String codbar, Integer tipo, Date dataIncial, Date dataFinal);

    public List<Fiscaltemp> getCarregarFiscalTemp(Date inicioOperacao);

    public void getRestoreTributacaoMixFiscal(List<Fiscaltemp> resultRestore);

    public List<Tabpro> getListTabproByCod(Integer codPro);

    public List<ImpTemp> getExistTabproimp(String codpro);

    public List<ImpTemp> getExistTabproimpE(String codpro);

    public Integer getCountExistTabproimpE(String codpro, Integer codfil);
    
     public List<RestoreImp> getResultImpTemp(Integer codfil);
}
