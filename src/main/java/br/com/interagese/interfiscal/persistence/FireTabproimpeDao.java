package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Tabproimpe;
import javax.persistence.TypedQuery;

@DataBase(getType = DataBase.dataBaseType.FIREBIRD)
public class FireTabproimpeDao extends AbstractDaoCrud<Tabproimpe> {

    @Override
    public void insert(Tabproimpe entity) {
        try {
            Tabproimpe impe = findById(entity.getId());
            if (impe == null) {
                super.insert(entity);
            } else {
                super.update(entity);
            }
        } finally {
            getEntityManager().close();
        }
    }

    public Tabproimpe getTabproimpeByCodPro(Integer codProduto, Integer codFilial) {
        try {
            TypedQuery<Tabproimpe> query = (TypedQuery<Tabproimpe>) getEntityManager().createNativeQuery("SELECT * FROM Tabproimpe o WHERE o.codigoProduto = :codpro  and o.codigoFilial = :codfil ", Tabproimpe.class);
            query.setParameter("codpro", codProduto);
            query.setParameter("codfil", codFilial);

            return query.getSingleResult();
        } finally {
            getEntityManager().close();
        }
    }

}
