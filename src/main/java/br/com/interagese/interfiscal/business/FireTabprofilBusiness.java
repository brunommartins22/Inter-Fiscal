package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabprofil;
import java.util.List;

public interface FireTabprofilBusiness extends BusinessCrud<Tabprofil> {

    public List<Tabprofil> getListTabprofilByCodProduto(String codPro);

}
