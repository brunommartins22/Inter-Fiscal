package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Fiscaltemp;
import java.util.Collections;
import java.util.List;

@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class fiscaltempDao extends AbstractDaoCrud<Fiscaltemp> {

    @Override
    public List<Fiscaltemp> getAll() {
        try {
            List<Fiscaltemp> result = super.getAll();

            Collections.sort(result, (o1, o2) -> {
                return o1.getCodigoProduto().compareTo(o2.getCodigoProduto()); //To change body of generated lambdas, choose Tools | Templates.
            });

            return result;
        } finally {
            getEntityManager().close();
        }
    }

//    @Override
//    public List<Fiscaltemp> getAll() {
//       List<Fiscaltemp> result =  super.getAll(); //To change body of generated methods, choose Tools | Templates.
//        for (Fiscaltemp2 item : result) {
//            TypedQuery<String> nmProduto = getEntityManager().createQuery("SELECT o.descpro FROM Tabpro o where o.codpro = '" + item.getCodigoProduto() + "'", String.class);
//            if(nmProduto.getResultList().isEmpty()){
//                item.setNmProduto(nmProduto.getSingleResult());
//            }
//        }
//       return result;
//    }
}
