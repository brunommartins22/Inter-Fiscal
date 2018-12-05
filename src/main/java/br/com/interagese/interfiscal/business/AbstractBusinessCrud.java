package br.com.interagese.interfiscal.business;

import br.com.interagese.interfiscal.entity.Model;
import br.com.interagese.interfiscal.persistence.DaoCrud;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.List;

abstract class AbstractBusinessCrud<T extends Model, S extends DaoCrud<T>> implements BusinessCrud<T> {

    private S dao;

    private Class<S> daoClass;

    private Class<T> beanClass;

//    private List<Observer> observers = new ArrayList<>();
    public AbstractBusinessCrud() {

        try {
            beanClass = (Class<T>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
            daoClass = (Class<S>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[1];
        } catch (Exception e) {
            beanClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            daoClass = (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }

        try {

            Constructor constructor = daoClass.getConstructor(null);

            dao = (S) constructor.newInstance(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insert(T entity) {
        dao.insert(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    public S getDao() {
        return dao;
    }

    @Override
    public List<T> getSearchAll(int position) {
        return dao.getSearchAll(position);
    }

//    /*@Override
//    public List<T> getAllWhereDtEnvServIsNull() {
//        return dao.getAllWhereDtEnvServIsNull();
//    }*/
//    @Override
//    public Long getRecordCountWhereEnvServIsNull() {
//        return dao.getRecordCountWhereEnvServIsNull();
//    }
//
//    @Override
//    public void registerObserver(Observer observer) {
//        observers.add(observer);
//    }
//
//    @Override
//    public void removeObserver(Observer observer) {
//        observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers(int value, String label) {
//        for (Observer observer : observers) {
//            observer.updateProgressBarValue(value);
//            observer.updateProgressBarLabel(label);
//        }
//    }
//
//    @Override
//    public void notifyObservers(int maxValue) {
//        for (Observer observer : observers) {
//            observer.updateProgressBarMaxValue(maxValue);
//        }
//    }
    @Override
    public void insertList(List<T> list) {

        dao.insertList(list);

    }

    @Override
    public void updateList(List<T> entity) {
    dao.updateList(entity);
    }
    
    

    @Override
    public T findById(Object id) {
        return dao.findById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public List<T> getAll(String parameter) {
        return dao.getAll(parameter);
    }

    @Override
    public List<T> getSearchAll(int position, String parameter) {
        return dao.getSearchAll(position, parameter);
    }

    @Override
    public Object count() {
    return dao.count();
    }
    
    
    

}
