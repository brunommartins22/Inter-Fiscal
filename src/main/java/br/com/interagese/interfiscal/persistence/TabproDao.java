package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.ImportacaoImp;
import br.com.interagese.interfiscal.entity.SincronizadorTable;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.utils.Actions;
import br.com.interagese.interfiscal.utils.TransformNativeQuery;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class TabproDao extends AbstractDaoCrud<Tabpro> {

    private Actions a = new Actions(null);

    @Override
    public Integer getMaxRowCount() {
        return 50;
    }

    public Tabpro getProdutoByCod(Integer icodpro) {
        try {
            TypedQuery<Tabpro> result = (TypedQuery<Tabpro>) getEntityManager().createNativeQuery("Select * from tabpro tp where tp.icodpro =" + icodpro, Tabpro.class);
            return result.getResultList().isEmpty() ? null : result.getResultList().get(0);
        } finally {
            getEntityManager().close();
        }
    }

    public List<Tabpro> getProdutobyDescorCod(Object o) {
        try {
            String query = "SELECT * FROM tabpro tp WHERE";
            boolean resp = a.possuiLetra(o.toString());
            if (!resp) {
                query += " tp.icodpro =" + Integer.parseInt(o.toString());
            } else {
                query += " tp.codbarun ='" + o.toString() + "' or tp.descpro like '%" + o.toString().toUpperCase() + "%'";
            }
//        System.out.println("SQL : "+query);
            TypedQuery<Tabpro> result = (TypedQuery<Tabpro>) getEntityManager().createNativeQuery(query, Tabpro.class);

            return result.getResultList();
        } finally {
            getEntityManager().close();
        }
    }

    public void getGerarEanbyCodigo() {
        try {
            String sql = "insert into tabprocod(codpro, codigo, unid, qtdun, vldesconto)"
                    + " select codpro, codbarun as codigo, unidade as unid, 1 as qtdun, 0 as vldesconto from tabpro"
                    + " where codbarun is not null and codbarun <> '' and codbarun not in(select distinct(codigo) from tabprocod)";
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery(sql).executeUpdate();
            getEntityManager().getTransaction().commit();
        } finally {
            getEntityManager().close();
        }
    }

    public Object countImportacao() {
        try {
            String sql = "select count(*)"
                    + " from tabpro tp join tabprofil tpf on tpf.codpro = tp.codpro ";

            return getEntityManager().createNativeQuery(sql).getSingleResult();
        } finally {
            getEntityManager().close();
        }
    }

    public List<SincronizadorTable> getCarregarALL() throws InstantiationException, IllegalAccessException {
        try {

            int maxRowCount = 1000;
            int countMax = ((Number) countImportacao()).intValue();
            int diference = countMax / maxRowCount;

            if (countMax % maxRowCount != 0) {
                diference++;
            }

            List<SincronizadorTable> result = new ArrayList<>();

            for (int i = 0; i < diference; i++) {

                String sql = "select tp.stprod,tp.codbarun as codbarun,tpf.codfil as codfil,tp.descpro as descpro,tpf.pratpro as pratpro, tpf.prvapro as prvapro"
                        + " from tabpro tp join tabprofil tpf on  tpf.codpro = tp.codpro ";

                List<Object[]> query = getEntityManager().createNativeQuery(sql).setFirstResult(i * 1000).setMaxResults(1000).getResultList();

                if (!query.isEmpty()) {
                    result.addAll(new TransformNativeQuery(SincronizadorTable.class).execute(query));
                }

            }

            return result;
        } finally {
            getEntityManager().close();
        }

    }

    public void updateAll(String codbarun, Double prvapro, Double pratpro) {
        try {

            String sql = "update tabprofil "
                    + "set prvapro = '" + prvapro + "',"
                    + "pratpro = '" + pratpro + "',"
                    + "rgdata = '" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "',"
                    + "rgevento = '2' "
                    + "where codpro in (select codpro from tabpro where codbarun ='" + codbarun + "') and codfil = '2'";
            getEntityManager().getTransaction().begin();
            getEntityManager().createNativeQuery(sql).executeUpdate();
            getEntityManager().getTransaction().commit();

        } finally {
            getEntityManager().close();
        }
    }

}
