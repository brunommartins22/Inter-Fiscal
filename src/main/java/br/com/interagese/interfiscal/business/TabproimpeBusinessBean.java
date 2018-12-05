package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabproimpe;
import br.com.interagese.interfiscal.persistence.TabproimpeDao;

public class TabproimpeBusinessBean extends AbstractBusinessCrud<Tabproimpe, TabproimpeDao> implements TabproimpeBusiness {
    
    @Override
    public Tabproimpe getTabproimpeByCodPro(Integer codProduto, Integer codFilial) {
        return getDao().getTabproimpeByCodPro(codProduto, codFilial);
    }
    
}
