package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Tabprofil;
import java.util.List;
import javax.persistence.TypedQuery;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class TabprofilDao extends AbstractDaoCrud<Tabprofil> {

    @Override
    public Integer getMaxRowCount() {
        return 50;//To change body of generated methods, choose Tools | Templates.
    }

    public List<Tabprofil> getListTabprofilByCodProduto(String codPro) {
        try {
            TypedQuery<Tabprofil> query = (TypedQuery<Tabprofil>) getEntityManager().createNativeQuery("SELECT * FROM Tabprofil o WHERE o.codpro= :codpro", Tabprofil.class);
            query.setParameter("codpro", codPro);

            return query.getResultList();
        } finally {
            getEntityManager().close();
        }
    }

}
