package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.persistence.TabproDao;
import java.util.List;

public class TabproBusinessBean extends AbstractBusinessCrud<Tabpro, TabproDao> implements TabproBusiness {

    @Override
    public List<Tabpro> getProdutobyDescorCod(Object o) {
        return getDao().getProdutobyDescorCod(o);
    }

    @Override
    public Tabpro getProdutoByCod(Integer icodpro) {
        return getDao().getProdutoByCod(icodpro);
    }

    @Override
    public void getGerarEanbyCodigo() {
        getDao().getGerarEanbyCodigo();
    }
}
