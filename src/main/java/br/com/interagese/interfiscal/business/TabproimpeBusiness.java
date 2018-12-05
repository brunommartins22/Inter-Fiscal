package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabproimpe;

public interface TabproimpeBusiness extends BusinessCrud<Tabproimpe> {

   public Tabproimpe getTabproimpeByCodPro(Integer codProduto, Integer codFilial);

}
