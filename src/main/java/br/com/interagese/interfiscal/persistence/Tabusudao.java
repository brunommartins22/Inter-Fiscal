/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Tabusu;
import javax.persistence.TypedQuery;

/**
 *
 * @author bruno
 */
@DataBase(getType = DataBase.dataBaseType.FIREBIRD)
public class Tabusudao extends AbstractDaoCrud<Tabusu> {

    public boolean getValidationUser(String password) {
        String sql = "SELECT * FROM TABUSU u WHERE u.nome='INTER' and u.senha='" + password + "'";
        System.out.println("sql"+sql);
        TypedQuery<Tabusu> result = (TypedQuery<Tabusu>) getEntityManager().createNativeQuery(sql, Tabusu.class);

        return result != null && !result.getResultList().isEmpty(); // if condition is different 'return false' else 'return true';

    }

}
