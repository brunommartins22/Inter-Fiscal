package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Piscofins;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class piscofinsDao extends AbstractDaoCrud<Piscofins> {

    @Override
    public List<Piscofins> getAll(String parameter) {
        EntityManager em = null;
        try {
            List<Piscofins> result = new ArrayList();
            int maxRowCount = 1000;
            int countMax = ((Number) count()).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }
            em = getEntityManager();
            String fields = "o.codigo as codigo, "
                    + "o.codigo_produto as codigo_produto, "
                    + "o.ean as ean, "
                    + "o.ncm as ncm, "
                    + "o.ncm_ex as ncm_ex, "
                    + "o.cod_natureza_receita as cod_natureza_receita, "
                    + "o.credito_presumido as credito_presumido, "
                    + "o.pis_cst_e as pis_cst_e, "
                    + "o.pis_cst_s as pis_cst_s, "
                    + "o.pis_alq_e as pis_alq_e, "
                    + "o.pis_alq_s as pis_alq_s, "
                    + "o.cofins_cst_e as cofins_cst_e, "
                    + "o.cofins_cst_s as cofins_cst_s, "
                    + "o.cofins_alq_e as cofins_alq_e, "
                    + "o.cofins_alq_s as cofins_alq_s, "
                    + "o.fundamento_legal as fundamento_legal, "
                    + "p.descpro as descpro ";
            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + " FROM mxf_tmp_pis_cofins o "
                        + " left join tabpro p on p.icodpro = o.codigo_produto "
                        + " order by o.codigo";
                result.addAll(em.createNativeQuery(sql, parameter).setFirstResult(i * 1000).setMaxResults(1000).getResultList());

            }

            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Piscofins> getSearchAll(int position, String parameter) {
        try {
            String fields = "o.codigo as codigo, "
                    + "o.codigo_produto as codigo_produto, "
                    + "o.ean as ean, "
                    + "o.ncm as ncm, "
                    + "o.ncm_ex as ncm_ex, "
                    + "o.cod_natureza_receita as cod_natureza_receita, "
                    + "o.credito_presumido as credito_presumido, "
                    + "o.pis_cst_e as pis_cst_e, "
                    + "o.pis_cst_s as pis_cst_s, "
                    + "o.pis_alq_e as pis_alq_e, "
                    + "o.pis_alq_s as pis_alq_s, "
                    + "o.cofins_cst_e as cofins_cst_e, "
                    + "o.cofins_cst_s as cofins_cst_s, "
                    + "o.cofins_alq_e as cofins_alq_e, "
                    + "o.cofins_alq_s as cofins_alq_s, "
                    + "o.fundamento_legal as fundamento_legal, "
                    + "p.descpro as descpro ";

            String sql = "SELECT " + fields + " FROM mxf_tmp_pis_cofins o "
                    + " left join tabpro p on p.icodpro = o.codigo_produto "
                    + " order by o.codigo";

            return getEntityManager().createNativeQuery(sql, parameter).setFirstResult(position).setMaxResults(100).getResultList();
        } finally {
            getEntityManager().close();
        }
    }

    public void deleteAll() {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery("DELETE FROM mxf_tmp_pis_cofins").executeUpdate();
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
            throw ex;
        } finally {
            getEntityManager().close();
        }
    }

}
