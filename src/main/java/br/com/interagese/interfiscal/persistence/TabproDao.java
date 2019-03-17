package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Tabpro;
import br.com.interagese.interfiscal.utils.Actions;
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
}
