package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabproimpe;
import br.com.interagese.interfiscal.persistence.FireTabproimpeDao;

public class FireTabproimpeBusinessBean extends AbstractBusinessCrud<Tabproimpe, FireTabproimpeDao> implements FireTabproimpeBusiness {
    
    @Override
    public Tabproimpe getTabproimpeByCodPro(Integer codProduto, Integer codFilial) {
        return getDao().getTabproimpeByCodPro(codProduto, codFilial);
    }
    
}
