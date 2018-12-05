package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.IcmsEntrada;
import java.util.ArrayList;
import java.util.List;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class icmsentradaDao extends AbstractDaoCrud<IcmsEntrada> {

    public void deleteAll() {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery("DELETE FROM mxf_tmp_icms_entrada").executeUpdate();
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
    public List<IcmsEntrada> getAll(String parameter) {
        try {
            List<IcmsEntrada> result = new ArrayList();
            int maxRowCount = 1000;
            int countMax = ((Number) count()).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }
            String fields = "o.codigo as codigo,"
                    + "o.codigo_produto as codigo_produto, "
                    + "o.ean as ean, "
                    + "o.tipo_mva as tipo_mva, "
                    + "o.mva as mva, "
                    + "o.mva_distribuidor as mva_distribuidor, "
                    + "o.mva_data_ini as mva_data_ini, "
                    + "o.mva_data_fim as mva_data_fim, "
                    + "o.credito_outorgado as credito_outorgado, "
                    + "o.ei_cst as ei_cst, "
                    + "o.ei_alq as ei_alq, "
                    + "o.ei_alqst as ei_alqst, "
                    + "o.ei_rbc as ei_rbc, "
                    + "o.ei_rbcst as ei_rbcst, "
                    + "o.ed_cst as ed_cst, "
                    + "o.ed_alq as ed_alq, "
                    + "o.ed_alqst as ed_alqst, "
                    + "o.ed_rbc as ed_rbc, "
                    + "o.ed_rbcst as ed_rbcst, "
                    + "o.es_cst as es_cst, "
                    + "o.es_alq as es_alq, "
                    + "o.es_alqst as es_alqst, "
                    + "o.es_rbc as es_rbc, "
                    + "o.es_rbcst as es_rbcst, "
                    + "o.nfi_cst as nfi_cst, "
                    + "o.nfd_cst as nfd_cst, "
                    + "o.nfs_csosn as nfs_csosn, "
                    + "o.nf_alq as nf_alq, "
                    + "o.fundamento_legal as fundamento_legal, "
                    + "p.descpro as descpro ";
            for (int i = 0; i < diference; i++) {
                String sql = "SELECT " + fields + " FROM mxf_tmp_icms_entrada o "
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
    public List<IcmsEntrada> getSearchAll(int position, String parameter) {
        try {
            String fields = "o.codigo as codigo,"
                    + "o.codigo_produto as codigo_produto, "
                    + "o.ean as ean, "
                    + "o.tipo_mva as tipo_mva, "
                    + "o.mva as mva, "
                    + "o.mva_distribuidor as mva_distribuidor, "
                    + "o.mva_data_ini as mva_data_ini, "
                    + "o.mva_data_fim as mva_data_fim, "
                    + "o.credito_outorgado as credito_outorgado, "
                    + "o.ei_cst as ei_cst, "
                    + "o.ei_alq as ei_alq, "
                    + "o.ei_alqst as ei_alqst, "
                    + "o.ei_rbc as ei_rbc, "
                    + "o.ei_rbcst as ei_rbcst, "
                    + "o.ed_cst as ed_cst, "
                    + "o.ed_alq as ed_alq, "
                    + "o.ed_alqst as ed_alqst, "
                    + "o.ed_rbc as ed_rbc, "
                    + "o.ed_rbcst as ed_rbcst, "
                    + "o.es_cst as es_cst, "
                    + "o.es_alq as es_alq, "
                    + "o.es_alqst as es_alqst, "
                    + "o.es_rbc as es_rbc, "
                    + "o.es_rbcst as es_rbcst, "
                    + "o.nfi_cst as nfi_cst, "
                    + "o.nfd_cst as nfd_cst, "
                    + "o.nfs_csosn as nfs_csosn, "
                    + "o.nf_alq as nf_alq, "
                    + "o.fundamento_legal as fundamento_legal, "
                    + "p.descpro as descpro ";

            String sql = "SELECT " + fields + " FROM mxf_tmp_icms_entrada o "
                    + " left join tabpro p on p.icodpro = o.codigo_produto "
                    + " order by o.codigo";

            return getEntityManager().createNativeQuery(sql, parameter).setFirstResult(position).setMaxResults(100).getResultList();
        } finally {
            getEntityManager().close();
        }
    }
}
