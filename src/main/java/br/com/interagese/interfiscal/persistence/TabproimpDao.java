package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Tabproimp;
import javax.persistence.TypedQuery;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class TabproimpDao extends AbstractDaoCrud<Tabproimp> {

    @Override
    public Integer getMaxRowCount() {
        return 50;
    }

//    @Override
//    public void insert(Tabproimp entity) {
//        Tabproimp imp = findById(entity.getTabproimpPK());
//        if (imp != null) {// se este registro está diferente de null ele realiza o update
//            super.update(entity);
//        } else { // caso contrario insere no banco de dados 
//            super.insert(entity);
//        }
//    }
    public Tabproimp getTabproimpByCodPro(String codPro, String codFil) {
        try {
            TypedQuery<Tabproimp> query = (TypedQuery<Tabproimp>) getEntityManager().createNativeQuery("SELECT * FROM Tabproimp o WHERE o.codpro = :codpro and o.codfil = :codfil", Tabproimp.class);
            query.setParameter("codpro", codPro);
            query.setParameter("codfil", codFil);

            return query.getSingleResult();
        } finally {
            getEntityManager().close();
        }
    }

}
