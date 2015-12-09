package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by richard on 2015/12/7.
 */
public class PosDao extends BaseDao{
    public void Retrieval(String ApplyDocCode){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "update PosEntity as model set model.sent = 1 where model.applyDocCode = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, ApplyDocCode);
            queryObject.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
