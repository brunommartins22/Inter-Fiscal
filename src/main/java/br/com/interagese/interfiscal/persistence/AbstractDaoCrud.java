package br.com.interagese.interfiscal.persistence;

import br.com.interagese.interfiscal.annotation.DataBase;
import br.com.interagese.interfiscal.entity.Model;
import br.com.interagese.interfiscal.utils.JPAUtil;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Table;

abstract class AbstractDaoCrud<T extends Model> implements DaoCrud<T> {

    private Integer maxRowCount = 1000;
    public String tpDB;
    private EntityManager em;
    private DataBase.dataBaseType dataBaseType;

    private Class<T> entityClass;

    public AbstractDaoCrud() {

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();

        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

        DataBase data = getClass().getDeclaredAnnotation(DataBase.class);
        dataBaseType = data != null ? data.getType() : DataBase.dataBaseType.FIREBIRD;

    }

    public void insert(T entity) {

        em = getEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    /**
     * Método para executar um query de update
     */
    @Override
    public void executeUpdate(List<String> sqls) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            for (String sql : sqls) {
                em.createNativeQuery(sql).executeUpdate();
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    @Override
    public T findById(Object id) {
        T t = null;
        em = getEntityManager();
        try {

            t = em.find(entityClass, id);

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

        return t;

    }

    public void update(T entity) {

        em = getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(entity);

            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(entity.getId());
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void delete(T entity) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void insertList(List<T> entity) {
        if (!entity.isEmpty()) {
            em = getEntityManager();
            try {

                em.getTransaction().begin();

                for (T e : entity) {
                    em.persist(e);
                }

                em.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } finally {
                if (em.isOpen()) {
                    em.close();
                }
            }
        }
    }

    public void updateList(List<T> entity) {
        if (!entity.isEmpty()) {
            em = getEntityManager();

            try {

                em.getTransaction().begin();

                for (T e : entity) {
                    em.merge(e);
                }

                em.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } finally {
                if (em.isOpen()) {
                    em.close();
                }
            }
        }

    }

    @Override
    public Object count() {

        em = getEntityManager();
        try {
            return em.createNativeQuery("SELECT COUNT(*) FROM " + entityClass.getAnnotation(Table.class).name() + " ").getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> getAll() {

        try {
            List<T> result = new ArrayList<>();
            int countMax = ((Number) count()).intValue();
            int diference = countMax / maxRowCount;
            EntityManager em = getEntityManager();

            if (countMax % maxRowCount != 0) {
                diference++;
            }
            for (int i = 0; i < diference; i++) {
                result.addAll(em.createNativeQuery("SELECT * FROM " + entityClass.getAnnotation(Table.class).name(), entityClass).setFirstResult(i * 1000).setMaxResults(1000).getResultList());
            }
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> getAll(String parameter) {

        return null;
    }

    @Override
    public List<T> getSearchAll(int position) {
        EntityManager em = getEntityManager();
        try {
            return em.createNativeQuery("SELECT * FROM " + entityClass.getAnnotation(Table.class).name() + " o order by o.codigo_produto", entityClass).setFirstResult(position).setMaxResults(100).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> getSearchAll(int position, String parameter) {
        return null;
    }

    public EntityManager getEntityManager() {
        if (em == null || em.isOpen() == false) {
            if (dataBaseType == DataBase.dataBaseType.FIREBIRD) {
                em = JPAUtil.createEntityManagerIntegrado();
            } else {
                em = JPAUtil.createEntityManagerIntegradoPostgres();
            }
        }
        return em;
    }

    public void closeEntityManager() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public Integer getMaxRowCount() {
        return maxRowCount;
    }

}
