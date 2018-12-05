package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabprofil;
import br.com.interagese.interfiscal.persistence.TabprofilDao;
import java.util.List;

public class TabprofilBusinessBean extends AbstractBusinessCrud<Tabprofil, TabprofilDao> implements TabprofilBusiness {

    @Override
    public List<Tabprofil> getListTabprofilByCodProduto(String codPro) {
        return getDao().getListTabprofilByCodProduto(codPro);
    }

}
