package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.IcmsSaida;
import java.util.ArrayList;
import java.util.List;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class icmssaidaDao extends AbstractDaoCrud<IcmsSaida> {

    public void deleteAll() {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery("DELETE FROM mxf_tmp_icms_saida").executeUpdate();
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

    @Override
    public List<IcmsSaida> getAll(String parameter) {
        try {
            List<IcmsSaida> result = new ArrayList();
            int maxRowCount = 1000;
            int countMax = ((Number) count()).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }
            String fields = "o.codigo as codigo,"
                    + "o.codigo_produto as codigo_produto, "
                    + "o.ean as ean, "
                    + "o.re_29560 as re_29560, "
                    + "o.cest as cest, "
                    + "o.sac_cst as sac_cst, "
                    + "o.sac_alq as sac_alq, "
                    + "o.sac_alqst as sac_alqst, "
                    + "o.sac_rbc as sac_rbc, "
                    + "o.sac_rbcst as sac_rbcst, "
                    + "o.sas_cst as sas_cst, "
                    + "o.sas_alq as sas_alq, "
                    + "o.sas_alqst as sas_alqst, "
                    + "o.sas_rbc as sas_rbc, "
                    + "o.sas_rbcst as sas_rbcst, "
                    + "o.svc_cst as svc_cst, "
                    + "o.svc_alq as svc_alq, "
                    + "o.svc_alqst as svc_alqst, "
                    + "o.svc_rbc as svc_rbc, "
                    + "o.svc_rbcst as svc_rbcst, "
                    + "o.snc_cst as snc_cst, "
                    + "o.snc_alq as snc_alq, "
                    + "o.snc_alqst as snc_alqst, "
                    + "o.snc_rbc as snc_rbc, "
                    + "o.snc_rbcst as snc_rbcst, "
                    + "o.sss_csosn as sss_csosn, "
                    + "o.svc_csosn as svc_csosn, "
                    + "o.snc_csosn as snc_csosn, "
                    + "o.fecp as fecp, "
                    + "o.fundamento_legal as fundamento_legal, "
                    + "p.descpro as descpro ";
            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + " FROM mxf_tmp_icms_saida o "
                        + " left join tabpro p on p.icodpro = o.codigo_produto "
                        + " order by o.codigo";
                result.addAll(getEntityManager().createNativeQuery(sql, parameter).setFirstResult(i * 1000).setMaxResults(1000).getResultList());

            }
            return result;
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public List<IcmsSaida> getSearchAll(int position, String parameter) {
        try {
            String fields = "o.codigo as codigo,"
                    + "o.codigo_produto as codigo_produto, "
                    + "o.ean as ean, "
                    + "o.re_29560 as re_29560, "
                    + "o.cest as cest, "
                    + "o.sac_cst as sac_cst, "
                    + "o.sac_alq as sac_alq, "
                    + "o.sac_alqst as sac_alqst, "
                    + "o.sac_rbc as sac_rbc, "
                    + "o.sac_rbcst as sac_rbcst, "
                    + "o.sas_cst as sas_cst, "
                    + "o.sas_alq as sas_alq, "
                    + "o.sas_alqst as sas_alqst, "
                    + "o.sas_rbc as sas_rbc, "
                    + "o.sas_rbcst as sas_rbcst, "
                    + "o.svc_cst as svc_cst, "
                    + "o.svc_alq as svc_alq, "
                    + "o.svc_alqst as svc_alqst, "
                    + "o.svc_rbc as svc_rbc, "
                    + "o.svc_rbcst as svc_rbcst, "
                    + "o.snc_cst as snc_cst, "
                    + "o.snc_alq as snc_alq, "
                    + "o.snc_alqst as snc_alqst, "
                    + "o.snc_rbc as snc_rbc, "
                    + "o.snc_rbcst as snc_rbcst, "
                    + "o.sss_csosn as sss_csosn, "
                    + "o.svc_csosn as svc_csosn, "
                    + "o.snc_csosn as snc_csosn, "
                    + "o.fecp as fecp, "
                    + "o.fundamento_legal as fundamento_legal, "
                    + "p.descpro as descpro ";

            String sql = "SELECT " + fields + " FROM mxf_tmp_icms_saida o "
                    + " left join tabpro p on p.icodpro = o.codigo_produto "
                    + " order by o.codigo";

            return getEntityManager().createNativeQuery(sql, parameter).setFirstResult(position).setMaxResults(100).getResultList();

        } finally {
            getEntityManager().close();
        }
    }

}
