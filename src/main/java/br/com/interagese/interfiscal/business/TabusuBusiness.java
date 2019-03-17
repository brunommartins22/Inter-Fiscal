package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Tabusu;

public interface TabusuBusiness extends BusinessCrud<Tabusu> {

    public boolean getValidationUser(String password);
}
