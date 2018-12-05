package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabpro;
import java.util.List;

public interface TabproBusiness extends BusinessCrud<Tabpro> {

    public List<Tabpro> getProdutobyDescorCod(Object o);

    public Tabpro getProdutoByCod(Integer icodpro);

    public void getGerarEanbyCodigo();
}
