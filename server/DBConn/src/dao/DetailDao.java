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
        try{
            String queryString = "select model.isBom from DetailEntity as model where model.posApplyDocCode = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, ApplyDocCode);
            if(!queryObject.list().isEmpty())
                return ((String)queryObject.list().get(0)).toUpperCase().equals("Y");
        }catch (HibernateException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
        }catch(Exception e){
//            e.printStackTrace();
            System.err.println(e.getMessage());
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
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
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
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
