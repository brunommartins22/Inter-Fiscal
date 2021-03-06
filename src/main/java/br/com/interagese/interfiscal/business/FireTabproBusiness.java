package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.SincronizadorTable;
import br.com.interagese.interfiscal.entity.Tabpro;
import java.util.List;

public interface FireTabproBusiness extends BusinessCrud<Tabpro> {

    public List<ImportacaoImp> getImportacaoImpostosAll(Integer codfil, Integer tipo) throws InstantiationException, IllegalAccessException;

    public List<ImportacaoImp> getImportacaoImpostosLimit(Integer codfil, Integer tipo) throws InstantiationException, IllegalAccessException;

    public Object countImportacao(Integer codfil, Integer tipo);

    public void getGerarEanbyCodigo();

    public List<ImportacaoImp> getProdutobyDescorCod(Object obj, Integer codfil, Integer tipo, Integer ordenar) throws InstantiationException, IllegalAccessException;

    public List<SincronizadorTable> getCarregarALL() throws InstantiationException, IllegalAccessException;

    public void updateAll(String codbarun, Double prvapro, Double pratpro);

}
