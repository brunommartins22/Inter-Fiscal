package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabfil;
import java.util.List;

public interface FireTabfilBusiness extends BusinessCrud<Tabfil> {
    public List<Tabfil> carregarFilialOnMixFiscal();

}
