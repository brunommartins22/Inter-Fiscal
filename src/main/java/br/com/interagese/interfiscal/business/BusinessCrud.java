/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Model;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author bruno
 * @param <T>
 */
public interface BusinessCrud<T extends Model> {

    public void insert(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T findById(Object id);

    public List<T> getAll();

    public void insertList(List<T> list);

    public List<T> getSearchAll(int position);

    public List<T> getAll(String parameter);

    public List<T> getSearchAll(int position, String parameter);

    public void updateList(List<T> entity);

    public Object count();

    public void executeUpdate(List<String> ListSql);
}
