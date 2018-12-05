package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabprofil;
import br.com.interagese.interfiscal.persistence.FireTabprofilDao;
import java.util.List;

public class FireTabprofilBusinessBean extends AbstractBusinessCrud<Tabprofil, FireTabprofilDao> implements FireTabprofilBusiness {

    @Override
    public List<Tabprofil> getListTabprofilByCodProduto(String codPro) {
        return getDao().getListTabprofilByCodProduto(codPro);
    }

}
