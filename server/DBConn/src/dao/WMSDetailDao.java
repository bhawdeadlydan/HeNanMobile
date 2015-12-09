package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by richard on 2015/12/7.
 */
public class WMSDetailDao extends BaseDao{
    public String getCodeByCNum(String CNum){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "select model.asnCode from WmsDetailEntity as model where model.cNum = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, CNum);
            tx.commit();
            return (String)queryObject.list().get(0);
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    public String getApplyDocCodeByCNum(String CNum){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "select model.applyDocCode from WmsDetailEntity as model where model.cNum = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, CNum);
            tx.commit();
            return (String)queryObject.list().get(0);
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    public void bind(int locationID, String CNum){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "update WmsDetailEntity as model set model.allocationId = ? where model.cNum = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, locationID);
            queryObject.setParameter(1, CNum);
            queryObject.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
