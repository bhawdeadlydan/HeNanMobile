package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by richard on 2015/12/7.
 */
public class ASNDao extends BaseDao{

    public Object[] getDesAndUnitBySaleBomCode(String SaleBomCode){
        String str[];
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "select model.saleBomName, model.saleBomUnit from SaleBomEntity as model where model.saleBomCode = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, SaleBomCode);
            tx.commit();
            if(!queryObject.list().isEmpty())
                return (Object[]) queryObject.list().get(0);
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

    public void Receiving(String Code){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "update AsnEntity as model set model.paid = 1 where model.code = ?";
            Query query = session.createQuery(queryString);
            query.setParameter(0, Code);
            query.executeUpdate();
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

    public List findUnPaid() {
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "from AsnEntity as model where model.paid = 0";
            Query queryObject = session.createQuery(queryString);
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
