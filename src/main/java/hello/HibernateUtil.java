package hello;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    @SuppressWarnings("deprecation")
    public static Session getHibernateSession() {
        return new Configuration().configure("criteria.cfg.xml").buildSessionFactory().openSession();
    }

}
