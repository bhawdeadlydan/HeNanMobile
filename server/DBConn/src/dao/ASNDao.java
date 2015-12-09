package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by richard on 2015/12/7.
 */
public class ASNDao extends BaseDao{
    public int getCartonNumByCode(String Code){
        int num = 0;
        return num;
    }

    public String getDescribeByCode(String Code){
        String str = "";
        return str;
    }

    public String getUnitByCode(String Code){
        String str = "";
        return str;
    }

    public void Receiving(String Code){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = "update AsnEntity as model set model.paid = true where model.code = ?";
            Query query = session.createQuery(queryString);
            query.setParameter(0, Code);
            query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
