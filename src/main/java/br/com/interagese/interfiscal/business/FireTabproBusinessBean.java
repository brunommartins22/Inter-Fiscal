package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.entity.Tabprofil;
import br.com.interagese.interfiscal.entity.Tabproimp;
import br.com.interagese.interfiscal.persistence.FireTabproDao;
import java.util.List;
import javax.persistence.TypedQuery;

public class FireTabproBusinessBean extends AbstractBusinessCrud<Tabpro, FireTabproDao> implements FireTabproBusiness {

    @Override
    public List<ImportacaoImp> getImportacaoImpostosAll(Integer codfil,Integer tipo) {
        return getDao().getImportacaoImpostosAll(codfil,tipo);
    }

    @Override
    public List<ImportacaoImp> getImportacaoImpostosLimit(Integer codfil,Integer tipo) {
        return getDao().getImportacaoImpostosLimit(codfil,tipo);
    }

    @Override
    public Object countImportacao(Integer codfil,Integer tipo) {
        return getDao().countImportacao(codfil,tipo);
    }

    @Override
    public void getGerarEanbyCodigo() {
        getDao().getGerarEanbyCodigo();
    }

    @Override
    public List<ImportacaoImp> getProdutobyDescorCod(Object obj, Integer codfil, Integer tipo) {
        return getDao().getProdutobyDescorCod(obj, codfil, tipo);
    }

}
