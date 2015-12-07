package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by richard on 2015/12/7.
 */
public class BaseDao {
    protected static final SessionFactory ourSessionFactory;
    protected static final ServiceRegistry serviceRegistry;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public Object addEntity(Object obj){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        Object ID = null;
        try{
            tx = session.beginTransaction();
            ID = session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return ID;
    }

    public List findAll(String entity) {
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        List result = null;
        try{
            tx = session.beginTransaction();
            result = session.createQuery("FROM " + entity).list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }
}
