package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by richard on 2015/12/20.
 */
public class StagingSiteDao extends BaseDao{
    public List getStagingSiteInfo(String constructUnit){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "from StagingsiteEntity as model where model.constructUnit = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, constructUnit);
            tx.commit();
            return queryObject.list();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
