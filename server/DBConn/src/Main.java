import dao.BaseDao;
import dao.LogDao;
import db.LogEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * Created by richard on 2015/12/6.
 */
public class Main extends BaseDao {

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void list(){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM CheckEntity ").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                CheckEntity log = (CheckEntity) iterator.next();
                System.out.println(log.getId());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void main(final String[] args) throws Exception {
        LogDao ldao = new LogDao();
        LogEntity l = new LogEntity();
        l.setNote("123");
        l.setOperation("asd");
        l.setTime(new Timestamp(System.currentTimeMillis()));
        ldao.addEntity(l);
        List list = ldao.findAll("LogEntity");
        for(Iterator it = list.iterator(); it.hasNext();) {
            LogEntity log = (LogEntity) it.next();
            System.out.println(log.getId());
        }
    }
}
