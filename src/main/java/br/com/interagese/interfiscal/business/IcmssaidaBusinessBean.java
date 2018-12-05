package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.IcmsSaida;
import br.com.interagese.interfiscal.persistence.icmssaidaDao;

public class IcmssaidaBusinessBean extends AbstractBusinessCrud<IcmsSaida, icmssaidaDao> implements IcmssaidaBusiness {

    
    @Override
    public void deleteAll(){
        getDao().deleteAll();
    }
}
