package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Piscofins;
import br.com.interagese.interfiscal.persistence.piscofinsDao;

public class PisCofinsBusinessBean extends AbstractBusinessCrud<Piscofins, piscofinsDao> implements PisCofinsBusiness {

 

    @Override
    public void deleteAll() {
    getDao().deleteAll();
    }

}
