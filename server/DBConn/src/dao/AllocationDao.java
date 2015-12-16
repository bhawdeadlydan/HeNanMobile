package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by richard on 2015/12/7.
 */
public class AllocationDao extends BaseDao{
    public Object[] getLocationInfoByID(int id){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "select model.area, model.location from AllocationEntity as model where model.id = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, id);
            query.setMaxResults(1);
            tx.commit();
            if(!query.list().isEmpty())
                return (Object[])query.list().get(0);
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}
