package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.entity.Model;
import java.util.List;

public interface DaoCrud<T extends Model> {

    public void insert(T entity);

    public void update(T entity);

    public void delete(T entity);

    public List<T> getAll();

    public void insertList(List<T> list);

    public T findById(Object id);

    public Object count();

    public List<T> getSearchAll(int position);

    public List<T> getAll(String parameter);
    
    public List<T> getSearchAll(int position,String parameter);
    
    public void updateList(List<T> entity);

}
