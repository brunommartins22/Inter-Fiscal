package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabproimp;
import br.com.interagese.interfiscal.persistence.TabproimpDao;

public class TabproimpBusinessBean extends AbstractBusinessCrud<Tabproimp, TabproimpDao> implements TabproimpBusiness {

    @Override
    public Tabproimp getTabproimpByCodPro(String codPro, String codFil) {
        return getDao().getTabproimpByCodPro(codPro, codFil);
    }

}
