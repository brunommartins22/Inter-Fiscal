package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.BaseInterage;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import br.com.interagese.interfiscal.entity.ImpTemp;
import br.com.interagese.interfiscal.entity.RestoreImp;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.persistence.fiscaltempDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.TypedQuery;

public class FiscalTempBusinessBean extends AbstractBusinessCrud<Fiscaltemp, fiscaltempDao> implements FiscalTempBusiness {

    public List<BaseInterage> getSearchAllBaseInterage(int position, List<BaseInterage> list) {
        if (list == null || list.isEmpty()) {
            list = new ArrayList<>();
        }
        for (Fiscaltemp ft : getSearchAll(position)) {
            BaseInterage bi = getFindCodList(ft.getCodigoProduto(), list);
            if (bi == null) {
                bi = new BaseInterage();
                bi.setCodBarras(ft.getEan());
                bi.setCodProduto(ft.getCodigoProduto());
                bi.setNmProduto(ft.getNomeProduto());
                list.add(bi);
            }
            bi.setAtualizacaoPisCofins(ft.getAtualizacaoPiscofins());
            bi.setAtualizacaoIcmsEntrada(ft.getAtualizacaoIcmsentrada());
            bi.setAtualizacaoIcmsSaida(ft.getAtualizacaoIcmssaida());
            bi.setPisCofins(ft.getPisCofins() == null ? false : ft.getPisCofins());
            bi.setIcmsEntrada(ft.getIcmsEntrada() == null ? false : ft.getIcmsEntrada());
            bi.setIcmsSaida(ft.getIcmsSaida() == null ? false : ft.getIcmsSaida());

        }
        return list;
    }

    public List<BaseInterage> getAllBaseInterage() {
        List<BaseInterage> result = new ArrayList<>();
        List<Fiscaltemp> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            Fiscaltemp ft = list.get(i);

            BaseInterage bi = getFindCodList(ft.getCodigoProduto(), result);
            if (bi == null) {
                bi = new BaseInterage();
                bi.setCodBarras(ft.getEan());
                bi.setCodProduto(ft.getCodigoProduto());
                bi.setNmProduto(ft.getNomeProduto());
                result.add(bi);
            }
            bi.setAtualizacaoPisCofins(ft.getAtualizacaoPiscofins());
            bi.setAtualizacaoIcmsEntrada(ft.getAtualizacaoIcmsentrada());
            bi.setAtualizacaoIcmsSaida(ft.getAtualizacaoIcmssaida());
            bi.setPisCofins(ft.getPisCofins() == null ? false : ft.getPisCofins());
            bi.setIcmsEntrada(ft.getIcmsEntrada() == null ? false : ft.getIcmsEntrada());
            bi.setIcmsSaida(ft.getIcmsSaida() == null ? false : ft.getIcmsSaida());
        }
        return result;
    }

    public BaseInterage getFindCodList(String cod, List<BaseInterage> result) {
        return result.stream().filter((BaseInterage t) -> {
            return Objects.equals(t.getCodProduto(), cod);
        }).findFirst().orElse(null);
    }

    public Fiscaltemp getExistItem(String codpro) {
        try {
            String sql = "SELECT * FROM fiscaltemp o WHERE o.codigo_produto = :codpro";

            TypedQuery<Fiscaltemp> result = (TypedQuery<Fiscaltemp>) getDao().getEntityManager().createNativeQuery(sql, Fiscaltemp.class).setParameter("codpro", codpro);

            if (result.getResultList().size() > 1) {

            }

            return result.getResultList().isEmpty() ? null : result.getResultList().get(0);
        } finally {
            getDao().getEntityManager().close();
        }
    }

    @Override
    public List<Fiscaltemp> getListagemRelatorio(String codbar, Integer tipo, Date dataIncial, Date dataFinal) {
        return getDao().getListagemRelatorio(codbar, tipo, dataIncial, dataFinal);
    }

    @Override
    public List<Fiscaltemp> getCarregarFiscalTemp(Date inicioOperacao) {
        return getDao().getCarregarFiscalTemp(inicioOperacao);
    }

    @Override
    public void getRestoreTributacaoMixFiscal(List<Fiscaltemp> resultRestore) {
        getDao().getRestoreTributacaoMixFiscal(resultRestore);
    }

    @Override
    public List<Tabpro> getListTabproByCod(Integer codPro) {
        return getDao().getListTabproByCod(codPro);
    }

    @Override
    public List<ImpTemp> getExistTabproimp(String codpro) {
        return getDao().getExistTabproimp(codpro);
    }

    @Override
    public Integer getCountExistTabproimpE(String codpro, Integer codfil) {
        return getDao().getCountExistTabproimpE(codpro, codfil);
    }

    @Override
    public List<ImpTemp> getExistTabproimpE(String codpro) {
        return getDao().getExistTabproimpE(codpro);
    }

    @Override
    public List<RestoreImp> getResultImpTemp(Integer codfil) {
        return getDao().getResultImpTemp(codfil);
    }

}
