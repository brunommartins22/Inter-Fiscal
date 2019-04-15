package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabfil;
import br.com.interagese.interfiscal.persistence.FireTabfildao;
import java.util.List;

public class FireTabfilBusinessBean extends AbstractBusinessCrud<Tabfil, FireTabfildao> implements FireTabfilBusiness {

    @Override
    public List<Tabfil> carregarFilialOnMixFiscal() {
        return getDao().carregarFilialOnMixFiscal();
    }

}
