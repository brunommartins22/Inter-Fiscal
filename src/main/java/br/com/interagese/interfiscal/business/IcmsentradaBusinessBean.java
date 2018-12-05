package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.IcmsEntrada;
import br.com.interagese.interfiscal.persistence.icmsentradaDao;

public class IcmsentradaBusinessBean extends AbstractBusinessCrud<IcmsEntrada, icmsentradaDao> implements IcmsentradaBusiness {


    @Override
    public void deleteAll() {

        getDao().deleteAll();

    }

}
