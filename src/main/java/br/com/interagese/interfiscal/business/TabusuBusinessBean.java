package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabusu;
import br.com.interagese.interfiscal.persistence.Tabusudao;

public class TabusuBusinessBean extends AbstractBusinessCrud<Tabusu, Tabusudao> implements TabusuBusiness {

    @Override
    public boolean getValidationUser(String password) {
        return getDao().getValidationUser(password);
    }

}
