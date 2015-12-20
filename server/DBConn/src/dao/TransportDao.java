package dao;

import db.TransportEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by richard on 2015/12/7.
 */
public class TransportDao extends BaseDao{
    public List<Object[]> getTransportInfo(){
        Session session = ourSessionFactory.openSession();
        try {
            String hql = "select model.TIME, model.POSITION, model.CHARGE, model1.RECEIVER, model.TYPE, model.POS_APPLY_DOC_CODE from transport as model left join pos as model1 " +
                    "on model.POS_APPLY_DOC_CODE = model1.APPLY_DOC_CODE order by model.TIME desc";
            SQLQuery query = session.createSQLQuery(hql);
            return query.list();
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

    public List<Object[]> getTimeInfo(String ConstructUnit){
        Session session = ourSessionFactory.openSession();
        try {
            String sql = "SELECT POS_APPLY_DOC_CODE,TIME FROM transport as model WHERE POS_APPLY_DOC_CODE in " +
                    "(select APPLY_DOC_CODE from pos where RECEIVER = ?) and TYPE = 0";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, ConstructUnit);
            return query.list();
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
