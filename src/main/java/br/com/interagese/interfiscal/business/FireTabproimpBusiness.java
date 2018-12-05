package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabproimp;

public interface FireTabproimpBusiness extends BusinessCrud<Tabproimp> {

    public Tabproimp getTabproimpByCodPro(String codPro, String codFil);

}
