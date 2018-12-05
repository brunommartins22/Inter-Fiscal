package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabproimp;
import br.com.interagese.interfiscal.persistence.FireTabproimpDao;

public class FireTabproimpBusinessBean extends AbstractBusinessCrud<Tabproimp, FireTabproimpDao> implements FireTabproimpBusiness {

    @Override
    public Tabproimp getTabproimpByCodPro(String codPro, String codFil) {
        return getDao().getTabproimpByCodPro(codPro, codFil);
    }

}
