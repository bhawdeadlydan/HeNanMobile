package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by richard on 2016/4/11.
 */
public class PicDao extends BaseDao{
    public List getPics(String ApplyDocCode){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "select model.url from PictureEntity as model where model.applyDocCode = ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, ApplyDocCode);
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
