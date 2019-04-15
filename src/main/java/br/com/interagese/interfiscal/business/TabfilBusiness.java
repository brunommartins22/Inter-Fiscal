package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabfil;
import java.util.List;

public interface TabfilBusiness extends BusinessCrud<Tabfil> {
    public List<Tabfil> carregarFilialOnMixFiscal();

}
