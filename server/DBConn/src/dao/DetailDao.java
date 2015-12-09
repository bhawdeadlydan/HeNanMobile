package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by richard on 2015/12/7.
 */
public class DetailDao extends BaseDao{
    public boolean isBom(String ApplyDocCode){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "select model.isBom from DetailEntity as model where model.posApplyDocCode = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, ApplyDocCode);
            tx.commit();
            String isbom = (String)queryObject.list().get(0);
            return isbom.toUpperCase() == "Y";
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
    public List<Object[]> getBomDistinctGoods(String ApplyDocCode){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "select model.saleBomCode, sum(model.expectedQuantity) from DetailEntity as model where posApplyDocCode = ? group by model.saleBomCode";
            Query query = session.createQuery(hql);
            query.setParameter(0, ApplyDocCode);
            tx.commit();
            return query.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getERPDistinctGoods(String ApplyDocCode){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "select model.itemErpCode, sum(model.expectedQuantity), model.itemName, model.itemErpUnit from DetailEntity as model where posApplyDocCode = ? group by model.itemErpCode";
            Query query = session.createQuery(hql);
            query.setParameter(0, ApplyDocCode);
            tx.commit();
            return query.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}
