/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Tabfil;
import java.util.List;

/**
 *
 * @author bruno
 */
@DataBase(getType = DataBase.dataBaseType.POSTGRES)
public class Tabfildao extends AbstractDaoCrud<Tabfil> {

    public List<Tabfil> carregarFilialOnMixFiscal() {
        try {
            return getEntityManager().createNativeQuery("Select * from tabfil where mixfiscal ='S'", Tabfil.class).getResultList();
        } finally {
            getEntityManager().close();
        }
    }

}
