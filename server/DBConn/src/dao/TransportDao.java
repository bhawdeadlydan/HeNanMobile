package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by richard on 2015/12/7.
 */
public class TransportDao extends BaseDao{
    public List<Object[]> getTransportInfo(String ApplyDocCode){
        Session session = ourSessionFactory.openSession();
        try {
            String hql = "select model.time, model.position, model.charge, model1.constructionUnit from TransportEntity as model left join PosEntity as model1 " +
                    "on (model.posApplyDocCode = model1.applyDocCode) where model.posApplyDocCode = ? order by model.time desc";
            Query query = session.createQuery(hql);
            query.setParameter(0, ApplyDocCode);
            return (List<Object[]>) query.list();
        }catch (HibernateException e){
//            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }catch(Exception e){
//            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }finally {
            session.close();
        }
    }
}
