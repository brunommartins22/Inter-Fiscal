package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabfil;
import br.com.interagese.interfiscal.persistence.FireTabfildao;
import br.com.interagese.interfiscal.persistence.Tabfildao;
import java.util.List;

public class TabfilBusinessBean extends AbstractBusinessCrud<Tabfil, Tabfildao> implements TabfilBusiness {

    @Override
    public List<Tabfil> carregarFilialOnMixFiscal() {
        return getDao().carregarFilialOnMixFiscal();
    }

}
